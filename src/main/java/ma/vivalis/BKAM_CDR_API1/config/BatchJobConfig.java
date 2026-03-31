package ma.vivalis.BKAM_CDR_API1.config;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchJobConfig {
    @Bean
    public Job bkamCdrProcessingJob(
            JobRepository jobRepository,
            Flow phase1CompareFlow,
            Flow phase2XmlFlow,
            Flow phaseEnvoiAPIFlow,
            Flow phase3MappingFlow,
            Flow phase4ArchivageFlow,
            Flow phase5PurgeFlow) {

        return new JobBuilder("bkamCdrProcessingJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(phase1CompareFlow)     // Comparer les 3 entités en //
                .next(phase2XmlFlow)
                //.next(phaseEnvoiAPIFlow)  //phase d envoi d api
                .next(phase3MappingFlow)      // Mapper les 3 entités en //
                .next(phase4ArchivageFlow)        // Archiver les clients dans l archive  //
                //.next(phase5PurgeFlow)        // Purger les 3 snapshots en //


                //.start(phase1CompareFlow)     // Comparer les 3 entités en // 1h51m26s221ms
                //.start(phase2XmlFlow)  //2m28s144ms
                //.start(phase3MappingFlow)      // Mapper les 3 entités en //
                //.start(phase4ArchivageFlow)        // Archiver les clients dans l archive  //
                //.start(phase5PurgeFlow)        // Purger les 3 snapshots en //
                .end()
                .build();
    }
}
