package com.example.demo;

import ch.qos.logback.core.encoder.NonClosableInputStream;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("http")
public class HttpRequestTest {
    private List<String> requestUrls = new ArrayList<>();
    private String url ="http://localhost:8080/fiso/appuserinfo/viewinfo";
    String tableName="userInfo";
    String userId="8ef3f163242b4059a4187f3eaf07f7e1";
    String re_url = url+"?"+"tableName="+tableName+"&"+"userId="+userId;


    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String doGet() throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();

        URI uri = new URIBuilder().setScheme("http").setHost("localhost").
                setPort(8080).setPath("/fiso/appuserinfo/viewinfo")
                .setParameter("tableName", tableName)
                .setParameter("userId", userId).build();
        HttpGet httpget = new HttpGet(uri);
        httpget.setHeader("Content-Type", "application/json");
        httpget.setHeader("Accept", "application/json");
        CloseableHttpResponse response = httpClient.execute(httpget);
        String result = IOUtils.toString(response.getEntity().getContent(), Consts.UTF_8);
        System.out.println(result);
        JSONObject json = JSONObject.parseObject(result);
        if (json.containsKey("data")) {
            JSONObject data = json.getJSONObject("data");
            System.out.println(data.size());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String doPost() throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URI uri = new URIBuilder().setScheme("http").setHost("localhost").
                setPort(8080).setPath("/fiso/appuserinfo/saveinfo")
                .setParameter("tableName", tableName)
                .setParameter("userId", userId).build();
        HttpPost httppost = new HttpPost(uri);
        httppost.setHeader("Content-Type", "application/json");
        httppost.setHeader("Accept", "application/json");

        CloseableHttpResponse response = httpClient.execute(httppost);
        String result = IOUtils.toString(response.getEntity().getContent(), Consts.UTF_8);
        return "";

    }


}
