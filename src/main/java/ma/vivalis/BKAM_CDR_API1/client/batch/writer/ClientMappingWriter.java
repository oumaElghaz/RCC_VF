package ma.vivalis.BKAM_CDR_API1.client.batch.writer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.repository.sss_cdr_client_stat_Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ClientMappingWriter implements ItemWriter<sss_cdr_client_stat> {
    private static final Logger log = LoggerFactory.getLogger(ClientMappingWriter.class);
    private final sss_cdr_client_stat_Repository sss_cdr_client_stat_Repository;
    @PersistenceContext
    private EntityManager em;

    public ClientMappingWriter(sss_cdr_client_stat_Repository sssCdrClientStatRepository) {
        sss_cdr_client_stat_Repository = sssCdrClientStatRepository;
    }

    @Override
    public void write(Chunk<? extends sss_cdr_client_stat> chunk) throws Exception {
        for (sss_cdr_client_stat client : chunk) {
            boolean exists = sss_cdr_client_stat_Repository.findById_lotAndDateExtractionAndId_client(
                    client.getId_lot(), client.getDateExtraction(), client.getId_client()
            ).isPresent();
            if (!exists) {
            em.persist(client);}
        }

        em.flush();
        em.clear();

        log.info("✅ {} clients mappés écrits dans client_final", chunk.size());

    }
}
