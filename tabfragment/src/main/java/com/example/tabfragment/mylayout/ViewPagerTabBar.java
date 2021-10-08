package com.example.tabfragment.mylayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.ThemeUtils;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

import com.example.tabfragment.BaseUtil;
import com.example.tabfragment.R;
import com.example.tabfragment.ThemeType;
import com.example.tabfragment.ZhuApplication;

public class ViewPagerTabBar extends FrameLayout {
    private static final String TAG = "ViewPagerTitleBar";
    private HorizontalScrollView mScrollView;
    private LinearLayout mTitleList;
    private View mIndicatorView;
    private int mMinTabWidth;
    private int mTabWidth;

    private OnTabClickListener mOnTabClickListener;

    public interface OnTabClickListener {
        void onItemClick(int postion);
    }

    public ViewPagerTabBar(Context context) {
        super(context);
        init(context);
    }

    public ViewPagerTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ViewPagerTabBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.viewpager_titlebar, this);

        mScrollView = (HorizontalScrollView)this.findViewById(R.id.scrollview);
        mTitleList = (LinearLayout)this.findViewById(R.id.titles);
        mIndicatorView = this.findViewById(R.id.indicator);

        mMinTabWidth = (int)getContext().getResources().getDimension(R.dimen.default_min_tab_width);
        mTabWidth = mMinTabWidth;
    }

    public void setOnTabClickListener(OnTabClickListener listener) {
        mOnTabClickListener = listener;
    }

    public int getTabSize() {
        return mTitleList == null ? -1 : mTitleList.getChildCount();
    }

    public void addTab(String tabName) {
        View view = View.inflate(getContext(), R.layout.viewpager_titlebar_tab, null);
        TextView title = (TextView)view.findViewById(R.id.title);
        title.setText(tabName);
        view.setBackgroundColor(0x000000);
        mTitleList.addView(view, LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        relayoutActionBar();

        final int position = mTitleList.getChildCount() - 1;
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTabClickListener != null) {
                    mOnTabClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        relayoutActionBar();
    }

    private void relayoutActionBar() {
        int tabCount = mTitleList.getChildCount();
        int tabWidth = calcTabWidth(mTitleList.getChildCount());
        for (int i = 0; i < tabCount; i++) {
            View tabView = mTitleList.getChildAt(i);
            ViewGroup.LayoutParams targetLP = tabView.getLayoutParams();
            targetLP.width = tabWidth;
            tabView.setLayoutParams(targetLP);
        }
    }

    private int calcTabWidth(int tabCount) {
        int screenWidth = BaseUtil.INSTANCE.getScreenWidth(getContext());
        mTabWidth = tabCount * mMinTabWidth > screenWidth ? mMinTabWidth : screenWidth / tabCount;
        return mTabWidth;
    }

    public void updateActionBar(int position, float offsetPercent) {
        updateScrollView(position, offsetPercent);
        updateTabText(position, offsetPercent);
        updateIndicator(position, offsetPercent);
    }

    private void updateScrollView(int position, float offsetPercent) {
        int currTabWidth = mTitleList.getChildAt(position).getWidth();
        int currTabLeft = mTitleList.getChildAt(position).getLeft();
        int expectedCurrTabLeft = (BaseUtil.INSTANCE.getScreenWidth(getContext()) - currTabWidth) / 2;
        int scrollOffset = (int)(currTabLeft + offsetPercent * currTabWidth - expectedCurrTabLeft);
        mScrollView.smoothScrollTo(scrollOffset, 0);
    }

    private void updateTabText(int position, float offsetPercent) {
        int currTabPos = position;
        int nextTabPos = position + 1;

        @SuppressLint("RestrictedApi") ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        ZhuApplication application = ZhuApplication.Companion.getInstance();
        int normalColor, selectedColor;
        switch (application.getCustomTheme()){
            case ThemeType.NIGHT:
                normalColor = getResources().getColor(R.color.common_text_color_black_night);
                selectedColor = getResources().getColor(R.color.viewpager_title_text_color_night);
                break;
            case ThemeType.COLORFUL:
                normalColor = getResources().getColor(R.color.common_text_color_black);
                selectedColor = getResources().getColor(R.color.viewpager_title_text_color_night);
                break;
            default:
                normalColor = getResources().getColor(R.color.common_text_color_black);
                selectedColor = getResources().getColor(R.color.viewpager_title_text_color);
                break;
        }
        @SuppressLint("RestrictedApi") int currTabTextColor = (Integer)argbEvaluator.evaluate(offsetPercent, selectedColor, normalColor);
        @SuppressLint("RestrictedApi") int nextTabTextColor = (Integer)argbEvaluator.evaluate(offsetPercent, normalColor, selectedColor);

        int childCount = mTitleList.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TextView titleView = (TextView)mTitleList.getChildAt(i).findViewById(R.id.title);
            titleView.setTextColor(normalColor);
        }

        TextView titleView = (TextView)mTitleList.getChildAt(currTabPos).findViewById(R.id.title);
        titleView.setTextColor(currTabTextColor);
        if (nextTabPos < mTitleList.getChildCount() && nextTabPos >= 0) {
            titleView = (TextView)mTitleList.getChildAt(nextTabPos).findViewById(R.id.title);
            titleView.setTextColor(nextTabTextColor);
        }
    }

    private void updateIndicator(int position, float offsetPercent) {
        View currView = mTitleList.getChildAt(position);
        int leftMargin = (int)(currView.getLeft() + offsetPercent * currView.getWidth());
        BaseUtil.INSTANCE.setViewLeftMargin(mIndicatorView, leftMargin);
        BaseUtil.INSTANCE.setViewWidth(mIndicatorView, currView.getWidth());
        mIndicatorView.setVisibility(View.VISIBLE);
    }

}
