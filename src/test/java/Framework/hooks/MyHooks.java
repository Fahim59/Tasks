package Framework.hooks;

import factory.DriverFactory;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.ConfigLoader;

import java.io.File;
import java.io.IOException;

public class MyHooks extends BasePage {
    private static WebDriver driver;

    private static final Logger logger = LogManager.getLogger(MyHooks.class);

    public static void saveLogFile() {
        try {
            File logFile = new File("Log Result/test.log");
            File outputFile = new File("Log Result/test_output.txt");

            String logContents = FileUtils.readFileToString(logFile, "UTF-8");
            FileUtils.writeStringToFile(outputFile, logContents, "UTF-8");
        }
        catch (Exception e) {
            System.out.println("Log save failed" +e);
        }
    }

    @Before()
    public void launch_browser(Scenario scenario) {
        System.out.println("Before Thread ID: " +Thread.currentThread().getId() +","+ "Scenario Name: " +scenario.getName());

        if(launch){
            driver = DriverFactory.initializeDriver(System.getProperty("browser",
                    new ConfigLoader().initializeProperty().getProperty("browser")));

            launch = false;

            logger.info("Browser launched successfully");
        }
    }

    @AfterStep
    public static void Screenshoot(Scenario scenario) throws IOException {
        if(scenario.isFailed()){
            String screenshotName = scenario.getName().replaceAll(" ", "_");

            File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "github");

            File destinationPath = new File("target/Failed_Scenario/FailedScreenshots/" + screenshotName + ".jpg");
            FileUtils.copyFile(image, destinationPath);
        }
    }

    @After
    public void quitBrowser(Scenario scenario) {
        System.out.println("After Thread ID: " +Thread.currentThread().getId() +","+ "Scenario Name: " +scenario.getName());
        saveLogFile();

        if(quit){
            driver.quit();
            logger.info("Browser quit and Test completed successfully");
        }
    }
}
