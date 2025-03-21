package com.example.lemonade.data;

import java.util.UUID;

import lombok.Data;

@Data
public class LemonadeRequest {

    UUID id;
    String name;
    String lastName;

}
