package com.li.mvpprogram.base;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.li.mvpprogram.R;
import com.li.mvpprogram.bean.HistoryBean;
import com.li.mvpprogram.config.Constants;
import com.li.mvpprogram.utils.PreferencesUtils;
import com.li.mvpprogram.utils.json.GsonUtils;
import com.li.mvpprogram.widget.view.SearchView;
import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchToolbarActivity extends ToolbarActivity {
    private SearchView custom_search;
    private Disposable disposable;
    private List<HistoryBean> historyList = new ArrayList<>();

    private HistoryRecyclerAdapter historyRecyclerAdapter;
    private TextView textview_clear_history;
    private LinearLayout linear_history;
    private FrameLayout frameLayout;

    private final int LENGTH = 6;  //保存最近的6条记录

    private String historyType = "ALL";
    private String HISTORYKEY = Constants.SP_KEY_SEARCH_CONTENT;

    public boolean isShowHistory = true;

    @Override
    protected void init() {
        super.init();
        showToolbar(false);
        initToolBar();
    }

    private void initToolBar() {
        custom_search = (SearchView)findViewById(R.id.custom_search);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.history_recycler);
        textview_clear_history = (TextView)findViewById(R.id.textview_clear_history);
        linear_history = (LinearLayout)findViewById(R.id.linear_history);
        frameLayout = (FrameLayout)findViewById(R.id.contentFrame);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        historyRecyclerAdapter = new HistoryRecyclerAdapter(this);
        recyclerView.setAdapter(historyRecyclerAdapter);

        if (isShowHistory){
            readHistoryFromLocal();
        }

        custom_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.tv_action:
                        if (custom_search.getActionText().equals(getString(R.string.search_cancle))){
                            finish();
                        }else if (custom_search.getActionText().equals(getString(R.string.search_search))){
                            //保存历史记录
                            custom_search.isShowDeleteImg(false);
                            setSoftInputMode(false);
                            if (TextUtils.isEmpty(custom_search.getEditText()))return;
                            saveHistoryToLocal(custom_search.getEditText());
                            custom_search.clearFocusable(SearchToolbarActivity.this);
                            hideSoftKeyboard();
                            linear_history.setVisibility(View.GONE);
                            frameLayout.setVisibility(View.VISIBLE);
                            searchData(custom_search.getEditText(SearchToolbarActivity.this));
                        }
                        break;
                    case R.id.et_search_input:
                        custom_search.isShowDeleteImg(true);
                        readHistoryFromLocal();

                        break;
                }

            }
        });

        custom_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                        custom_search.isShowDeleteImg(true);
                        readHistoryFromLocal();

                }else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            custom_search.isShowDeleteImg(false);
                            linear_history.setVisibility(View.GONE);
                            frameLayout.setVisibility(View.VISIBLE);
                        }
                    },80);

                }
            }
        });


        textview_clear_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearHistoryToLocal();
                historyList.clear();
                setHistoryBeans(historyList);
                linear_history.setVisibility(View.GONE);
                frameLayout.setVisibility(View.VISIBLE);
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (adapter == null)return;
                HistoryBean historyBean = (HistoryBean) adapter.getData().get(position);
                switch (view.getId()){
                    case R.id.img_delete:
                        adapter.getData().remove(position);
                        adapter.notifyDataSetChanged();
                        if (adapter.getData().size()<=0){
                            textview_clear_history.setVisibility(View.GONE);
                        }
                        saveHistoryToLocal(adapter.getData());
                        break;
                    case R.id.linear_histroy:
                         setEditText(historyBean.getTitle());
                        break;
                }
            }
        });

    }


    public void setHistoryBeans(List<HistoryBean> historyBeans) {
        if (historyBeans == null || historyRecyclerAdapter == null)return;
        historyRecyclerAdapter.setNewData(historyBeans);
        historyRecyclerAdapter.notifyDataSetChanged();
        if (historyBeans.size() >0 ){
            if (textview_clear_history == null)return;
            textview_clear_history.setVisibility(View.VISIBLE);
        }
    }

    public void setEditText(String edit){
        if (TextUtils.isEmpty(edit)){
            readHistoryFromLocal();
            setSoftInputMode(true);
            custom_search.setFocusable(this);

            return;
        }
        setSoftInputMode(false);
        custom_search.setEditText(edit);
        linear_history.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        saveHistoryToLocal(custom_search.getEditText());
        custom_search.isShowDeleteImg(false);
        custom_search.clearFocusable(SearchToolbarActivity.this);
        hideSoftKeyboard();
        searchData(edit);

    }

    public void setEditHintText(String hint){
        if (TextUtils.isEmpty(hint))return;
        custom_search.setEditHintText(hint);
    }
    //历史记录类型  应用：ICON 微博：ARTICLE 全部ALL
    public void getHistoryType(String type){
        if (TextUtils.isEmpty(type))return;
        historyType = type;
        switch (type){
            case "ICON":
                HISTORYKEY = Constants.SP_KEY_SEARCH_CONTENT_ICON;
                isShowHistory = false;
                break;
            case "ARTICLE":
                HISTORYKEY = Constants.SP_KEY_SEARCH_CONTENT_ARTICAL;
                isShowHistory = false;
                break;
            case "ALL":
                default:
                    HISTORYKEY = Constants.SP_KEY_SEARCH_CONTENT;
                    break;

        }

    }

    private void setSoftInputMode(boolean isVisible){
        if (HISTORYKEY.equals(Constants.SP_KEY_SEARCH_CONTENT_ARTICAL)){
            if (isVisible){
                this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }else {
                this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }

        }
    }

    protected abstract void searchData(String content);

    //读取本地搜索历史记录
    private void readHistoryFromLocal() {
//        linear_history.setVisibility(View.VISIBLE);
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String historyContent = PreferencesUtils.getString(Constants.SEARCH_PREFERENCE,
                        SearchToolbarActivity.this, HISTORYKEY, "");
                emitter.onNext(historyContent);
            }
        }).subscribeOn(Schedulers.io())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return true;
                    }
                }).flatMap(new Function<String, ObservableSource<List<HistoryBean>>>() {
            @Override
            public ObservableSource<List<HistoryBean>> apply(String s) throws Exception {
                return Observable.just(GsonUtils.jsonToList(s,HistoryBean.class));
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<HistoryBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<HistoryBean> historyBeans) {
                        setHistoryBeans(historyBeans);
                        linear_history.setVisibility(View.VISIBLE);
                        frameLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {

                        linear_history.setVisibility(View.GONE);
                        frameLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    //保存历史记录
    private void saveHistoryToLocal(String editText) {
        if (TextUtils.isEmpty(editText))return;
        if (isShowHistory){
            if (historyRecyclerAdapter != null){
                historyList = historyRecyclerAdapter.getData();
            }
        }else {
            String historyContent = PreferencesUtils.getString(Constants.SEARCH_PREFERENCE,
                    SearchToolbarActivity.this, HISTORYKEY, "");
            if (!TextUtils.isEmpty(historyContent)){
                historyList = GsonUtils.jsonToList(historyContent,HistoryBean.class);
            }
        }

        HistoryBean historyBean = new HistoryBean();
        historyBean.setTitle(editText);
        if (historyList.contains(historyBean)){
            historyList.remove(historyList.indexOf(historyBean));
            historyList.add(0,historyBean);
        }else {
            if (historyList.size() < LENGTH){
                historyList.add(0,historyBean);
            }else {
                historyList.remove(LENGTH - 1);
                historyList.add(0,historyBean);
            }
        }

        saveHistoryToLocal(historyList);
    }

    //保存历史记录
    public void saveHistoryToLocal(List<HistoryBean> historyLists) {
        if (historyLists == null || historyLists.size() <= 0 || TextUtils.isEmpty(GsonUtils.GsonString(historyLists))){
            PreferencesUtils.putString(Constants.SEARCH_PREFERENCE, SearchToolbarActivity.this,
                    HISTORYKEY, "");
        }else {
            PreferencesUtils.putString(Constants.SEARCH_PREFERENCE, SearchToolbarActivity.this,
                    HISTORYKEY, GsonUtils.GsonString(historyLists));
        }
    }

    //清空历史记录
    public void clearHistoryToLocal() {
        PreferencesUtils.putString(Constants.SEARCH_PREFERENCE, SearchToolbarActivity.this,
                    HISTORYKEY, "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    //隐藏软键盘
    private void hideSoftKeyboard(){
        if (custom_search == null)return;
        InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        if (imm == null)return;
        imm.hideSoftInputFromWindow(custom_search.getWindowToken(), 0); //强制隐藏键盘
    }

    private void showSoftKeyboard(){
        if (custom_search == null)return;
        InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        if (imm == null)return;
        imm.showSoftInput(custom_search, 0);
    }

    private void closeInputMethod() {
        if (custom_search == null)return;
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (isOpen) {
            // imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//没有显示则显示
            imm.hideSoftInputFromWindow(custom_search.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //隐藏软键盘
    public void hideSoftKeyboard(boolean flag){
        if (flag){
            closeInputMethod(); //强制隐藏键盘
        }

    }

    public void clearFource(){
        custom_search.clearFocusable(this);
    }
}
