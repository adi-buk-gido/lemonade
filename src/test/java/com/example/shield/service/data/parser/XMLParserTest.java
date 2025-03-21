package com.example.shield.service.data.parser;


import static org.junit.jupiter.api.Assertions.*;

import com.example.shield.model.conversation.Conversation;
import com.example.shield.model.conversation.RoomConversation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;

class XMLParserTest {

    @InjectMocks
    private XMLParser xmlParser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testParseData_Success() {
        // Load the test XML file
        InputStream xmlStream = getClass().getClassLoader().getResourceAsStream("test-room-conversation.xml");
        assertNotNull(xmlStream, "Test XML file is missing!");

        // Parse XML
        RoomConversation conversations = xmlParser.parseData(xmlStream);
        Conversation conversation = conversations.getConversations().get(0);

        // Assertions
        assertNotNull(conversation, "Parsed conversation should not be null");
        assertEquals("CHAT-123456", conversation.getRoomId(), "Room ID should match");
        assertEquals("08/27/2023 10:00:00", conversation.getStartTime(), "Start time should match");
        assertEquals(1, conversation.getParticipantsEntered().size(), "One participant should have entered");
        assertEquals(1, conversation.getMessages().size(), "There should be 1 message in the conversation");
        assertEquals(1, conversation.getAttachments().size(), "There should be 1 attachment in the conversation");
        assertEquals(1, conversation.getParticipantsLeft().size(), "One participant should have left");
    }


    @Test
    void testParseData_InvalidXML() {
        // Load an invalid XML file (missing closing tag)
        InputStream xmlStream = getClass().getClassLoader().getResourceAsStream("invalid-room-conversation.xml");
        assertNotNull(xmlStream, "Invalid test XML file is missing!");

        // Expect an exception
        Exception exception = assertThrows(RuntimeException.class, () -> {
            xmlParser.parseData(xmlStream);
        });

        assertTrue(exception.getMessage().contains("Failed to parse XML"), "Expected parsing failure message");
    }
}