package context;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestContext {
    public WebDriver driver = DriverFactory.getDriver();
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
}
