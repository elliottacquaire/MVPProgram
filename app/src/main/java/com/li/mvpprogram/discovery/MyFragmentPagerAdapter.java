package com.li.mvpprogram.discovery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;
import com.li.mvpprogram.bean.MapVo;

import java.util.List;

/**
 */

public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private Fragment[] fragmentPages;
    private List<MapVo> pageTitles;
    private FragmentManager fragmentManager;
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentManager = fm ;
    }

    public void setPageTitles(List<MapVo> pageTitles) {
       this.pageTitles = pageTitles;
    }
    public void setFragmentPages(Fragment[] fragmentPages) {
        if (this.fragmentPages != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            for (Fragment f : this.fragmentPages) {
                ft.remove(f);
            }
            ft.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        this.fragmentPages = fragmentPages;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int position) {
        if (fragmentPages!= null && fragmentPages.length>position){
            return fragmentPages[position];
        }else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return fragmentPages != null ? fragmentPages.length: 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (pageTitles != null && pageTitles.size()>position){
            return pageTitles.get(position).getValue();
        }else {
            return "";
        }

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
			super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (pageTitles.size()<= position) return null;
        RecommendFragment fragment = (RecommendFragment) super.instantiateItem(container, position);
        fragment.updateArguments(pageTitles.get(position).getKey());
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
