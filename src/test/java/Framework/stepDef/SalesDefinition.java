package Framework.stepDef;

import context.TestContext;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class SalesDefinition extends BasePage{
    public WebDriver driver;
    private final DashboardPage dashboardPage;
    private final SalesPage salesPage;

    private static final Logger logger = LogManager.getLogger(DashboardDefinition.class);

    FileReader data;
    JSONObject salesDetails;

    public SalesDefinition(TestContext context) {
        driver = context.driver;
        dashboardPage = PageFactoryManager.getDashboardPage(context.driver);
        salesPage = PageFactoryManager.getSalesPage(context.driver);
    }

    @Given("I'm on sales guidance page")
    public void iMOnSalesGuidancePage() {
        dashboardPage.clickSalesGuidanceMenu();

        logger.info("I'm on sales guidance page");
    }

    @And("I click sales add button")
    public void iClickSalesAddButton() throws InterruptedException {
        SmallWait(5000);

        salesPage.clickAddBtn();

        logger.info("I click sales add button");
    }

    @When("I enter sales details and click save button")
    public void iEnterSalesDetailsAndClickSaveButton() throws InterruptedException, FileNotFoundException {
        SmallWait(2000);

        try {
            String file = "src/main/resources/data.json";
            data = new FileReader(file);

            JSONTokener tokener = new JSONTokener(data);
            salesDetails = new JSONObject(tokener);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        salesPage.setSalesDetails(salesDetails.getJSONObject("salesInfo").getString("title"),
                salesDetails.getJSONObject("salesInfo").getString("date"),salesDetails.getJSONObject("salesInfo").getString("message"));

        salesPage.clickIconBtn();

        SmallWait(2000);
        BasePage.Upload_File("//span[normalize-space()='Browse a file from your device']", "icon.gif");

        SmallWait(4000);

        salesPage.clickSaveBtn();

        logger.info("I enter sales details and click save button");
    }

    @Then("I see the new sales details on sales table")
    public void iSeeTheNewSalesDetailsOnSalesTable() throws InterruptedException {
        SmallWait(5000);
        salesPage.checkSales("Test Title");

        SmallWait(2000);
        dashboardPage.clickDashboardMenu();

        quit = true;
        logger.info("I see the new sales details on sales table");
    }
}
