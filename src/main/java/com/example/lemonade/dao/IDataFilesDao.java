package com.example.lemonade.dao;

import com.example.lemonade.model.file.FileProcessStatus;

public interface IDataFilesDao {

    public FileProcessStatus getFileRetStatus(String inputId);

    public void saveFileToDb();

    public void setStatus(String inputId, FileProcessStatus status);

}
