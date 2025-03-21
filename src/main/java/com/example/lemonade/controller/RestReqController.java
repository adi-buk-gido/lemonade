package com.example.lemonade.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lemonade.consts.RestConsts;


@RestController
public class RestReqController {

    @GetMapping(RestConsts.Mappings.SIMPLE_GET)
    public String simpleGet(){
        return "Hello Oz the Funky";
    }



}
