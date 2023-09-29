package Framework.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                features = "src/test/resources/Framework",
                glue = {"Framework.stepDef", "Framework.hooks"},
                plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                        "rerun:target/Failed_Scenario/failedscenarios.txt"},
                snippets = CucumberOptions.SnippetType.CAMELCASE
        )
public class JUnitRunnerTest { }
