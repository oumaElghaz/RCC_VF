package ma.vivalis.BKAM_CDR_API1;

import ma.vivalis.BKAM_CDR_API1.config.BatchFlowConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.FlowExecutor;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.step.Step;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BatchFlowConfigPhaseEnvoiApiFlowTest {

    @Mock
    private Step envoiApiClientStep;
    @Mock
    private Step envoiApiClientPerStep;
    @Mock
    private Step envoiApiContratStep;
    @Mock
    private Step envoiApiContratPerStep;
    @Mock
    private Step envoiApiGarantieStep;
    @Mock
    private Step envoiApiInfoNegaStep;

    @Mock
    private JobExecutionDecider myStepDecider;
    @Mock
    private JobExecutionDecider endOfMonthDecider;

    @Mock
    private FlowExecutor flowExecutor;

    @Test
    void shouldBuildPhaseEnvoiApiFlow() {
        Flow flow = buildFlow();

        assertThat(flow).isNotNull();
        assertThat(flow.getName()).isEqualTo("phase");
    }

    @Test
    void shouldExecuteAllApiStepsWhenBothDecidersReturnExecute() throws Exception {
        when(myStepDecider.decide(any(), any())).thenReturn(new FlowExecutionStatus("EXECUTE"));
        when(endOfMonthDecider.decide(any(), any())).thenReturn(new FlowExecutionStatus("EXECUTE"));
        when(flowExecutor.executeStep(any(Step.class))).thenReturn("COMPLETED");

        FlowExecution flowExecution = buildFlow().start(flowExecutor);

        ArgumentCaptor<Step> stepCaptor = ArgumentCaptor.forClass(Step.class);
        verify(flowExecutor, times(6)).executeStep(stepCaptor.capture());

        List<Step> executedSteps = stepCaptor.getAllValues();
        assertThat(executedSteps).containsExactlyInAnyOrder(
                envoiApiClientStep,
                envoiApiClientPerStep,
                envoiApiContratStep,
                envoiApiContratPerStep,
                envoiApiGarantieStep,
                envoiApiInfoNegaStep
        );
        assertThat(flowExecution.getStatus().getName()).isEqualTo("COMPLETED");
    }

    @Test
    void shouldSkipAllApiStepsWhenBothDecidersReturnSkip() throws Exception {
        when(myStepDecider.decide(any(), any())).thenReturn(new FlowExecutionStatus("SKIP"));
        when(endOfMonthDecider.decide(any(), any())).thenReturn(new FlowExecutionStatus("SKIP"));

        FlowExecution flowExecution = buildFlow().start(flowExecutor);

        verify(flowExecutor, never()).executeStep(any(Step.class));
        assertThat(flowExecution.getStatus().getName()).isEqualTo("COMPLETED");
    }

    private Flow buildFlow() {
        TaskExecutor taskExecutor = new SyncTaskExecutor();

        return new BatchFlowConfig().phaseEnvoiAPIFlow(
                envoiApiClientStep,
                envoiApiClientPerStep,
                envoiApiContratStep,
                envoiApiContratPerStep,
                envoiApiGarantieStep,
                envoiApiInfoNegaStep,
                myStepDecider,
                endOfMonthDecider,
                taskExecutor
        );
    }
}

