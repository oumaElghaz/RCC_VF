package ma.vivalis.BKAM_CDR_API1;

import ma.vivalis.BKAM_CDR_API1.services.sss_cdr_client_stat_service_impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BkamCdrApi1Application implements CommandLineRunner {
    private final sss_cdr_client_stat_service_impl sss_cdr_client_stat_service_impl;
    private static final Logger logger = LoggerFactory.getLogger(BkamCdrApi1Application.class);
    public BkamCdrApi1Application(sss_cdr_client_stat_service_impl sssCdrClientStatServiceImpl) {
        sss_cdr_client_stat_service_impl = sssCdrClientStatServiceImpl;
    }

    public static void main(String[] args) {

        SpringApplication.run(BkamCdrApi1Application.class, args);


	}

    @Override
    public void run(String... args) throws Exception {
        logger.info("Démarrage de la generation  XML...");
        sss_cdr_client_stat_service_impl.generer_xml_clientStat();
        logger.info("Fin de la generation  XML...");
    }
}
