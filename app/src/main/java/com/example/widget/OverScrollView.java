package com.example.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by admin on 2016/12/19.
 */
public class OverScrollView extends ScrollView {

    private ScrollView parentScrollView;
    private LinearLayout mLinearlayout;
    private View handlerView;
    private int currentY;

    public OverScrollView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        init();
    }

    private void init(){
        mLinearlayout = new LinearLayout(getContext());
        mLinearlayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        addView(mLinearlayout,params);
    }

    public LinearLayout getContextView(){
        return mLinearlayout;
    }

    public void setHandlerView(View view){
        handlerView = view;
    }

    public void setParentView(ScrollView mContentView){
        parentScrollView = mContentView;
    }

    private int getScrollRange() {
        int scrollRange = 0;
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            scrollRange = Math.max(0, child.getHeight() - (getHeight()));
        }
        return scrollRange;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (parentScrollView == null) {
            return super.onInterceptTouchEvent(ev);
        } else {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {// 将父scrollview的滚动事件拦截
                currentY = (int)ev.getY();
                setParentScrollAble(false);
                return super.onInterceptTouchEvent(ev);
            } else if (ev.getAction() == MotionEvent.ACTION_UP) {// 把滚动事件恢复给父Scrollview
                setParentScrollAble(true);
            } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    private boolean isTriggerUpEvent(){
        int scroolY = getScrollY();
        int top = handlerView.getTop();
        int bottom = handlerView.getBottom();
        return scroolY >= top && bottom < scroolY + getHeight();
    }

    private boolean isTriggerDownEvent(){
        int scroolY = getScrollY();
        int top = handlerView.getTop();
        int bottom = handlerView.getBottom();
        return scroolY + getHeight() < bottom && scroolY < top;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        View child = getChildAt(0);
        if (parentScrollView != null) {
            if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                int height = child.getMeasuredHeight();
                height = height - getMeasuredHeight();// System.out.println("height=" + height);
                int scrollY = getScrollY();// System.out.println("scrollY" + scrollY);
                int y = (int)ev.getY();// 手指向下滑动
                if (currentY < y) {
                    if (isTriggerDownEvent()) {// 如果向下触发事件，就把滚动交给父Scrollview
                        setParentScrollAble(true);
                        return false;
                    } else {
                        setParentScrollAble(false);
                    }
                } else if (isTriggerUpEvent()) {
                    if (scrollY >= height) {// 如果向上触发事件，就把滚动交给父Scrollview
                        setParentScrollAble(true);
                        return false;
                    } else {
                        setParentScrollAble(false);
                    }
                }
                currentY = y;
            }
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 是否把滚动事件交给父scrollview
     *
     * @param able
     */
    private void setParentScrollAble(boolean able) {
        parentScrollView.requestDisallowInterceptTouchEvent(!able);
    }

}
