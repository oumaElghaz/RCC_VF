package ma.vivalis.BKAM_CDR_API1.garantie.batch.stepConfig;

import generated.ComGar;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.processor.ClientPerArchProcessor;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.processor.ClientPerMappingProcessor;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.writer.ClientPerArchWriter;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.writer.ClientPerMappingWriter;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_arch_client_per;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_client_per;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_inter_client_per;
import ma.vivalis.BKAM_CDR_API1.common.PurgeTasklet;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_garantie;
import ma.vivalis.BKAM_CDR_API1.garantie.batch.processor.GarantieArchProcessor;
import ma.vivalis.BKAM_CDR_API1.garantie.batch.processor.GarantieCompareProcessor;
import ma.vivalis.BKAM_CDR_API1.garantie.batch.processor.GarantieMappingProcessor;
import ma.vivalis.BKAM_CDR_API1.garantie.batch.processor.GarantieTraitementMapping;
import ma.vivalis.BKAM_CDR_API1.garantie.batch.tasklet.XmlFooterTaskletGar;
import ma.vivalis.BKAM_CDR_API1.garantie.batch.tasklet.XmlHeaderTaskletGar;
import ma.vivalis.BKAM_CDR_API1.garantie.batch.writer.GarantieAppendWriter;
import ma.vivalis.BKAM_CDR_API1.garantie.batch.writer.GarantieArchWriter;
import ma.vivalis.BKAM_CDR_API1.garantie.batch.writer.GarantieCompareWriter;
import ma.vivalis.BKAM_CDR_API1.garantie.batch.writer.GarantieMappingWriter;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_arch_garantie;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_garantie;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_inter_garantie;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
public class GarantieStepConfig {


    // ═══════════════════════════════════════════════════════
    // STEP 1 : Comparaison garantie
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step compareGarantieStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_snapshot_garantie> garantieSnapshotItemReader,
            GarantieCompareProcessor garantieCompareProcessor,
            GarantieCompareWriter garantieCompareWriter
    ) {



        return new StepBuilder("compareClientPerStep", jobRepository)
                .<sss_cdr_snapshot_garantie, sss_cdr_inter_garantie>chunk(500)
                .transactionManager(tx)
                .reader(garantieSnapshotItemReader)
                .processor(garantieCompareProcessor)
                .writer(garantieCompareWriter)
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
    public Step headerXmlGarantieStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            XmlHeaderTaskletGar xmlHeaderTaskletGar) {

        return new StepBuilder("headerXmlGarantieStep", jobRepository)
                .tasklet(xmlHeaderTaskletGar, tx)
                .build();
    }
//


    // ═══════════════════════════════════════════════════════
// STEP 2b : Écrire les ComGar(contenu)
// ═══════════════════════════════════════════════════════
    @Bean
    public Step contentXmlGarantieStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_garantie> garantieIntermediaireItemReader,
            GarantieTraitementMapping garantieTraitementMapping,
            GarantieAppendWriter garantieAppendWriter) {

        return new StepBuilder("contentXmlGarantieStep", jobRepository)
                .<sss_cdr_inter_garantie, ComGar>chunk(200).transactionManager(tx)
                .reader(garantieIntermediaireItemReader)
                .processor(garantieTraitementMapping)
                .writer(garantieAppendWriter)
                .build();
    }


    // ════════════════════════════════════════���══════════════
    // STEP 2c : Écrire le footer XML
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step footerXmlGarantieStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            XmlFooterTaskletGar xmlFooterTaskletGar) {

        return new StepBuilder("footerXmlGarantieStep", jobRepository)
                .tasklet(xmlFooterTaskletGar, tx)
                .build();
    }
    //
    // ═══════════════════════════════════════════════════════
    // STEP 3 : Mapping garantie
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step mappingGarantieStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_garantie> garantieIntermediaireItemReader,
            GarantieMappingProcessor garantieMappingProcessor,
            GarantieMappingWriter garantieMappingWriter
    ) {


        return new StepBuilder("mappingGarantieStep", jobRepository)
                .<sss_cdr_inter_garantie, sss_cdr_garantie>chunk(500)
                .transactionManager(tx)
                .reader(garantieIntermediaireItemReader)
                .processor(garantieMappingProcessor)
                .writer(garantieMappingWriter)
                .build();
    }



    // ═══════════════════════════════════════════════════════
    // STEP 4 : Archivage garantie
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step archiverGarantieStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_garantie> garantieIntermediaireItemReader,
            GarantieArchProcessor garantieArchProcessor,
            GarantieArchWriter GarantieArchWriter
    ) {

        return new StepBuilder("archiverGarantieStep", jobRepository)
                .<sss_cdr_inter_garantie, sss_cdr_arch_garantie>chunk(200)
                .transactionManager(tx)
                .reader(garantieIntermediaireItemReader)
                .processor(garantieArchProcessor)
                .writer(GarantieArchWriter)
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
    public Step purgeGarantieStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JdbcTemplate jdbcTemplate) {

        return new StepBuilder("purgeGarantieStep", jobRepository)
                .tasklet(new PurgeTasklet(
                        jdbcTemplate,
                        "sss_cdr_snapshot_garantie",
                        PurgeTasklet.PurgeMode.DELETE_CASCADE  // ← Supprime les enfants d'abord
                ), tx)
                .build();
    }


}
