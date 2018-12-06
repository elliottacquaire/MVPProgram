/*
 *
 *  *
 *  *  * 乡邻小站
 *  *  *   *Copyright (c) 2017 XiangLin,Inc.All Rights Reserved.
 *  *
 *
 */

package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;

/**
 * 【类功能说明】
 * 扫描身份证 rectView
 *
 * @author zhangxiang
 * @version v 3.2.0 2017/9/20 下午7:32 XLXZ Exp $
 * @email ex-zhangxiang@xianglin.cn
 */
public class MyRectView extends View {

    //取景框高度的比值
    protected final static double RECT_RATIO = 0.8;
    //取景框宽度与高度的比值，就是身份证的宽高比
    protected final static double ASPECT_RATIO = 1.586;

    private int screenWidth;
    private int screenHeight;
    private int rectWidth;
    private int rectHeight;
    private int top, bottom, left, right;
    public boolean[] drawEdges;
    private Paint pen_notEdge, pen_edge, pen_dack;
    private RectF[] dark_rect;

    private float lineRatioX, lineRatioY = (float) 0.1;

    public MyRectView(Context context, int width, int height) {
        super(context);
        screenWidth = width;
        screenHeight = height;
        rectHeight = (int) (height * RECT_RATIO);
        rectWidth = (int) (rectHeight * ASPECT_RATIO);
        top = (int) ((height - rectHeight) / 2);
        bottom = (int) ((height + rectHeight) / 2);
        left = (int) ((width - rectWidth) / 2);
        right = (int) ((width + rectWidth) / 2);

        lineRatioX = lineRatioY * rectHeight / rectWidth;

        this.drawEdges = new boolean[4];

        pen_notEdge = new Paint();
        pen_notEdge.setColor(Color.WHITE);
        pen_notEdge.setStrokeWidth(6);
        pen_notEdge.setStyle(Style.STROKE);
        pen_notEdge.setShadowLayer(4, 2, 2, Color.BLACK);

        pen_edge = new Paint();
        pen_edge.setColor(Color.WHITE);
        pen_edge.setStrokeWidth(10);
        pen_edge.setStyle(Style.STROKE);
        pen_edge.setShadowLayer(4, 2, 2, Color.BLACK);

        pen_dack = new Paint();
        pen_dack.setAlpha(180);
        pen_dack.setStyle(Style.FILL);

        dark_rect = new RectF[]{
                new RectF(0, 0, screenWidth, top),
                new RectF(0, top, left, bottom),
                new RectF(right, top, screenWidth, bottom),
                new RectF(0, bottom, screenWidth, screenHeight)
        };

        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (RectF r : dark_rect) {
            canvas.drawRect(r, pen_dack);
        }
        myDrawLine(canvas, left, top, left, bottom, drawEdges[0]);
        myDrawLine(canvas, right, top, right, bottom, drawEdges[1]);
        myDrawLine(canvas, left, top, right, top, drawEdges[2]);
        myDrawLine(canvas, left, bottom, right, bottom, drawEdges[3]);
    }

    private void myDrawLine(Canvas canvas, float startX, float startY, float stopX,
                            float stopY, Boolean isEdge) {
        if (isEdge)
            canvas.drawLine(startX, startY, stopX, stopY, pen_edge);
        else {
            //起点向终点画0.2，终点向起点画0.2
            canvas.drawLine(startX, startY, startX + (stopX - startX) * lineRatioX,
                    startY + (stopY - startY) * lineRatioY, pen_notEdge);
            canvas.drawLine(stopX, stopY, stopX + (startX - stopX) * lineRatioX,
                    stopY + (startY - stopY) * lineRatioY, pen_notEdge);
        }
    }

    public int getRectWidth() {
        return rectWidth;
    }

    public int getRectHeight() {
        return rectHeight;
    }
}