package io.github.welshmullet.urlshortener.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.welshmullet.urlshortener.generated.model.ApiError;
import io.github.welshmullet.urlshortener.generated.model.UrlResponse;

/**
 * Contains the Cucumber Step Definition 'Glue code'
 * 
 * @author Daniel
 *
 */
public class UrlShortenerStepDefinitions {
	private static final String DEFAULT_URL = "https://www.google.com";

	@Autowired
	private UrlShortenerHttpClient urlShortenerHttpClient;

	private ResponseEntity<?> lastResponse;
	private String lastDecodeRequest;
	private Map<String, String> encodedDecodedPairs = new HashMap<>();

	// Utility

	private @NotNull String getLastResponseUrl() {
		return ((ResponseEntity<UrlResponse>) lastResponse).getBody().getUrl();
	}

	private @NotNull String getLastResponseErrorMessage() {
		return ((ResponseEntity<ApiError>) lastResponse).getBody().getMessage();
	}

	// Given

	@Given("{int} URLs have been encoded")
	public void x_urls_have_been_encoded(final int quantity) {
		IntStream.range(0, quantity).forEach(i -> {
			String url = DEFAULT_URL + i;
			lastResponse = urlShortenerHttpClient.postEncode(url);
			encodedDecodedPairs.put(url, getLastResponseUrl());
		});
	}

	@Given("a URL has been encoded")
	public void a_url_has_been_encoded() {
		lastResponse = urlShortenerHttpClient.postEncode(DEFAULT_URL);
		encodedDecodedPairs.put(DEFAULT_URL, getLastResponseUrl());
	}

	// When

	@When("I POST to the encode endpoint with the URL {string}")
	public void i_post_to_the_encode_endpoint_with_the_url(String url) {
		lastResponse = urlShortenerHttpClient.postEncode(url);
		if (lastResponse.getStatusCodeValue() == 201) {
			encodedDecodedPairs.put(url, getLastResponseUrl());
		}
	}

	@When("I POST to the decode endpoint with the encoded URL")
	public void i_post_to_the_decode_endpoint_with_the_url() {
		lastDecodeRequest = getLastResponseUrl();
		lastResponse = urlShortenerHttpClient.postDecode(getLastResponseUrl());
	}

	@When("I POST to the decode endpoint with the first encoded URL")
	public void i_post_to_the_decode_endpoint_with_the_first_url() {
		lastResponse = urlShortenerHttpClient.postDecode(encodedDecodedPairs.get(DEFAULT_URL + 0));
	}

	@When("I POST to the decode endpoint with the second encoded URL")
	public void i_post_to_the_decode_endpoint_with_the_second_url() {
		lastResponse = urlShortenerHttpClient.postDecode(encodedDecodedPairs.get(DEFAULT_URL + 1));
	}

	@When("I POST to the decode endpoint with a URL for which there is no encoded URL")
	public void i_post_to_the_decode_endpoint_with_a_url_for_which_there_is_no_encoded_url() {
		lastDecodeRequest = "www.anotherUrl.com";
		lastResponse = urlShortenerHttpClient.postDecode("www.anotherUrl.com");
	}

	// Then

	@Then("I get a {int} response")
	public void i_get_a_response(Integer int1) {
		assertEquals(int1, lastResponse.getStatusCode().value());
	}

	@Then("the response contains an encoded URL")
	public void the_response_contains_an_encoded_url() {
		assertNotNull(getLastResponseUrl());
	}

	@Then("the response contains the correct decoded URL")
	public void the_response_contains_the_correct_decoded_url() {
		assertEquals(lastDecodeRequest, encodedDecodedPairs.get(getLastResponseUrl()));
	}

	@Then("the response contains the first decoded URL")
	public void the_response_contains_the_first_decoded_url() {
		assertEquals(DEFAULT_URL + 0, getLastResponseUrl());
	}

	@Then("the response contains the second decoded URL")
	public void the_response_contains_the_second_decoded_url() {
		assertEquals(DEFAULT_URL + 1, getLastResponseUrl());
	}

	@Then("the response contains an error message")
	public void the_response_contains_an_error_message() {
		assertNotNull(getLastResponseErrorMessage());
	}

}
