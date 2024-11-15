package FailedTestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "@target/failed.txt",
glue = {"StepDefinitions", "hooks"},
plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "rerun:target/failed.txt"}
 )

public class FailedTestRunner extends AbstractTestNGCucumberTests {

}
