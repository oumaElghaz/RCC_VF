package ma.vivalis.BKAM_CDR_API1.garantie.batch.reader;

import jakarta.persistence.EntityManagerFactory;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_garantie;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GarantieFinalReader {

    @Bean
    public JpaPagingItemReader<sss_cdr_garantie> garantieFinalItemReader(
            EntityManagerFactory entityManagerFactory) throws Exception{

        JpaPagingItemReader<sss_cdr_garantie> reader = new JpaPagingItemReaderBuilder<sss_cdr_garantie>()
                .name("garantieFinalItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(
                        "SELECT c FROM sss_cdr_garantie c " +
                                "WHERE c.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_garantie c2) " +
                                "ORDER BY c.idGar"

                )
                .pageSize(200)
                .build();

        reader.afterPropertiesSet();

        return reader;
    }
}
