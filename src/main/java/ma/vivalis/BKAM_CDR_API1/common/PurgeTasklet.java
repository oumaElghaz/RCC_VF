package ma.vivalis.BKAM_CDR_API1.common;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;

public class PurgeTasklet implements Tasklet {
    private static final Logger log = LoggerFactory.getLogger(PurgeTasklet.class);

    private final JdbcTemplate jdbcTemplate;
    private final String tableName;
    private final PurgeMode purgeMode;



    // ═══════════════════════════════════════════════════════
    // Modes de purge
    // ═══════════════════════════════════════════════════════
    public enum PurgeMode {
        /**
         * TRUNCATE TABLE : instantané
         * ✅ Très rapide (pas de scan ligne par ligne)
         * ✅ Remet l'auto-increment à 0
         * ❌ Ne déclenche PAS les triggers
         * ❌ Ne peut pas être rollbacké (DDL)
         * ❌ Échoue si la table a des FK entrantes
         */
        TRUNCATE,

        /**
         * DELETE FROM : transactionnel
         * ✅ Rollback possible en cas d'erreur
         * ✅ Déclenche les triggers
         * ✅ Compatible avec les FK (si ON DELETE CASCADE)
         * ❌ Plus lent (scan + log de chaque ligne)
         */
        DELETE,

        /**
         * DELETE avec purge des tables liées d'abord
         * ✅ Gère les FK (supprime les enfants avant le parent)
         * ❌ Le plus lent
         */
        DELETE_CASCADE
    }

    // ═══════════════════════════════════════════════════════
    // Constructeurs
    // ═══════════════════════════════════════════════════════

    /** Constructeur simple — mode TRUNCATE par défaut */
    public PurgeTasklet(JdbcTemplate jdbcTemplate, String tableName) {
        this(jdbcTemplate, tableName, PurgeMode.TRUNCATE);
    }

    /** Constructeur complet — choix du mode */
    public PurgeTasklet(JdbcTemplate jdbcTemplate, String tableName, PurgeMode purgeMode) {
        this.jdbcTemplate = jdbcTemplate;
        this.tableName = tableName;
        this.purgeMode = purgeMode;
    }

    // ═══════════════════════════════════════════════════════
    // Exécution
    // ═══════════════════════════════════════════════════════
    @Override
    public @Nullable RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
            throws Exception {

        log.info("🗑️ Début de la purge de la table [{}] en mode [{}]", tableName, purgeMode);

        long startTime = System.currentTimeMillis();
        int rowsAffected = 0;

        switch (purgeMode) {

            case TRUNCATE:
                jdbcTemplate.execute("TRUNCATE TABLE " + tableName);
                log.info("✅ TRUNCATE TABLE {} exécuté", tableName);
                break;

            case DELETE:
                rowsAffected = jdbcTemplate.update("DELETE FROM " + tableName);
                log.info("✅ DELETE FROM {} — {} lignes supprimées", tableName, rowsAffected);
                break;

            case DELETE_CASCADE:
                rowsAffected = purgeWithCascade();
                log.info("✅ DELETE CASCADE {} — {} lignes supprimées au total",
                        tableName, rowsAffected);
                break;
        }

        long duration = System.currentTimeMillis() - startTime;
        log.info("🏁 Purge de [{}] terminée en {} ms", tableName, duration);

        // Stocker les métriques dans le contexte pour le monitoring
        contribution.incrementWriteCount(rowsAffected);
        chunkContext.getStepContext()
                .getStepExecution()
                .getExecutionContext()
                .putLong("purge.duration.ms", duration);
        chunkContext.getStepContext()
                .getStepExecution()
                .getExecutionContext()
                .putString("purge.table", tableName);

        return RepeatStatus.FINISHED;
    }

    // ═══════════════════════════════════════════════════════
    // Purge en cascade (tables liées)
    //
    // Votre modèle :
    //   sss_cdr_snapshot_client_stat
    //     ├── sss_cdr_snapshot_client_act    (FK: id_client)
    //     ├── sss_cdr_snapshot_client_benef  (FK: id_client)
    //     ├── Adresse_snap                   (FK: adresse_id)
    //     ├── DonneesIntPP_snap              (FK: donneesInt_pp_id)
    //     └── DonneesIntPM_snap              (FK: donneesInt_pm_id)
    //
    // Il faut supprimer les ENFANTS avant le PARENT
    // ═══════════════════════════════════════════════════════
    private int purgeWithCascade() {
        int total = 0;

        if ("sss_cdr_snapshot_client_stat".equals(tableName)) {
            total += deleteAndLog("sss_cdr_snapshot_client_act");
            total += deleteAndLog("sss_cdr_snapshot_client_benef");
            total += deleteAndLog("Adresse");
            total += deleteAndLog("DonneesIntPP");
            total += deleteAndLog("DonneesIntPM");
            total += deleteAndLog(tableName);

        } else if ("sss_cdr_snapshot_contrat_stat".equals(tableName)) {
            total += deleteAndLog("ListCliContrat");
            total += deleteAndLog("ListLinkContrat");
            total += deleteAndLog("ListConsort");
            total += deleteAndLog("ListGarant");
            total += deleteAndLog(tableName);

        } else if ("sss_cdr_snapshot_garantie".equals(tableName)) {
            total += deleteAndLog(tableName);

        } else if ("sss_cdr_snapshot_infoNega_stat".equals(tableName)) {
            // ✅ COMINFNEG référence cette table via FK_INF_NEG_STAT
            total += deleteAndLog("INFNEG");
            total += deleteAndLog("COMINFNEG");       // enfant d'abord

            total += deleteAndLog(tableName);          // parent ensuite

        } else {
            total += deleteAndLog(tableName);
        }

        return total;
    }

    private int deleteAndLog(String table) {
        int rows = jdbcTemplate.update("DELETE FROM " + table);
        log.info("   ↳ DELETE FROM {} — {} lignes", table, rows);
        return rows;
    }
}
