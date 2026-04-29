package ma.vivalis.BKAM_CDR_API1.contrat_per.batch.stepConfig;

import generated.ComConPer;

import ma.vivalis.BKAM_CDR_API1.API.model.MyRequestBody;
import ma.vivalis.BKAM_CDR_API1.API.model.sss_cdr_api1;
import ma.vivalis.BKAM_CDR_API1.API.processor.MyRequestProcessor;
import ma.vivalis.BKAM_CDR_API1.API.writer.MyRequestWriter;
import ma.vivalis.BKAM_CDR_API1.common.PurgeTasklet;
import ma.vivalis.BKAM_CDR_API1.contrat_per.batch.processor.ContratPerArchProcessor;
import ma.vivalis.BKAM_CDR_API1.contrat_per.batch.processor.ContratPerCompare;
import ma.vivalis.BKAM_CDR_API1.contrat_per.batch.processor.ContratPerMappingProcessor;
import ma.vivalis.BKAM_CDR_API1.contrat_per.batch.processor.ContratPerTraitementMapping;
import ma.vivalis.BKAM_CDR_API1.contrat_per.batch.tasklet.XmlFooterTaskletContratPer;
import ma.vivalis.BKAM_CDR_API1.contrat_per.batch.tasklet.XmlHeaderTaskletContratPer;
import ma.vivalis.BKAM_CDR_API1.contrat_per.batch.writer.ContratPerAppendWriter;
import ma.vivalis.BKAM_CDR_API1.contrat_per.batch.writer.ContratPerArchWriter;
import ma.vivalis.BKAM_CDR_API1.contrat_per.batch.writer.ContratPerCompareWriter;
import ma.vivalis.BKAM_CDR_API1.contrat_per.batch.writer.ContratPerMappingWriter;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_arch_contrat_per;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_contrat_per;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_inter_contrat_per;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_contrat_per;
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
public class ContratPerStepConfig {
    // ═══════════════════════════════════════════════════════
    // STEP 1 : Comparaison contrat per
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step compareContratPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_snapshot_contrat_per> ContratPerSnapshotReader,
            ContratPerCompare contratPerCompare,
            ContratPerCompareWriter contratPerCompareWriter
    ) {



        return new StepBuilder("compareContratPerStep", jobRepository)
                .<sss_cdr_snapshot_contrat_per, sss_cdr_inter_contrat_per>chunk(500)
                .transactionManager(tx)
                .reader(ContratPerSnapshotReader)
                .processor(contratPerCompare)
                .writer(contratPerCompareWriter)
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
    public Step headerXmlContratPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            XmlHeaderTaskletContratPer xmlHeaderTaskletContratPer) {

        return new StepBuilder("headerXmlContratPerStep", jobRepository)
                .tasklet(xmlHeaderTaskletContratPer, tx)
                .build();
    }
//

    // ═══════════════════════════════════════════════════════
// STEP 2b : Écrire les ComConPer (contenu)
// ═══════════════════════════════════════════════════════
    @Bean
    public Step contentXmlContratPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_contrat_per> ContratPerIntermediaireReader,
            ContratPerTraitementMapping contratPerTraitementMapping,
            ContratPerAppendWriter contratPerAppendWriter) {

        return new StepBuilder("contentXmlContratPerStep", jobRepository)
                .<sss_cdr_inter_contrat_per, ComConPer>chunk(200).transactionManager(tx)
                .reader(ContratPerIntermediaireReader)
                .processor(contratPerTraitementMapping)
                .writer(contratPerAppendWriter)
                .build();
    }
    // ════════════════════════════════════════���══════════════
    // STEP 2c : Écrire le footer XML
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step footerXmlContratPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            XmlFooterTaskletContratPer xmlFooterTaskletContratPer) {

        return new StepBuilder("footerXmlContratPerStep", jobRepository)
                .tasklet(xmlFooterTaskletContratPer, tx)
                .build();
    }
    //

    // ═══════════════════════════════════════════════════════
    // STEP  : Envoi Api
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step envoiApiContratPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            @Qualifier("readerContratPer") ItemReader<MyRequestBody> readerContratPer,
            MyRequestProcessor myRequestProcessor,
            MyRequestWriter myRequestWriter


    ) {


        return new StepBuilder("envoiApiContratPerStep", jobRepository)
                .<MyRequestBody, sss_cdr_api1>chunk(2)
                .transactionManager(tx)
                .reader(readerContratPer)
                .processor(myRequestProcessor)
                .writer(myRequestWriter)
                .build();
    }
    // ═══════════════════════════════════════════════════════
    // STEP 3 : Mapping contrat
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step mappingContratPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_contrat_per> ContratPerIntermediaireReader,
            ContratPerMappingProcessor contratPerMappingProcessor,
            ContratPerMappingWriter contratPerMappingWriter
    ) {


        return new StepBuilder("mappingContratPerStep", jobRepository)
                .<sss_cdr_inter_contrat_per, sss_cdr_contrat_per>chunk(500)
                .transactionManager(tx)
                .reader(ContratPerIntermediaireReader)
                .processor(contratPerMappingProcessor)
                .writer(contratPerMappingWriter)
                .build();
    }



    // ═══════════════════════════════════════════════════════
    // STEP 4 : Archivage contrat
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step archiverContratPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_contrat_per> ContratPerIntermediaireReader,
            ContratPerArchProcessor contratPerArchProcessor,
            ContratPerArchWriter contratPerArchWriter
    ) {

        return new StepBuilder("archiverContratPerStep", jobRepository)
                .<sss_cdr_inter_contrat_per, sss_cdr_arch_contrat_per>chunk(200)
                .transactionManager(tx)
                .reader(ContratPerIntermediaireReader)
                .processor(contratPerArchProcessor)
                .writer(contratPerArchWriter)
                .faultTolerant()
                .skipLimit(100)
                .skip(Exception.class)
                .build();
    }



    // ═══════════════════════════════════════════════════════
// Mode DELETE_CASCADE — Si vous avez des FK
// AVANT de supprimer le contrat snapshot
// ═══════════════════════════════════════════════════════
    @Bean
    public Step purgeContratPerStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JdbcTemplate jdbcTemplate) {

        return new StepBuilder("purgeContratPerStep", jobRepository)
                .tasklet(new PurgeTasklet(
                        jdbcTemplate,
                        "sss_cdr_snapshot_contrat_per",
                        PurgeTasklet.PurgeMode.DELETE_CASCADE  // ← Supprime les enfants d'abord
                ), tx)
                .build();
    }

}

