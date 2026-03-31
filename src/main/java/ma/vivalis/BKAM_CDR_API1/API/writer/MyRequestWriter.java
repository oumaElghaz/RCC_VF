package ma.vivalis.BKAM_CDR_API1.API.writer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.API.model.sss_cdr_api1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyRequestWriter implements ItemWriter<sss_cdr_api1> {
    private static final Logger log = LoggerFactory.getLogger(MyRequestWriter.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public void write(Chunk<? extends sss_cdr_api1> chunk) throws Exception {
        for (sss_cdr_api1 api1 : chunk) {


            em.persist(api1);



        }


        em.flush();
        em.clear();

    }
}
