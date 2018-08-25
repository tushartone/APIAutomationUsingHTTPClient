package restAPITesting;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.qa.client.RestAPIClient;
import org.qa.testBase.Testbase;
import org.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetAPITest extends Testbase{

	Testbase testbase;
	String url;
	String serviceurl;
	String apiurl;
	RestAPIClient restAPIClient;
	CloseableHttpResponse httpResponse;
	
	@BeforeMethod()
	public void setUp() throws IOException
	{
		testbase = new Testbase();
		serviceurl = prop.getProperty("URL");
		apiurl = prop.getProperty("serviceurl");
		url =serviceurl+apiurl;
	}
	@Test
	public void getTest() throws ClientProtocolException, IOException
	{
		restAPIClient = new RestAPIClient();
		httpResponse = restAPIClient.get(url);
		
		//a.Get the status code
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				System.out.println("Status code is -------->"+statusCode);
				
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200-OK");
				
				//b.Json string
				String responseData = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
				JSONObject responseJsonObj = new JSONObject(responseData);
				System.out.println("Responce Json from------->"+responseJsonObj);
				
				//single value assertion:
				//per_page:
				String perPageValue = TestUtil.getValueByJPath(responseJsonObj, "/per_page");
				System.out.println("value of per page is-->"+ perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 3);
				
				//total:
				String totalValue = TestUtil.getValueByJPath(responseJsonObj, "/total");
				System.out.println("value of total is-->"+ totalValue);		
				Assert.assertEquals(Integer.parseInt(totalValue), 12);

				//get the value from JSON ARRAY:
				String lastName = TestUtil.getValueByJPath(responseJsonObj, "/data[0]/last_name");
				String id = TestUtil.getValueByJPath(responseJsonObj, "/data[0]/id");
				String avatar = TestUtil.getValueByJPath(responseJsonObj, "/data[0]/avatar");
				String firstName = TestUtil.getValueByJPath(responseJsonObj, "/data[0]/first_name");

				System.out.println(lastName);
				System.out.println(id);
				System.out.println(avatar);
				System.out.println(firstName);
				
				//c.all headers
				Header[] headerArrayData = httpResponse.getAllHeaders();
				HashMap<String, String> allHeaders = new HashMap<String,String>();
				for(Header header : headerArrayData)
				{
					allHeaders.put(header.getName(),header.getValue());
				}
				System.out.println("All headers data is ------>"+allHeaders);
				
	}
	}


