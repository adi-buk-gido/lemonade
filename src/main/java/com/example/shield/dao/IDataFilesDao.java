package com.example.shield.dao;

import com.example.shield.model.file.FileProcessStatus;

public interface IDataFilesDao {

    public FileProcessStatus getFileRetStatus(String inputId);

    public void saveFileToDb();

    public void setStatus(String inputId, FileProcessStatus status);

}
