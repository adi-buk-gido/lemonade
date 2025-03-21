package com.example.shield.model.conversation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@XmlRootElement(name = "Attachment") // Root element for XML
@XmlAccessorType(XmlAccessType.FIELD) // Allows direct mapping of fields
@JsonInclude(JsonInclude.Include.NON_NULL) // Excludes null values from JSON
@JsonIgnoreProperties(ignoreUnknown = true) // Ignores unknown JSON propertes

public class Attachment {

    @XmlElement(name = "User") // Maps "User" tag in XML
    @JsonProperty("user") // Maps "user" field in JSON
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

    @XmlElement(name = "FileName")
    @JsonProperty("fileName")
    private String fileName;

    @XmlElement(name = "FileID")
    @JsonProperty("fileId")
    private String fileId;

    @XmlElement(name = "FileSize")
    @JsonProperty("fileSize")
    private String fileSize;
}