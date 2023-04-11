package com.example.myui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.beauty.basicui.rcl.fragments.RefreshRclFragment;
import com.example.myui.activity.CommonActivity;
import com.example.myui.activity.HuanhangActivity;
import com.example.myui.activity.SanlieActivity;
import com.example.myui.xuanfuwindow.service.FloatingButtonService;
import com.example.myui.xuanfuwindow.service.FloatingImageDisplayService;
import com.example.myui.xuanfuwindow.service.FloatingVideoService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button xuanfu1 = findViewById(R.id.xuanfu1);
        Button xuanfu2 = findViewById(R.id.xuanfu2);
        Button xunafu3 = findViewById(R.id.xuanfu3);
        Button huanhang = findViewById(R.id.huanhang);
        Button sanlie = findViewById(R.id.sanlie);
        Button recyclerview = findViewById(R.id.recycler_view);

        xuanfu1.setOnClickListener(this);
        xuanfu2.setOnClickListener(this);
        xunafu3.setOnClickListener(this);
        huanhang.setOnClickListener(this);
        sanlie.setOnClickListener(this);
        recyclerview.setOnClickListener(this);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                startService(new Intent(MainActivity.this, FloatingButtonService.class));
            }
        } else if (requestCode == 1) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                startService(new Intent(MainActivity.this, FloatingImageDisplayService.class));
            }
        } else if (requestCode == 2) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                startService(new Intent(MainActivity.this, FloatingVideoService.class));
            }
        }
    }

    public void startFloatingButtonService() {
        if (FloatingButtonService.isStarted) {
            return;
        }
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
        } else {
            startService(new Intent(MainActivity.this, FloatingButtonService.class));
        }
    }

    public void startFloatingImageDisplayService() {
        if (FloatingImageDisplayService.isStarted) {
            return;
        }
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 1);
        } else {
            startService(new Intent(MainActivity.this, FloatingImageDisplayService.class));
        }
    }

    public void startFloatingVideoService() {
        if (FloatingVideoService.isStarted) {
            return;
        }
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 2);
        } else {
            startService(new Intent(MainActivity.this, FloatingVideoService.class));
        }
    }

    public void startHuanhangActivity() {
        Intent intent = new Intent(this, HuanhangActivity.class);
        startActivity(intent);
    }

    public void startSanlieActivity() {
        Intent intent = new Intent(this, SanlieActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.xuanfu1:
                startFloatingButtonService();
                break;
            case R.id.xuanfu2:
                startFloatingImageDisplayService();
                break;
            case R.id.xuanfu3:
                startFloatingVideoService();
                break;
            case R.id.huanhang:
                startHuanhangActivity();
                break;
            case R.id.sanlie:
                startSanlieActivity();
                break;
            case R.id.recycler_view:
                CommonActivity.Companion.start(this, RefreshRclFragment.Companion.newInstance());
                break;
            default:
                break;
        }
    }
}