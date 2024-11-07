package StepDefinations;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import Utilities.ExcelUtilitiy;
import Utilities.ScreenshotUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.HomePage;
import pageObject.WellnessPage;

public class WellnessPlanDefination {
	public static WebDriver driver;

	@Given("user navigate to practo webpage")
	public void user_navigate_to_practo_webpage() {
		driver = DriverSetup.getDriver();
	}

	@Given("opens wellness page")
	public void opens_wellness_page() {
		HomePage hp = new HomePage(driver);
		hp.clickOnWellnesslink();
		DriverSetup.switchTab();
	}

	@When("all inputs filled through excel")
	public void all_inputs_filled_through_excel() throws IOException {
		ExcelUtilitiy ex = new ExcelUtilitiy();
		String[] data = ex.getExcelData();

		WellnessPage wp = new WellnessPage(driver);
		wp.setName(data[0]);
		wp.setOrganizationName(data[1]);
		wp.setContactNumber(data[2]);
		wp.setEmail(data[3]);
		wp.setOrganizationDropdow(data[4]);
		wp.SetIntestedINDropDow(data[5]);
	}

	@Then("Take screenshot")
	public void take_screenshot() throws IOException {
		ScreenshotUtility sc = new ScreenshotUtility();
		DriverSetup.captureScreenShot("CucucmberValidation");
	}

}
