package com.example.lemonade.dao;

import java.util.HashMap;
import java.util.Map;

import com.example.lemonade.model.file.FileProcessStatus;

public class DataFilesDaoImp implements IDataFilesDao {

    private Map<String, FileProcessStatus> fileStatus = new HashMap<>();

    @Override
    public FileProcessStatus getFileRetStatus(String inputId) {
        FileProcessStatus status = fileStatus.get(inputId);
        return status;
    }

    @Override
    public void saveFileToDb() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveFileToDb'");
    }

    @Override
    public void setStatus(String inputId, FileProcessStatus status) {
        fileStatus.put(inputId, status);
    }

}
