package com.example.android.signup.Infrastructure;


public class UserInformation {

    private String Description;
    private String Address;
    private String Mobile;
    private int Status;
    private String Type;
    private String TypeDetail;
    private String key;
    private String cid;
    private String imageUID;
    private String Locality;
    private String Username;

    public void setUsername(String username) {
        Username = username;
    }

    public String getUsername() {

        return Username;
    }



    public UserInformation() {
    }

    public UserInformation(String description, String address, String mobile, int status, String type, String typeDetail, String key, String cid, String imageUID, String locality) {
        Description = description;
        Address = address;
        Mobile = mobile;
        Status = status;
        Type = type;
        TypeDetail = typeDetail;
        this.key = key;
        this.cid = cid;
        this.imageUID = imageUID;
        Locality = locality;
    }

    public String getDescription() {
        return Description;
    }

    public String getAddress() {
        return Address;
    }

    public String getMobile() {
        return Mobile;
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

    public void setAddress(String address) {
        Address = address;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
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

    public String getLocality() {
        return Locality;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }
}
