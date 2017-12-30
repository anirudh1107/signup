package com.example.android.signup.Infrastructure;

/**
 * Created by dellpc on 12/30/2017.
 */

public class AdminProf {

    private String email;
    private String mobile;
    private String name;
    private String Locality;

    public AdminProf(String email, String mobile, String name, String locality) {
        this.email = email;
        this.mobile = mobile;
        this.name = name;
        Locality = locality;
    }

    public AdminProf() {
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocality() {
        return Locality;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }
}
