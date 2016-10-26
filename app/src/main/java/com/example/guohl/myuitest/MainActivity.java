package com.example.guohl.myuitest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.guohl.myuitest.activitySwitch.FirstActivity;
import com.example.guohl.myuitest.customview.CustomViewActivity;
import com.example.guohl.myuitest.photo.MyPtohoActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //webview 初始化
        WebView webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        webView.loadUrl("file:///android_asset/sample.html");
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
    }

    @OnClick({R.id.btnCustomView0, R.id.btnCustomViewWeiXinPhoto, R.id.btnActivitySwitch})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCustomView0:
                startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
                break;
            case R.id.btnCustomViewWeiXinPhoto:
                startActivity(new Intent(MainActivity.this, MyPtohoActivity.class));
                break;
            case R.id.btnActivitySwitch:
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
                break;
        }
    }

    public class WebAppInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page
         */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }

}
