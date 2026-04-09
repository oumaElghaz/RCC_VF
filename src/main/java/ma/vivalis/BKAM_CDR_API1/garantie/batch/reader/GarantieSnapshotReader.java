package ma.vivalis.BKAM_CDR_API1.garantie.batch.reader;

import jakarta.persistence.EntityManagerFactory;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_garantie;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GarantieSnapshotReader {
    @Bean
    public JpaPagingItemReader<sss_cdr_snapshot_garantie> garantieSnapshotItemReader(
            EntityManagerFactory entityManagerFactory) throws Exception{

        JpaPagingItemReader<sss_cdr_snapshot_garantie> reader = new JpaPagingItemReaderBuilder<sss_cdr_snapshot_garantie>()
                .name("GarantieSnapshotReader")
                .entityManagerFactory(entityManagerFactory)

                .queryString(
                        "SELECT DISTINCT c FROM sss_cdr_snapshot_garantie c " +
                                "ORDER BY c.idGar"
                )
                .pageSize(200)  // ✅ Pagination pour éviter OutOfMemory
                .build();

        reader.afterPropertiesSet();

        return reader;
    }
}
