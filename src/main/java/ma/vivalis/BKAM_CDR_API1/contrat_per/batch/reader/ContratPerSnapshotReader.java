package ma.vivalis.BKAM_CDR_API1.contrat_per.batch.reader;

import jakarta.persistence.EntityManagerFactory;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_contrat_per;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContratPerSnapshotReader {
    @Bean
    public JpaPagingItemReader<sss_cdr_snapshot_contrat_per> contratPerSnapshotItemReader(
            EntityManagerFactory entityManagerFactory) throws Exception{

        JpaPagingItemReader<sss_cdr_snapshot_contrat_per> reader = new JpaPagingItemReaderBuilder<sss_cdr_snapshot_contrat_per>()
                .name("contratPerSnapshotItemReader")
                .entityManagerFactory(entityManagerFactory)

                .queryString(
                        "SELECT DISTINCT c FROM sss_cdr_snapshot_contrat_per c " +
                                "ORDER BY c.idCont"
                )
                .pageSize(200)  // ✅ Pagination pour éviter OutOfMemory
                .build();

        reader.afterPropertiesSet();

        return reader;
    }
}
