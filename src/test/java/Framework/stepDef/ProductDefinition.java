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

public class ProductDefinition extends BasePage{
    public WebDriver driver;
    private final DashboardPage dashboardPage;
    private final ProductPage productPage;

    private static final Logger logger = LogManager.getLogger(DashboardDefinition.class);

    String name;

    FileReader data;
    JSONObject productDetails;

    public ProductDefinition(TestContext context) {
        driver = context.driver;
        dashboardPage = PageFactoryManager.getDashboardPage(context.driver);
        productPage = PageFactoryManager.getProductPage(context.driver);
    }

    @Given("I'm on product page")
    public void iMOnProductPage() {
        dashboardPage.clickProductCatalogMenu();

        logger.info("I'm on product page");
    }

    @And("I click product add button")
    public void iClickProductAddButton() throws InterruptedException {
        SmallWait(5000);

        productPage.clickAddBtn();

        logger.info("I click customer add button");
    }

    @When("I enter product details and click save button")
    public void iEnterProductDetailsAndClickSaveButton() throws InterruptedException, FileNotFoundException {
        SmallWait(2000);

        try {
            String file = "src/main/resources/data.json";
            data = new FileReader(file);

            JSONTokener tokener = new JSONTokener(data);
            productDetails = new JSONObject(tokener);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        name = new Faker().food().ingredient() + " Chocolate";

        productPage.clickPicBtn();

        SmallWait(2000);
        Upload_File("//span[normalize-space()='Browse a file from your device']", "chocklet.jpg");

        SmallWait(2000);

        productPage.setProductDetails(name, productDetails.getJSONObject("productInfo").getString("description"),
                productDetails.getJSONObject("productInfo").getString("quantity"), productDetails.getJSONObject("productInfo").getString("price"));

        productPage.clickSaveBtn();

        logger.info("I enter product details and click save button");
    }

    @Then("I see the new added product on product table")
    public void iSeeTheNewAddedProductOnProductTable() throws InterruptedException {
        SmallWait(5000);
        productPage.checkCustomer(name);

        SmallWait(2000);
        dashboardPage.clickDashboardMenu();

        quit = true;
        logger.info("I see the new added product on product table");
    }
}
