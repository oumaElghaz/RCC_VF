package ma.vivalis.BKAM_CDR_API1.contrat.batch.reader;

import jakarta.persistence.EntityManagerFactory;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_inter_contrat_stat;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContratIntermediaireReader {
    @Bean
    public JpaPagingItemReader<sss_cdr_inter_contrat_stat> contratIntermediaireItemReader(
            EntityManagerFactory entityManagerFactory) throws Exception {

        JpaPagingItemReader<sss_cdr_inter_contrat_stat> reader =
                new JpaPagingItemReaderBuilder<sss_cdr_inter_contrat_stat>()
                        .name("ContratIntermediaireReader")
                        .entityManagerFactory(entityManagerFactory)
                        .queryString(
                                "SELECT DISTINCT a FROM sss_cdr_inter_contrat_stat a " +
                                        "LEFT JOIN FETCH a.listCliContrat " +
                                        "LEFT JOIN FETCH a.listLinkContrat " +
                                        "LEFT JOIN FETCH a.listConsort " +
                                        "LEFT JOIN FETCH a.listGarant " +
                                        "WHERE a.id_lot = (SELECT MAX(c2.val) FROM LotSequence c2) " +
                                        "ORDER BY a.idCont"
                        )
                        .pageSize(500)
                        .build();

        reader.afterPropertiesSet();
        return reader;
    }



}
