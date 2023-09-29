package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CustomerPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private final By addBtn = By.xpath("//span[normalize-space()='Add']");

    private final By customerType = By.xpath("//div[@class='dx-texteditor-container']//div[@class='dx-dropdowneditor-icon']");
    private final By employeeField = By.xpath("//div[contains(text(),'Select Sales Rep')]");
    private final By chooseEmp = By.xpath("(//input[@role='textbox'])[6]");

    private final By contactNameField = By.xpath("//input[@name='stringField5']");
    private final By numberField = By.xpath("//input[@id='phone']");
    private final By emailField = By.xpath("//input[@name='stringField6']");
    private final By customerNameField = By.xpath("//input[@name='stringField7']");

    private final By saveBtn = By.xpath("//span[normalize-space()='Save']");

    private final String cusTable = "/html/body/app-root/app-app-launch/dx-drawer/div/div[1]/div[2]/div[2]/div/div[1]/app-list-view/div[2]/app-data-grid/div[1]/dx-data-grid/div/div[6]/div[1]/div/div/div/table/tbody";
    private final By cusTableTr = By.xpath(cusTable+"/tr");

    public void clickAddBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }

    public CustomerPage selectCustomerType(){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement combo = wait.until(ExpectedConditions.visibilityOfElementLocated(customerType));

        combo.click();
        WebElement m = driver.findElement(By.xpath("//div[@class='dx-item-content dx-list-item-content'][normalize-space()='Re-Seller']"));
        js.executeScript("arguments[0].click();", m);

        return this;
    }

    public CustomerPage selectSalesRep(String salesRep) throws InterruptedException {
        WebElement selectBox = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeField));
        selectBox.click();

        WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(chooseEmp));
        textBox.sendKeys(salesRep);
        BasePage.SmallWait(1500);
        textBox.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        return this;
    }

    public CustomerPage enterContactName(String name){
        WebElement combo = wait.until(ExpectedConditions.visibilityOfElementLocated(contactNameField));
        String value = combo.getAttribute("value");

        if (value.isEmpty()) {
            combo.sendKeys(name);
        }
        else {
            combo.clear();
            combo.sendKeys(name);
        }

        return this;
    }

    public CustomerPage enterNumber(String mobile){
        WebElement combo = wait.until(ExpectedConditions.visibilityOfElementLocated(numberField));
        String value = combo.getAttribute("value");

        if (value.isEmpty()) {
            combo.sendKeys(mobile);
        }
        else {
            combo.clear();
            combo.sendKeys(mobile);
        }

        return this;
    }

    public CustomerPage enterEmail(String email){
        WebElement combo = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        String value = combo.getAttribute("value");

        if (value.isEmpty()) {
            combo.sendKeys(email);
        }
        else {
            combo.clear();
            combo.sendKeys(email);
        }

        return this;
    }

    public CustomerPage enterCustomerName(String name){
        WebElement combo = wait.until(ExpectedConditions.visibilityOfElementLocated(customerNameField));
        String value = combo.getAttribute("value");

        if (value.isEmpty()) {
            combo.sendKeys(name);
        }
        else {
            combo.clear();
            combo.sendKeys(name);
        }

        return this;
    }

    public CustomerPage setCustomerDetails(String salesRep, String contact, String mobile, String email, String name) throws InterruptedException {
        return selectCustomerType().selectSalesRep(salesRep).enterContactName(contact).enterNumber(mobile).enterEmail(email).enterCustomerName(name);
    }

    public void clickSaveBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public int getMemberTableTrSize() {
        List<WebElement> elements = driver.findElements(cusTableTr);
        return elements.size();
    }

    public CustomerPage checkCustomer(String name) {
        for(int l = 1; l <= getMemberTableTrSize(); l++){

            String cusName = driver.findElement(By.xpath(cusTable+"/tr["+l+"]/td[3]/div/span")).getText();

            if(cusName.equalsIgnoreCase(name)){
                System.out.println("Customer Added Successfully");

                break;
            }
        }

        return this;
    }
}
