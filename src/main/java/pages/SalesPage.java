package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SalesPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SalesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private final By addBtn = By.xpath("//span[normalize-space()='Add']");

    private final By titleField = By.xpath("//input[@name='stringField1']");

    private final By dateField = By.xpath("//input[@aria-haspopup='true']");

    private final By messageField = By.xpath("(.//*[@class='ql-editor ql-blank dx-htmleditor-content'])[1]");

    private final By iconField = By.xpath("(//i[@class='fa fa-plus fa-plus-custom'])[2]");

    private final By saveBtn = By.xpath("//span[normalize-space()='Save']");

    private final String salesTable = "/html/body/app-root/app-app-launch/dx-drawer/div/div[1]/div[2]/div[2]/div/div[1]/app-list-view/div[2]/app-data-grid/div[1]/dx-data-grid/div/div[6]/div[1]/div/div/div/table/tbody";
    private final By salesTableTr = By.xpath(salesTable+"/tr");

    public void clickAddBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }

    public SalesPage enterTitle(String title){
        WebElement combo = wait.until(ExpectedConditions.visibilityOfElementLocated(titleField));
        String value = combo.getAttribute("value");

        if (value.isEmpty()) {
            combo.sendKeys(title);
        }
        else {
            combo.clear();
            combo.sendKeys(title);
        }

        return this;
    }

    public SalesPage enterDate(String date){
        WebElement combo = wait.until(ExpectedConditions.visibilityOfElementLocated(dateField));
        String value = combo.getAttribute("value");

        if (value.isEmpty()) {
            combo.sendKeys(date);
        }
        else {
            combo.clear();
            combo.sendKeys(date);
        }

        return this;
    }

    public SalesPage enterMessage(String message){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(messageField));

        element.sendKeys(Keys.TAB, Keys.BACK_SPACE);
        element.sendKeys(message);

        return this;
    }

    public void clickIconBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(iconField)).click();
    }

    public SalesPage setSalesDetails(String title, String date, String message) {
        return enterTitle(title).enterDate(date).enterMessage(message);
    }

    public void clickSaveBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public int getProductTableTrSize() {
        List<WebElement> elements = driver.findElements(salesTableTr);
        return elements.size();
    }

    public SalesPage checkSales(String title) {
        for(int l = 1; l <= getProductTableTrSize(); l++){

            String cusName = driver.findElement(By.xpath(salesTable+"/tr["+l+"]/td[3]/div/span")).getText();

            if(cusName.equalsIgnoreCase(title)){
                System.out.println("Sales Details Added Successfully");

                break;
            }
        }

        return this;
    }
}
