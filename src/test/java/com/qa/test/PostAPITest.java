package com.qa.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PostAPITest extends TestBase {
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

    @Test
    public void postAPITest() throws IOException {
        this.restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");

        //jackson API
        ObjectMapper mapper = new ObjectMapper();
        Users users = new Users("peter@klaven", "cityslicka");//expected users object

        //convert users java object to json file:
        mapper.writeValue(new File("C:\\Users\\Mira\\ReqRes\\src\\main\\java\\com\\qa\\data\\users.json"), users);

        //java object to json in String//marshalling
        String usersAsString = mapper.writeValueAsString(users);
        System.out.println(usersAsString);

        closeableHttpResponse = this.restClient.post(this.url, usersAsString, headerMap);//call the API

        //validate responses from API
        //1. status code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);

        //2. JsonString
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        JSONObject jsonObject = new JSONObject(responseString);
        System.out.println("The response from API is " + jsonObject);


        //json to java object//unmarshalling
        Users userResponseObj = mapper.readValue(responseString, Users.class);//actual users object
        System.out.println(userResponseObj);

        Assert.assertTrue(users.getEmail().equals(userResponseObj.getEmail()));

        Assert.assertTrue(users.getPassword().equals(userResponseObj.getPassword()));

        System.out.println(userResponseObj.getId());
        System.out.println(userResponseObj.getCreatedAt());

    }
}
