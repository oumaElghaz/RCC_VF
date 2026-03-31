package ma.vivalis.BKAM_CDR_API1.client_per.batch.reader;

import jakarta.persistence.EntityManagerFactory;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_inter_client_per;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientPerIntermediaireReader {

    @Bean
    public JpaPagingItemReader<sss_cdr_inter_client_per> clientPerIntermediaireItemReader(
            EntityManagerFactory entityManagerFactory) throws Exception {

        JpaPagingItemReader<sss_cdr_inter_client_per> reader =
                new JpaPagingItemReaderBuilder<sss_cdr_inter_client_per>()
                        .name("ClientPerIntermediaireReader")
                        .entityManagerFactory(entityManagerFactory)
                        .queryString(
                                "SELECT DISTINCT c FROM sss_cdr_inter_client_per c " +
                                        "WHERE c.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_inter_client_per c2) " +
                                        "ORDER BY c.codClient"
                        )
                        .pageSize(500)
                        .build();

        reader.afterPropertiesSet();
        return reader;
    }
}
