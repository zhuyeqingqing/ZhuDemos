package cn.zhuwl.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends FragmentActivity {
    private static String[] TONGDUN_PERMISSION = new String[]
            {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
            };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            //requestPermissions(new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},0);
            if(!Environment.isExternalStorageManager()){
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent,0);
            } else {
                FragmentManager fm = getSupportFragmentManager();
                Fragment fg = new MyFragment();
                fm.beginTransaction()
                        .add(android.R.id.content, fg)
                        .commit();
            }
        } else {*/
        requestPermission();
        //}
        //DeviceUtil.getImieStatus(this);
        //DeviceUtil.getAndroidId(this);



    }

    private void requestPermission() {
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        )
                != PackageManager.PERMISSION_GRANTED)
        ) { //判断是否已经赋予权限
            ActivityCompat.requestPermissions(
                    this, TONGDUN_PERMISSION, 1
            );
        }else{
            FragmentManager fm = getSupportFragmentManager();
            Fragment fg = new MyFragment();
            fm.beginTransaction()
                    .add(android.R.id.content, fg)
                    .commit();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fg = new MyFragment();
        fm.beginTransaction()
                .add(android.R.id.content, fg)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("返回码1："+resultCode);
        /*if(requestCode == 0){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                if(Environment.isExternalStorageManager()){
                    System.out.println("返回码："+resultCode);
                    FragmentManager fm = getSupportFragmentManager();
                    Fragment fg = new MyFragment();
                    fm.beginTransaction()
                            .add(android.R.id.content, fg)
                            .commit();
                } else {
                    Toast.makeText(this,"权限拒绝",Toast.LENGTH_SHORT).show();
                }
            }
        }*/
    }
}
