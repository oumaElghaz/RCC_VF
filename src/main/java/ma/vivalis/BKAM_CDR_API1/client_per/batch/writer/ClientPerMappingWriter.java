package ma.vivalis.BKAM_CDR_API1.client_per.batch.writer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_client_per;
import ma.vivalis.BKAM_CDR_API1.client_per.repository.sss_cdr_client_per_repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ClientPerMappingWriter implements ItemWriter<sss_cdr_client_per> {
    private static final Logger log = LoggerFactory.getLogger(ClientPerMappingWriter.class);
    private final sss_cdr_client_per_repository sss_cdr_client_per_repository;
    @PersistenceContext
    private EntityManager em;

    public ClientPerMappingWriter(sss_cdr_client_per_repository sssCdrClientPerRepository) {
        sss_cdr_client_per_repository = sssCdrClientPerRepository;
    }

    @Override
    public void write(Chunk<? extends sss_cdr_client_per> chunk) throws Exception {
        for (sss_cdr_client_per client : chunk) {
            boolean exists = sss_cdr_client_per_repository.findById_lotAndDateExtractionAndId_client(
                    client.getId_lot(), client.getDateExtraction(), client.getCodClient()
            ).isPresent();
            if (!exists) {
                em.persist(client);}
        }

        em.flush();
        em.clear();

        log.info("✅ {} clients mappés écrits dans client_final", chunk.size());

    }
}
