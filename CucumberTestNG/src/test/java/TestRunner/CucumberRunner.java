package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
glue = {"StepDefinitions", "hooks"},
plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "rerun:target/failed.txt"}
 )

public class CucumberRunner extends AbstractTestNGCucumberTests {

}
