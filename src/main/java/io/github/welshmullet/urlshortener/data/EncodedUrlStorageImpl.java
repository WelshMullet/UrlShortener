package io.github.welshmullet.urlshortener.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class EncodedUrlStorageImpl implements EncodedUrlStorage {

	private Map<String, String> storageMap = new HashMap<>();

	@Override
	public void storeEncodedUrl(String originalUrl, String encodedUrl) {
		storageMap.put(encodedUrl, originalUrl);
	}

	@Override
	public String retrieveOriginalUrl(String encodedUrl) {
		return storageMap.get(encodedUrl);
	}

}
