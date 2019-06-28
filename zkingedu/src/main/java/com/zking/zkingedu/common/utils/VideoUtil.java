package com.zking.zkingedu.common.utils;

import com.youku.uploader.YoukuUploader;

import java.util.HashMap;

/**
 * 视频上传至优酷工具类
 * yan
 */
public class VideoUtil {


    private static YoukuUploader uploader;


    private static String client_id="59c9a21060e1d847";

    private static String client_secret="b755241273f070a6e268dbccec738f2d";

    private static String access_token="2a5a40a1c30244b120f3e66427376f08";

    /**
     * 视频上传至优酷  返回优酷视频id
     * @return
     */
    public static String videoUpload(){
//        String client_id = "59c9a21060e1d847";
//        String client_secret = "b755241273f070a6e268dbccec738f2d";
        String result = "";
//        String access_token = "2a5a40a1c30244b120f3e66427376f08";
        HashMap<String, String> params, uploadInfo;
        String filename = "C:\\Users\\Administrator\\Desktop\\97fb9e3274938fbb4e7679b8ff222cc3.mp4";//视频绝对路径
        params = new HashMap<String, String>();
        params.put("access_token", access_token);
        uploadInfo = new HashMap<String, String>();
        uploadInfo.put("target", "youku"); // 上传目标可以选择上传至优酷或者视频云 必填 youku or cloud
        uploadInfo.put("file_name", filename); // 指定：文件名
        uploadInfo.put("title", "java上传测试"); // 指定：标题
        uploadInfo.put("tags", "原创"); // 指定：分类
        uploadInfo.put("public_type", "all"); //视频公开类型（all：公开（默认），friend：仅好友，password：需要输入密码才能观看）
        //  uploadInfo.put("watch_password", "123456"); //密码，当public_type为password时，必填
        uploader = new YoukuUploader(client_id, client_secret);
        result = uploader.upload(params, uploadInfo, filename, false); // 第四个参数：boolean类型（true：显示进度 false：不显示进度）
        return result;
    }


//    public static void main(String[] args) {
//        System.err.println("开始上传");
//        String s = videoUpload();
//
//        System.err.println("上传结束视频id:"+s);
//
//    }
}
