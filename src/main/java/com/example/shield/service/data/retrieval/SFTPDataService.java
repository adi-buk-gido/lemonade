package com.example.shield.service.data.retrieval;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.example.shield.model.conversation.RoomConversation;

@Service
public class SFTPDataService implements IDataRetrievalService {


    @Override
    public void connect() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connect'");
    }

    @Override
    public void disconnect() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disconnect'");
    }

    @Override
    public InputStream retrieveData(String inputId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrieveData'");
    }

    @Override
    public RoomConversation convertData(InputStream format, String input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertData'");
    }
}
