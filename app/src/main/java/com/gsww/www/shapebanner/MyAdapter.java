package com.gsww.www.shapebanner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author   : luweicheng on 2017/6/15 0015 10:40
 * E-mail   ：1769005961@qq.com
 * GitHub   : https://github.com/luweicheng24
 * funcation:
 */

public class MyAdapter extends PagerAdapter {
    private Context mContext;
    private List<View> data;
    public MyAdapter(Context context ,List<View> data){
            mContext = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView(data.get(position%data.size()));
    }

    @Override
    public Object instantiateItem(View container, int position) {
                ((ViewPager)container).addView(data.get(position%data.size()),0);


        return data.get(position%data.size());
    }


}
