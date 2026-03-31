package ma.vivalis.BKAM_CDR_API1.API.reader;

import ma.vivalis.BKAM_CDR_API1.API.model.MyRequestBody;
import ma.vivalis.BKAM_CDR_API1.common.XmlToZipBase64;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class MyRequestBodyReader implements ItemReader<MyRequestBody> {
    private String fileName;


    public MyRequestBodyReader(@Value("#{jobParameters['fileName']}") String fileName) {
        this.fileName = fileName;

    }
    @Override
    public @Nullable MyRequestBody read() throws Exception {
        String fichier = XmlToZipBase64.convertXmlToZippedBase64(fileName);
        String version="1.0";
        return new MyRequestBody(version, fichier);
    }
}
