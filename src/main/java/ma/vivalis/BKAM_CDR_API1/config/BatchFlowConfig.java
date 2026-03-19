package ma.vivalis.BKAM_CDR_API1.config;

import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.Step;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class BatchFlowConfig {

    //  SUPPRIMÉ — défini dans ThreadPoolConfig uniquement
    // @Bean
    // public TaskExecutor flowTaskExecutor() { ... }

    // ═══════════════════════════════════════════════════════
    // PHASE 1 : Comparaison
    // ═══════════════════════════════════════════════════════
    @Bean
    public Flow phase1CompareFlow(
            Step compareClientStep,
            Step compareInfoNegaStep,
            //Step compareAndMapClientStep,
            //Step compareContratStep,
            //Step compareGarantieStep,
            TaskExecutor flowTaskExecutor) {
        //           ^^^^^^^^^^^^^^^^ ← Injecté depuis ThreadPoolConfig

        return new FlowBuilder<SimpleFlow>("phase1")
                .split(flowTaskExecutor)
                //      ^^^^^^^^^^^^^^^^ ← Paramètre, pas un appel de méthode
                .add(
                        new FlowBuilder<SimpleFlow>("compareClientFlow")
                                .start(compareClientStep).build(),
                        new FlowBuilder<SimpleFlow>("compareInfoNegaFlow")
                        .start(compareInfoNegaStep).build()//,
                        //new FlowBuilder<SimpleFlow>("compareGarantieFlow")
                        //.start(compareGarantieStep).build()
                )
                .build();
    }

    // ═══════════════════════════════════════════════════════
    // PHASE 2 : XML séparés — header + contenu + footer
    // ═══════════════════════════════════════════════════════
    @Bean
    public Flow phase2XmlFlow(
            Step headerXmlClientStep,
            Step contentXmlClientStep,
            Step footerXmlClientStep,
            Step headerXmlInfoNegaStep,
            Step contentXmlInfoStep,
            Step footerXmlInfoStep,
            //Step headerXmlGarantieStep,
            //Step contentXmlGarantieStep,
            //Step footerXmlGarantieStep,
            TaskExecutor flowTaskExecutor) {

        Flow clientXmlFlow = new FlowBuilder<SimpleFlow>("clientXmlFlow")
                .start(headerXmlClientStep)
                .next(contentXmlClientStep)
                .next(footerXmlClientStep)
                .build();

        Flow infoNegaXmlFlow = new FlowBuilder<SimpleFlow>("infoNegaXmlFlow")
        .start(headerXmlInfoNegaStep)
        .next(contentXmlInfoStep)
        .next(footerXmlInfoStep)
        .build();

        //Flow garantieXmlFlow = new FlowBuilder<SimpleFlow>("garantieXmlFlow")
        //.start(headerXmlGarantieStep)
        //.next(contentXmlGarantieStep)
        //.next(footerXmlGarantieStep)
        //.build();

        return new FlowBuilder<SimpleFlow>("phase2")
                .split(flowTaskExecutor)
                .add(clientXmlFlow, infoNegaXmlFlow)
                .build();
    }

    // ═══════════════════════════════════════════════════════
    // PHASE 3 : Mapping
    // ═══════════════════════════════════════════════════════
    @Bean
    public Flow phase3MappingFlow(
            Step mappingClientStep,
            Step mappingInfoNegaStep,
            //Step mappingContratStep,
            //Step mappingGarantieStep,
            TaskExecutor flowTaskExecutor) {

        return new FlowBuilder<SimpleFlow>("phase3")
                .split(flowTaskExecutor)
                .add(
                        new FlowBuilder<SimpleFlow>("mappingClientFlow")
                                .start(mappingClientStep).build(),
                        new FlowBuilder<SimpleFlow>("mappingInfoNegaFlow")
                        .start(mappingInfoNegaStep).build()//,
                        //new FlowBuilder<SimpleFlow>("mappingGarantieFlow")
                        //.start(mappingGarantieStep).build()
                )
                .build();
    }
    // ═══════════════════════════════════════════════════════
    // PHASE 4 : Archivage
    // ═══════════════════════════════════════════════════════
    @Bean
    public Flow phase4ArchivageFlow(
            Step archiverClientStep,
            Step archiverInfoStep,
            //Step compareContratStep,
            //Step compareGarantieStep,
            TaskExecutor flowTaskExecutor) {


        return new FlowBuilder<SimpleFlow>("phase4")
                .split(flowTaskExecutor)

                .add(
                        new FlowBuilder<SimpleFlow>("archivClientFlow")
                                .start(archiverClientStep).build(),
                        new FlowBuilder<SimpleFlow>("archivInfoNegaFlow")
                        .start(archiverInfoStep).build()//,
                        //new FlowBuilder<SimpleFlow>("compareGarantieFlow")
                        //.start(compareGarantieStep).build()
                )
                .build();
    }


    // ═══════════════════════════════════════════════════════
    // PHASE 5 : Purge
    // ═══════════════════════════════════════════════════════
    @Bean
    public Flow phase5PurgeFlow(
            Step purgeClientStep,
            //Step purgeInfoStep,
            //Step purgeGarantieStep,
            TaskExecutor flowTaskExecutor) {

        return new FlowBuilder<SimpleFlow>("phase5")
                .split(flowTaskExecutor)
                .add(
                        new FlowBuilder<SimpleFlow>("purgeClientFlow")
                                .start(purgeClientStep).build()//,
                        //new FlowBuilder<SimpleFlow>("purgeInfoNegaFlow")
                        //.start(purgeInfoStep).build()//,
                        //new FlowBuilder<SimpleFlow>("purgeGarantieFlow")
                        //.start(purgeGarantieStep).build()
                )
                .build();
    }




}