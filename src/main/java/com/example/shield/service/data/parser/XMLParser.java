package com.example.shield.service.data.parser;

import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.springframework.stereotype.Component;
import com.example.shield.model.conversation.RoomConversation;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class XMLParser implements IDataParser {

    @Override
    public RoomConversation parseData(InputStream fileStream) {
        try {
            // Use StAX to read XML as a stream
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader reader = factory.createXMLStreamReader(fileStream);

            JAXBContext context = JAXBContext.newInstance(RoomConversation.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return unmarshaller.unmarshal(reader, RoomConversation.class).getValue();
        } catch (Exception e) {
            log.error("Error parsing XML", e);
            throw new RuntimeException("Failed to parse XML", e);
        }
    }

}
