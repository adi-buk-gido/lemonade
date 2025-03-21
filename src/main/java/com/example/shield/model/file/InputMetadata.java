package com.example.shield.model.file;

import lombok.Data;

@Data
public class InputMetadata {
    private SourceType sourceType;
    private String inputId;
    private String inputFormat;
}