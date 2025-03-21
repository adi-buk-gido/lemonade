package com.example.lemonade.service.data.parser;

import java.io.InputStream;

import com.example.lemonade.model.conversation.RoomConversation;

public interface IDataParser {

    public RoomConversation parseData(InputStream fileStream);

}
