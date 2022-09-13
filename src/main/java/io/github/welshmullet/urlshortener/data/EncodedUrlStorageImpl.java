package io.github.welshmullet.urlshortener.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Simple implementation of {@link EncodedUrlStorage} using an in-memory map.
 * Not thread-safe but no non-functional requirement, or functional requirements
 * present that would require anything more complicated
 * 
 * @author Daniel
 *
 */
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
