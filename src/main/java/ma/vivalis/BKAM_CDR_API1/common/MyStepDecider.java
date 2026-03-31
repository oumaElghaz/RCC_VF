package ma.vivalis.BKAM_CDR_API1.common;

import ma.vivalis.BKAM_CDR_API1.common.repository.DecisionRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.stereotype.Component;

@Component
public class MyStepDecider implements JobExecutionDecider {
    private final DecisionRepository decisionRepository;

    public MyStepDecider(DecisionRepository decisionRepository) {
        this.decisionRepository = decisionRepository;
    }

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, @Nullable StepExecution stepExecution) {
        Boolean shouldExecute = decisionRepository.shouldRunStep(); // Adapter la logique
        return shouldExecute ? new FlowExecutionStatus("EXECUTE") : new FlowExecutionStatus("SKIP");
    }
}
