package ma.vivalis.BKAM_CDR_API1.client.batch.reader;

import ma.vivalis.BKAM_CDR_API1.API.model.MyRequestBody;
import ma.vivalis.BKAM_CDR_API1.API.reader.MyRequestBodyReader;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReaderApiClient {
    @Value("${batch.output.dir:output/}")
    private String outputDir;

    @Value("${batch.output.client.file:clients_cdr.xml}")
    private String fileName;

    @Bean
    public ItemReader<MyRequestBody> readerClient() {
        String filePath = outputDir + fileName;
        return new MyRequestBodyReader(filePath);
    }
}
