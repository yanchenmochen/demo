package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;

/**
 * @param: jsonParam
 * @return 直接通过@RequestBody的方式，直接将json的数据注入到JSONObject里面
 */


@RestController
public class FastJsonTest {
    /**
     * @description:
     * @url: localhost:8080/json/data
     * @author: Song Quanheng
     * @date: 2018/8/16-17:16
     * @return:
     */
    @ResponseBody
    @RequestMapping(value = "/json/data", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getByJson(@RequestBody JSONObject jsonParam) {
        System.out.println("jsonParam: " + jsonParam.toJSONString());

        if (jsonParam.containsKey("name")) {
            System.out.println("Json contains key name");
        }
        if (jsonParam.containsKey("pwd")) {
            System.out.println("Json contains key password");
        }
        if (jsonParam.containsKey("phone")) {
            System.out.println("Json contains key phone");
        }

        String name = jsonParam.getString("name");
        String pwd = jsonParam.getString("pwd");
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
        result.put("data", jsonParam);

        return result.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/request/data", method = RequestMethod.POST)
    public String getByRequest(HttpServletRequest request) {
        System.out.println(request.getMethod());
        System.out.println(request.getQueryString());
        System.out.println(request.getParameterNames().toString());
        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL());
        JSONObject jsonParam = this.getJsonParam(request);

        JSONObject result = new JSONObject();

        result.put("msg", "ok");
        result.put("method", "request");
        result.put("data", jsonParam);
        System.out.println(jsonParam.toJSONString());
        return result.toJSONString();
    }

    public JSONObject getJsonParam(HttpServletRequest request) {

        JSONObject jsonParam = null;
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                System.out.println(line);
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam;
    }
}
