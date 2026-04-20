package ma.vivalis.BKAM_CDR_API1.contrat_per.batch.tasklet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.common.FileNameService;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_inter_contrat_per;
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
import java.time.format.DateTimeFormatter;

@Component
public class XmlHeaderTaskletContratPer implements Tasklet {
    private static final Logger log = LoggerFactory.getLogger(XmlHeaderTaskletContratPer.class);
    private final FileNameService fileNameService;
    @PersistenceContext
    private EntityManager em;
    @Value("${batch.output.dir:output/}")
    private String outputDir;

   // @Value("${batch.output.contratPer.file:contrats_per_cdr.xml}")
    private String fileName;

    public XmlHeaderTaskletContratPer(FileNameService fileNameService) {
        this.fileNameService = fileNameService;
    }

    @Override
    public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        File dir = new File(outputDir);
        if (!dir.exists()) {
            dir.mkdirs();    // Crée output/ et tous les sous-dossiers nécessaires
        }
        // ── Récupérer le premier contrat pour les infos du header ──
        sss_cdr_inter_contrat_per contrat = em.createQuery(
                "SELECT c FROM sss_cdr_inter_contrat_per c WHERE c.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_inter_contrat_per c2) ORDER BY c.idCont",
                sss_cdr_inter_contrat_per.class
        ).setMaxResults(1).getResultStream().findFirst().orElse(null);
        fileName=fileNameService.retournerFileNames("CCMP");
        String filePath = outputDir + fileName;

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filePath), StandardCharsets.UTF_8)) {

            String entObserv = "415";
            String entDeclar = "415";
            String idDest = "001";
            String dtCreation = "";
            if (contrat != null && contrat.getDateExtraction() != null) {
                //dtCreation = new SimpleDateFormat("yyyy-MM-dd").format(contrat.getDateExtraction());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                dtCreation = contrat.getDateExtraction().format(formatter);


            }

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<RCC version=\"1.0\">\n");
            writer.write("  <controle entObserv=\"" + entObserv + "\" "
                    + "entDeclar=\"" + entDeclar + "\" "
                    + "idDest=\"" + idDest + "\" "
                    + "dtCreation=\"" + dtCreation + "\"/>\n");
            writer.write("  <contenu>\n");
            //writer.write("    <comEntPer>\n");

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
}
