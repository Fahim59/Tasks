package pages;

import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import utils.ConfigLoader;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class BasePage {

    public static boolean launch = true;
    public static boolean quit = false;

    public static void Open_Website(String endPoint){
        DriverFactory.getDriver().get(new ConfigLoader().initializeProperty().getProperty("baseUrl") +endPoint);
    }

    public static void SmallWait(int second) throws InterruptedException {Thread.sleep(second);}

    public static void Upload_File(String xpath, String fileName) throws InterruptedException {
        Actions builder = new Actions(DriverFactory.getDriver());
        builder.moveToElement(DriverFactory.getDriver().findElement(By.xpath(xpath))).click().build().perform();
        SmallWait(1000);

        try {
            StringSelection filePath = new StringSelection("E:\\Intellij Files\\M2Sys\\Images\\" + fileName);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            SmallWait(1000);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
