package ma.vivalis.BKAM_CDR_API1.client_per.batch.stepConfig;

import generated.ComEntPer;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.processor.ClientPerCompare;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.processor.ClientPerTraitementMapping;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.tasklet.XmlFooterTaskletClientPer;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.tasklet.XmlHeaderTaskletClientPer;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.writer.ClientPerAppendWriter;
import ma.vivalis.BKAM_CDR_API1.client_per.batch.writer.ClientPerCompareWriter;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_inter_client_per;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_client_per;


import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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



}
