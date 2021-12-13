package cn.zhuwl.test.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class Settings {
    private static final String TAG = "Settings";
    private static final String DEFAULT_NAME = "Settings";
    private SharedPreferences mSharedPreferences;
    private Editor mEditor;
    private static ConcurrentHashMap<String, WeakReference<Pair<SharedPreferences, Editor>>> mSPHashMap = new ConcurrentHashMap();
    private static Settings.ContextWrapperProvider mContextWrapperProvider;

    public Settings(@NonNull Context context, @NonNull String name) {
        Pair<SharedPreferences, Editor> spPair = getSettings(context, name);
        if (spPair == null) {
        } else {
            this.mSharedPreferences = (SharedPreferences)spPair.first;
            if (this.mSharedPreferences == null) {
            } else {
                this.mEditor = (Editor)spPair.second;
            }
        }
    }

    public Settings(@NonNull Context context) {
        this(context, "Settings");
    }

    public boolean set(@NonNull String key, @Nullable String value) {
        return this.set(key, value, true);
    }

    public boolean set(@NonNull String key, @Nullable String value, boolean isCommit) {
        if (this.mSharedPreferences != null) {
            String getVal = this.mSharedPreferences.getString(key, "");
            if (this.mSharedPreferences.contains(key) && getVal.equals(value)) {
                return true;
            }
        }

        if (this.mEditor == null & this.mSharedPreferences != null) {
            this.mEditor = this.mSharedPreferences.edit();
        }

        if (this.mEditor != null) {
            this.mEditor.putString(key, value);
            if (isCommit) {
                return this.mEditor.commit();
            }
        }

        return false;
    }

    public void remove(@NonNull String key) {
        if (this.mEditor == null & this.mSharedPreferences != null) {
            this.mEditor = this.mSharedPreferences.edit();
        }

        if (this.mEditor != null) {
            this.mEditor.remove(key);
            this.mEditor.apply();
        }

    }

    public int removeAll(@NonNull List<String> keys) {
        if (this.mEditor == null & this.mSharedPreferences != null) {
            this.mEditor = this.mSharedPreferences.edit();
        }

        if (this.mEditor == null) {
            return 1;
        } else {
            Iterator var2 = keys.iterator();

            while(var2.hasNext()) {
                String key = (String)var2.next();
                this.mEditor.remove(key);
            }

            return this.mEditor.commit() ? 0 : 2;
        }
    }

    @Nullable
    public String get(@NonNull String key) {
        return this.get(key, "");
    }

    @Nullable
    public String get(@NonNull String key, @Nullable String defVal) {
        if (this.mSharedPreferences != null) {
            try {
                String value = this.mSharedPreferences.getString(key, defVal);
                Log.d("Settings", "getString key = : " + key + ", value = " + value);
                return value;
            } catch (ClassCastException var4) {
                Log.e("Settings", "get e = " + var4.toString());
            }
        }

        return defVal;
    }

    public boolean getBoolean(@NonNull String key) {
        return this.getBoolean(key, false);
    }

    public boolean getBoolean(@NonNull String key, boolean defVal) {
        String value = this.get(key, (String)null);
        if (value != null) {
            try {
                return Boolean.valueOf(value);
            } catch (Exception var5) {
                Log.e("Settings", "getBoolean e = " + var5.toString());
            }
        }

        return defVal;
    }

    public boolean setBoolean(@NonNull String key, boolean value) {
        return this.setBoolean(key, value, true);
    }

    public boolean setBoolean(@NonNull String key, boolean value, boolean isCommit) {
        return this.set(key, Boolean.toString(value), isCommit);
    }

    public int getInt(@NonNull String key) {
        return this.getInt(key, 0);
    }

    public int getInt(@NonNull String key, int defVal) {
        String value = this.get(key, (String)null);
        if (value != null) {
            try {
                return Integer.valueOf(value);
            } catch (Exception var5) {
                Log.e("Settings", "getInt e = " + var5.toString());
            }
        }

        return defVal;
    }

    public boolean setInt(@NonNull String key, int value) {
        return this.setInt(key, value, true);
    }

    public boolean setInt(@NonNull String key, int value, boolean isCommit) {
        return this.set(key, Integer.toString(value), isCommit);
    }

    public long getLong(@NonNull String key) {
        return this.getLong(key, 0L);
    }

    public long getLong(@NonNull String key, long defVal) {
        String value = this.get(key, (String)null);
        if (value != null) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException var6) {
                Log.e("Settings", "getInt e = " + var6.toString());
            }
        }

        return defVal;
    }

    public boolean setLong(@NonNull String key, long value) {
        return this.setLong(key, value, true);
    }

    public boolean setLong(@NonNull String key, long value, boolean isCommit) {
        return this.set(key, Long.toString(value), isCommit);
    }

    public boolean contains(@NonNull String key) {
        return this.mSharedPreferences != null && this.mSharedPreferences.contains(key);
    }

    @NonNull
    public Map<String, ?> getAll() {
        return (Map)(this.mSharedPreferences == null ? new HashMap() : this.mSharedPreferences.getAll());
    }

    public void clear() {
        if (this.mEditor == null & this.mSharedPreferences != null) {
            this.mEditor = this.mSharedPreferences.edit();
        }

        if (this.mEditor != null) {
            this.mEditor.clear();
            this.mEditor.apply();
        }

    }

    public void commit() {
        if (this.mEditor == null & this.mSharedPreferences != null) {
            this.mEditor = this.mSharedPreferences.edit();
        }

        if (this.mEditor != null) {
            this.mEditor.apply();
        }

    }

    public void apply() {
        if (this.mEditor == null & this.mSharedPreferences != null) {
            this.mEditor = this.mSharedPreferences.edit();
        }

        if (this.mEditor != null) {
            this.mEditor.apply();
        }

    }

    public static void setContextWrapperProvider(Settings.ContextWrapperProvider provider) {
        mContextWrapperProvider = provider;
    }

    public static Settings.ContextWrapperProvider getContextWrapperProvider() {
        return mContextWrapperProvider;
    }

    private static Pair<SharedPreferences, Editor> getSettings(Context context, String name) {
        if (context == null) {
            return null;
        } else {
            WeakReference<Pair<SharedPreferences, Editor>> wr = (WeakReference)mSPHashMap.get(name);
            Pair pair;
            if (wr == null || (pair = (Pair)wr.get()) == null) {
                mSPHashMap.remove(name);

                try {
                    if (mContextWrapperProvider != null) {
                        Context change = mContextWrapperProvider.getContextWrapper(context);
                        if (change != null) {
                            context = change;
                        }
                    }

                    SharedPreferences sharedPreferences = context.getSharedPreferences(name, 0);
                    if (sharedPreferences == null) {
                        return null;
                    }

                    pair = new Pair(sharedPreferences, (Object)null);
                } catch (Exception var5) {
                    return null;
                }

                mSPHashMap.put(name, new WeakReference(pair));
            }

            return pair;
        }
    }

    public interface ContextWrapperProvider {
        Context getContextWrapper(Context var1);
    }
}
