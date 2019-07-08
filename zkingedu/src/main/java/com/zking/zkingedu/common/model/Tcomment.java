package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 帖子评论表
 * @ClassName Tcomment
 * @Author likai
 **/

@Component
public class Tcomment implements Serializable {
    private static final long serialVersionUID = -6015599453427748055L;
    //帖子评论id'
    private Integer tcommentID;
    //帖子id
    private Integer tcommentCid;
    //用户id
    private Integer tcommentUid;
    //评论内容
    private String tcommentContent;
    //评论时间
    private String tcommentTime;
    //评论回复父id
    private Integer tcommentFid;

    //评论对应的uid,及用户
    private User user2;

    //某条评论下面的所有评论
    List<Tcomment> chirden=new ArrayList<>();
    //配关系
    private User user;
    //楼主顺序
    private  Integer tcommentLounum;
    //要回复谁的id
    private  Integer tcommentUid2;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getTcommentID() {
        return tcommentID;
    }

    public void setTcommentID(Integer tcommentID) {
        this.tcommentID = tcommentID;
    }

    public Integer getTcommentCid() {
        return tcommentCid;
    }

    public void setTcommentCid(Integer tcommentCid) {
        this.tcommentCid = tcommentCid;
    }

    public Integer getTcommentUid() {
        return tcommentUid;
    }

    public void setTcommentUid(Integer tcommentUid) {
        this.tcommentUid = tcommentUid;
    }

    public String getTcommentContent() {
        return tcommentContent;
    }

    public void setTcommentContent(String tcommentContent) {
        this.tcommentContent = tcommentContent;
    }

    public String getTcommentTime() {
        return tcommentTime;
    }

    public void setTcommentTime(String tcommentTime) {
        this.tcommentTime = tcommentTime;
    }

    public Integer getTcommentFid() {
        return tcommentFid;
    }

    public void setTcommentFid(Integer tcommentFid) {
        this.tcommentFid = tcommentFid;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public List<Tcomment> getChirden() {
        return chirden;
    }

    public void setChirden(List<Tcomment> chirden) {
        this.chirden = chirden;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTcommentLounum() {
        return tcommentLounum;
    }

    public void setTcommentLounum(Integer tcommentLounum) {
        this.tcommentLounum = tcommentLounum;
    }

    public Integer getTcommentUid2() {
        return tcommentUid2;
    }

    public void setTcommentUid2(Integer tcommentUid2) {
        this.tcommentUid2 = tcommentUid2;
    }

    @Override
    public String toString() {
        return "Tcomment{" +
                "tcommentID=" + tcommentID +
                ", tcommentCid=" + tcommentCid +
                ", tcommentUid=" + tcommentUid +
                ", tcommentContent='" + tcommentContent + '\'' +
                ", tcommentTime='" + tcommentTime + '\'' +
                ", tcommentFid=" + tcommentFid +
                ", user2=" + user2 +
                ", chirden=" + chirden +
                ", user=" + user +
                ", tcommentLounum=" + tcommentLounum +
                ", tcommentUid2=" + tcommentUid2 +
                '}';
    }
}
