package io.github.welshmullet.urlshortener.integration;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import io.github.welshmullet.urlshortener.application.UrlShortenerApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = UrlShortenerApplication.class)
@ActiveProfiles("test")
public class CucumberSpringConfiguration {
	private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringConfiguration.class);

	/**
	 * Need this method so the cucumber will recognize this class as glue and load
	 * spring context configuration
	 */
	@Before
	public void setUp() {
		LOG.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
	}
}