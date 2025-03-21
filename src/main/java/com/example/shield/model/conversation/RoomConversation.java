package com.example.shield.model.conversation;

import lombok.Data;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@XmlRootElement(name = "FileDump") // Defines the root XML element
@XmlAccessorType(XmlAccessType.FIELD) // Maps all fields directly to XML
@JsonInclude(JsonInclude.Include.NON_NULL) // Excludes null values in JSON
@JsonIgnoreProperties(ignoreUnknown = true) // Ignores unknown JSON properties
public class RoomConversation {

    @XmlElement(name = "Conversation")
    @JsonProperty("conversations")
    private List<Conversation> conversations;
}
