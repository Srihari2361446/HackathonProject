package testCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.ExcelUtilitiy;
import Utilities.ScreenshotUtility;
import pageObject.HomePage;
import pageObject.WellnessPage;
import testBase.BaseClass;

public class TC_003_WellnessCare extends BaseClass {
	
	@Test(priority = 1,groups = {"Regression"})
	public void validateWellnessPage() {
		logger.info("********* Test case 7 started *********");
		HomePage hp = new HomePage(driver);
		hp.homepageNavigation();
		hp.clickOnWellnesslink();
		Set<String> windows = driver.getWindowHandles();
		List<String> window  = new ArrayList<>(windows);
		String childWindow = window.get(1);
		driver.switchTo().window(childWindow);
		String title = "Employee Health | Corporate Health & Wellness Plans | Practo";
		WellnessPage wp = new WellnessPage(driver);
		logger.info("********* Test case 7 Ended *********");
//		Assert.assertEquals(title, wp.getTitle());
	}
	
	@Test(priority = 2,groups = {"Regression"})
	public void ScheduleDmo() throws IOException {
		ExcelUtilitiy ex =new ExcelUtilitiy();
		String[] data = ex.getExcelData();
		
		WellnessPage wp = new WellnessPage(driver);
		wp.setName(data[0]);
		wp.setOrganizationName(data[1]);
		wp.setContactNumber(data[2]);
		wp.setEmail(data[3]);	
		wp.setOrganizationDropdow(data[4]);
		wp.SetIntestedINDropDow(data[5]);
		ScreenshotUtility sc= new ScreenshotUtility();
		sc.captureScreenshot("ScheduleDemo");
	}
	
}
