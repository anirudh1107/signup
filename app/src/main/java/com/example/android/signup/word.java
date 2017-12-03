package com.example.android.signup;

import android.widget.LinearLayout;

/**
 * Created by dellpc on 11/9/2017.
 */

public class word {


    private String Description;
    private String Location;
    private String MobNumber;
    private int Status;
    private String Type;
    private String TypeDetail;
    private String key;
    private String cid;
    private String imageUID;

    public word() {
    }

    public word(String description, String location, String mobNumber, int status, String type,String typeDetail,String Cid,String imageuid) {
        Description = description;
        Location = location;
        MobNumber = mobNumber;
        Status = status;
        Type = type;
        TypeDetail=typeDetail;
        cid=Cid;
        imageUID=imageuid;

    }

    public String getDescription() {
        return Description;
    }

    public String getLocation() {
        return Location;
    }

    public String getMobNumber() {
        return MobNumber;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getType() {
        return Type;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setMobNumber(String mobNumber) {
        MobNumber = mobNumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        this.Status = status;
    }

    public String getTypeDetail() {
        return TypeDetail;
    }

    public void setTypeDetail(String typeDetail) {
        TypeDetail = typeDetail;
    }

    public String getImageUID() {
        return imageUID;
    }

    public void setImageUID(String imageUID) {
        this.imageUID = imageUID;
    }
}
