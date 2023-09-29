package Framework.stepDef;

import constants.EndPoint;
import context.TestContext;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;

public class LoginDefinition extends BasePage{
    public WebDriver driver;
    private final LoginPage loginPage;

    private static final Logger logger = LogManager.getLogger(LoginDefinition.class);

    FileReader data;
    JSONObject loginDetails;

    public LoginDefinition(TestContext context) {
        driver = context.driver;
        loginPage = PageFactoryManager.getloginPage(context.driver);
    }

    @Given("I'm a user")
    public void iMAUser() {
        Open_Website(EndPoint.LOGIN.url);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        logger.info("Website launched");
    }

    @And("I'm on the login page")
    public void iMOnTheLoginPage() {
        Assert.assertEquals("Sign in to your CloudApper Account", loginPage.getLoginText());

        logger.info("I'm on the login page");
    }

    @When("I enter login details and click login button")
    public void iEnterLoginDetailsAndClickLoginButton() throws FileNotFoundException {

        try {
            String file = "src/main/resources/data.json";
            data = new FileReader(file);

            JSONTokener tokener = new JSONTokener(data);
            loginDetails = new JSONObject(tokener);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        loginPage.enterLoginDetails(loginDetails.getJSONObject("loginCredentials").getString("email"),
                loginDetails.getJSONObject("loginCredentials").getString("password"));

        loginPage.clickLoginBtn();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));

        logger.info("I enter login details and click login button");
    }
}
