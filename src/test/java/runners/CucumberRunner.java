package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@SelectClasspathResource("features")   // the path to your feature files
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps, steps.api")          // the path to step definition
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-report.html")// tell cucumber where to put report
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@logout")  // tell cucumber to run specific tag
//@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@getPatients or @smoke")  // tell cucumber to run specific tag
@ConfigurationParameter(key = EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false") // "True" skip step definitions that has implemented before, will show unimpelementd definition so we can copy and define

public class CucumberRunner {

}
