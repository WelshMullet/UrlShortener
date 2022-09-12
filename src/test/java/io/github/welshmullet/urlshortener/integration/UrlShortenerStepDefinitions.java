package io.github.welshmullet.urlshortener.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.welshmullet.urlshortener.model.UrlResponse;

public class UrlShortenerStepDefinitions {

	@Autowired
	private UrlShortenerHttpClient urlShortenerHttpClient;
	
	private ResponseEntity<UrlResponse> lastResponse;

	private String getLastResponseUrl() {
		return lastResponse.getBody().getUrl();
	}
	
	@When("I POST to the encode endpoint with the URL {string}")
	public void i_post_to_the_encode_endpoint_with_the_url(String string) {
		lastResponse = urlShortenerHttpClient.postEncode(string);
	}

	@Then("I get a {int} response")
	public void i_get_a_response(Integer int1) {
		assertEquals(int1, lastResponse.getStatusCode().value());
	}

	@Then("the response contains an encoded URL")
	public void the_response_contains_an_encoded_url() {
		assertNotNull(getLastResponseUrl());
	}
}
