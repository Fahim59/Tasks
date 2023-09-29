package Framework.stepDef;

import com.github.javafaker.Faker;
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

public class CustomerDefinition extends BasePage{
    public WebDriver driver;
    private final DashboardPage dashboardPage;
    private final CustomerPage customerPage;

    private static final Logger logger = LogManager.getLogger(DashboardDefinition.class);

    String firstName, fullName;

    FileReader data;
    JSONObject customerDetails;

    public CustomerDefinition(TestContext context) {
        driver = context.driver;
        dashboardPage = PageFactoryManager.getDashboardPage(context.driver);
        customerPage = PageFactoryManager.getCustomerPage(driver);
    }

    @Given("I'm a customer")
    public void iMACustomer() {
        dashboardPage.clickCustomerMenu();

        logger.info("I click on customer menu");
    }

    @And("I click customer add button")
    public void iClickCustomerAddButton() throws InterruptedException {
        SmallWait(5000);

        customerPage.clickAddBtn();

        logger.info("I click customer add button");
    }

    @When("I enter customer details and click save button")
    public void iEnterCustomerDetailsAndClickSaveButton() throws InterruptedException, FileNotFoundException {
        SmallWait(2000);

        try {
            String file = "src/main/resources/data.json";
            data = new FileReader(file);

            JSONTokener tokener = new JSONTokener(data);
            customerDetails = new JSONObject(tokener);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        firstName = new Faker().name().firstName();
        fullName = new Faker().name().fullName();

        customerPage.setCustomerDetails(customerDetails.getJSONObject("customerInfo").getString("salesRep"), firstName,
                customerDetails.getJSONObject("customerInfo").getString("mobile"),firstName.toLowerCase()+"@gmail.com", fullName);

        customerPage.clickSaveBtn();

        logger.info("I enter customer details and click save button");
    }

    @Then("I see the new added customer on customer table")
    public void iSeeTheNewAddedCustomerOnCustomerTable() throws InterruptedException {
        SmallWait(5000);
        customerPage.checkCustomer(fullName);

        SmallWait(2000);
        dashboardPage.clickDashboardMenu();

        quit = true;
        logger.info("I see the new added customer on customer table");
    }
}
