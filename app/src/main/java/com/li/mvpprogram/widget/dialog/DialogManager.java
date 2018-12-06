package com.li.mvpprogram.widget.dialog;

import android.app.Dialog;

/**
 * [MyProgressDialog,ProgressDialogManager管理类]
 */

public class DialogManager {

    private static Dialog mDialog;

    //展示
    public static boolean isShow() {
        if (mDialog == null) {
            return false;
        }

        return mDialog.isShowing();

    }

    //隐藏当前dialog
    public static void hideDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }

    }

    //仅有确定
    /*public static void showMyAlerDialog(Context context,
                                        String title,
                                        String content,
                                        MyAlertDialog.ConfirmListener confirmListener) {
        if (title == null || content == null) {
            return;
        }
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new MyAlertDialog(context, title, content, confirmListener);
        mDialog.show();

    }

    //确定，title的Gravity
    public static void showMyAlerDialog(Context context,
                                        String title,
                                        String content,
                                        MyAlertDialog.ConfirmListener confirmListener,
                                        int gravity) {
        if (title == null || content == null) {
            return;
        }
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new MyAlertDialog(context, title, content, confirmListener, gravity);
        mDialog.show();

    }

    //可确定，取消
    public static void showMyAlerDialog(Context context,
                                        String title,
                                        String content,
                                        MyAlertDialog.ConfirmListener confirmListener,
                                        MyAlertDialog.CancelListener cancelListener) {
        if (title == null || content == null) {
            return;
        }
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new MyAlertDialog(context, title, content, confirmListener, cancelListener);
        mDialog.show();
    }

    //可确定，取消
    public static void showMyAlerDialog(Context context,
                                        String title,
                                        String content,
                                        String confirmText,
                                        String cancelText,
                                        MyAlertDialog.ConfirmListener confirmListener,
                                        MyAlertDialog.CancelListener cancelListener,
                                        int gravity,
                                        boolean flag) {
        if (title == null || content == null) {
            return;
        }
        if (mDialog != null) {
            mDialog = null;
        }
        if(TextUtils.isEmpty(confirmText)){
            mDialog = new MyAlertDialog(context, title, content, confirmListener, gravity, flag);
        }else{
            mDialog = new MyAlertDialog(context, title, content, confirmText, cancelText, confirmListener, cancelListener, gravity, flag);
        }
        mDialog.show();
    }

    //可确定，取消,title的Gravity
    public static void showMyAlerDialog(Context context,
                                        String title,
                                        String content,
                                        MyAlertDialog.ConfirmListener confirmListener,
                                        MyAlertDialog.CancelListener cancelListener,
                                        int gravity) {
        if (title == null || content == null) {
            return;
        }
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new MyAlertDialog(context, title, content, confirmListener, cancelListener, gravity);
        mDialog.show();
    }

    //进度
    public static void showProgressDialog(Context context, String message) {
        if (message == null) {
            return;
        }
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new MyProgressDialog(context, message);
        mDialog.show();
    }

    public static void showTimerDialog(Context context, String title,
                                       String content,
                                       String content2,
                                       TimerDialog.ConfirmListener confirmListener,
                                       TimerDialog.CancelListener cancelListener) {
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new TimerDialog(context, title, content, content2 ,confirmListener, cancelListener);
        mDialog.show();
    }

    public static void showGuidePagesDialog(Context context) {
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new GuidePagesDialog(context);
        mDialog.show();
    }

    public static Dialog getDialog() {
        return mDialog;
    }

    *//**
     * 群邀请对话框
     * @param context 上下文
     * @param content 展示内容
     * @param infirmListener 确定回调
     *//*
    public static void showGroupInviteDialog(Context context, String content, GroupInviteDialog.InfirmListener infirmListener) {
        if (mDialog != null) {
            mDialog = null;
        }
        mDialog = new GroupInviteDialog(context,content,infirmListener);
        mDialog.show();
    }*/
}
