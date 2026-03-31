package ma.vivalis.BKAM_CDR_API1.client.batch.StepConfig;

import generated.ComEnt;
import ma.vivalis.BKAM_CDR_API1.API.model.MyRequestBody;
import ma.vivalis.BKAM_CDR_API1.API.model.MyResponseBody;
import ma.vivalis.BKAM_CDR_API1.API.model.sss_cdr_api1;
import ma.vivalis.BKAM_CDR_API1.API.processor.MyRequestProcessor;
import ma.vivalis.BKAM_CDR_API1.API.writer.MyRequestWriter;
import ma.vivalis.BKAM_CDR_API1.client.batch.processor.ClientTraitementMappingProcessor;
import ma.vivalis.BKAM_CDR_API1.client.batch.writer.ClientXmlAppendWriter;
import ma.vivalis.BKAM_CDR_API1.client.batch.tasklet.XmlFooterTasklet;
import ma.vivalis.BKAM_CDR_API1.client.batch.tasklet.XmlHeaderTasklet;
import ma.vivalis.BKAM_CDR_API1.client.batch.processor.ClientArchProcessor;
import ma.vivalis.BKAM_CDR_API1.client.batch.processor.ClientCompareProcessor;
import ma.vivalis.BKAM_CDR_API1.client.batch.processor.ClientDirectMappingProcessor;
import ma.vivalis.BKAM_CDR_API1.client.batch.processor.ClientMappingProcessor;
import ma.vivalis.BKAM_CDR_API1.client.batch.writer.ClientArchWriter;
import ma.vivalis.BKAM_CDR_API1.client.batch.writer.ClientCompareWriter;
import ma.vivalis.BKAM_CDR_API1.client.batch.writer.ClientDirectWriter;
import ma.vivalis.BKAM_CDR_API1.client.batch.writer.ClientMappingWriter;
import ma.vivalis.BKAM_CDR_API1.client.model.dto.ClientChangeDTO;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_arch_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_inter_client_stat;
import ma.vivalis.BKAM_CDR_API1.common.PurgeTasklet;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_client_stat;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.database.JdbcCursorItemReader;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ClientStepConfig {




    // ── Step 1 : Comparaison + Mapping direct ──
    @Bean
    public Step compareAndMapClientStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JdbcCursorItemReader<ClientChangeDTO> clientCompareReader,
            ClientDirectMappingProcessor clientDirectMappingProcessor,
            ClientDirectWriter clientDirectWriter) {

        return new StepBuilder("compareAndMapClientStep", jobRepository)
                .<ClientChangeDTO, sss_cdr_inter_client_stat>chunk(500)
                .transactionManager(tx)
                .reader(clientCompareReader)
                .processor(clientDirectMappingProcessor)
                .writer(clientDirectWriter)
                .build();
    }
    // ═══════════════════════════════════════════════════════
    // STEP 1 : Comparaison client
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step compareClientStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_snapshot_client_stat> clientSnapshotItemReader,
            ClientCompareProcessor clientCompareProcessor,
            ClientCompareWriter clientCompareWriter//,
            //ThreadPoolTaskExecutor batchTaskExecutor
    ) {

        // Thread-safe wrapper pour le multi-threading
        //SynchronizedItemReader<sss_cdr_snapshot_client_stat> syncReader =
              //  new SynchronizedItemReader<>(clientSnapshotItemReader);


        return new StepBuilder("compareClientStep", jobRepository)
                .<sss_cdr_snapshot_client_stat, sss_cdr_inter_client_stat>chunk(500)
                .transactionManager(tx)
                .reader(clientSnapshotItemReader)
                .processor(clientCompareProcessor)
                .writer(clientCompareWriter)
                //.taskExecutor(batchTaskExecutor)
                .faultTolerant()
                .skipLimit(100)
                .skip(Exception.class)
                .build();
    }



    //




    // ═══════════════════════════════════════════════════════
// STEP 2a : Écrire le header XML
// ═══════════════════════════════════════════════════════
    @Bean
    public Step headerXmlClientStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            XmlHeaderTasklet xmlHeaderTasklet) {

        return new StepBuilder("headerXmlClientStep", jobRepository)
                .tasklet(xmlHeaderTasklet, tx)
                .build();
    }

    // ═══════════════════════════════════════════════════════
