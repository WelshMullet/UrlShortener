package io.github.welshmullet.urlshortener.integration;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.welshmullet.urlshortener.generated.model.ApiError;
import io.github.welshmullet.urlshortener.generated.model.UrlRequest;
import io.github.welshmullet.urlshortener.generated.model.UrlResponse;


import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import java.io.IOException;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class UrlShortenerHttpClient {

	private static final String ENDPOINT_URL_PATTERN = "%s:%d%s";
	private static final String SERVER_URL = "http://localhost";
	private static final String ENCODE_ENDPOINT = "/encode";
	private static final String DECODE_ENDPOINT = "/decode";

	@LocalServerPort
	private int port;
	private final RestTemplate restTemplate = new RestTemplate();

	private String encodeEndpoint() {
		return String.format(ENDPOINT_URL_PATTERN, SERVER_URL, port, ENCODE_ENDPOINT);
	}

	private String decodeEndpoint() {
		return String.format(ENDPOINT_URL_PATTERN, SERVER_URL, port, DECODE_ENDPOINT);
	}

	public ResponseEntity<?> postEncode(final String url) {
		try {
			return restTemplate.postForEntity(encodeEndpoint(), new UrlRequest().url(url), UrlResponse.class);
		} catch (HttpClientErrorException e) {

			try {
				ObjectMapper objectMapper = new ObjectMapper();
				ApiError error = objectMapper.readValue(e.getResponseBodyAsByteArray(), ApiError.class);
				return ResponseEntity.status(e.getStatusCode()).body(error);
			} catch (IOException e1) {
				return null;
			}

		}
	}

	public ResponseEntity<?> postDecode(final String url) {
		try {
			return restTemplate.postForEntity(decodeEndpoint(), new UrlRequest().url(url), UrlResponse.class);
		} catch (HttpClientErrorException e) {

			try {
				ObjectMapper objectMapper = new ObjectMapper();
				ApiError error = objectMapper.readValue(e.getResponseBodyAsByteArray(), ApiError.class);
				return ResponseEntity.status(e.getStatusCode()).body(error);
			} catch (IOException e1) {
				return null;
			}

		}
	}

}