package ma.vivalis.BKAM_CDR_API1.infoNeg.batch.reader;

import jakarta.persistence.EntityManagerFactory;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_infoNegative;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class infoNegaFinalItemReader {
    @Bean
    public JpaPagingItemReader<sss_cdr_infoNegative> infoNegaFinalReader(
            EntityManagerFactory entityManagerFactory) throws Exception{

        JpaPagingItemReader<sss_cdr_infoNegative> reader = new JpaPagingItemReaderBuilder<sss_cdr_infoNegative>()
                .name("infoNegaFinalItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(
                        "SELECT c FROM sss_cdr_infoNegative c " +
                                "LEFT JOIN FETCH c.comInfNegs " +
                                "WHERE c.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_infoNegative c2) " +
                                "ORDER BY c.id"

                )
                .pageSize(200)
                .build();

        reader.afterPropertiesSet();

        return reader;
    }
}
