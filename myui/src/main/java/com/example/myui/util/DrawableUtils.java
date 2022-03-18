package com.example.myui.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import com.example.myui.UiUtils;

public class DrawableUtils {
	//  shape
	public static GradientDrawable  getGradientDrawable(Context context,  int color){
		GradientDrawable drawable=new GradientDrawable();
		//  设置图形的颜色 
		drawable.setColor(color);
		drawable.setCornerRadius(UiUtils.dip2px(context,5));
		// 设置边框的宽度和颜色
		drawable.setStroke(1, Color.TRANSPARENT);
		return drawable;
		
	}
	
	public static StateListDrawable getStateListDrawable(Drawable pressDrawable,Drawable normalDrawable){
/*	    <item android:drawable="@drawable/btn_pressed" android:state_pressed="true"/>
	    <item android:drawable="@drawable/btn_normal"/>*/
		StateListDrawable drawable=new StateListDrawable();
		drawable.addState(new int[]{android.R.attr.state_pressed}, pressDrawable);
		drawable.addState(new int[]{},normalDrawable);
		return drawable;
		
	}
}
