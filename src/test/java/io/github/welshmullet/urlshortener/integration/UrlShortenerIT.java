package io.github.welshmullet.urlshortener.integration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/integration/features", plugin = { "pretty",
		"json:target/cucumber-report.json", "html:target/cucmber.html" }, glue = {
				"io.github.welshmullet.urlshortener.integration" }, stepNotifications = true, tags = "not @cucumberIgnore")
public class UrlShortenerIT {

}
