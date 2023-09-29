package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private final By addBtn = By.xpath("//span[normalize-space()='Add']");

    private final By picBtn = By.xpath("//i[@class='fa fa-plus fa-plus-custom']");

    private final By productName = By.xpath("//input[@name='stringField4']");

    private final By productDescription = By.xpath("//textarea[@role='textbox']");

    private final By productCategory = By.xpath("//div[contains(text(),'Select Category')]");
    private final By chooseCategory = By.xpath("(//input[@role='textbox'])[4]");

    private final By productQuantity = By.xpath("(//input[@role='spinbutton'])[1]");

    private final By quantityUnit = By.xpath("//div[contains(text(),'Select Unit of Quantity')]");
    private final By chooseUnit = By.xpath("(//input[@role='textbox'])[5]");

    private final By priceField = By.xpath("(//input[@role='spinbutton'])[2]");

    private final By saveBtn = By.xpath("//span[normalize-space()='Save']");

    private final String productTable = "/html/body/app-root/app-app-launch/dx-drawer/div/div[1]/div[2]/div[2]/div/div[1]/app-list-view/div[2]/app-data-grid/div[1]/dx-data-grid/div/div[6]/div[1]/div/div/div/table/tbody";
    private final By proTableTr = By.xpath(productTable+"/tr");

    public void clickAddBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }

    public void clickPicBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(picBtn)).click();
    }

    public ProductPage enterProductName(String name){
        WebElement combo = wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
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

    public ProductPage enterProductDescription(String description){
        WebElement combo = wait.until(ExpectedConditions.visibilityOfElementLocated(productDescription));
        String value = combo.getAttribute("value");

        if (value.isEmpty()) {
            combo.sendKeys(description);
        }
        else {
            combo.clear();
            combo.sendKeys(description);
        }

        return this;
    }

    public ProductPage selectProductCategory() throws InterruptedException {
        WebElement selectBox = wait.until(ExpectedConditions.visibilityOfElementLocated(productCategory));
        selectBox.click();

        WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(chooseCategory));
        textBox.sendKeys("(SAMPLE) Chocolate");
        BasePage.SmallWait(1500);
        textBox.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        return this;
    }

    public ProductPage enterProductQuantity(String quantity){
        WebElement combo = wait.until(ExpectedConditions.visibilityOfElementLocated(productQuantity));
        String value = combo.getAttribute("value");

        if (value.isEmpty()) {
            combo.sendKeys(quantity);
        }
        else {
            combo.clear();
            combo.sendKeys(quantity);
        }

        return this;
    }

    public ProductPage enterQuantityUnit() throws InterruptedException {
        WebElement selectBox = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityUnit));
        selectBox.click();

        WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(chooseUnit));
        textBox.sendKeys("(SAMPLE) pcs");
        BasePage.SmallWait(1500);
        textBox.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

        return this;
    }

    public ProductPage enterProductPrice(String price){
        WebElement combo = wait.until(ExpectedConditions.visibilityOfElementLocated(priceField));
        String value = combo.getAttribute("value");

        if (value.isEmpty()) {
            combo.sendKeys(price);
        }
        else {
            combo.clear();
            combo.sendKeys(price);
        }

        return this;
    }

    public ProductPage setProductDetails(String name, String description, String quantity, String price) throws InterruptedException {
        return enterProductName(name).enterProductDescription(description).selectProductCategory().
                enterProductQuantity(quantity).enterQuantityUnit().enterProductPrice(price);
    }

    public void clickSaveBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public int getProductTableTrSize() {
        List<WebElement> elements = driver.findElements(proTableTr);
        return elements.size();
    }

    public ProductPage checkCustomer(String name) {
        for(int l = 1; l <= getProductTableTrSize(); l++){

            String cusName = driver.findElement(By.xpath(productTable+"/tr["+l+"]/td[3]/div/span")).getText();

            if(cusName.equalsIgnoreCase(name)){
                System.out.println("Product Added Successfully");

                break;
            }
        }

        return this;
    }
}
