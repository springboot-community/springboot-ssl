package io.springboot.ssl.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public Object index(){
        Map<String,String> map = new HashMap<>();
        map.put("name","Javaweb开发者社区");
        return map;
    }
}
