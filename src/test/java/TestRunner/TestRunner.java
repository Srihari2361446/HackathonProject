package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
				features = {"feature/cucumber_test01.feature"},
				glue = "StepDefinations",
				plugin = {
						"pretty","html:reports/cucumberReports/myreport.html",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
						"rerun:target/rerun.txt"
				},
				dryRun = false, //checks the feature file with stepDefinations
				monochrome=true,  // to avoid the junk characters
				publish = true    // We use public to generate temperory report in cucumber server
		)
public class TestRunner {

}
