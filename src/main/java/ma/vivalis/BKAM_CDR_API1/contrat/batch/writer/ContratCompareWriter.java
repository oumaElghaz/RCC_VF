package ma.vivalis.BKAM_CDR_API1.contrat.batch.writer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_inter_contrat_stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContratCompareWriter implements ItemWriter<sss_cdr_inter_contrat_stat> {
    private static final Logger log = LoggerFactory.getLogger(ContratCompareWriter.class);
    @PersistenceContext
    private EntityManager em;

    @Override
    public void write(Chunk<? extends sss_cdr_inter_contrat_stat> chunk) throws Exception {
        for (sss_cdr_inter_contrat_stat inter : chunk) {

            // 1. Sauvegarder dans INTERMÉDIAIRE
            em.persist(inter);



        }

        // Flush pour envoyer les INSERTs à la BDD dans ce chunk
        em.flush();
        em.clear();  // Libérer la mémoire du contexte de persistance

        log.info("✅ {} contrats écrits dans intermédiaire", chunk.size());


    }
}
