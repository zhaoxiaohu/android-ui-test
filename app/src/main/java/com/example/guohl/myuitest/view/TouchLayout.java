package com.example.guohl.myuitest.view;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
//import android.test.TouchUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class TouchLayout extends FrameLayout {

    private String TAG = TouchLayout.class.getSimpleName();

    public TouchLayout(Context context) {
        super(context);

    }

    public TouchLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        setClickable(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);

        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //
//        final int action = MotionEventCompat.getActionMasked(ev);
//        // Always handle the case of the touch gesture being complete.
//        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
//            // Do not intercept touch event, let the child handle it
//            return false;
//        }
//
//        TouchUtils.showEventInfo(TAG + "--> [onInterceptTouchEvent]", action);

        Boolean b = Boolean.FALSE;
        Log.e(TAG + "-->[onIntercept]", b.toString());
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        TouchUtils.showEventInfo(TAG + "--> [onTouchEvent]", ev.getAction());
        Log.d(TAG, "### is Clickable = " + isClickable());
        return super.onTouchEvent(ev);
//        return true;
    }

}
