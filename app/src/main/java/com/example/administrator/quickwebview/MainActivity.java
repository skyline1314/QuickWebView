package com.example.administrator.quickwebview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.quickwebview.sdk.FileUtil;
import com.example.administrator.quickwebview.sdk.GlobalConfig;

public class MainActivity extends Activity {

    private TextView btn;
    private TextView btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create cache file root
        FileUtil.makeDir(GlobalConfig.PROGRAM_PATH);
        initView();
    }

    private void initView() {
        btn = (TextView) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, WebActivity.class);
                intent.putExtra("mode","1");
                startActivity(intent);
            }
        });
        btn1 = (TextView) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("mode","2");
                intent.setClass(MainActivity.this, WebActivity.class);
                startActivity(intent);
            }
        });
    }
}
