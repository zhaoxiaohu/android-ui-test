package com.example.guohl.myuitest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class TouchTv extends TextView {

    private String TAG = TouchTv.class.getSimpleName();

    public TouchTv(Context context) {
        this(context, null);
    }

    public TouchTv(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchTv(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        TouchUtils.showEventInfo(TAG + "#dispatchTouchEvent", ev.getAction());
        boolean result = super.dispatchTouchEvent(ev);

        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        TouchUtils.showEventInfo(TAG + "#onTouchEvent", ev.getAction());
        boolean result = super.onTouchEvent(ev);
        Log.e(TAG, TAG + "--> [onTouchEvent] result = " + "false"/*result*/);
        return false;
    }
}
