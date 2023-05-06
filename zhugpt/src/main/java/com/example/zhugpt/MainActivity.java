package com.example.zhugpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.zhugpt.activity.CommonActivity;
import com.example.zhugpt.databinding.ActivityMainBinding;
import com.example.zhugpt.fragment.ChatFragment;
import com.example.zhugpt.fragment.CompletionFragment;
import com.example.zhugpt.fragment.EditFragment;
import com.example.zhugpt.fragment.ImageFragment;
import com.example.zhugpt.fragment.ModelsFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.btModels.setOnClickListener(this::gotoModelPage);
        mBinding.btCompletion.setOnClickListener(this::gotoCompletion);
        mBinding.btChat.setOnClickListener(this::gotoChatPage);
        mBinding.btEdit.setOnClickListener(this::gotoEditPage);
        mBinding.btImage.setOnClickListener(this::gotoImagePage);
    }

    private void gotoModelPage(View view){
        CommonActivity.Companion.start(this, ModelsFragment.Companion.newInstance());
    }

    private void gotoCompletion(View view){
        CommonActivity.Companion.start(this, CompletionFragment.Companion.newInstance());
    }

    private void gotoChatPage(View view){
        CommonActivity.Companion.start(this, ChatFragment.Companion.newInstance());
    }

    private void gotoEditPage(View view){
        CommonActivity.Companion.start(this, EditFragment.Companion.newInstance());
    }

    private void gotoImagePage(View view){
        CommonActivity.Companion.start(this, ImageFragment.Companion.newInstance());
    }
}