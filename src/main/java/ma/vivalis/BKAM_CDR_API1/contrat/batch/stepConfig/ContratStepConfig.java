package ma.vivalis.BKAM_CDR_API1.contrat.batch.stepConfig;

import generated.ComCon;
import ma.vivalis.BKAM_CDR_API1.contrat.batch.processor.ContratCompareProcessor;
import ma.vivalis.BKAM_CDR_API1.contrat.batch.processor.ContratTraitementMappingProcessor;
import ma.vivalis.BKAM_CDR_API1.contrat.batch.tasklet.XmlFooterTaskletContrat;
import ma.vivalis.BKAM_CDR_API1.contrat.batch.tasklet.XmlHeaderTaskletContrat;
import ma.vivalis.BKAM_CDR_API1.contrat.batch.writer.ContratCompareWriter;
import ma.vivalis.BKAM_CDR_API1.contrat.batch.writer.ContratXmlAppendWriter;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_inter_contrat_stat;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_contrat_stat;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ContratStepConfig {
    // ═══════════════════════════════════════════════════════
    // STEP 1 : Comparaison contrat
    // ═══════════════════════════════════════════════════════
    @Bean
    public Step compareContratStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_snapshot_contrat_stat> contratSnapshotItemReader,
            ContratCompareProcessor contratCompareProcessor,
            ContratCompareWriter contratCompareWriter
    ) {


        return new StepBuilder("compareContratStep", jobRepository)
                .<sss_cdr_snapshot_contrat_stat, sss_cdr_inter_contrat_stat>chunk(500)
                .transactionManager(tx)
                .reader(contratSnapshotItemReader)
                .processor(contratCompareProcessor)
                .writer(contratCompareWriter)
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
    public Step headerXmlContratStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            XmlHeaderTaskletContrat xmlHeaderTaskletContrat) {

        return new StepBuilder("headerXmlContratStep", jobRepository)
                .tasklet(xmlHeaderTaskletContrat, tx)
                .build();
    }

    // ═══════════════════════════════════════════════════════
// STEP 2b : Écrire les DonneesEnt (contenu)
// ═══════════════════════════════════════════════════════
    @Bean
    public Step contentXmlContratStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            JpaPagingItemReader<sss_cdr_inter_contrat_stat> contratIntermediaireItemReader,
            ContratTraitementMappingProcessor contratTraitementMappingProcessor,
            ContratXmlAppendWriter contratXmlAppendWriter) {

        return new StepBuilder("contentXmlContratStep", jobRepository)
                .<sss_cdr_inter_contrat_stat, ComCon.Con>chunk(200).transactionManager(tx)
                .reader(contratIntermediaireItemReader)
                .processor(contratTraitementMappingProcessor)
                .writer(contratXmlAppendWriter)
                // ⚠️ Pas de taskExecutor (écriture séquentielle dans le fichier)
                .build();
    }

    // ════════════════════════════════════════���══════════════
// STEP 2c : Écrire le footer XML
// ═══════════════════════════════════════════════════════
    @Bean
    public Step footerXmlContratStep(
            JobRepository jobRepository,
            PlatformTransactionManager tx,
            XmlFooterTaskletContrat xmlFooterTaskletContrat) {

        return new StepBuilder("footerXmlContratStep", jobRepository)
                .tasklet(xmlFooterTaskletContrat, tx)
                .build();
    }


    //
    //
}
