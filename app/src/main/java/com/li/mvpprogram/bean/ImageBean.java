package com.li.mvpprogram.bean;

/**
 * [类功能说明]
 * 图片类
 *
 * @author ex-heguogui
 * @version v 2.0.0 2017/2/15 16:48 XLXZ Exp $
 * @email ex-heguoguo@xianglin.cn
 */


public class ImageBean {

    private String url;
    private int width;
    private int height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
