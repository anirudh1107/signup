package com.example.android.signup.Infrastructure;

/**
 * Created by dellpc on 12/23/2017.
 */

public class UserAddInfo {

    private String EmailId;
    private String Password;
    private String Address;
    private String Mobile;
    private String Username;
    private String key;
    private String Locality;


    public UserAddInfo(String emailId, String password, String address, String mobile, String username, String key, String locality) {
        EmailId = emailId;
        Password = password;
        Address = address;
        Mobile = mobile;
        Username = username;
        this.key = key;
        Locality = locality;
    }

    public String getLocality() {
        return Locality;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }
    public UserAddInfo() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmailId() {
        return EmailId;
    }

    public String getPassword() {
        return Password;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }
//
    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getUsername() {
        return Username;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public void setUsername(String username) {
        Username = username;
    }

}
