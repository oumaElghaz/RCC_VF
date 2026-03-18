package ma.vivalis.BKAM_CDR_API1.infoNeg.batch.writer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.client.batch.writer.ClientMappingWriter;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_infoNegative;
import ma.vivalis.BKAM_CDR_API1.infoNeg.repository.sss_cdr_infoNegaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class InfoNegaMappingWriter implements ItemWriter<sss_cdr_infoNegative> {

    private static final Logger log = LoggerFactory.getLogger(ClientMappingWriter.class);
    private final sss_cdr_infoNegaRepository sss_cdr_infoNegaRepository;
    @PersistenceContext
    private EntityManager em;

    public InfoNegaMappingWriter(sss_cdr_infoNegaRepository sssCdrInfoNegaRepository) {
        sss_cdr_infoNegaRepository = sssCdrInfoNegaRepository;
    }

    @Override
    public void write(Chunk<? extends sss_cdr_infoNegative> chunk) throws Exception {
        for (sss_cdr_infoNegative info : chunk) {
            boolean exists = sss_cdr_infoNegaRepository.findById_lotAndDateExtractionAndId(
                    info.getId_lot(), info.getDateExtraction(), info.getId()
            ).isPresent();
            if (!exists) {
            em.persist(info);}
        }

        em.flush();
        em.clear();

        log.info("✅ {} infos mappés écrits dans info_final", chunk.size());
    }
}
