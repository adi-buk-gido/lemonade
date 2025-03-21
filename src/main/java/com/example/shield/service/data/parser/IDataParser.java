package com.example.shield.service.data.parser;

import java.io.InputStream;

import com.example.shield.model.conversation.RoomConversation;

public interface IDataParser {

    public RoomConversation parseData(InputStream fileStream);

}
