package ma.vivalis.BKAM_CDR_API1.garantie.batch.writer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_arch_garantie;
import ma.vivalis.BKAM_CDR_API1.garantie.repository.sss_cdr_arch_garantie_repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GarantieArchWriter implements ItemWriter<sss_cdr_arch_garantie> {
    private static final Logger log = LoggerFactory.getLogger(GarantieArchWriter.class);
    private final sss_cdr_arch_garantie_repository sss_cdr_arch_garantie_repository;
    @PersistenceContext
    private EntityManager em;

    public GarantieArchWriter(sss_cdr_arch_garantie_repository sssCdrArchGarantieRepository) {
        sss_cdr_arch_garantie_repository = sssCdrArchGarantieRepository;
    }

    @Override
    public void write(Chunk<? extends sss_cdr_arch_garantie> chunk) throws Exception {
        for (sss_cdr_arch_garantie gar : chunk) {
            boolean exists = sss_cdr_arch_garantie_repository.findById_lotAndDateExtractionAndId_gar(
                    gar.getId_lot(), gar.getDateExtraction(), gar.getIdGar()
            ).isPresent();
            if (!exists) {
                em.persist(gar);}
        }

        em.flush();
        em.clear();

        log.info("✅ {} garanties archivés  dans l'archive", chunk.size());

    }
}
