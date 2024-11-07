package StepDefinations;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import Utilities.ScreenshotUtility;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class DriverSetup {
	public static WebDriver driver;
	public static Properties p;
	public static Logger logger;
	
	@Before
	public void CreateDriver() throws IOException  {
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		logger = LogManager.getLogger(this.getClass());
		logger.info("-----Cucumber-Creating Driver-------");
		switch ("chrome") {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.info("-------Cucumber-Opening URL----------");
		driver.get(p.getProperty("baseurl"));
	}
	
	public static WebDriver getDriver() {
		return driver;	
	}

	@After
	public void closeAllTab() {
		driver.quit();
	}

	public static void switchTab() {
		List<String> list = new ArrayList<>(driver.getWindowHandles());
		for (int i = 0; i < list.size(); i++) {
			driver.switchTo().window(list.get(i));
		}
	}
	
	@AfterStep
	public void afterStep(Scenario scenario) throws IOException {
		if(scenario.isFailed()) {
			ScreenshotUtility sc = new ScreenshotUtility();
			captureScreenShot(scenario.getName());
		}
	}
	
	public static void captureScreenShot(String filename) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		Files.createDirectories(Paths.get(System.getProperty("user.dir")+"/ScreenShots/"));
		Files.copy(src.toPath(), new File(System.getProperty("user.dir")+"/ScreenShots/"+timeStamp+filename+".png").toPath());
	}
}
