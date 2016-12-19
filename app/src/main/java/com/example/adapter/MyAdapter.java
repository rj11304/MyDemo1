package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/12/19.
 */
public class MyAdapter {

    private Context mContext;
    private List<ItemModel> list = new ArrayList<ItemModel>();

    public MyAdapter(Context context, List<ItemModel> models){
        mContext = context;
        if(models != null){
            list.clear();
            list.addAll(models);
        }
    }

    public View getView(int position){
        ItemModel model = getItemModel(position);
        View view = new View(mContext);
        view.setBackgroundColor(model.color);
        view.setTag(model);
        return view;
    }

    public int getCount(){
        return list.size();
    }

    public ViewGroup.LayoutParams getParams(int position){
        int dp = (int)mContext.getResources().getDisplayMetrics().density;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,50*dp);
        params.rightMargin = 15*dp;
        params.leftMargin = 15*dp;
        params.topMargin = 15*dp;
        params.bottomMargin = 15*dp;
        return params;
    }

    public ItemModel getItemModel(int position){
        return list.get(position);
    }
}
