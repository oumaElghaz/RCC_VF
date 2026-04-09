package ma.vivalis.BKAM_CDR_API1.garantie.batch.writer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_garantie;
import ma.vivalis.BKAM_CDR_API1.garantie.repository.sss_cdr_garantie_repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class GarantieMappingWriter implements ItemWriter<sss_cdr_garantie> {
    private static final Logger log = LoggerFactory.getLogger(GarantieMappingWriter.class);
    private final sss_cdr_garantie_repository sss_cdr_garantie_repository;
    @PersistenceContext
    private EntityManager em;

    public GarantieMappingWriter(sss_cdr_garantie_repository sssCdrGarantieRepository) {
        sss_cdr_garantie_repository = sssCdrGarantieRepository;
    }

    @Override
    public void write(Chunk<? extends sss_cdr_garantie> chunk) throws Exception {
        for (sss_cdr_garantie gar : chunk) {
            boolean exists = sss_cdr_garantie_repository.findById_lotAndDateExtractionAndId_gar(
                    gar.getId_lot(), gar.getDateExtraction(), gar.getIdGar()
            ).isPresent();
            if (!exists) {
                em.persist(gar);}
        }

        em.flush();
        em.clear();

        log.info("✅ {} garanties mappés écrits dans client_final", chunk.size());


    }
}
