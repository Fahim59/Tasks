package Framework.stepDef;

import context.TestContext;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.time.Duration;

public class DashboardDefinition extends BasePage{
    public WebDriver driver;
    private final DashboardPage dashboardPage;

    private static final Logger logger = LogManager.getLogger(DashboardDefinition.class);

    public DashboardDefinition(TestContext context) {
        driver = context.driver;
        dashboardPage = PageFactoryManager.getDashboardPage(context.driver);
    }

    @Then("I see the dashboard")
    public void iSeeTheDashboard() throws InterruptedException {
        dashboardPage.clickSkipBtn();
        dashboardPage.clickYesBtn();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        SmallWait(5000);
        logger.info("I see the dashboard");
    }
}
