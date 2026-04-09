package ma.vivalis.BKAM_CDR_API1.garantie.batch.tasklet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_inter_garantie;
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
import java.time.format.DateTimeFormatter;

@Component
public class XmlHeaderTaskletGar implements Tasklet {

    private static final Logger log = LoggerFactory.getLogger(XmlHeaderTaskletGar.class);

    @PersistenceContext
    private EntityManager em;

    @Value("${batch.output.dir:output/}")
    private String outputDir;

    @Value("${batch.output.garantie.file:garantie_cdr.xml}")
    private String fileName;

    @Override
    public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        File dir = new File(outputDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        sss_cdr_inter_garantie gar = em.createQuery(
                "SELECT c FROM sss_cdr_inter_garantie c WHERE c.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_inter_garantie c2) ORDER BY c.idGar",
                sss_cdr_inter_garantie.class
        ).setMaxResults(1).getResultStream().findFirst().orElse(null);

        String filePath = outputDir + fileName;

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filePath), StandardCharsets.UTF_8)) {

            String entObserv ="415";
            String entDeclar ="415" ;
            String idDest = "001";
            String dtCreation = "";
            if (gar != null && gar.getDateExtraction() != null) {
                //dtCreation = new SimpleDateFormat("yyyy-MM-dd").format(gar.getDateExtraction());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                dtCreation = gar.getDateExtraction().format(formatter);


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
