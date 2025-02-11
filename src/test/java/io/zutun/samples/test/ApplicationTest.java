package io.zutun.samples.test;

import io.cucumber.core.options.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectPackages({"pe.inretail.cord.paymentmethods.test.steps"})
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "pe.inretail.cord.paymentmethods.test.steps")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "junit")
@ConfigurationParameter(key = Constants.EXECUTION_ORDER_PROPERTY_NAME, value = "lexical")
public class ApplicationTest {
}
