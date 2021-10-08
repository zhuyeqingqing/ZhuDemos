package cn.zhuwl.test.tabview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class SViewPager extends ViewPager {

	private boolean canScroll;

	public SViewPager(Context context) {
		super(context);
		canScroll = false;
	}

	public SViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		canScroll = false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (canScroll) {
			try {
				return super.onInterceptTouchEvent(ev);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (canScroll) {
			return super.onTouchEvent(event);
		}
		return false;
	}

	public void toggleLock() {
		canScroll = !canScroll;
	}

	public void setCanScroll(boolean canScroll) {
		this.canScroll = canScroll;
	}

	public boolean isCanScroll() {
		return canScroll;
	}

}
