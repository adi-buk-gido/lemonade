package com.example.shield;

import com.example.shield.model.conversation.RoomConversation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonDataParserTest{

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testParseValidJson() throws IOException {
        // Load test JSON file
        InputStream jsonStream = getClass().getClassLoader().getResourceAsStream("test-room-conversation.json");
        assertNotNull(jsonStream, "Test JSON file is missing!");

        // Parse JSON
        RoomConversation conversation = objectMapper.readValue(jsonStream, RoomConversation.class);

        // Assertions
        assertNotNull(conversation, "Parsed conversation should not be null");
        assertEquals(1, conversation.getConversations().size(), "Should contain 1 conversation");

        // Verify message content
        assertEquals("Hello, this is a test message.", 
                     conversation.getConversations().get(0).getMessages().get(0).getContent(),
                     "Message content should match");
    }

    @Test
    void testParseInvalidJson() {
        // Load invalid JSON file
        InputStream jsonStream = getClass().getClassLoader().getResourceAsStream("invalid-room-conversation.json");
        assertNotNull(jsonStream, "Invalid test JSON file is missing!");

        // Expect parsing failure
        Exception exception = assertThrows(IOException.class, () -> {
            objectMapper.readValue(jsonStream, RoomConversation.class);
        });

        assertTrue(exception.getMessage().contains("Unexpected end-of-input"));
    }
}

