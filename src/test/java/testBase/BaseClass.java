package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger; //log4j
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public static Logger logger;
	public static Properties p;

	@BeforeTest(groups = {"Master","Sanity","Regression"})
	@Parameters("browserName")
	public void getDriver(String browser) throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		logger = LogManager.getLogger(this.getClass());
		logger.info("-----Creating Driver-------");
		switch (browser) {
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
		logger.info("-------Opening URL----------");
		driver.get(p.getProperty("baseurl"));
	}

	public void closeCurrentTab() {
		driver.close();
	}

	public void switchTab() {
		List<String> list = new ArrayList<>(driver.getWindowHandles());
		for (int i = 0; i < list.size(); i++) {
			driver.switchTo().window(list.get(i));
		}
	}

	@AfterTest(groups = {"Master","Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}
}
