package com.example.myui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myui.group.huanhangGroup;

public class HuanhangActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        huanhangGroup huanhangGroup = new huanhangGroup(this);
        for (int i = 0; i < 31; i++){
            TextView textView = new TextView(this);
            textView.setPadding(30,20,30,20);
            textView.setBackgroundColor(Color.BLUE);
            textView.setText("adhs");
            huanhangGroup.addView(textView);
        }

        setContentView(huanhangGroup);
    }
}
