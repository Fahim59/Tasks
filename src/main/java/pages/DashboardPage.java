package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private final By skipBtn = By.xpath("//a[normalize-space()='SKIP']");

    private final By yesBtn = By.xpath("//button[normalize-space()='Yes']");

    private final By dashboardMenu = By.xpath("(.//span[text()=' Dashboard '])[1]");

    private final By customerMenu = By.xpath("(.//span[text()=' Customers '])[1]");

    private final By productCatMenu = By.xpath("(.//span[text()=' Product Catalog '])[1]");

    private final By salesGuiMenu = By.xpath("(.//span[text()=' Sales Guidance '])[1]");

    public void clickSkipBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(skipBtn)).click();
    }

    public void clickYesBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(yesBtn)).click();
    }

    public void clickDashboardMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(dashboardMenu)).click();
    }

    public void clickCustomerMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(customerMenu)).click();
    }

    public void clickProductCatalogMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(productCatMenu)).click();
    }

    public void clickSalesGuidanceMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(salesGuiMenu)).click();
    }
}
