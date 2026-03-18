package ma.vivalis.BKAM_CDR_API1.infoNeg.batch;

import jakarta.annotation.PostConstruct;
import ma.vivalis.BKAM_CDR_API1.infoNeg.batch.processor.InfoNegaCompareProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonBatchInit {
    @Autowired
    InfoNegaCompareProcessor processor;
    @PostConstruct
    public void bootstrap() {
        processor.loadCache(); // Ou appelle-le toi-même au setup de ton job batch !
    }
}