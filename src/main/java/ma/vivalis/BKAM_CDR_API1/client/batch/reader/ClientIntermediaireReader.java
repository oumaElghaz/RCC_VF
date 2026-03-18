package ma.vivalis.BKAM_CDR_API1.client.batch.reader;

import jakarta.persistence.EntityManagerFactory;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_inter_client_stat;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientIntermediaireReader {

    @Bean
    public JpaPagingItemReader<sss_cdr_inter_client_stat> clientIntermediaireItemReader(
            EntityManagerFactory entityManagerFactory) throws Exception {

        JpaPagingItemReader<sss_cdr_inter_client_stat> reader =
                new JpaPagingItemReaderBuilder<sss_cdr_inter_client_stat>()
                        .name("clientIntermediaireReader")
                        .entityManagerFactory(entityManagerFactory)
                        .queryString(
                                "SELECT DISTINCT c FROM sss_cdr_inter_client_stat c " +
                                        "LEFT JOIN FETCH c.adresse " +
                                        "LEFT JOIN FETCH c.donneesInt_pp " +
                                        "LEFT JOIN FETCH c.donneesInt_pm " +
                                        "LEFT JOIN FETCH c.actionnariats " +
                                        "LEFT JOIN FETCH c.benEffects " +
                                        "WHERE c.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_inter_client_stat c2) " +
                                        "ORDER BY c.id_client"
                        )
                        .pageSize(500)
                        .build();

        reader.afterPropertiesSet();
        return reader;
    }
}