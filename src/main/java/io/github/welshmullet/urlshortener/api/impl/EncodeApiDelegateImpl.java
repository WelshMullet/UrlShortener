package io.github.welshmullet.urlshortener.api.impl;

import java.util.UUID;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.welshmullet.urlshortener.data.EncodedUrlStorage;
import io.github.welshmullet.urlshortener.generated.api.EncodeApiDelegate;
import io.github.welshmullet.urlshortener.generated.model.UrlRequest;
import io.github.welshmullet.urlshortener.generated.model.UrlResponse;


/**
 * Implementation for the /encode endpoint
 * 
 * @author Daniel
 *
 */
@Service
public class EncodeApiDelegateImpl implements EncodeApiDelegate {
	private static final String BASE_URL = "https://localhost:8080/url/";

	private static final String URL_REGEX = "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))"
			+ "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" + "([).!';/?:,][[:blank:]])?$";

	private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

	@Autowired
	private EncodedUrlStorage encodedUrlStorage;

	@Override
	public ResponseEntity<UrlResponse> encodeUrl(UrlRequest urlRequest) {
		if (urlValidator(urlRequest.getUrl())) {
			final String encodedUrl = BASE_URL + UUID.randomUUID();
			encodedUrlStorage.storeEncodedUrl(urlRequest.getUrl(), encodedUrl);
			return ResponseEntity.ok(new UrlResponse().url(encodedUrl));
		}
		throw new InvalidUrlException();
	}

	private static boolean urlValidator(String url) {
		if (url == null) {
			return false;
		}

		final Matcher matcher = URL_PATTERN.matcher(url);
		return matcher.matches();
	}
}
