package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.li.mvpprogram.R;
import com.li.mvpprogram.utils.StringUtils;
import com.li.mvpprogram.utils.ToastUtils;


public class SearchView extends RelativeLayout {
    private SearchEditText editText;
    private ImageView deleteImg;
    private TextView actionTv;
    private FrameLayout framelayout;

    public SearchView(Context context) {
        super(context);
        initView(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_searchview,this,true);
        framelayout = (FrameLayout)findViewById(R.id.framelayout);
        editText = (SearchEditText)findViewById(R.id.et_search_input);
        deleteImg = (ImageView)findViewById(R.id.iv_search_delete);
        actionTv = (TextView)findViewById(R.id.tv_action);
        editTextHandle(context);
        initClick(context);

    }

    public void setEditText(String editContent){
        if (editText == null || TextUtils.isEmpty(editContent))return;
        editText.setText(editContent);
        editText.setSelection(editContent.length());
    }

    public void setEditHintText(String hint){
        if (editText == null || TextUtils.isEmpty(hint))return;
        editText.setHint(hint);
    }

    private void dismissDeleteImg(){
        if (deleteImg == null)return;
        deleteImg.setVisibility(GONE);
    }
    private void showDeleteImg(){
        if (deleteImg == null)return;
        deleteImg.setVisibility(VISIBLE);
    }

    //是否显示删除图片
    public void isShowDeleteImg(boolean flag){
        if (flag){
            if (TextUtils.isEmpty(getEditText())){
                dismissDeleteImg();
            }else {
                showDeleteImg();
            }
        }else {
            dismissDeleteImg();
        }
    }
    public void setFocusable(Context context){
        if (editText == null)return;
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setSelected(true);

        showSoftKeyboard(context);
    }
    /**
     * textview 显示内容
     * @param type 0 显示取消，1显示搜索
     * @param context
     */
    private void setActionTv(int type,Context context){
        if (actionTv == null || context == null)return;
        switch (type){
            case 0:
                actionTv.setText(context.getText(R.string.search_search));
                break;
            case 1:
                actionTv.setText(context.getText(R.string.search_search));
                break;

                default:
                    actionTv.setText(context.getText(R.string.search_search));
                    break;
        }

    }

    private void editTextHandle(final Context context){
        if (editText == null || context == null)return;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String name = s.toString();
                if (!StringUtils.isEmpty(name)) {
                    showDeleteImg();
                    setActionTv(1,context);
                    if (name.length() >= 18){
                        ToastUtils.show(context,"最多输入18个字");
                    }
                } else {
                    dismissDeleteImg();
                    setActionTv(0,context);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initClick(Context context){

        if (context == null)return;

        deleteImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                dismissDeleteImg();
            }
        });

    }

    public void setOnClickListener(OnClickListener onClickListener){
        actionTv.setOnClickListener(onClickListener);
        editText.setOnClickListener(onClickListener);
    }

    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener){
        editText.setOnFocusChangeListener(onFocusChangeListener);
    }

    public String getEditText(Context context) {
        if (editText == null || context == null)return "";
        if (TextUtils.isEmpty(editText.getText().toString().trim())){
//            ToastUtils.show(context,"搜索内容不能为空");
            return "";
        }
        return editText.getText().toString().trim();
    }
    public String getEditText() {
        if (editText == null)return "";
        return editText.getText().toString().trim();
    }
    public String getActionText() {
        if (actionTv == null)return "";
        return actionTv.getText().toString().trim();
    }

    public void clearFocusable(Context context){
        if (editText == null)return;
        if (framelayout == null)return;
        framelayout.requestFocus();
        framelayout.setFocusable(true);
        framelayout.setFocusableInTouchMode(true);
        editText.clearFocus();
        editText.setSelected(false);

        hideSoftKeyboard(context);
    }


    private void hideSoftKeyboard(Context context){
        if (editText == null || context == null)return;
        InputMethodManager imm = (InputMethodManager) context. getSystemService(context.INPUT_METHOD_SERVICE);
        if (imm == null)return;
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0); //强制隐藏键盘
    }

    private void showSoftKeyboard(Context context){
        if (editText == null || context == null)return;
        InputMethodManager imm = (InputMethodManager)context. getSystemService(context.INPUT_METHOD_SERVICE);
        if (imm == null)return;
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

}
