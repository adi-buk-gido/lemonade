package com.example.shield.model.conversation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data@XmlRootElement(name = "Participant") // Defines the root XML element
@XmlAccessorType(XmlAccessType.FIELD) // Maps all fields directly to XML
@JsonInclude(JsonInclude.Include.NON_NULL) // Excludes null values in JSON
@JsonIgnoreProperties(ignoreUnknown = true) // Ignores unknown JSON properties
public class Participant {

    @XmlElement(name = "User")
    @JsonProperty("user")
    private User user;

    @XmlElement(name = "DateTime")
    @JsonProperty("dateTime")
    private String dateTime;

    @XmlElement(name = "DateTimeUTC")
    @JsonProperty("dateTimeUTC")
    private String dateTimeUTC;

    @XmlElement(name = "ConversationID")
    @JsonProperty("conversationId")
    private String conversationId;
}
