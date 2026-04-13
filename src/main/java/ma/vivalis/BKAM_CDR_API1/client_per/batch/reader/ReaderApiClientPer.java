package ma.vivalis.BKAM_CDR_API1.client_per.batch.reader;

import ma.vivalis.BKAM_CDR_API1.API.model.MyRequestBody;
import ma.vivalis.BKAM_CDR_API1.API.reader.MyRequestBodyReader;
import ma.vivalis.BKAM_CDR_API1.common.FileNameService;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReaderApiClientPer {
    private final FileNameService fileNameService;
    @Value("${batch.output.dir:output/}")
    private String outputDir;

    //@Value("${batch.output.clientPer.file:clients_per_cdr.xml}")
    private String fileName;

    public ReaderApiClientPer(FileNameService fileNameService) {
        this.fileNameService = fileNameService;
    }

    @Bean
    public ItemReader<MyRequestBody> readerClientPer() {
        fileName=fileNameService.retournerFileNames("CEMA");
        String filePath = outputDir + fileName;
        return new MyRequestBodyReader(filePath);
    }
}
