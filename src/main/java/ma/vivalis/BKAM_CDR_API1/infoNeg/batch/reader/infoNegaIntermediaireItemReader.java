package ma.vivalis.BKAM_CDR_API1.infoNeg.batch.reader;

import jakarta.persistence.EntityManagerFactory;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_inter_infoNegative;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class infoNegaIntermediaireItemReader {
    @Bean
    public JpaPagingItemReader<sss_cdr_inter_infoNegative> infoNegaIntermediaireReader(
            EntityManagerFactory entityManagerFactory) throws Exception {

        JpaPagingItemReader<sss_cdr_inter_infoNegative> reader =
                new JpaPagingItemReaderBuilder<sss_cdr_inter_infoNegative>()
                        .name("infoNegaIntermediaireReader")
                        .entityManagerFactory(entityManagerFactory)
                        .queryString(
                                "SELECT DISTINCT c FROM sss_cdr_inter_infoNegative c " +
                                        "LEFT JOIN FETCH c.comInfNegs " +
                                        "WHERE c.id_lot = (SELECT MAX(c2.val) FROM LotSequence c2) " +
                                        "ORDER BY c.id"
                        )
                        .pageSize(500)
                        .build();

        reader.afterPropertiesSet();
        return reader;
    }
}
