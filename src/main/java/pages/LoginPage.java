package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private final By loginText = By.xpath("//h4[normalize-space()='Sign in to your CloudApper Account']");

    private final By emailField = By.id("txtEmail");
    private final By passwordField = By.id("txtUserPassword");

    private final By loginBtn = By.id("btnLogin");

    public String getLoginText(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginText)).getText();
    }

    public LoginPage enterEmail(String email){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        element.clear();
        element.sendKeys(email);

        return this;
    }

    public LoginPage enterPassword(String password){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        element.clear();
        element.sendKeys(password);

        return this;
    }

    public void clickLoginBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public LoginPage enterLoginDetails(String email, String password){
        return enterEmail(email).enterPassword(password);
    }
}
