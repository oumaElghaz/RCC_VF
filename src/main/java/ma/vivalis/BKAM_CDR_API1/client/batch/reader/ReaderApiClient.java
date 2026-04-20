package ma.vivalis.BKAM_CDR_API1.client.batch.reader;

import ma.vivalis.BKAM_CDR_API1.API.model.MyRequestBody;
import ma.vivalis.BKAM_CDR_API1.API.reader.MyRequestBodyReader;
import ma.vivalis.BKAM_CDR_API1.common.FileNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ReaderApiClient {
    private static final Logger log = LoggerFactory.getLogger(ReaderApiClient.class);

    private final FileNameService fileNameService;
    @Value("${batch.output.dir:output/}")
    private String outputDir;

    //@Value("${batch.output.client.file:clients_cdr.xml}")
    private String fileName;

    public ReaderApiClient(FileNameService fileNameService) {
        this.fileNameService = fileNameService;
    }

    @Bean
    public ItemReader<MyRequestBody> readerClient() {
        fileName=fileNameService.retournerFileNames("CENT");
        log.info("retournerFileNames api  : {}",fileName);
        String filePath = outputDir + fileName;
        return new MyRequestBodyReader(filePath);
    }
}