// STEP 2b : Écrire les DonneesEnt (contenu)
// ═══════════════════════════════════════════════════════
    @Bean
    public Step contentXmlClientStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_client_stat> clientIntermediaireItemReader,
            ClientTraitementMappingProcessor clientTraitementMappingProcessor,
            ClientXmlAppendWriter clientXmlAppendWriter) {

        return new StepBuilder("contentXmlClientStep", jobRepository)
                .<sss_cdr_inter_client_stat, ComEnt.DonneesEnt>chunk(200).transactionManager(tx)
                .reader(clientIntermediaireItemReader)
                .processor(clientTraitementMappingProcessor)
                .writer(clientXmlAppendWriter)
                // ⚠️ Pas de taskExecutor (écriture séquentielle dans le fichier)
                .build();
    }

    // ════════════════════════════════════════���══════════════
// STEP 2c : Écrire le footer XML
// ═══════════════════════════════════════════════════════
    @Bean
    public Step footerXmlClientStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            XmlFooterTasklet xmlFooterTasklet) {

        return new StepBuilder("footerXmlClientStep", jobRepository)
                .tasklet(xmlFooterTasklet, tx)
                .build();
    }


    //
    //




    // ═══════════════════════════════════════════════════════
    // STEP  : Envoi Api
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step envoiApiClientStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            @Qualifier("readerClient") ItemReader<MyRequestBody> readerClient,
            MyRequestProcessor  myRequestProcessor,
            MyRequestWriter myRequestWriter


    ) {


        return new StepBuilder("envoiApiClientStep", jobRepository)
                .<MyRequestBody, sss_cdr_api1>chunk(500)
                .transactionManager(tx)
                .reader(readerClient)
                .processor(myRequestProcessor)
                .writer(myRequestWriter)
                .build();
    }



    // ═══════════════════════════════════════════════════════
    // STEP 3 : Mapping client
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step mappingClientStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_client_stat> clientIntermediaireItemReader,
            ClientMappingProcessor clientMappingProcessor,
            ClientMappingWriter ClientMappingWriter//,
            //ThreadPoolTaskExecutor batchTaskExecutor
    ) {

        //SynchronizedItemReader<sss_cdr_inter_client_stat> syncReader =
        //new SynchronizedItemReader<>(clientIntermediaireItemReader);


        return new StepBuilder("mappingClientStep", jobRepository)
                .<sss_cdr_inter_client_stat, sss_cdr_client_stat>chunk(500)
                .transactionManager(tx)
                .reader(clientIntermediaireItemReader)
                .processor(clientMappingProcessor)
                .writer(ClientMappingWriter)
                //.taskExecutor(batchTaskExecutor)
                .build();
    }



    // ═══════════════════════════════════════════════════════
    // STEP 4 : Archivage client
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step archiverClientStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_client_stat> clientIntermediaireItemReader,
            ClientArchProcessor clientArchProcessor,
            ClientArchWriter clientArchWriter
    ) {




        return new StepBuilder("archiverClientStep", jobRepository)
                .<sss_cdr_inter_client_stat, sss_cdr_arch_client_stat>chunk(200)
                .transactionManager(tx)
                .reader(clientIntermediaireItemReader)
                .processor(clientArchProcessor)
                .writer(clientArchWriter)
                //.taskExecutor(batchTaskExecutor)
                .faultTolerant()
                .skipLimit(100)
                .skip(Exception.class)
                .build();
    }

    // ═══════════════════════════════════════════════════════
// Mode DELETE_CASCADE — Si vous avez des FK
// Supprime actionnariats + bénéficiaires + adresses + PP + PM
// AVANT de supprimer le client snapshot
// ═══════════════════════════════════════════════════════
    @Bean
    public Step purgeClientStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JdbcTemplate jdbcTemplate) {

        return new StepBuilder("purgeClientStep", jobRepository)
                .tasklet(new PurgeTasklet(
                        jdbcTemplate,
                        "sss_cdr_snapshot_client_stat",
                        PurgeTasklet.PurgeMode.DELETE_CASCADE  // ← Supprime les enfants d'abord
                ), tx)
                .build();
    }




}
