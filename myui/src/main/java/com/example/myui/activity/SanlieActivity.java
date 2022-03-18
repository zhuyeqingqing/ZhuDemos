package com.example.myui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myui.R;
import com.example.myui.UiUtils;
import com.example.myui.group.Sanlielayout;
import com.example.myui.util.DrawableUtils;

import java.util.Random;

public class SanlieActivity extends Activity {
    private String[] datas = new String[]{"新浪","阿罗那顺","吉利可汗","大唐西域记","李元霸男儿当自强","故乡的风",
            "新浪","阿罗那顺","吉利可汗","大唐西域记","李元霸男儿当自强","故乡的风","新浪","阿罗那顺","吉利可汗","大唐西域记","李元霸男儿当自强","故乡的风",
            "大美河南","风情万种的胡丽梅","偷偷的告诉你","没门","批准","今天要大专","可可西里","支付宝","微信","发"," 方法发","发顺丰",
            "发生大师傅","发大水发发发","公司大V广告","十多个地方官","是的","范德萨发大纲的三个","范德萨发到付散发","发的发","范德萨","发的发生的","范德萨发","沙发的法定",
            "和人","实打实地方","发光发热额","发","发多少","广东省","涣发大号","海灯法师","贵公司","感受到","re","热太晚",
            "广东省","感受到","二哥","扔","二哥","扔","仍未","仍未","热舞会认为","哥哥我跟","grew各位","特我让他问题",};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = new ScrollView(getApplicationContext());
        Sanlielayout sanlielayout = new Sanlielayout(getApplicationContext());
        int layoutPadding = UiUtils.dip2px(getApplicationContext(), 13);
        sanlielayout.setBackgroundResource(R.drawable.grid_item_bg_normal);
        sanlielayout.setPadding(layoutPadding, layoutPadding, layoutPadding, layoutPadding);
        int pressColor=0xffcecece;
        GradientDrawable pressedDrawable = DrawableUtils.getGradientDrawable(getApplicationContext(), pressColor);

        for(int i=0;i<datas.length;i++){
            final TextView tv=new TextView(getApplicationContext());
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
            tv.setGravity(Gravity.CENTER);
            tv.setText(datas[i]);
            int textPaddingV = UiUtils.dip2px(getApplicationContext(),4);
            int textPaddingH = UiUtils.dip2px(getApplicationContext(),7);
            tv.setPadding(textPaddingH, textPaddingV, textPaddingH, textPaddingV);
            tv.setClickable(true);
            tv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    UiUtils.showToast(getApplicationContext(),tv.getText().toString());
                }
            });
            // 声明随机的颜色
            Random random=new Random();
            int red=random.nextInt(208)+20;   //  0-255
            int green=random.nextInt(208)+20;
            int blue=random.nextInt(208)+20;
            int rgb = Color.rgb(red, green, blue);

            GradientDrawable normalDrawable = DrawableUtils.getGradientDrawable(getApplicationContext(), rgb);
            // 通过代码给TextView设置状态选择器
            tv.setBackgroundDrawable(DrawableUtils.getStateListDrawable(pressedDrawable, normalDrawable));
            sanlielayout.addView(tv,new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        }
        scrollView.addView(sanlielayout);
        setContentView(scrollView);
    }
}
