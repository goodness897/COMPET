package com.mu.compet.data;

import android.graphics.drawable.Drawable;

/**
 * Created by Mu on 2016-10-20.
 */

public class Reply {

    private int userNum;
    private String userNick;
    private String userId;
    private int replyNum;
    private String replycontent;
    private String replyRegDate;
    private String replyUdtDate;


    private Drawable profileImage;
    private String nickName;
    private String date;
    private String content;

    public Drawable getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Drawable profileImage) {
        this.profileImage = profileImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
