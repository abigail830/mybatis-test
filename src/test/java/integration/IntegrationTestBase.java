package integration;

import com.github.abigail830.mybatictest.MybaticTestApplication;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


/**
 * Given it was in integration folder instead of com.github.abigail830.mybatistest,
 * When specify @SpringBootTest, we must specify the MybaticTestApplication.class
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MybaticTestApplication.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        MockitoTestExecutionListener.class})
public class IntegrationTestBase {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

}
