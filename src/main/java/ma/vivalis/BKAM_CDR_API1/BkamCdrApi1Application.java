package ma.vivalis.BKAM_CDR_API1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class BkamCdrApi1Application //implements CommandLineRunner
{

    public static void main(String[] args) {
        SpringApplication.run(BkamCdrApi1Application.class, args);
    }
/*
    private final sss_cdr_client_stat_service_impl sss_cdr_client_stat_service_impl;
    private final client_stat_mapp client_stat_mapp;
    private static final Logger logger = LoggerFactory.getLogger(BkamCdrApi1Application.class);
    private final ApplicationContext context;

    public BkamCdrApi1Application(
            sss_cdr_client_stat_service_impl sssCdrClientStatServiceImpl,
            client_stat_mapp clientStatMapp, ApplicationContext context) {
        sss_cdr_client_stat_service_impl = sssCdrClientStatServiceImpl;
        client_stat_mapp = clientStatMapp;
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(BkamCdrApi1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        long globalStart = System.currentTimeMillis();

        logger.info("═══════════════════════════════════════════════════════════════");
        logger.info(" DÉMARRAGE DE LA GÉNÉRATION XML OPTIMISÉE");
        logger.info("═══════════════════════════════════════════════════════════════");

        try {
            //Initialiser le cache de mappings
            logger.info("Étape 1/4 : Initialisation du cache de mappings");
            long cacheStart = System.currentTimeMillis();
            client_stat_mapp.initializeMappingCache();
            long cacheTime = System.currentTimeMillis() - cacheStart;
            logger.info("Cache initialisé en " + cacheTime + "ms");

            //ÉTAPE 2 : Préparer le fichier de sortie
            logger.info("Étape 2/4 : Préparation du fichier de sortie");
            String outputDir = "C:/Users/PC/Documents/vivalis/BKAM_CDR/BKAM_CDR_API1/tmp";
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filePath = outputDir + "/rcc_clients_stat_" + timestamp + ".xml";

            // Créer le répertoire s'il n'existe pas
            File dir = new File(outputDir);
            if (!dir.exists() && !dir.mkdirs()) {
                throw new RuntimeException("Impossible de créer le répertoire : " + outputDir);
            }
            logger.info("Fichier de sortie : " + filePath);

            // ÉTAPE 3 : Générer le XML
            logger.info("Étape 3/4 : Génération du fichier XML (mode streaming)...");
            long xmlStart = System.currentTimeMillis();

            // UTILISER LA VERSION OPTIMISÉE
            sss_cdr_client_stat_service_impl.generer_xml_clientStat_optimized(filePath);

            long xmlTime = System.currentTimeMillis() - xmlStart;
            logger.info("XML généré en " + xmlTime + "ms");

            // ÉTAPE 4 : Compression et Base64
            logger.info("Étape 4/4 : Compression et encodage Base64");
            long compressStart = System.currentTimeMillis();

            try {
                String base64Zip = XmlToZipBase64.convertXmlToZippedBase64(filePath);

                long compressTime = System.currentTimeMillis() - compressStart;
                logger.info("Compression et encodage réussis en " + compressTime + "ms");

                // ========== Résultats ==========
                File xmlFile = new File(filePath);
                long xmlFileSize = xmlFile.length();

                logger.info("═══════════════════════════════════════════════════════════════");
                logger.info(" SUCCÈS - PROCESSUS TERMINÉ");
                logger.info("═══════════════════════════════════════════════════════════════");
                logger.info(" STATISTIQUES :");
                logger.info("   • Fichier XML : " + formatFileSize(xmlFileSize));
                logger.info("   • Temps total : " + formatTime(System.currentTimeMillis() - globalStart));
                logger.info("   • Chemin : " + filePath);
                logger.info("═══════════════════════════════════════════════════════════════");

                // Afficher un aperçu du Base64 (premiers 100 caractères)
                String preview = base64Zip.substring(0, Math.min(100, base64Zip.length())) + "...";
                logger.info("Base64 généré (aperçu) : " + preview);
                logger.info("═══════════════════════════════════════════════════════════════");

                // Optionnel : sauvegarder le Base64 dans un fichier
                String base64FilePath = filePath.replace(".xml", "_base64.txt");
                saveBase64ToFile(base64Zip, base64FilePath);
                logger.info("Base64 sauvegardé dans : " + base64FilePath);

            } catch (Exception e) {
                logger.error("Erreur lors de la compression/encoding Base64", e);
                throw new RuntimeException("Erreur compression", e);
            }

        } catch (Exception e) {
            long totalTime = System.currentTimeMillis() - globalStart;
            logger.error("═══════════════════════════════════════════════════════════════");
            logger.error("ERREUR - Le processus a échoué après " + formatTime(totalTime));
            logger.error("═══════════════════════════════════════════════════════════════");
            logger.error("Message d'erreur : " + e.getMessage());
            e.printStackTrace();

            // Propager l'erreur pour que Spring Boot la gère correctement
            throw new RuntimeException("Erreur lors de la génération XML", e);
        }
        logger.info("═══════════════════════════════════════════════════════════════");
        logger.info("Fin traitement - arrêt application");
        logger.info("═══════════════════════════════════════════════════════════════");

        SpringApplication.exit(context, () -> 0);
        System.exit(0);
    }

    *//**
     * Formate la taille du fichier en unités lisibles (B, KB, MB, GB)
     *//*
    private String formatFileSize(long bytes) {
        if (bytes <= 0) return "0 B";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(bytes) / Math.log10(1024));
        return String.format("%.2f %s", bytes / Math.pow(1024, digitGroups), units[digitGroups]);
    }

    *//**
     * Formate le temps en secondes/minutes
     *//*
    private String formatTime(long millis) {
        if (millis < 1000) {
            return millis + "ms";
        } else if (millis < 60000) {
            return String.format("%.2fs", millis / 1000.0);
        } else {
            long minutes = millis / 60000;
            long seconds = (millis % 60000) / 1000;
            return String.format("%dm %ds", minutes, seconds);
        }
    }

    *//**
     * Sauvegarde le Base64 dans un fichier
     *//*
    private void saveBase64ToFile(String base64, String filePath) {
        try {
            File file = new File(filePath);
            try (java.io.FileWriter writer = new java.io.FileWriter(file)) {
                writer.write(base64);
            }
            logger.info("Base64 sauvegardé : " + formatFileSize(file.length()));
        } catch (Exception e) {
            logger.warn("Impossible de sauvegarder le Base64 : " + e.getMessage());
        }
    }*/
}