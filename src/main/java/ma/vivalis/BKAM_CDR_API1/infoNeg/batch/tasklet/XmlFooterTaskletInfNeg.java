package ma.vivalis.BKAM_CDR_API1.infoNeg.batch.tasklet;

import ma.vivalis.BKAM_CDR_API1.common.FileNameService;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Component
public class XmlFooterTaskletInfNeg implements Tasklet {

    private static final Logger log = LoggerFactory.getLogger(ma.vivalis.BKAM_CDR_API1.client.batch.tasklet.XmlFooterTasklet.class);
    private final FileNameService fileNameService;

    @Value("${batch.output.dir:output/}")
    private String outputDir;

    //@Value("${batch.output.info.file:infoNegatives_cdr.xml}")
    private String fileName;

    public XmlFooterTaskletInfNeg(FileNameService fileNameService) {
        this.fileNameService = fileNameService;
    }


    @Override
    public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        fileName=fileNameService.retournerFileNames("CNEG");
        String filePath = outputDir + fileName;

        // APPEND mode
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filePath, true), StandardCharsets.UTF_8)) {


            writer.write("  </contenu>\n");
            writer.write("</RCC>\n");

            writer.flush();
        }

        log.info("✅ Footer XML écrit — fichier {} terminé", filePath);

        return RepeatStatus.FINISHED;
    }
}
