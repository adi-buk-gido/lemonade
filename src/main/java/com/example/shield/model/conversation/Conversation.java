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
@XmlRootElement(name = "Conversation") // Root element for XML
@XmlAccessorType(XmlAccessType.FIELD) // Maps fields directly
@JsonInclude(JsonInclude.Include.NON_NULL) // Excludes null values from JSON
@JsonIgnoreProperties(ignoreUnknown = true) // Ignores unknown JSON fields
public class Conversation {

    @XmlElement(name = "RoomID")
    @JsonProperty("roomId")
    private String roomId;

    @XmlElement(name = "StartTime")
    @JsonProperty("startTime")
    private String startTime;

    @XmlElement(name = "StartTimeUTC")
    @JsonProperty("startTimeUTC")
    private String startTimeUTC;

    @XmlElement(name = "EndTime")
    @JsonProperty("endTime")
    private String endTime;

    @XmlElement(name = "EndTimeUTC")
    @JsonProperty("endTimeUTC")
    private String endTimeUTC;

    @XmlElement(name = "ParticipantEntered")
    @JsonProperty("participantsEntered")
    private List<Participant> participantsEntered;

    @XmlElement(name = "ParticipantLeft")
    @JsonProperty("participantsLeft")
    private List<Participant> participantsLeft;

    @XmlElement(name = "Message")
    @JsonProperty("messages")
    private List<Message> messages;

    @XmlElement(name = "Attachment")
    @JsonProperty("attachments")
    private List<Attachment> attachments;
}

