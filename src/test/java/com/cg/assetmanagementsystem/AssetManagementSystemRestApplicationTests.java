package com.cg.assetmanagementsystem;

import com.cg.assetmanagementsystem.entities.Asset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AssetManagementSystemRestApplicationTests {
	@LocalServerPort
	int randomServerPort;
	@Test
	public void testGetAllAssetSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String url = "http://localhost:"+randomServerPort+"/assets";
		URI endPointURL = new URI(url);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(endPointURL,String.class);
		Assertions.assertEquals(200,responseEntity.getStatusCodeValue());
		Assertions.assertNotEquals("",responseEntity.getBody());
	}
	@Test
	public void testGetAssetFailure() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String url = "http://localhost:"+randomServerPort+"/assets/100";
		URI endPointURL = new URI(url);
		try {
			restTemplate.getForEntity(endPointURL, Asset.class);
			Assertions.fail();
		}
		catch(HttpClientErrorException exception){
			Assertions.assertEquals(404,exception.getRawStatusCode());
			Assertions.assertTrue(exception.getMessage()!=null&&exception.getMessage().equals("No Asset found with assetId=100."));
		}
	}
	@Test
	public void testNonExistingEndpoint() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:"+randomServerPort+"/asset";
		URI nonExistingURL = new URI(url);
		try{
			restTemplate.getForEntity(nonExistingURL,String.class);
			Assertions.fail();
		}
		catch(HttpClientErrorException exception){
			Assertions.assertEquals(404,exception.getRawStatusCode());
		}
	}

}
