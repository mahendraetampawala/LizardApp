package com.example.lockbot;

public class FileModel {
    private int id;
    private String fileName;
    private String date;
    private String fileType;
    private Boolean visibility;

    public FileModel(int id, String fileName, String date, String fileType, Boolean visibility) {
        this.id = id;
        this.fileName = fileName;
        this.date = date;
        this.fileType = fileType;
        this.visibility = visibility;
    }

    public FileModel() {
    }

    @Override
    public String toString() {
        return "FileModel{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", date='" + date + '\'' +
                ", fileType='" + fileType + '\'' +
                ", visibility=" + visibility +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }
}
