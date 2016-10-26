package com.example.guohl.myuitest.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.guohl.myuitest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewEventActivity extends Activity {
    private static final String tag = ViewEventActivity.class.getSimpleName();

    @BindView(R.id.btnView)
    Button btn;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.activity_view_event)
    RelativeLayout parentViewGroup;

    MyLog btnLog = new MyLog("button");
    MyLog textLog = new MyLog("textView");
    MyLog parentLog = new MyLog("parentViewGroup");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        ButterKnife.bind(this);

        //
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnLog.logLongClick();
                return false;
            }
        });
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnLog.logTouchDown();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnLog.logTouchUp();
                }else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                    btnLog.logTouchMove();
                }else{

                }
                return false;
            }
        });
        //
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                textLog.logLongClick();
                return false;
            }
        });
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    textLog.logTouchDown();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    textLog.logTouchUp();
                }else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                    textLog.logTouchMove();
                }else{

                }
                return false;
            }
        });
        parentViewGroup.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                parentLog.logLongClick();
                return false;
            }
        });
        parentViewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    parentLog.logTouchDown();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    parentLog.logTouchUp();
                }else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                    parentLog.logTouchMove();
                }else{

                }
                return false;
            }
        });
    }

    @OnClick({R.id.btnView, R.id.textView, R.id.activity_view_event})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnView:
                btnLog.logClick();
                break;
            case R.id.textView:
                textLog.logClick();
                break;
            case R.id.activity_view_event:
                parentLog.logClick();
                break;
        }
    }

    static class MyLog {
        private String tag;

        public MyLog(String tag) {
            this.tag = tag;
        }

        void log(String s) {
            Log.e(tag, s);
        }

        void logClick() {
            log(tag + " -> " + "Click");
        }

        void logTouchDown() {
            log(tag + " -> " + "TouchDown");
        }

        void logTouchUp() {
            log(tag + " -> " + "TouchUp");
        }

        void logTouchMove() {
            log(tag + " -> " + "TouchMove");
        }
        void logLongClick() {
            log(tag + " -> " + "LongClick");
        }
    }

}
