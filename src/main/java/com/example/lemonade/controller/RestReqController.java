package com.example.lemonade.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.lemonade.consts.RestConsts;
import com.example.lemonade.data.LemonadeRequest;


@RestController
public class RestReqController {

    private static final Logger logger = LoggerFactory.getLogger(RestReqController.class);



    @GetMapping(RestConsts.Mappings.SIMPLE_GET)
    public String simpleGet(){
        return "Hello Oz the Funky";
    }

    @RequestMapping(value = RestConsts.Mappings.SIMPLE_POST, method = RequestMethod.POST)
    @ResponseBody
    public String simplePost(@RequestHeader(value = RestConsts.CommonHeaders.tenantId) String tenantIdString,
                            @RequestBody(required = true) LemonadeRequest lemonadeRequest) throws Exception{
        logger.info("Simple post triggered: " + lemonadeRequest.toString());
        if(lemonadeRequest.getId() == null){
            logger.error("ID is empty");
            throw new Exception("ID is empty");
        }
        return "Yay Adi!";
    }



}
