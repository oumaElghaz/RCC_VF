package ma.vivalis.BKAM_CDR_API1;

import ma.vivalis.BKAM_CDR_API1.common.MyStepDecider;
import ma.vivalis.BKAM_CDR_API1.common.repository.DecisionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = BatchDeciderSpringContextTest.TestConfig.class)
class BatchDeciderSpringContextTest {

    @Configuration
    @ComponentScan(basePackageClasses = MyStepDecider.class)
    static class TestConfig {
    }

    @Autowired
    private MyStepDecider myStepDecider;

    @MockitoBean
    private DecisionRepository decisionRepository;

    @Test
    void shouldReturnExecuteWithSpringContextWhenRepositoryReturnsTrue() {
        when(decisionRepository.shouldRunStep()).thenReturn(true);

        FlowExecutionStatus status = myStepDecider.decide(null, null);

        assertThat(status.getName()).isEqualTo("EXECUTE");
    }

    @Test
    void shouldReturnSkipWithSpringContextWhenRepositoryReturnsFalse() {
        when(decisionRepository.shouldRunStep()).thenReturn(false);

        FlowExecutionStatus status = myStepDecider.decide(null, null);

        assertThat(status.getName()).isEqualTo("SKIP");
    }
}

