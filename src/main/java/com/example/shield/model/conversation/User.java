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
@XmlRootElement(name = "User") // Defines the root XML element
@XmlAccessorType(XmlAccessType.FIELD) // Maps all fields directly to XML
@JsonInclude(JsonInclude.Include.NON_NULL) // Excludes null values in JSON
@JsonIgnoreProperties(ignoreUnknown = true) // Ignores unknown JSON properties
public class User {

    @XmlElement(name = "LoginName")
    @JsonProperty("loginName")
    private String loginName;

    @XmlElement(name = "FirstName")
    @JsonProperty("firstName")
    private String firstName;

    @XmlElement(name = "LastName")
    @JsonProperty("lastName")
    private String lastName;

    @XmlElement(name = "UUID")
    @JsonProperty("uuid")
    private String uuid;

    @XmlElement(name = "FirmNumber")
    @JsonProperty("firmNumber")
    private String firmNumber;

    @XmlElement(name = "AccountNumber")
    @JsonProperty("accountNumber")
    private String accountNumber;

    @XmlElement(name = "CompanyName")
    @JsonProperty("companyName")
    private String companyName;

    @XmlElement(name = "EmailAddress") 
    @JsonProperty("emailAddress")
    private String emailAddress;
    
    @XmlElement(name = "CorporateEmailAddress")
    @JsonProperty("corporateEmailAddress")
    private String corporateEmailAddress;
}
