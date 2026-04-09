package ma.vivalis.BKAM_CDR_API1.contrat_per.batch.writer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_inter_contrat_per;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContratPerCompareWriter  implements ItemWriter<sss_cdr_inter_contrat_per> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void write(Chunk<? extends sss_cdr_inter_contrat_per> chunk) throws Exception {
        for (sss_cdr_inter_contrat_per inter : chunk) {

            // 1. Sauvegarder dans INTERMÉDIAIRE
            em.persist(inter);



        }

        // Flush pour envoyer les INSERTs à la BDD dans ce chunk
        em.flush();
        em.clear();  // Libérer la mémoire du contexte de persistance

    }
}
