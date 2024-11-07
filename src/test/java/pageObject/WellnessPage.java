package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class WellnessPage extends BaseClass{

	public WellnessPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="name")
	WebElement nameInput;
	@FindBy(id="organizationName")
	WebElement organizationNameInput;
	@FindBy(id="contactNumber")
	WebElement contactNumberInput;
	@FindBy(id="officialEmailId")
	WebElement emailInput;
	@FindBy(id="organizationSize")
	WebElement organizationSizaDropDown;
	@FindBy(id="interestedIn")
	WebElement intrestedInDropDown;
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void setName(String name) {
		nameInput.sendKeys(name);
	}
	
	public void setOrganizationName(String OrganisationName) {
		organizationNameInput.sendKeys(OrganisationName);
	}
	public void setContactNumber(String phnumber) {
		contactNumberInput.sendKeys(phnumber);
	}
	
	public void setEmail(String email) {
		emailInput.sendKeys(email);
	}
	
	public void setOrganizationDropdow(String value) {
		Select select = new Select(organizationSizaDropDown);
		select.selectByValue(value);
	}
	
	public void SetIntestedINDropDow(String value) {
		Select select = new Select(intrestedInDropDown);
		select.selectByValue(value);
	}
}
