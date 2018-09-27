package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RequestBodyTest {
    @PostMapping(value = "/parameter")
    @ResponseBody
    public String Parameter(HttpServletRequest request, @RequestBody String Content) {
        System.out.println("?后的内容为: " + request.getQueryString());
        System.out.println(Content);
        JSONObject result = JSONObject.parseObject(Content);
        System.out.println(result.getString("name"));
        return Content;
    }

    /**
     * @param request
     * @param input
     * @return
     */
    @PostMapping(value = "/parameter1")
    @ResponseBody
    public String Parameter2(HttpServletRequest request, @RequestBody Map<String, Object> input) {
        System.out.println("?后的内容为: " + request.getQueryString());
        String email = (String) input.get("name");
        //String id = (String)123;
        String password = (String) input.get("pwd");
        //注意input.get("phone")返回的是一个Object
        //参数body为
        //{
        // "name":"sqh",
        // "pwd":"123",
        // "phone":1234
        // }
        Integer id = (Integer) input.get("phone");
        return password;
    }
}
