package com.example.admin.mydemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.adapter.MyAdapter;
import com.example.model.ItemModel;
import com.example.widget.OverScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ScrollView mScrollView1;
    private OverScrollView mScrollView2;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollView1 = (ScrollView)findViewById(R.id.scroolview1);
        mScrollView2 = (OverScrollView)findViewById(R.id.scrollview2);
        mLinearLayout = mScrollView2.getContextView();
        mScrollView2.setParentView(mScrollView1);
        init();
    }

    private void init(){
        List<ItemModel> list = new ArrayList<ItemModel>();
        ItemModel model = new ItemModel();
        model.color = Color.parseColor("#cccccc");
        list.add(model);
        model = new ItemModel();
        model.color = Color.parseColor("#cccccc");
        list.add(model);
        model = new ItemModel();
        model.color = Color.parseColor("#cccccc");
        list.add(model);
        model = new ItemModel();
        model.color = Color.parseColor("#00ff00");
        list.add(model);
        model = new ItemModel();
        model.color = Color.parseColor("#cccccc");
        list.add(model);
        model = new ItemModel();
        model.color = Color.parseColor("#cccccc");
        list.add(model);
        model = new ItemModel();
        model.color = Color.parseColor("#cccccc");
        list.add(model);
        MyAdapter adapter = new MyAdapter(this,list);
        mLinearLayout.removeAllViews();
        for(int i = 0;i < adapter.getCount();i++){
            mLinearLayout.addView(adapter.getView(i),adapter.getParams(i));
        }

        for(int i = 0;i < mLinearLayout.getChildCount();i++){
            ItemModel itemModel = (ItemModel)mLinearLayout.getChildAt(i).getTag();
            if(itemModel.color == Color.parseColor("#00ff00")){
                mScrollView2.setHandlerView(mLinearLayout.getChildAt(i));
                return;
            }
        }
    }
}
