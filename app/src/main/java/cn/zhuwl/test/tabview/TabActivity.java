package cn.zhuwl.test.tabview;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import cn.zhuwl.test.MyFragment;
import cn.zhuwl.test.tabview.fragment.TabHomeFragment;

public class TabActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fg = new TabHomeFragment();
        fm.beginTransaction()
                .add(android.R.id.content, fg)
                .commit();
    }
}
