package io.github.welshmullet.urlshortener.integration;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.welshmullet.urlshortener.generated.model.UrlRequest;
import io.github.welshmullet.urlshortener.generated.model.UrlResponse;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

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

	public ResponseEntity<UrlResponse> postEncode(final String url) {
		return restTemplate.postForEntity(encodeEndpoint(), new UrlRequest().url(url), UrlResponse.class);
	}

	public ResponseEntity<UrlResponse> postDecode(final String url) {
		return restTemplate.postForEntity(decodeEndpoint(), new UrlRequest().url(url), UrlResponse.class);
	}

}