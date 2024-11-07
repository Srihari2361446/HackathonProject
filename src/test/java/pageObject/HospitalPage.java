package pageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HospitalPage extends BaseClass {
	public List<WebElement> hospitalList = new ArrayList<>();
	List<String> filteredHospital=new ArrayList<>();
	List<WebElement> Amenities = new ArrayList<>();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	Actions action = new Actions(driver);
	
	public HospitalPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@placeholder='Search location']") 
	WebElement locationInput;
	@FindBy(xpath = "//div[normalize-space()='Bangalore']")
	WebElement bangloreLocation;
	@FindBy(xpath="//span[contains(text(),'Read more info')]")
	WebElement readMoreInfoButton;
	@FindBy(xpath = "//div[@class=\"c-prime-header\"]//h1[contains(text(),\"Hospitals in Bangalore\")]")
	WebElement FirstResult;
	
	@FindBy (xpath = "//span[@class='common__star-rating__value']")
	WebElement ratingElement;
	
	
	

	public void clearLocation() {
		locationInput.clear();
	}

	public void clickLocationInput() {
		locationInput.click();
	}

	public void SetLocation() {
		locationInput.sendKeys("Bangalore");
	}

	public void BangloreLocationSelection() {
		bangloreLocation.click();
	}

	public List<String> filterHospital() {
		int count =0;
		wait.until(ExpectedConditions.visibilityOf(FirstResult));
		hospitalList = driver.findElements(By.xpath("//div[@class=\"c-estb-card\"]//a[@target=\"_blank\"]"));
		System.out.println();
		System.out.println();
		System.out.println("------- Hospital opened 24/7, having Parking fecility and having ratings more than 3.5 --------");
		for (WebElement ele : hospitalList) {
			String HospitalName = ele.getText(); 
			ele.click();
			Set<String> windows = driver.getWindowHandles();
			List<String> window = new ArrayList<>(windows);
			String parentWindow = window.get(0);
			String childWindow = window.get(1);
			driver.switchTo().window(childWindow);
			action.moveToElement(readMoreInfoButton);
			readMoreInfoButton.click();
			Amenities = driver.findElements(By.xpath("//div[@data-qa-id=\"amenities_list\"]//span"));
			for(WebElement elem : Amenities) {
				if(elem.getText().equals("24X7 Pharmacy" ) || elem.getText().equalsIgnoreCase("Parking")) {
//					System.out.println(elem.getText());
					count++;
				}
			}
			double rating = Double.parseDouble(ratingElement.getText());
			if(count>1 && rating >3.5) {
				System.out.println("Hospital : "+HospitalName);
				filteredHospital.add(HospitalName);
			}
			count=0;
			driver.close();
			driver.switchTo().window(parentWindow);
		}
		System.out.println();
		System.out.println();
		return filteredHospital;
	}


}
