package testCase;

import java.util.List;

import org.testng.annotations.Test;

import Utilities.ExcelUtilitiy;
import pageObject.HomePage;
import pageObject.SurgeriesPage;
import testBase.BaseClass;

public class TC_002_GetDiagnosticsCenterCityName extends BaseClass {

	@Test(priority = 1,groups = {"Master","Sanity","Regression"})
	public void ValidatingSurgeriPage() {
		logger.info("********* Test case 5 started *********");
		HomePage hp = new HomePage(BaseClass.driver);
		hp.homepageNavigation();
		hp.clickONSurgeries();
		SurgeriesPage sp = new SurgeriesPage(driver);
		String title = "Practo Care Surgeries | End to end care from top surgeons in your city";
		logger.info("********* Test case 5 Ended *********");
//		Assert.assertEquals(title, sp.getTitle());
	}

	@Test(priority = 2,groups = {"Master","Sanity","Regression"})
	public void getMainCityNamesofDiagnosticsCenter() {
		logger.info("********* Test case 6 started *********");
		SurgeriesPage sp = new SurgeriesPage(driver);
		sp.ClickOnLabTest();
		System.out.println("------------- Cities with Diagnostics Center -------------");
		//sp.clickonAurangabad();
		List<String> cities=sp.getDiagnosticsCitys();
		sp.clickonAurangabad();
		logger.info("********* Test case 6 Ended *********");
		ExcelUtilitiy s = new ExcelUtilitiy();
		s.setExcelUtility(TC_001_NearBy_Hospital.ResultHospital,cities );
	}

}
