package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.messaging.MappingFastJsonMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world") String name, String value) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }


}
