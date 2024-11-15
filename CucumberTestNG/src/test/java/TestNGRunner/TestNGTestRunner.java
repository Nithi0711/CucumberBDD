package TestNGRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;

import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features",
glue = {"StepDefinitions", "hooks"},
plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "rerun:target/failed.txt"}
 )
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	public static ExtentReports extent;
	public static WebDriver driver;
	public static ExtentTest logger;

	@Override
	@DataProvider
	public Object[][] scenarios() {
		return super.scenarios();
		
	}
	@BeforeTest
	public void beforeheTestBegins() {
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "Cucumber.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setReportName("Youtube Search Report");
		spark.config().setDocumentTitle("Youtube Search Extent Report");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}
	@BeforeMethod
	public void beforetheFirstMethod(Method testMethod) {

		logger = extent.createTest(testMethod.getName());
	}
	@Test(
			groups = {"cucumber"},
			description = "Run cucumber scenarios",
			dataProvider = "scenarios",
			retryAnalyzer = Retry.class
			)
	@Override
	public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
		super.runScenario(pickleWrapper, featureWrapper);
	}
	
	@AfterMethod
	public void AfterTheMethodCompletes(ITestResult result) {
		if(result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Test Case Passed", ExtentColor.GREEN));
		}
		else if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + "Test Case Failed", ExtentColor.RED));
		}
		else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "Test Case Skipped", ExtentColor.AMBER));
		}
	}
	
	@AfterTest
	public void afterTestCompletes() {
		extent.flush();
	}
	
}
