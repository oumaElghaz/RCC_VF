package ma.vivalis.BKAM_CDR_API1.client.batch.writer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_inter_client_stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ClientDirectWriter implements ItemWriter<sss_cdr_inter_client_stat> {

    private static final Logger log = LoggerFactory.getLogger(ClientDirectWriter.class);

    @PersistenceContext
    private EntityManager em;

    private static final int BATCH_SIZE = 50;


    @Override
    public void write(Chunk<? extends sss_cdr_inter_client_stat> chunk) throws Exception {
        int count = 0;
        for (sss_cdr_inter_client_stat client : chunk) {
            if (client != null) {
                em.persist(client);
                count++;
                if (count % BATCH_SIZE == 0) {
                    em.flush();
                    em.clear();
                }
            }
        }
        em.flush();
        em.clear();
        log.info(" {} clients persistés", count);

    }
}
