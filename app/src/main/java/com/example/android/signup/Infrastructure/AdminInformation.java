package com.example.android.signup.Infrastructure;

public class AdminInformation {
    private String uid;
    private String key;
    private String name;
    private String email;
    private String mobile;
    private int status;
    private String Locality;

    public AdminInformation() {
    }

    public AdminInformation(String uid, String key, String name, String email, String mobile, int status, String locality) {
        this.uid = uid;
        this.key = key;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.status = status;
        Locality = locality;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {

        return status;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }

    public String getLocality() {

        return Locality;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String phoneNumber) {
        this.mobile = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUid() {

        return uid;
    }

    public String getKey() {
        return key;
    }
}
