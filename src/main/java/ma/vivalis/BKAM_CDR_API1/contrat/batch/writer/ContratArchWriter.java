package ma.vivalis.BKAM_CDR_API1.contrat.batch.writer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_arch_contrat_stat;
import ma.vivalis.BKAM_CDR_API1.contrat.repository.sss_cdr_arch_contrat_stat_repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContratArchWriter implements ItemWriter<sss_cdr_arch_contrat_stat> {
    private static final Logger log = LoggerFactory.getLogger(ContratArchWriter.class);
    private final sss_cdr_arch_contrat_stat_repository sss_cdr_arch_contrat_stat_repository;
    @PersistenceContext
    private EntityManager em;

    public ContratArchWriter(sss_cdr_arch_contrat_stat_repository sssCdrArchContratStatRepository) {
        sss_cdr_arch_contrat_stat_repository = sssCdrArchContratStatRepository;
    }


    @Override
    public void write(Chunk<? extends sss_cdr_arch_contrat_stat> chunk) throws Exception {

        for (sss_cdr_arch_contrat_stat contrat : chunk) {
            boolean exists = sss_cdr_arch_contrat_stat_repository.findById_lotAndDateExtractionAndIdCont(
                    contrat.getId_lot(), contrat.getDateExtraction(), contrat.getIdCont()
            ).isPresent();
            if (!exists) {
                em.persist(contrat);}
        }

        em.flush();
        em.clear();

        log.info("✅ {} contrats archivés  dans l'archive", chunk.size());

    }
}
