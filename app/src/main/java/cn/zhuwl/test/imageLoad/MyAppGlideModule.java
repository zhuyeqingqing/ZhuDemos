package cn.zhuwl.test.imageLoad;

import android.content.Context;
import android.util.Log;

import androidx.annotation.CheckResult;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import java.nio.ByteBuffer;

@GlideModule
public class MyAppGlideModule extends AppGlideModule {
  @Override
  public void registerComponents(Context context, Glide glide, Registry registry) {
    Log.i("hehe","registerComponents");
    registry.prepend(String.class, ByteBuffer.class, new Base64ModelLoaderFactory());
  }
}
