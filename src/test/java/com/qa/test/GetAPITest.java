package com.qa.test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class GetAPITest extends TestBase {
    TestBase testBase;
    String url;
    String apiUrl;
    String serviceUrl;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() throws IOException {
        this.testBase = new TestBase();
        this.apiUrl = prop.getProperty("apiUrl");
        this.serviceUrl = prop.getProperty("serviceUrl");
        this.url = this.apiUrl + this.serviceUrl;
    }

    @Test(priority = 1)
    public void getAPITestWithoutHeader() throws IOException {
        this.restClient = new RestClient();
        this.closeableHttpResponse = this.restClient.get(this.url);

        //a. Status Code:
        int statusCode = this.closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code: " + statusCode);

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        //b. Json String
        String responseString = EntityUtils.toString(this.closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJsonObject = new JSONObject(responseString);
        System.out.println("Response Json from API " + responseJsonObject);

        //per_page:
        String perPageValue = TestUtil.getValueByJPath(responseJsonObject, "/per_page");
        System.out.println("Value of per_page is " + perPageValue);
        Assert.assertEquals(Integer.parseInt(perPageValue), 3);

        String totalValue = TestUtil.getValueByJPath(responseJsonObject, "/total");
        System.out.println("Value of total is " + totalValue);
        Assert.assertEquals(Integer.parseInt(totalValue), 12);

        //get the value from JSON ARRAY:
        String last_name = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/last_name");
        String first_name = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/first_name");
        String id = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/id");
        String avatar = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/avatar");

        System.out.println(last_name);
        System.out.println(first_name);
        System.out.println(id);
        System.out.println(avatar);

        Assert.assertEquals(last_name, "Bluth");
        //all headers
        Header[] headersArray = this.closeableHttpResponse.getAllHeaders();

        HashMap<String, String> allHeaders = new HashMap<String, String>();
        for (Header header : headersArray) {
            allHeaders.put(header.getName(), header.getValue());
        }

        System.out.println("Headers Array " + allHeaders);

    }

    @Test(priority = 2)
    public void getAPITestWithHeaders() throws IOException {
        this.restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
//        headerMap.put("userName", "test123");
//        headerMap.put("password", "password@");
        this.closeableHttpResponse = this.restClient.get(this.url, headerMap);

        //a. Status Code:
        int statusCode = this.closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code: " + statusCode);

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        //b. Json String
        String responseString = EntityUtils.toString(this.closeableHttpResponse.getEntity(), "UTF-8");
        JSONObject responseJsonObject = new JSONObject(responseString);
        System.out.println("Response Json from API " + responseJsonObject);

        //per_page:
        String perPageValue = TestUtil.getValueByJPath(responseJsonObject, "/per_page");
        System.out.println("Value of per_page is " + perPageValue);
        Assert.assertEquals(Integer.parseInt(perPageValue), 3);

        String totalValue = TestUtil.getValueByJPath(responseJsonObject, "/total");
        System.out.println("Value of total is " + totalValue);
        Assert.assertEquals(Integer.parseInt(totalValue), 12);

        //get the value from JSON ARRAY:
        String last_name = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/last_name");
        String first_name = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/first_name");
        String id = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/id");
        String avatar = TestUtil.getValueByJPath(responseJsonObject, "/data[0]/avatar");

        System.out.println(last_name);
        System.out.println(first_name);
        System.out.println(id);
        System.out.println(avatar);

        Assert.assertEquals(last_name, "Bluth");
        //all headers
        Header[] headersArray = this.closeableHttpResponse.getAllHeaders();

        HashMap<String, String> allHeaders = new HashMap<String, String>();
        for (Header header : headersArray) {
            allHeaders.put(header.getName(), header.getValue());
        }

        System.out.println("Headers Array " + allHeaders);

    }

}
