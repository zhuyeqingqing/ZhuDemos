package cn.zhuwl.test.tabview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.viewpager2.widget.ViewPager2;

import cn.zhuwl.test.R;

public class SolveConflictLayout extends FrameLayout {
    private float mStartX = 0;
    private float mStartY = 0;
    private ViewPager2 mViewPager2;
    public SolveConflictLayout(@NonNull Context context) {
        super(context);
        initViewPager2(context);
    }

    public SolveConflictLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViewPager2(context);
    }

    public SolveConflictLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewPager2(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SolveConflictLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViewPager2(context);
    }

    private void initViewPager2(Context context){
        final View rootView;
        rootView = LayoutInflater.from(context).inflate(R.layout.solve_conflict_viewpager2,this);
        mViewPager2 = rootView.findViewById(R.id.fragment_home_viewPager);
    }

    public ViewPager2 getmViewPager2() {
        return mViewPager2;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
         switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mViewPager2.isUserInputEnabled()) {
                    float endX = event.getX();
                    float endY = event.getY();
                    float disX = Math.abs(endX - mStartX);
                    float disY = Math.abs(endY - mStartY);

                    if (disX < disY) { // 水平滑动距离小雨垂直的滑动距离, 禁止vp滑动}
                        mViewPager2.setUserInputEnabled(false);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mStartX = 0;
                mStartY = 0;
                mViewPager2.setUserInputEnabled(true);
                break;
        }
        return super.onInterceptTouchEvent(event);
    }
}
