package ma.vivalis.BKAM_CDR_API1.config;

import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.Step;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class BatchFlowConfig {


    // ═══════════════════════════════════════════════════════
    // PHASE 1 : Comparaison
    // ═══════════════════════════════════════════════════════
    @Bean
    public Flow phase1CompareFlow(
            Step compareClientStep,
            Step compareInfoNegaStep,
            Step compareClientPerStep,
            Step compareGarantieStep,
            Step compareContratPerStep,
            Step compareContratStep,


            TaskExecutor flowTaskExecutor) {


        return new FlowBuilder<SimpleFlow>("phase1")
                .split(flowTaskExecutor)

                .add(
                        new FlowBuilder<SimpleFlow>("compareClientFlow")
                                .start(compareClientStep).build(),
                        new FlowBuilder<SimpleFlow>("compareInfoNegaFlow")
                        .start(compareInfoNegaStep).build(),
                        new FlowBuilder<SimpleFlow>("compareClientPerFlow")
                                .start(compareClientPerStep).build(),
                        new FlowBuilder<SimpleFlow>("compareGarantieFlow")
                        .start(compareGarantieStep).build(),
                        new FlowBuilder<SimpleFlow>("compareContratPerFlow")
                                .start(compareContratPerStep).build(),
                        new FlowBuilder<SimpleFlow>("compareContratFlow")
                                .start(compareContratStep).build()
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

            Step headerXmlClientPerStep,
            Step contentXmlClientPerStep,
            Step footerXmlClientPerStep,

            Step headerXmlGarantieStep,
            Step contentXmlGarantieStep,
            Step footerXmlGarantieStep,


            Step headerXmlContratPerStep,
            Step contentXmlContratPerStep,
            Step footerXmlContratPerStep,

            Step headerXmlContratStep,
            Step contentXmlContratStep,
            Step footerXmlContratStep,

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

        Flow clientPerXmlFlow = new FlowBuilder<SimpleFlow>("clientPerXmlFlow")
        .start(headerXmlClientPerStep)
        .next(contentXmlClientPerStep)
        .next(footerXmlClientPerStep)
        .build();

        Flow garantieXmlFlow = new FlowBuilder<SimpleFlow>("garantieXmlFlow")
                .start(headerXmlGarantieStep)
                .next(contentXmlGarantieStep)
                .next(footerXmlGarantieStep)
                .build();

        Flow contratPerXmlFlow = new FlowBuilder<SimpleFlow>("contratPerXmlFlow")
                .start(headerXmlContratPerStep)
                .next(contentXmlContratPerStep)
                .next(footerXmlContratPerStep)
                .build();

        Flow contratXmlFlow = new FlowBuilder<SimpleFlow>("clientXmlFlow")
                .start(headerXmlContratStep)
                .next(contentXmlContratStep)
                .next(footerXmlContratStep)
                .build();

        return new FlowBuilder<SimpleFlow>("phase2")
                .split(flowTaskExecutor)
                .add(clientXmlFlow, infoNegaXmlFlow,clientPerXmlFlow,garantieXmlFlow,contratPerXmlFlow,contratXmlFlow)
                .build();
    }



    // ═══════════════════════════════════════════════════════
    // PHASE  : Envoi API
    // ═══════════════════════════════════════════════════════
    @Bean
    public Flow phaseEnvoiAPIFlow(
            Step envoiApiClientStep,
            Step envoiApiClientPerStep,
            Step envoiApiContratStep,
            Step envoiApiContratPerStep,
            Step envoiApiGarantieStep,
            Step envoiApiInfoNegaStep,
            JobExecutionDecider myStepDecider,
            JobExecutionDecider endOfMonthDecider,
            TaskExecutor flowTaskExecutor) {

        return new FlowBuilder<SimpleFlow>("phase")
                .split(flowTaskExecutor)
                .add(
                        new FlowBuilder<SimpleFlow>("FlowClient")
                                .start(myStepDecider) // Ajout du decider
                                .on("EXECUTE").to(envoiApiClientStep)
                                .from(myStepDecider).on("SKIP").end()
                                .build(),

                        new FlowBuilder<SimpleFlow>("FlowClientPer")
                                .start(endOfMonthDecider) // Ajout du decider
                                .on("EXECUTE").to(envoiApiClientPerStep)
                                .from(endOfMonthDecider).on("SKIP").end()
                                .build(),

                        new FlowBuilder<SimpleFlow>("FlowContrat")
                                .start(myStepDecider) // Ajout du decider
                                .on("EXECUTE").to(envoiApiContratStep)
                                .from(myStepDecider).on("SKIP").end()
                                .build(),

                        new FlowBuilder<SimpleFlow>("FlowContratPer")
                                .start(endOfMonthDecider) // Ajout du decider
                                .on("EXECUTE").to(envoiApiContratPerStep)
                                .from(endOfMonthDecider).on("SKIP").end()
                                .build(),

                        new FlowBuilder<SimpleFlow>("FlowGarantie")
                                .start(myStepDecider) // Ajout du decider
                                .on("EXECUTE").to(envoiApiGarantieStep)
                                .from(myStepDecider).on("SKIP").end()
                                .build(),

                        new FlowBuilder<SimpleFlow>("FlowInfosNega")
                                .start(myStepDecider) // Ajout du decider
                                .on("EXECUTE").to(envoiApiInfoNegaStep)
                                .from(myStepDecider).on("SKIP").end()
                                .build()
                )
                .build();
    }




    // ═══════════════════════════════════════════════════════
    // PHASE 3 : Mapping
    // ═══════════════════════════════════════════════════════
    @Bean
    public Flow phase3MappingFlow(
            Step mappingClientStep,
            Step mappingInfoNegaStep,
            Step mappingClientPerStep,
            Step mappingGarantieStep,
            Step mappingContratPerStep,
            Step mappingContratStep,
            JobExecutionDecider myStepDecider,
            JobExecutionDecider endOfMonthDecider,
            TaskExecutor flowTaskExecutor) {

        return new FlowBuilder<SimpleFlow>("phase3")
                .split(flowTaskExecutor)
                .add(
                        new FlowBuilder<SimpleFlow>("mappingClientFlow")
                                .start(myStepDecider) // Ajout du decider
                                .on("EXECUTE")
                                .to(mappingClientStep).from(myStepDecider).on("SKIP").end().build(),
                        new FlowBuilder<SimpleFlow>("mappingInfoNegaFlow")
                                .start(myStepDecider) // Ajout du decider
                                .on("EXECUTE")
                        .to(mappingInfoNegaStep).from(myStepDecider).on("SKIP").end().build(),
                        new FlowBuilder<SimpleFlow>("mappingClientPerFlow")
                                .start(endOfMonthDecider) // Ajout du decider
                                .on("EXECUTE")
                        .to(mappingClientPerStep).from(endOfMonthDecider).on("SKIP").end().build(),
                        new FlowBuilder<SimpleFlow>("mappingGarantieFlow")
                                .start(myStepDecider) // Ajout du decider
                                .on("EXECUTE")
                                .to(mappingGarantieStep).from(myStepDecider).on("SKIP").end().build(),
                        new FlowBuilder<SimpleFlow>("mappingContratPerFlow")
                                .start(endOfMonthDecider) // Ajout du decider
                                .on("EXECUTE")
                                .to(mappingContratPerStep).from(endOfMonthDecider).on("SKIP").end().build(),
                        new FlowBuilder<SimpleFlow>("mappingContratFlow")
                                .start(myStepDecider) // Ajout du decider
                                .on("EXECUTE")
                                .to(mappingContratStep).from(myStepDecider).on("SKIP").end().build()
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
            Step archiverClientPerStep,
            Step archiverGarantieStep,
            Step archiverContratPerStep,
            Step archiverContratStep,
            JobExecutionDecider myStepDecider,
            JobExecutionDecider endOfMonthDecider,
            TaskExecutor flowTaskExecutor) {


        return new FlowBuilder<SimpleFlow>("phase4")
                .split(flowTaskExecutor)

                .add(
                        new FlowBuilder<SimpleFlow>("archivClientFlow")
                                .start(myStepDecider) // Ajout du decider
                                .on("EXECUTE")
                                .to(archiverClientStep).from(myStepDecider).on("SKIP").end().build(),
                        new FlowBuilder<SimpleFlow>("archivInfoNegaFlow")
                                .start(myStepDecider) // Ajout du decider
                                .on("EXECUTE")
                        .to(archiverInfoStep).from(myStepDecider).on("SKIP").end().build(),
                        new FlowBuilder<SimpleFlow>("archiverClientPerFlow")
                                .start(endOfMonthDecider) // Ajout du decider
                                .on("EXECUTE")
                        .to(archiverClientPerStep).from(endOfMonthDecider).on("SKIP").end().build(),
                        new FlowBuilder<SimpleFlow>("archiverGarantieFlow")
                                .start(myStepDecider) // Ajout du decider
                                .on("EXECUTE")
                                .to(archiverGarantieStep).from(myStepDecider).on("SKIP").end().build(),
                        new FlowBuilder<SimpleFlow>("archiverContratPerFlow")
                                .start(endOfMonthDecider) // Ajout du decider
                                .on("EXECUTE")
                                .to(archiverContratPerStep).from(endOfMonthDecider).on("SKIP").end().build(),
                        new FlowBuilder<SimpleFlow>("archiverContratFlow")
                                .start(myStepDecider) // Ajout du decider
                                .on("EXECUTE")
                                .to(archiverContratStep).from(myStepDecider).on("SKIP").end().build()
                )
                .build();
    }


    // ═══════════════════════════════════════════════════════
    // PHASE 5 : Purge
    // ═══════════════════════════════════════════════════════
    @Bean
    public Flow phase5PurgeFlow(
            Step purgeClientStep,
            Step purgeInfoStep,
            Step purgeClientPerStep,
            Step purgeGarantieStep,
            Step purgeContratPerStep,
            Step purgeContratStep,
            TaskExecutor flowTaskExecutor) {

        return new FlowBuilder<SimpleFlow>("phase5")
                .split(flowTaskExecutor)
                .add(
                        new FlowBuilder<SimpleFlow>("purgeClientFlow")
                                .start(purgeClientStep).build(),
                        new FlowBuilder<SimpleFlow>("purgeInfoNegaFlow")
                        .start(purgeInfoStep).build(),
                        new FlowBuilder<SimpleFlow>("purgeClientPerFlow")
                        .start(purgeClientPerStep).build(),
                        new FlowBuilder<SimpleFlow>("purgeGarantieFlow")
                        .start(purgeGarantieStep).build(),
                        new FlowBuilder<SimpleFlow>("purgeContratPerFlow")
                        .start(purgeContratPerStep).build(),
                        new FlowBuilder<SimpleFlow>("purgeContratFlow")
                        .start(purgeContratStep).build()
                )
                .build();
    }




}