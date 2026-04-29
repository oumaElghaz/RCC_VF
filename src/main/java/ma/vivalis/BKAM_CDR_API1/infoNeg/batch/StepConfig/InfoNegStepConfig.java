package ma.vivalis.BKAM_CDR_API1.infoNeg.batch.StepConfig;



import generated.ComInfNeg;
import ma.vivalis.BKAM_CDR_API1.API.model.MyRequestBody;
import ma.vivalis.BKAM_CDR_API1.API.model.sss_cdr_api1;
import ma.vivalis.BKAM_CDR_API1.API.processor.MyRequestProcessor;
import ma.vivalis.BKAM_CDR_API1.API.writer.MyRequestWriter;
import ma.vivalis.BKAM_CDR_API1.client.batch.processor.ClientArchProcessor;
import ma.vivalis.BKAM_CDR_API1.client.batch.processor.ClientMappingProcessor;
import ma.vivalis.BKAM_CDR_API1.client.batch.writer.ClientArchWriter;
import ma.vivalis.BKAM_CDR_API1.client.batch.writer.ClientMappingWriter;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_arch_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_inter_client_stat;
import ma.vivalis.BKAM_CDR_API1.common.PurgeTasklet;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_infoNega_stat;
import ma.vivalis.BKAM_CDR_API1.infoNeg.batch.processor.InfoNegaArchProcessor;
import ma.vivalis.BKAM_CDR_API1.infoNeg.batch.processor.InfoNegaCompareProcessor;
import ma.vivalis.BKAM_CDR_API1.infoNeg.batch.processor.InfoNegaMappingProcessor;
import ma.vivalis.BKAM_CDR_API1.infoNeg.batch.processor.InfoNegaTraitementMappingProcessor;
import ma.vivalis.BKAM_CDR_API1.infoNeg.batch.tasklet.XmlFooterTaskletInfNeg;
import ma.vivalis.BKAM_CDR_API1.infoNeg.batch.tasklet.XmlHeaderTaskletInfNeg;
import ma.vivalis.BKAM_CDR_API1.infoNeg.batch.writer.InfoNegaArchWriter;
import ma.vivalis.BKAM_CDR_API1.infoNeg.batch.writer.InfoNegaCompareWriter;
import ma.vivalis.BKAM_CDR_API1.infoNeg.batch.writer.InfoNegaMappingWriter;
import ma.vivalis.BKAM_CDR_API1.infoNeg.batch.writer.InfoNegaXmlAppendWriter;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_arch_infoNegative;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_infoNegative;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_inter_infoNegative;
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

import java.util.List;

@Configuration
public class InfoNegStepConfig {
    // ═══════════════════════════════════════════════════════
    // STEP 1 : Comparaison info
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step compareInfoNegaStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_snapshot_infoNega_stat> infoNegaSnapshotReader,
            InfoNegaCompareProcessor infoNegaCompareProcessor,
            InfoNegaCompareWriter infoNegaCompareWriter
    ) {



        return new StepBuilder("compareInfoNegaStep", jobRepository)
                .<sss_cdr_snapshot_infoNega_stat, sss_cdr_inter_infoNegative>chunk(500)
                .transactionManager(tx)
                .reader(infoNegaSnapshotReader)
                .processor(infoNegaCompareProcessor)
                .writer(infoNegaCompareWriter)
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
    public Step headerXmlInfoNegaStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            XmlHeaderTaskletInfNeg xmlHeaderTasklet) {

        return new StepBuilder("headerXmlInfoNegaStep", jobRepository)
                .tasklet(xmlHeaderTasklet, tx)
                .build();
    }
//


    // ═══════════════════════════════════════════════════════
// STEP 2b : Écrire les comInfNeg  (contenu)
// ═══════════════════════════════════════════════════════
    @Bean
    public Step contentXmlInfoStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_infoNegative> infoNegaIntermediaireReader,
            InfoNegaTraitementMappingProcessor infoNegaTraitementMappingProcessor,
            InfoNegaXmlAppendWriter infoNegaXmlAppendWriter) {

        return new StepBuilder("contentXmlInfoStep", jobRepository)
                .<sss_cdr_inter_infoNegative, List<ComInfNeg>>chunk(200).transactionManager(tx)
                .reader(infoNegaIntermediaireReader)
                .processor(infoNegaTraitementMappingProcessor)
                .writer(infoNegaXmlAppendWriter)
                // ⚠️ Pas de taskExecutor (écriture séquentielle dans le fichier)
                .build();
    }
    // ════════════════════════════════════════���══════════════
    // STEP 2c : Écrire le footer XML
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step footerXmlInfoStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            XmlFooterTaskletInfNeg xmlFooterTasklet) {

        return new StepBuilder("footerXmlInfoStep", jobRepository)
                .tasklet(xmlFooterTasklet, tx)
                .build();
    }
    //
    // ═══════════════════════════════════════════════════════
    // STEP  : Envoi Api
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step envoiApiInfoNegaStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            @Qualifier("readerInfoNega") ItemReader<MyRequestBody> readerInfoNega,
            MyRequestProcessor myRequestProcessor,
            MyRequestWriter myRequestWriter


    ) {


        return new StepBuilder("envoiApiInfoNegaStep", jobRepository)
                .<MyRequestBody, sss_cdr_api1>chunk(2)
                .transactionManager(tx)
                .reader(readerInfoNega)
                .processor(myRequestProcessor)
                .writer(myRequestWriter)
                .build();
    }

    //
    // ═══════════════════════════════════════════════════════
    // STEP 3 : Mapping info
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step mappingInfoNegaStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_infoNegative> infoNegaIntermediaireReader,
            InfoNegaMappingProcessor infoNegaMappingProcessor,
            InfoNegaMappingWriter infoNegaMappingWriter
    ) {



        return new StepBuilder("mappingInfoNegaStep", jobRepository)
                .<sss_cdr_inter_infoNegative, sss_cdr_infoNegative>chunk(500)
                .transactionManager(tx)
                .reader(infoNegaIntermediaireReader)
                .processor(infoNegaMappingProcessor)
                .writer(infoNegaMappingWriter)
                .build();
    }



    // ═══════════════════════════════════════════════════════
    // STEP 4 : Archivage info
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step archiverInfoStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_infoNegative> infoNegaIntermediaireReader,
            InfoNegaArchProcessor infoNegaArchProcessor,
            InfoNegaArchWriter infoNegaArchWriter
    ) {




        return new StepBuilder("archiverInfoStep", jobRepository)
                .<sss_cdr_inter_infoNegative, sss_cdr_arch_infoNegative>chunk(200)
                .transactionManager(tx)
                .reader(infoNegaIntermediaireReader)
                .processor(infoNegaArchProcessor)
                .writer(infoNegaArchWriter)
                //.taskExecutor(batchTaskExecutor)
                .faultTolerant()
                .skipLimit(100)
                .skip(Exception.class)
                .build();
    }
    //

    // ═══════════════════════════════════════════════════════
    // Mode DELETE_CASCADE
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step purgeInfoStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JdbcTemplate jdbcTemplate) {

        return new StepBuilder("purgeInfoStep", jobRepository)
                .tasklet(new PurgeTasklet(
                        jdbcTemplate,
                        "sss_cdr_snapshot_infoNega_stat",
                        PurgeTasklet.PurgeMode.DELETE_CASCADE  // ← Supprime les enfants d'abord
                ), tx)
                .build();
    }


}
