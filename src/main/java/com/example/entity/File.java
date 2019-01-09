package com.example.entity;

import java.sql.Date;

public class File {
    private  String fId;
    private  String fName;
    private String fType;
    private  String fSize;
    private Date fTime;

    public File(String fId, String fName, String fType, String fSize, Date fTime) {
        this.fId = fId;
        this.fName = fName;
        this.fType = fType;
        this.fSize = fSize;
        this.fTime = fTime;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public String getfSize() {
        return fSize;
    }

    public void setfSize(String fSize) {
        this.fSize = fSize;
    }

    public Date getfTime() {
        return fTime;
    }

    public void setfTime(Date fTime) {
        this.fTime = fTime;
    }
}
