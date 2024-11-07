package pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	public static WebDriver driver;
	public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	public BaseClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
