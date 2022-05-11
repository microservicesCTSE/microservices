package com.pos.inventory.adapter.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.pos.inventory.service.util.MultipartInputStreamFileResource;
import com.pos.inventory.service.util.SSLUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * contains 3rd party linkfx api integration service calls adapters
 *
 * @author Nimesha Alahakoon
 */
@Component
@Slf4j
@Data
public class InventoryServiceAdapter {
	@Value("${WooCommerce.services.stock}")
	private String servicesUrl;

	@Value("${WooCommerce.services.logPath}")
	private String logPath;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * API http url call invoker method for a single returning value
	 *
	 * @param url
	 * @param body
	 * @param httpMethod
	 * @return response
	 */
	public Object executeForSingleValue(String url, Object body, HttpMethod httpMethod, Boolean includeHeaders) {
		log.info(
				"TerrapayServiceAdapter.executeForSingleValue(String url, Object body, HttpMethod httpMethod, String accessToken) invoked");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

//		if (includeHeaders.equals(true)) {
//
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String date = simpleDateFormat.format(new Date());
//
//			headers.add("X-USERNAME", username);
//			headers.add("X-PASSWORD", password);
//			headers.add("X-DATE", date);
//			headers.add("X-ORIGINCOUNTRY", originCountry);
//		}
		HttpEntity<Object> entity = new HttpEntity<>(body, headers);
		try {
			SSLUtil.turnOffSslChecking();
			ResponseEntity<String> response = restTemplate.exchange(servicesUrl.concat(url), httpMethod, entity,
					String.class);
			if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
				log.info("Data retrieved from TerrapayServiceAdapter.executeForSingleValue with accesstoken");
				return response.getBody();
			} else {
				log.info("Unable to retrieve data from TerrapayServiceAdapter.executeForSingleValue with accesstoken");
				return null;
			}
		} catch (Exception e) {
			log.error(
					"Exception Occured while reciving data from TerrapayServiceAdapter.executeForSingleValue with accesstoken",
					e);
			throw new RuntimeException("Empty Response");
		}
	}

}
