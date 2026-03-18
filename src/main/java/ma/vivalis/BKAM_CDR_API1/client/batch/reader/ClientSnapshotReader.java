package ma.vivalis.BKAM_CDR_API1.client.batch.reader;

import jakarta.persistence.EntityManagerFactory;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_client_stat;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientSnapshotReader {
    @Bean
    public JpaPagingItemReader<sss_cdr_snapshot_client_stat> clientSnapshotItemReader(
            EntityManagerFactory entityManagerFactory) throws Exception{

        JpaPagingItemReader<sss_cdr_snapshot_client_stat> reader = new JpaPagingItemReaderBuilder<sss_cdr_snapshot_client_stat>()
                .name("clientSnapshotReader")
                .entityManagerFactory(entityManagerFactory)
                //  FETCH JOIN pour charger toutes les relations en 1 seule requête
                // Évite le problème N+1 (1 requête par client pour chaque relation)
                .queryString(
                        "SELECT DISTINCT c FROM sss_cdr_snapshot_client_stat c " +
                                "LEFT JOIN FETCH c.adresse " +
                                "LEFT JOIN FETCH c.donneesInt_pp " +
                                "LEFT JOIN FETCH c.donneesInt_pm " +
                                // ⚠️ Les @OneToMany (actionnariats, benEffects) seront chargées
                                //    automatiquement par JPA quand on y accède (LAZY loading)
                                //    car on est encore dans la transaction du Step
                                "LEFT JOIN FETCH c.actionnariats " +
                                "LEFT JOIN FETCH c.benEffects " +
                                "ORDER BY c.id_client"
                )
                .pageSize(200)  // ✅ Pagination pour éviter OutOfMemory
                .build();

        reader.afterPropertiesSet();

        return reader;
    }
}
