package ma.vivalis.BKAM_CDR_API1.infoNeg.batch.reader;

import jakarta.persistence.EntityManagerFactory;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_infoNega_stat;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfoNegaSnapshotItemReader {
    @Bean
    public JpaPagingItemReader<sss_cdr_snapshot_infoNega_stat> infoNegaSnapshotReader(
            EntityManagerFactory entityManagerFactory) throws Exception{

        JpaPagingItemReader<sss_cdr_snapshot_infoNega_stat> reader = new JpaPagingItemReaderBuilder<sss_cdr_snapshot_infoNega_stat>()
                .name("InfoNegaSnapshotItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(
                        "SELECT DISTINCT c FROM sss_cdr_snapshot_infoNega_stat c " +
                                "LEFT JOIN FETCH c.comInfNegs " +
                                "ORDER BY c.id"
                )
                .pageSize(200)  // ✅ Pagination pour éviter OutOfMemory
                .build();

        reader.afterPropertiesSet();

        return reader;
    }
}
