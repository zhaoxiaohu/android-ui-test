package com.example.guohl.myuitest.activitySwitch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.guohl.myuitest.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends Activity {

    private static final String tag = FirstActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        Log.e(tag, tag + "->onCreate");
    }

    public FirstActivity() {
        super();
        Log.e(tag, tag + "->Object Create");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(tag, tag + "->Object onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(tag, tag + "->Object onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(tag, tag + "->Object onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(tag, tag + "->Object onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(tag, tag + "->Object onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(tag, tag + "->Object onStop");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e(tag, tag + "->Object onTrimMemory->level: " + level);
    }

    @OnClick(R.id.btnGotoSecond)
    public void onClick() {
        startActivity(new Intent(FirstActivity.this, SecondActivity.class));
    }
}
