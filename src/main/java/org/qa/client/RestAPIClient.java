package org.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestAPIClient {

	//1.Get Method
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient = HttpClients.createDefault(); // create the connection call
		HttpGet httpget = new HttpGet(url); //http get the request
		CloseableHttpResponse httpResponse = httpClient.execute(httpget); // hit the url i.e click on send button to send the request and get the response
		
		return httpResponse;
			
			
			
			
			
	}
}
