package ma.vivalis.BKAM_CDR_API1.contrat_per.batch.writer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_contrat_per;
import ma.vivalis.BKAM_CDR_API1.contrat_per.repository.sss_cdr_contrat_per_repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ContratPerMappingWriter implements ItemWriter<sss_cdr_contrat_per> {
    private static final Logger log = LoggerFactory.getLogger(ContratPerMappingWriter.class);
    private final sss_cdr_contrat_per_repository sss_cdr_contrat_per_repository;
    @PersistenceContext
    private EntityManager em;

    public ContratPerMappingWriter(sss_cdr_contrat_per_repository sssCdrClientPerRepository, sss_cdr_contrat_per_repository sssCdrContratPerRepository) {
        sss_cdr_contrat_per_repository = sssCdrContratPerRepository;
    }

    @Override
    public void write(Chunk<? extends sss_cdr_contrat_per> chunk) throws Exception {
        for (sss_cdr_contrat_per contrat : chunk) {
            boolean exists = sss_cdr_contrat_per_repository.findById_lotAndDateExtractionAndIdCont(
                    contrat.getId_lot(), contrat.getDateExtraction(), contrat.getIdCont()
            ).isPresent();
            if (!exists) {
                em.persist(contrat);}
        }

        em.flush();
        em.clear();

        log.info("✅ {} contrats mappés écrits dans contratt_final", chunk.size());


    }
}
