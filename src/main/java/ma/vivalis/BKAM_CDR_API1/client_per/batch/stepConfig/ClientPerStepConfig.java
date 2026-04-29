package ma.vivalis.BKAM_CDR_API1.client_per.batch.stepConfig;

import generated.ComEntPer;
import ma.vivalis.BKAM_CDR_API1.API.model.MyRequestBody;
import ma.vivalis.BKAM_CDR_API1.API.model.sss_cdr_api1;
import ma.vivalis.BKAM_CDR_API1.API.processor.MyRequestProcessor;
import ma.vivalis.BKAM_CDR_API1.API.writer.MyRequestWriter;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.processor.ClientPerArchProcessor;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.processor.ClientPerCompare;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.processor.ClientPerMappingProcessor;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.processor.ClientPerTraitementMapping;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.tasklet.XmlFooterTaskletClientPer;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.tasklet.XmlHeaderTaskletClientPer;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.writer.ClientPerAppendWriter;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.writer.ClientPerArchWriter;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.writer.ClientPerCompareWriter;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.writer.ClientPerMappingWriter;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_arch_client_per;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_client_per;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_inter_client_per;
import ma.vivalis.BKAM_CDR_API1.common.PurgeTasklet;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_client_per;


import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class ClientPerStepConfig {


    // ═══════════════════════════════════════════════════════
    // STEP 1 : Comparaison client per
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step compareClientPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_snapshot_client_per> ClientPerSnapshotReader,
            ClientPerCompare clientPerCompare,
            ClientPerCompareWriter clientPerCompareWriter
    ) {



        return new StepBuilder("compareClientPerStep", jobRepository)
                .<sss_cdr_snapshot_client_per, sss_cdr_inter_client_per>chunk(500)
                .transactionManager(tx)
                .reader(ClientPerSnapshotReader)
                .processor(clientPerCompare)
                .writer(clientPerCompareWriter)
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
    public Step headerXmlClientPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            XmlHeaderTaskletClientPer xmlHeaderTaskletClientPer) {

        return new StepBuilder("headerXmlClientPerStep", jobRepository)
                .tasklet(xmlHeaderTaskletClientPer, tx)
                .build();
    }
//


    // ═══════════════════════════════════════════════════════
// STEP 2b : Écrire les ComEntPer (contenu)
// ═══════════════════════════════════════════════════════
    @Bean
    public Step contentXmlClientPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_client_per> ClientPerIntermediaireReader,
            ClientPerTraitementMapping clientPerTraitementMapping,
            ClientPerAppendWriter clientPerAppendWriter) {

        return new StepBuilder("contentXmlClientPerStep", jobRepository)
                .<sss_cdr_inter_client_per, ComEntPer>chunk(200).transactionManager(tx)
                .reader(ClientPerIntermediaireReader)
                .processor(clientPerTraitementMapping)
                .writer(clientPerAppendWriter)
                .build();
    }
    // ════════════════════════════════════════���══════════════
    // STEP 2c : Écrire le footer XML
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step footerXmlClientPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            XmlFooterTaskletClientPer xmlFooterTaskletClientPer) {

        return new StepBuilder("footerXmlClientPerStep", jobRepository)
                .tasklet(xmlFooterTaskletClientPer, tx)
                .build();
    }
    //


    // ═══════════════════════════════════════════════════════
    // STEP  : Envoi Api
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step envoiApiClientPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            @Qualifier("readerClientPer") ItemReader<MyRequestBody> readerClientPer,
            MyRequestProcessor myRequestProcessor,
            MyRequestWriter myRequestWriter


    ) {


        return new StepBuilder("envoiApiClientPerStep", jobRepository)
                .<MyRequestBody, sss_cdr_api1>chunk(2)
                .transactionManager(tx)
                .reader(readerClientPer)
                .processor(myRequestProcessor)
                .writer(myRequestWriter)
                .build();
    }

    // ═══════════════════════════════════════════════════════
    // STEP 3 : Mapping client
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step mappingClientPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_client_per> ClientPerIntermediaireReader,
            ClientPerMappingProcessor clientPerMappingProcessor,
            ClientPerMappingWriter clientPerMappingWriter
    ) {


        return new StepBuilder("mappingClientPerStep", jobRepository)
                .<sss_cdr_inter_client_per, sss_cdr_client_per>chunk(500)
                .transactionManager(tx)
                .reader(ClientPerIntermediaireReader)
                .processor(clientPerMappingProcessor)
                .writer(clientPerMappingWriter)
                .build();
    }



    // ═══════════════════════════════════════════════════════
    // STEP 4 : Archivage client
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step archiverClientPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_client_per> clientPerIntermediaireReader,
            ClientPerArchProcessor clientPerArchProcessor,
            ClientPerArchWriter clientPerArchWriter
    ) {

        return new StepBuilder("archiverClientPerStep", jobRepository)
                .<sss_cdr_inter_client_per, sss_cdr_arch_client_per>chunk(200)
                .transactionManager(tx)
                .reader(clientPerIntermediaireReader)
                .processor(clientPerArchProcessor)
                .writer(clientPerArchWriter)
                .faultTolerant()
                .skipLimit(100)
                .skip(Exception.class)
                .build();
    }



    // ═══════════════════════════════════════════════════════
// Mode DELETE_CASCADE — Si vous avez des FK
// AVANT de supprimer le client snapshot
// ═══════════════════════════════════════════════════════
    @Bean
    public Step purgeClientPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JdbcTemplate jdbcTemplate) {

        return new StepBuilder("purgeClientPerStep", jobRepository)
                .tasklet(new PurgeTasklet(
                        jdbcTemplate,
                        "sss_cdr_snapshot_client_per",
                        PurgeTasklet.PurgeMode.DELETE_CASCADE  // ← Supprime les enfants d'abord
                ), tx)
                .build();
    }

}
