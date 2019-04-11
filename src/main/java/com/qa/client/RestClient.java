package com.qa.client;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    // 1. Get method without Headers:
    public CloseableHttpResponse get(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();//will create one client connection
        HttpGet httpget = new HttpGet(url);//http get request
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);//hit the GET URL

        return closeableHttpResponse;
    }

    //2. Get method with Headers:
    public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();//will create one client connection
        HttpGet httpget = new HttpGet(url);//http get request

        for(Map.Entry<String, String> entry: headerMap.entrySet()){
            httpget.addHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);//hit the GET URL
        return closeableHttpResponse;
    }

    //3. post call
    public CloseableHttpResponse post(String url, String entity, HashMap<String, String>headerMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();//will create one client connection
        HttpPost httpPost = new HttpPost(url);//http post request
        httpPost.setEntity(new StringEntity(entity));//for payload

        //for headers
        for(Map.Entry<String, String> entry: headerMap.entrySet()){
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
        return closeableHttpResponse;

    }



}
