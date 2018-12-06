package com.li.mvpprogram.bean;

public class HistoryBean {
    private String title; //搜索历史记录

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HistoryBean)) {
            return false;
        }
        HistoryBean historyBean =  (HistoryBean)obj;
        return (this.title.equals(historyBean.title));
    }
}
