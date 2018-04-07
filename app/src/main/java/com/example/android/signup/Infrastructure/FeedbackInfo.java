package com.example.android.signup.Infrastructure;

/**
 * Created by utkarshh12 on 4/7/2018.
 */

public class FeedbackInfo {
    private String detail;
    private String Uid;
    private String Locality;
    public FeedbackInfo()
    {

    }
    public String getLocality() {
        return Locality;
    }

    public FeedbackInfo(String detail, String uid, String locality) {
        this.detail = detail;
        this.Uid = uid;
        Locality = locality;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }

    public String getDetail() {
        return detail;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        this.Uid = uid;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


}
