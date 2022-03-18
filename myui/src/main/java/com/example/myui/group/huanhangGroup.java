package com.example.myui.group;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class huanhangGroup extends ViewGroup {


    public huanhangGroup(Context context) {
        super(context);
    }

    public huanhangGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public huanhangGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public huanhangGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int index = 0; index < getChildCount(); index++)
        {
            final View child = getChildAt(index);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        int margin_top = 20;
        int margin_left = 20;
        final int count = getChildCount();
        int row = 0;// which row lay you view relative to parent
        int lengthX = left; // right position of child relative to parent
        int lengthY = top; // bottom position of child relative to parent
        for (int i = 0; i < count; i++)
        {
            final View child = this.getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();


            lengthX += width + margin_left;
            lengthY = row * (height + margin_top) + margin_top + height ;


            // if it can't drawing on a same line , skip to next line
            if (lengthX > right)
            {
                lengthX = width + margin_left + left;
                row++;
                lengthY = row * (height + margin_top) + margin_top + height ;

            }

            if (lengthY > (bottom - top)) {
                return;
            }
            child.layout(lengthX - width, lengthY - height, lengthX, lengthY);
        }
    }
}
