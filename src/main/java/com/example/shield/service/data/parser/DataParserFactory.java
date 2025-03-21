package com.example.shield.service.data.parser;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.utils.ImmutableMap;

@Component
public class DataParserFactory {

    private static Map<String, IDataParser> parsers;
    
        @Autowired
        XMLParser xmlDataParser;
        JsonDataParser jsonDataParser;
    
    
        @PostConstruct
            public void init(){
                DataParserFactory.parsers = ImmutableMap.of(
                    "xml", xmlDataParser,
                "json", jsonDataParser
            );
        }

    public static IDataParser getParser(String type) {
        return parsers.getOrDefault(type.toLowerCase(), null);
    }

}
