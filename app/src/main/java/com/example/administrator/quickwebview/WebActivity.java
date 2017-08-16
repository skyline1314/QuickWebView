package com.example.administrator.quickwebview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.administrator.quickwebview.sdk.FileUtil;
import com.example.administrator.quickwebview.sdk.GlobalConfig;
import com.example.administrator.quickwebview.sdk.QuickWeb;

public class WebActivity extends Activity {

    private WebView webview;
    private String mode;
    private Long initTime = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        mode = intent.getStringExtra("mode");
        if (mode == null) {
            mode = "";
        }
        FileUtil.makeDir(GlobalConfig.PROGRAM_PATH);
        initView();
    }

    private void initView() {
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (mode.equals("1")) {
                    // only test from cache but system have cache
                    return QuickWeb.getInstance().requestResource(url);
                } else {
                    return super.shouldInterceptRequest(view, url);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Long time = System.currentTimeMillis() - initTime;
                Toast.makeText(getApplicationContext(), time + "", Toast.LENGTH_LONG);
                Log.e("time", time + "" + (mode.equals("1") ? "快速模式" : "普通模式"));
                super.onPageFinished(view, url);
            }
        });
        webview.loadUrl("http://www.tuishiben.com/");
    }
}
