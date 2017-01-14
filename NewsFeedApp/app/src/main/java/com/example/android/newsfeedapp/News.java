package com.example.android.newsfeedapp;



/**
 * Created by RAHUL YADAV on 12-01-2017.
 */

public class News {
    private String mWebTitle;
    private String mSectionName;
    private String mWebPublicationDate;
    private String mUrl;

    public News(String webTitle, String sectionName, String webPublicationDate, String url) {
        mWebTitle = webTitle;
        mSectionName = sectionName;
        mWebPublicationDate = webPublicationDate;
        mUrl = url;
    }




    public String getmWebPublicationDate() {
        return mWebPublicationDate;
    }

    public String getmSectionName() {
        return mSectionName;
    }

    public String getmWebTitle() {
        return mWebTitle;
    }

    public String getmUrl() {
        return mUrl;
    }
}
