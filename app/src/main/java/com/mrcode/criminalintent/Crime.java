package com.mrcode.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by mrcode on 16-6-1.
 */
public class Crime {
    private UUID mID;
    private String mTitle;
    private boolean mSolved;

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    private Date mDate;
    public Crime(){
        mID = UUID.randomUUID();
        mDate = new Date();
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public UUID getID(){
        return mID;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String title){
        mTitle = title;
    }
}
