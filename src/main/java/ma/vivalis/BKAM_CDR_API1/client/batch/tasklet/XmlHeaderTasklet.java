package ma.vivalis.BKAM_CDR_API1.client.batch.tasklet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_inter_client_stat;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

@Component
public class XmlHeaderTasklet implements Tasklet {

    private static final Logger log = LoggerFactory.getLogger(XmlHeaderTasklet.class);

    @PersistenceContext
    private EntityManager em;

    @Value("${batch.output.dir:output/}")
    private String outputDir;

    @Value("${batch.output.client.file:clients_cdr.xml}")
    private String fileName;

    @Override
    public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
// ✅ 3 lignes ajoutées avant l'écriture du fichier
        File dir = new File(outputDir);
        if (!dir.exists()) {
            dir.mkdirs();    // Crée output/ et tous les sous-dossiers nécessaires
        }
        // ── Récupérer le premier client pour les infos du header ──
        sss_cdr_inter_client_stat client = em.createQuery(
                "SELECT c FROM sss_cdr_inter_client_stat c WHERE c.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_inter_client_stat c2) ORDER BY c.id_client",
                sss_cdr_inter_client_stat.class
        ).setMaxResults(1).getResultStream().findFirst().orElse(null);

        String filePath = outputDir + fileName;

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filePath), StandardCharsets.UTF_8)) {

            String entObserv = escapeXml(client != null ? client.getEntObserv() : "");
            String entDeclar = escapeXml(client != null ? client.getEntDeclar() : "");
            String dtCreation = "";
            if (client != null && client.getDateExtraction() != null) {
                dtCreation = new SimpleDateFormat("yyyy-MM-dd").format(client.getDateExtraction());


            }

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<RCC version=\"1.0\">\n");
            writer.write("  <controle entObserv=\"" + entObserv + "\" "
                    + "entDeclar=\"" + entDeclar + "\" "
                    + "idDest=\"001\" "
                    + "dtCreation=\"" + dtCreation + "\"/>\n");
            writer.write("  <contenu>\n");
            writer.write("    <ComEnt>\n");

            writer.flush();
        }

        log.info("✅ Header XML écrit dans {}", filePath);

        // Stocker le chemin dans le contexte pour les steps suivants
        chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .putString("xmlFilePath", filePath);

        return RepeatStatus.FINISHED;
    }


    private String escapeXml(String value) {
        if (value == null) return "";
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
