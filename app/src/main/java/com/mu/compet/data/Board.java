package com.mu.compet.data;

import java.io.Serializable;

/**
 * Created by Mu on 2016-10-20.
 */

public class Board implements Serializable{

    private int userNum;
    private String userId;
    private String userNick;
    private int boardNum;
    private String boardContent;
    private String boardRegDate;
    private String boardUdtDate;
    private String boardFirstImg;
    private int imgCnt;
    private int audCnt;
    private int vidCnt;
    private int replyCnt;

    public String getBoardFirstImg() {
        return boardFirstImg;
    }

    public void setBoardFirstImg(String boardFirstImg) {
        this.boardFirstImg = boardFirstImg;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public int getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardRegDate() {
        return boardRegDate;
    }

    public void setBoardRegDate(String boardRegDate) {
        this.boardRegDate = boardRegDate;
    }

    public String getBoardUdtDate() {
        return boardUdtDate;
    }

    public void setBoardUdtDate(String boardUdtDate) {
        this.boardUdtDate = boardUdtDate;
    }

    public int getImgCnt() {
        return imgCnt;
    }

    public void setImgCnt(int imgCnt) {
        this.imgCnt = imgCnt;
    }

    public int getAudCnt() {
        return audCnt;
    }

    public void setAudCnt(int audCnt) {
        this.audCnt = audCnt;
    }

    public int getVidCnt() {
        return vidCnt;
    }

    public void setVidCnt(int vidCnt) {
        this.vidCnt = vidCnt;
    }

    public int getReplyCnt() {
        return replyCnt;
    }

    public void setReplyCnt(int replyCnt) {
        this.replyCnt = replyCnt;
    }

}