package com.li.mvpprogram.bean;

import java.util.List;

public class RefreshFromChannelEvent {
    private List<MapVo> mapVoList;

    public RefreshFromChannelEvent(List<MapVo> mapVoList) {
        this.mapVoList = mapVoList;
    }

    public List<MapVo> getMapVoList() {
        return mapVoList;
    }

    public void setMapVoList(List<MapVo> mapVoList) {
        this.mapVoList = mapVoList;
    }
}
