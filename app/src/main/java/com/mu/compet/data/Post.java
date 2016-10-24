package com.mu.compet.data;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Mu on 2016-10-20.
 */

public class Post implements Serializable{

    private Drawable profileImage;
    private String nickName;
    private String date;
    private Drawable postImage;
    private String imageCount;
    private String postContent;
    private String replyCount;

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

    public Drawable getPostImage() {
        return postImage;
    }

    public void setPostImage(Drawable postImage) {
        this.postImage = postImage;
    }

    public String getImageCount() {
        return imageCount;
    }

    public void setImageCount(String imageCount) {
        this.imageCount = imageCount;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }


}