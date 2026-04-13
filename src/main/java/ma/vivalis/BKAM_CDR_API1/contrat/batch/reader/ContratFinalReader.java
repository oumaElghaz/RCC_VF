package ma.vivalis.BKAM_CDR_API1.contrat.batch.reader;

import jakarta.persistence.EntityManagerFactory;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_contrat_stat;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContratFinalReader {

    @Bean
    public JpaPagingItemReader<sss_cdr_contrat_stat> contratFinalItemReader(
            EntityManagerFactory entityManagerFactory) throws Exception{

        JpaPagingItemReader<sss_cdr_contrat_stat> reader = new JpaPagingItemReaderBuilder<sss_cdr_contrat_stat>()
                .name("contratFinalItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(
                        "SELECT a FROM sss_cdr_contrat_stat a " +
                                "LEFT JOIN FETCH a.listCliContrat " +
                                "LEFT JOIN FETCH a.listLinkContrat " +
                                "LEFT JOIN FETCH a.listConsort " +
                                "LEFT JOIN FETCH a.listGarant " +
                                "WHERE a.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_contrat_stat c2) " +
                                "ORDER BY a.idCont"

                )
                .pageSize(200)
                .build();

        reader.afterPropertiesSet();

        return reader;
    }
}
