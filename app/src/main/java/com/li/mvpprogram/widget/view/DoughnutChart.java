/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import com.li.mvpprogram.R;
import com.li.mvpprogram.utils.XChartCalcUtils;

/**
 * 环形图
 * ex-heguogui
 * 2016/8/12
 */
public class DoughnutChart extends View {

    private int ScrWidth, ScrHeight;
    //默认比例参数
    private float arrPer[] = new float[]{100f};
    //RGB颜色数组
    private int arrColorRgb[][] = {{135, 206, 250}, {255, 182, 193}, {255, 228, 181}, {64, 224, 208}};

    private int[] bankColor = {156, 207, 253};
    private int[] shopColor = {255, 166, 165};
    private int[] loanColor = {255, 237, 184};
    private int[] mobileColor = {255, 237, 184};
    private int[] rechangeColor = {186, 244, 227};
    private int[] defaultColor = {240, 240, 240};
    private String[] mType = {""};
    private Context mContext;

    public DoughnutChart(Context context) {
        super(context);
    }

    public DoughnutChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        ScrHeight = dm.heightPixels;
        ScrWidth = dm.widthPixels;
        this.mContext = context;
    }

    public DoughnutChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void onDraw(Canvas canvas) {
        //画布背景

        float cirX = ScrWidth / 7 + 15;//环形的宽度
        float cirY = ScrHeight / 10;//环形的高度
        float radius = ScrHeight / 13;//环框大小

        float arcLeft = cirX - radius;
        float arcTop = cirY - radius;
        float arcRight = cirX + radius;
        float arcBottom = cirY + radius;
        RectF arcRF0 = new RectF(arcLeft, arcTop, arcRight, arcBottom);

        //画笔初始化
        Paint PaintArc = new Paint();
        PaintArc.setAntiAlias(true);
        Paint PaintLabel = new Paint();
        PaintLabel.setColor(Color.WHITE);
        PaintLabel.setTextSize(10);
        //位置计算类
        XChartCalcUtils xcalc = new XChartCalcUtils();

        float Percentage = 0.0f;
        float CurrPer = 0.0f;
        int i = 0;
        for (i = 0; i < arrPer.length; i++) {
            //将百分比转换为饼图显示角度
            Percentage = 360 * (arrPer[i] / 100);
            Percentage = (float) (Math.round(Percentage * 100)) / 100;
            //分配颜色
            if (mType[i].equals(mContext.getString(R.string.app_name))) {
                PaintArc.setARGB(255, shopColor[0], shopColor[1], shopColor[2]);
            } else if (mType[i].equals(mContext.getString(R.string.app_name))) {
                PaintArc.setARGB(255, rechangeColor[0], rechangeColor[1], rechangeColor[2]);
            } else if (mType[i].equals(mContext.getString(R.string.app_name))) {
                PaintArc.setARGB(255, mobileColor[0], mobileColor[1], mobileColor[2]);
            } else {
                PaintArc.setARGB(255, defaultColor[0], defaultColor[1], defaultColor[2]);
            }
            //在饼图中显示所占比例
            canvas.drawArc(arcRF0, CurrPer, Percentage, true, PaintArc);
            //计算百分比标签
            xcalc.CalcArcEndPointXY(cirX, cirY, radius - radius / 2 / 2, CurrPer + Percentage / 2);
            //下次的起始角度
            CurrPer += Percentage;
        }

        //画圆心颜色
        PaintArc.setColor(Color.WHITE);
        canvas.drawCircle(cirX, cirY, (3 * radius) / 4, PaintArc);

    }

    /**
     * 设置值
     *
     * @param values 比例值
     * @param types  类型值
     */
    public void setValues(float values[], String types[]) {
        this.mType = types;
        this.arrPer = values;
        invalidate();
    }


}

