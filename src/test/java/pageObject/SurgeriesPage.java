package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SurgeriesPage extends BaseClass {
	List<String> Cities=new ArrayList<>();
	
	public SurgeriesPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "(//div[.='Surgeries'])[2]")
	WebElement surgeries;
	@FindBy(linkText = "Lab Tests")
	WebElement labTest;
	@FindBy(xpath = "//div[contains(text(),\"Aurangabad\")]")
	WebElement AurangabadElement;
	
	public boolean validation() {
		return surgeries.isDisplayed();
		
	}
	
	public void ClickOnLabTest() {
		surgeries.click();
		wait.until(ExpectedConditions.visibilityOf(labTest));
		labTest.click();
	}
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void clickonAurangabad() {
		AurangabadElement.click();
	}
	
	public List<String> getDiagnosticsCitys() {
		List<WebElement> cities = driver.findElements(By.xpath("//div[@class=\"u-d__inline city-selector__city u-marginb--std-half u-pointer\"]"));
		wait.until(ExpectedConditions.visibilityOfAllElements(cities));
		for(WebElement ele : cities) {
			System.out.print(ele.getText()+", ");
			Cities.add(ele.getText());
		}
		return Cities;
	}
	

}
