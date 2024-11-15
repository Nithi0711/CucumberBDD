package StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;

import TestNGRunner.TestNGTestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;


public class search_steps {


	@Given("^Launch Youtube ([^\"]*)$")
	public void launch_youtube_url(String url) {
		TestNGTestRunner.driver.get(url);
		TestNGTestRunner.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@And("^Search for a ([^\\\"]*)$")
	public void search_for_a_value(String value) {
		TestNGTestRunner.driver.findElement(By.xpath("//input[@id='search']")).sendKeys(value);
		TestNGTestRunner.driver.findElement(By.xpath("//button[@id='search-icon-legacy']")).click();
		//driver.quit();
	}



}
