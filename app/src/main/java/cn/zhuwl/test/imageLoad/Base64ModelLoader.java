package cn.zhuwl.test.imageLoad;

import android.util.Log;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Loads an {@link InputStream} from a Base 64 encoded String.
 */
public final class Base64ModelLoader implements ModelLoader<String, ByteBuffer> {
  // From: https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/Data_URIs.
  private static final String DATA_URI_PREFIX = "data:";

  public Base64ModelLoader(){
    Log.i("hehe","Base64ModelLoader");
  }
  @Nullable
  @Override
  public LoadData<ByteBuffer> buildLoadData(String model, int width, int height, Options options) {
    Log.i("hehe","buildLoadData");
    return new LoadData<>(new ObjectKey(model), new Base64DataFetcher(model));
  }

  @Override
  public boolean handles(String model) {
    return model.startsWith(DATA_URI_PREFIX);
  }
}
