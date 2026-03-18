package ma.vivalis.BKAM_CDR_API1.infoNeg.batch.writer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_arch_infoNegative;
import ma.vivalis.BKAM_CDR_API1.infoNeg.repository.sss_cdr_arch_infoNegaRepository;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfoNegaArchWriter implements ItemWriter<sss_cdr_arch_infoNegative> {
    private final sss_cdr_arch_infoNegaRepository sss_cdr_arch_infoNegaRepository;

    @PersistenceContext
    private EntityManager em;

    public InfoNegaArchWriter(sss_cdr_arch_infoNegaRepository sssCdrArchInfoNegaRepository) {
        sss_cdr_arch_infoNegaRepository = sssCdrArchInfoNegaRepository;
    }

    @Override
    public void write(Chunk<? extends sss_cdr_arch_infoNegative> chunk) throws Exception {
        for (sss_cdr_arch_infoNegative info : chunk) {
            boolean exists = sss_cdr_arch_infoNegaRepository.findById_lotAndDateExtractionAndId(
                    info.getId_lot(), info.getDateExtraction(), info.getId()
            ).isPresent();
            if (!exists) {
            em.persist(info);
            }
        }

        em.flush();
        em.clear();
    }
}
