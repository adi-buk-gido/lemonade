package com.example.shield.service.data.retrieval;

import java.io.InputStream;

import com.example.shield.model.conversation.RoomConversation;
import com.example.shield.service.data.parser.DataParserFactory;
import com.example.shield.service.data.parser.IDataParser;

public interface IDataRetrievalService extends IDataParser {


    /**
     * Connect to the relevant source
     */
    void connect();

    /**
     * Disconnect from the relevant source
     */
    void disconnect();

    /**
     * Get the data to our DB/S3 any other source
     */
    InputStream retrieveData(String inputId);

    
    /**
     * Convert the data based on the source to an internal format
     */
    default RoomConversation convertData(InputStream fileStream, String format){
        IDataParser parser = DataParserFactory.getParser(format);
        if (parser == null) {
            throw new IllegalArgumentException("Unsupported data format: " + format);
        }

        return parser.parseData(fileStream);
    };

    default RoomConversation parseData(InputStream input) {
        throw new UnsupportedOperationException("Parsing not implemented in this service.");
    }

}
