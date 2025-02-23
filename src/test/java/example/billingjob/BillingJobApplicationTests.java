package example.billingjob;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@SpringBatchTest
@ExtendWith(OutputCaptureExtension.class)
class BillingJobApplicationTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp(){
        jobRepositoryTestUtils.removeJobExecutions();
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "BILLING_DATA");
    }

    @Test
    void testJobExecution() throws Exception {
        // given
        // given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("input.file", "input/billing-2023-01.csv")
                .addString("output.file", "staging/billing-report-2023-01.csv")
                .addJobParameter("data.year", 2023, Integer.class)
                .addJobParameter("data.month", 1, Integer.class)
                .toJobParameters();

        // when
        JobExecution jobExecution = this.jobLauncherTestUtils.launchJob(jobParameters);

        // then
        Assertions.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        assertTrue(Files.exists(Paths.get("staging", "billing-2023-01.csv")));
        Assertions.assertEquals(1000, JdbcTestUtils.countRowsInTable(jdbcTemplate,"BILLING_DATA"));

        Path billingReport = Paths.get("staging", "billing-report-2023-01.csv");
        assertTrue(Files.exists(billingReport));
        assertEquals(781, Files.lines(billingReport).count());
    }
}