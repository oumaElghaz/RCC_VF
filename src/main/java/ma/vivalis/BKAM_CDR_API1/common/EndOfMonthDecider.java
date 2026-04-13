package ma.vivalis.BKAM_CDR_API1.common;

import ma.vivalis.BKAM_CDR_API1.common.repository.DecisionRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EndOfMonthDecider implements JobExecutionDecider {
    private final DecisionRepository decisionRepository;

    public EndOfMonthDecider(DecisionRepository decisionRepository) {
        this.decisionRepository = decisionRepository;
    }

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, @Nullable StepExecution stepExecution) {
        boolean isLastDayOfMonth = LocalDate.now().equals(LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()));
        boolean shouldExecute = Boolean.TRUE.equals(decisionRepository.shouldRunStep());

        return (shouldExecute && isLastDayOfMonth) ? new FlowExecutionStatus("EXECUTE") : new FlowExecutionStatus("SKIP");

    }
}
