package ma.vivalis.BKAM_CDR_API1.infoNeg.batch.reader;

import ma.vivalis.BKAM_CDR_API1.API.model.MyRequestBody;
import ma.vivalis.BKAM_CDR_API1.API.reader.MyRequestBodyReader;
import ma.vivalis.BKAM_CDR_API1.common.FileNameService;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReaderApiInfoNega {
    private final FileNameService fileNameService;
    @Value("${batch.output.dir:output/}")
    private String outputDir;
    //@Value("${batch.output.info.file:infoNegatives_cdr.xml}")
    private String fileName;

    public ReaderApiInfoNega(FileNameService fileNameService) {
        this.fileNameService = fileNameService;
    }

    @Bean
    public ItemReader<MyRequestBody> readerInfoNega() {
        fileName=fileNameService.retournerFileNames("CNEG");
        String filePath = outputDir + fileName;
        return new MyRequestBodyReader(filePath);
    }
}
