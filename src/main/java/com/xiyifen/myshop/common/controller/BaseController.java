package com.xiyifen.myshop.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseController {

    @ApiIgnore
    @GetMapping("/unauth")
    public Map<String,Object> unAuth(){
        Map<String,Object> map=new HashMap<>();
        map.put("message","请先登录");
        map.put("data",null);
        return map;
    }

}
