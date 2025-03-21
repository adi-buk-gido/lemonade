package com.example.shield.service.data.parser;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.example.shield.model.conversation.RoomConversation;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JsonDataParser implements IDataParser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public RoomConversation parseData(InputStream fileStream) {
        try {
            log.info("Streaming and parsing JSON data...");

            // Use streaming API to process large JSON efficiently
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jsonParser = jsonFactory.createParser(fileStream);

            return objectMapper.readValue(jsonParser, RoomConversation.class);

        } catch (Exception e) {
            log.error("Error parsing JSON file", e);
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

}
