/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.akingyin.img.model;

import java.io.Serializable;

/**
 * 图文 显示
 * @author king
 * @version V1.0
 * @ Description:
 *
 *
 * @ Date 2017/12/5 11:34
 */
public class ImageTextModel  implements Serializable {

    private static final long serialVersionUID = -3037511222172328451L;
    public    String   localPath;

    public    String   serverPath;

    public    String   text;

    public    String   title;

    /**
     * 0=文字 1=图片 2=视频 3=音频
     */
    public    int      multimediaType;

    //是否连接网络获取
    public     boolean     haveNetServer;


    public ImageTextModel() {
    }

    public ImageTextModel(String localPath, String serverPath, String text,int multimediaType, boolean haveNetServer) {
        this.localPath = localPath;
        this.serverPath = serverPath;
        this.text = text;
        this.haveNetServer = haveNetServer;
        this.multimediaType = multimediaType;
    }

    public ImageTextModel(String localPath, String serverPath, String text,int multimediaType) {
        this.localPath = localPath;
        this.multimediaType = multimediaType;
        this.serverPath = serverPath;
        this.text = text;
    }

    public   static   ImageTextModel buildWebModel(String   serverPath,int multimediaType){

        return  buildWebModel(serverPath,"",multimediaType);
    }

    public   static   ImageTextModel buildWebModel(String   serverPath,String  title,int multimediaType){
        ImageTextModel   imageTextModel = new ImageTextModel();
        imageTextModel.haveNetServer = true;
        imageTextModel.multimediaType = multimediaType;
        imageTextModel.serverPath = serverPath;
        imageTextModel.title = title;
        return  imageTextModel;
    }

    public   static   ImageTextModel buildModel(String  localPath,String   serverPath,String  title,int multimediaType){
        ImageTextModel   imageTextModel = new ImageTextModel();
        imageTextModel.multimediaType = multimediaType;
        imageTextModel.haveNetServer = true;
        imageTextModel.serverPath = serverPath;
        imageTextModel.title = title;
        imageTextModel.localPath = localPath;
        return  imageTextModel;
    }

}
