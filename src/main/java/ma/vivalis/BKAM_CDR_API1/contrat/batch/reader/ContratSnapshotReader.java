package ma.vivalis.BKAM_CDR_API1.contrat.batch.reader;

import jakarta.persistence.EntityManagerFactory;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_contrat_stat;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContratSnapshotReader {
    @Bean
    public JpaPagingItemReader<sss_cdr_snapshot_contrat_stat> contratSnapshotItemReader(
            EntityManagerFactory entityManagerFactory) throws Exception{

        JpaPagingItemReader<sss_cdr_snapshot_contrat_stat> reader = new JpaPagingItemReaderBuilder<sss_cdr_snapshot_contrat_stat>()
                .name("ContratSnapshotReader")
                .entityManagerFactory(entityManagerFactory)
                //  FETCH JOIN pour charger toutes les relations en 1 seule requête
                // Évite le problème N+1 (1 requête par client pour chaque relation)
                .queryString(
                        "SELECT DISTINCT a FROM sss_cdr_snapshot_contrat_stat a " +
                                "LEFT JOIN FETCH a.listCliContrat " +
                                "LEFT JOIN FETCH a.listLinkContrat " +
                                "LEFT JOIN FETCH a.listConsort " +
                                "LEFT JOIN FETCH a.listGarant " +
                                "ORDER BY a.idCont"
                )
                .pageSize(200)  // ✅ Pagination pour éviter OutOfMemory
                .build();

        reader.afterPropertiesSet();

        return reader;
    }
}
