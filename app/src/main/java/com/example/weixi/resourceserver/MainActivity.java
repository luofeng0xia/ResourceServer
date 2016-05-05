package com.example.weixi.resourceserver;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtStart;
    Button mBtStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtStart = (Button) findViewById(R.id.bt_start);
        mBtStop = (Button) findViewById(R.id.bt_stop);
        mBtStart.setOnClickListener(this);
        mBtStop.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start:
                onStartClick();
                break;
            case R.id.bt_stop:
                onStopClick();
                break;
        }
    }

    public void onStartClick() {
        Intent intent = new Intent(this, WebService.class);
        startService(intent);
    }

    public void onStopClick() {
        Intent intent = new Intent(this, WebService.class);
        stopService(intent);
    }
}
