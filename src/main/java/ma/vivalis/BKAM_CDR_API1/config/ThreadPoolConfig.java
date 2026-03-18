package ma.vivalis.BKAM_CDR_API1.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Configuration des pools de threads pour Spring Batch.
 *
 * 2 pools distincts pour 2 niveaux de parallélisme :
 *
 * ┌──────────────────────────────────────────────────────────────────┐
 * │  NIVEAU 1 : flowTaskExecutor (entre entités)                    │
 * │  ─────────────────────────────────���───────────                  │
 * │  Gère les Flows parallèles dans chaque Phase (Split)           │
 * │                                                                  │
 * │    flow-1 → [Compare Client ████████████]                       │
 * │    flow-2 → [Compare Contrat ██████████████]                    │
 * │    flow-3 → [Compare Garantie ████████]                         │
 * │                                                                  │
 * │  NIVEAU 2 : batchTaskExecutor (dans chaque Step)                │
 * │  ─────────────────────────────────────────────                  │
 * │  Gère les Chunks parallèles à l'intérieur d'un Step            │
 * │                                                                  │
 * │    batch-1 → [Chunk 1] [Chunk 4] [Chunk 7]                     │
 * │    batch-2 → [Chunk 2] [Chunk 5] [Chunk 8]                     │
 * │    batch-3 → [Chunk 3] [Chunk 6] [Chunk 9]                     │
 * │    batch-4 →           [Chunk 10][Chunk 11]                     │
 * └──────────────────────────────────────────────────────────────────┘
 */
@Configuration
public class ThreadPoolConfig {



    private static final Logger log = LoggerFactory.getLogger(ThreadPoolConfig.class);

    // ═══════════════════════════════════════════════════════
    // Propriétés configurables via application.properties
    // ═══════════════════════════════════════════════════════

    /** Nombre de threads min pour le traitement des chunks */
    @Value("${batch.thread.chunk.core-size:4}")
    private int chunkCoreSize;

    /** Nombre de threads max pour le traitement des chunks */
    @Value("${batch.thread.chunk.max-size:8}")
    private int chunkMaxSize;

    /** Taille de la file d'attente des chunks */
    @Value("${batch.thread.chunk.queue-capacity:50}")
    private int chunkQueueCapacity;

    /** Nombre de threads pour les Flows parallèles (= nombre d'entités) */
    @Value("${batch.thread.flow.core-size:3}")
    private int flowCoreSize;

    /** Nombre max de threads pour les Flows */
    @Value("${batch.thread.flow.max-size:5}")
    private int flowMaxSize;

    // ═══════════════════════════════════════════════════════
    // POOL 1 : batchTaskExecutor
    // Pour le multi-threading DANS chaque Step (chunks //)
    //
    // Utilisé dans : compareClientStep, mappingClientStep,
    //                compareContratStep, mappingContratStep,
    //                compareGarantieStep, mappingGarantieStep
    // ═══════════════════════════════════════════════════════
    @Bean
    public ThreadPoolTaskExecutor batchTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(chunkCoreSize);
        executor.setMaxPoolSize(chunkMaxSize);
        executor.setQueueCapacity(chunkQueueCapacity);
        executor.setThreadNamePrefix("batch-chunk-");

        // Timeout pour les threads inactifs au-delà du corePoolSize
        executor.setKeepAliveSeconds(60);

        // Permettre aux threads du core de mourir aussi si inactifs
        executor.setAllowCoreThreadTimeOut(true);

        // Politique de rejet : si la file est pleine,
        // le thread appelant exécute la tâche lui-même (pas de perte)
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // Attendre la fin de toutes les tâches avant shutdown
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(120);

        executor.initialize();

        log.info("✅ batchTaskExecutor initialisé — core={}, max={}, queue={}",
                chunkCoreSize, chunkMaxSize, chunkQueueCapacity);

        return executor;
    }

    // ═══════════════════════════════════════════════════════
    // POOL 2 : flowTaskExecutor
    // Pour le parallélisme ENTRE entités (Split dans les Phases)
    //
    // Utilisé dans : phase1CompareFlow, phase2MappingFlow,
    //                phase3XmlFlow, phase4PurgeFlow
    // ═══════════════════════════════════════════════════════
    @Bean
    public TaskExecutor flowTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(flowCoreSize);
        executor.setMaxPoolSize(flowMaxSize);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("flow-entity-");

        executor.setKeepAliveSeconds(30);
        executor.setAllowCoreThreadTimeOut(true);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(120);

        executor.initialize();

        log.info("✅ flowTaskExecutor initialisé — core={}, max={}, queue=10",
                flowCoreSize, flowMaxSize);

        return executor;
    }

}
