package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import TestNGRunner.TestNGTestRunner;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Additional_hooks {
	

	
	@AfterStep()
	public void actionaftereachstep(Scenario scenario) {
		TakesScreenshot ts = (TakesScreenshot)TestNGTestRunner.driver;
		byte[] screenhsot = ts.getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenhsot, "image/png", ts.toString());
		
	}
	@After
	public void actionaftertest() {
		TestNGTestRunner.driver.quit();
	}

}
