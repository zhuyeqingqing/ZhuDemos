package com.example.myui;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class UiUtils {
    public static int dip2px(Context context,int dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /** pxz转换dip */

    public static int px2dip(Context context,int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    public static void showToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context,int str_id) {
        Toast.makeText(context, str_id, Toast.LENGTH_SHORT).show();
    }

    /**
     * 创建view对象
     *
     * @param layout_id
     *            布局id
     * @return
     */
    public static View inflate(Context context,int layout_id) {
        return View.inflate(context, layout_id, null);
    }

}
