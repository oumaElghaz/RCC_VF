/*
package ma.vivalis.BKAM_CDR_API1;

import ma.vivalis.BKAM_CDR_API1.common.MyStepDecider;
import ma.vivalis.BKAM_CDR_API1.common.repository.DecisionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BatchDeciderTest {

    @Mock
    private DecisionRepository decisionRepository;

    @Mock
    private JobExecution jobExecution;

    @InjectMocks
    private MyStepDecider myStepDecider;

    @Test
    void shouldReturnExecuteWhenRepositorySaysTrue() {
        when(decisionRepository.shouldRunStep()).thenReturn(true);

        FlowExecutionStatus status = myStepDecider.decide(jobExecution, null);

        assertThat(status.getName()).isEqualTo("EXECUTE");
    }

    @Test
    void shouldReturnSkipWhenRepositorySaysFalse() {
        when(decisionRepository.shouldRunStep()).thenReturn(false);

        FlowExecutionStatus status = myStepDecider.decide(jobExecution, null);

        assertThat(status.getName()).isEqualTo("SKIP");
    }

    @Test
    void shouldReturnSkipWhenRepositoryReturnsNull() {
        when(decisionRepository.shouldRunStep()).thenReturn(null);

        FlowExecutionStatus status = myStepDecider.decide(jobExecution, null);

        assertThat(status.getName()).isEqualTo("SKIP");
    }
}*/
