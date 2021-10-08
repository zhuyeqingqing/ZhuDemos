/*
Copyright 2014 shizhefei（LuckyJayce）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package cn.zhuwl.test.tabview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

/**
 * 将indicatorView，ViewPager联合使用
 */
public class IndicatorViewPager {
    protected Indicator indicatorView;
    protected ViewPager2 viewPager;
    private IndicatorPagerAdapter adapter;
    protected OnIndicatorPageChangeListener onIndicatorPageChangeListener;
    private boolean anim = true;
    public Activity mActivity;
    public List<Fragment> mFragmentList;

    public IndicatorViewPager(Indicator indicator, ViewPager2 viewPager) {
        this(indicator, viewPager, true);
    }

    public IndicatorViewPager(Indicator indicator, ViewPager2 viewPager, boolean indicatorClickable) {
        super();
        this.indicatorView = indicator;
        this.viewPager = viewPager;
        indicator.setItemClickable(indicatorClickable);
        iniOnItemSelectedListener();
        iniOnPageChangeListener();
    }

    public void setClickIndicatorAnim(boolean anim) {
        this.anim = anim;
    }

    protected void iniOnItemSelectedListener() {
        indicatorView.setOnItemSelectListener(new Indicator.OnItemSelectedListener() {

            @Override
            public void onItemSelected(View selectItemView, int select, int preSelect) {
                if (viewPager instanceof ViewPager2) {
                    viewPager.setCurrentItem(select, false);
                } else {
                    viewPager.setCurrentItem(select, anim);
                }

            }
        });
    }

    protected void iniOnPageChangeListener() {
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                indicatorView.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                indicatorView.setCurrentItem(position, true);
                if (onIndicatorPageChangeListener != null) {
                    onIndicatorPageChangeListener.onIndicatorPageChange(indicatorView.getPreSelectItem(), position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                indicatorView.onPageScrollStateChanged(state);
            }
        });
    }


    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setAdapter(IndicatorPagerAdapter adapter) {
        this.adapter = adapter;
        viewPager.setAdapter(adapter.getPagerAdapter());
        indicatorView.setAdapter(adapter.getIndicatorAdapter());
    }

    /**
     * 切换至指定的页面
     *
     * @param item 页面的索引
     * @param anim 是否动画效果
     */
    public void setCurrentItem(int item, boolean anim) {
        viewPager.setCurrentItem(item, anim);
        indicatorView.setCurrentItem(item, anim);
    }

    /**
     * 设置页面切换监听
     */
    public void setOnIndicatorPageChangeListener(OnIndicatorPageChangeListener onIndicatorPageChangeListener) {
        this.onIndicatorPageChangeListener = onIndicatorPageChangeListener;
    }

    /**
     * 设置indicatorView上滑动变化的转换监听，tab在切换过程中会调用此监听。
     *
     * @param onTransitionListener
     */
    public void setIndicatorOnTransitionListener(Indicator.OnTransitionListener onTransitionListener) {
        indicatorView.setOnTransitionListener(onTransitionListener);
    }

    /**
     * 设置indicatorView的滑动块样式
     *
     * @param scrollBar
     */
    public void setIndicatorScrollBar(ScrollBar scrollBar) {
        indicatorView.setScrollBar(scrollBar);
    }

    /**
     * 设置Indicator tab项的点击事件，在Indicator 的 onItemSelectListener前触发和拦截处理
     *
     * @param onIndicatorItemClickListener
     */
    public void setOnIndicatorItemClickListener(Indicator.OnIndicatorItemClickListener onIndicatorItemClickListener) {
        indicatorView.setOnIndicatorItemClickListener(onIndicatorItemClickListener);
    }

    /**
     * @return
     */
    public Indicator.OnIndicatorItemClickListener getOnIndicatorItemClickListener() {
        return indicatorView.getOnIndicatorItemClickListener();
    }

    /**
     * 设置缓存界面的个数，左右两边缓存界面的个数，不会被重新创建。<br>
     * 默认是1，表示左右两边 相连的1个界面和当前界面都会被缓存住，比如切换到左边的一个界面，那个界面是不会重新创建的。
     *
     * @param limit
     */
    public void setPageOffscreenLimit(int limit) {
        viewPager.setOffscreenPageLimit(limit);
    }

    /**
     * 设置page间的图片的宽度
     *
     * @param marginPixels
     */
   /* public void setPageMargin(int marginPixels) {
        viewPager.setPageMargin(marginPixels);
    }*/

    /**
     * 设置page间的图片
     *
     * @param d
     */
  /*  public void setPageMarginDrawable(Drawable d) {
        viewPager.setPageMarginDrawable(d);
    }*/

    /**
     * 设置page间的图片
     *
     * @param resId
     */
    /*public void setPageMarginDrawable(int resId) {
        viewPager.setPageMarginDrawable(resId);
    }*/

    /**
     * 获取上一次选中的索引
     *
     * @return 上一次选中的索引
     */
    public int getPreSelectItem() {
        return indicatorView.getPreSelectItem();
    }

    /**
     * 获取当前选中的索引
     *
     * @return 当前选中的索引
     */
    public int getCurrentItem() {
        return indicatorView.getCurrentItem();
    }

    public IndicatorPagerAdapter getAdapter() {
        return this.adapter;
    }

    public OnIndicatorPageChangeListener getOnIndicatorPageChangeListener() {
        return onIndicatorPageChangeListener;
    }

    public Indicator getIndicatorView() {
        return indicatorView;
    }

    public ViewPager2 getViewPager() {
        return viewPager;
    }

    /**
     * 通知适配器数据变化，重新加载页面
     */
    public void notifyDataSetChanged() {
        if (this.adapter != null) {
            this.adapter.notifyDataSetChanged();
        }
    }

    public interface OnIndicatorPageChangeListener {
        /**
         * 注意 preItem 可能为 -1。表示之前没有选中过,每次adapter.notifyDataSetChanged也会将preItem
         * 设置为-1；
         *
         * @param preItem
         * @param currentItem
         */
        void onIndicatorPageChange(int preItem, int currentItem);
    }

    public interface IndicatorPagerAdapter {

        RecyclerView.Adapter getPagerAdapter();

        Indicator.IndicatorAdapter getIndicatorAdapter();

        void notifyDataSetChanged();

    }

    static abstract class LoopAdapter implements IndicatorPagerAdapter {

        abstract int getRealPosition(int position);

        abstract void setLoop(boolean loop);

        abstract int getCount();
    }


    /**
     * viewpage 的每个页面是Fragment的形式
     */
    public static abstract class IndicatorFragmentPagerAdapter extends LoopAdapter {
        private FragmentStateAdapter pagerAdapter;
        private boolean loop;

        @Override
        int getRealPosition(int position) {
            return position % getCount();
        }


        @Override
        void setLoop(boolean loop) {
            this.loop = loop;
            indicatorAdapter.setIsLoop(loop);
        }

        public IndicatorFragmentPagerAdapter(FragmentManager fragmentManager,Fragment fragment) {
            super();
            pagerAdapter = new FragmentStateAdapter(fragmentManager, fragment.getLifecycle()){

                @Override
                public int getItemCount() {
                    if (IndicatorFragmentPagerAdapter.this.getCount() == 0) {
                        return 0;
                    }
                    if (loop) {
                        return Integer.MAX_VALUE - 100;
                    }
                    return IndicatorFragmentPagerAdapter.this.getCount();
                }

                @NonNull
                @Override
                public Fragment createFragment(int position) {
                    return IndicatorFragmentPagerAdapter.this.getFragmentForPage(getRealPosition(position));
                }
            };
        }

        private Indicator.IndicatorAdapter indicatorAdapter = new Indicator.IndicatorAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return IndicatorFragmentPagerAdapter.this.getViewForTab(position, convertView, parent);
            }

            @Override
            public int getCount() {
                return IndicatorFragmentPagerAdapter.this.getCount();
            }

        };

        public abstract int getCount();

        public abstract View getViewForTab(int position, View convertView, ViewGroup container);

        public abstract Fragment getFragmentForPage(int position);

        public float getPageRatio(int position) {
            return 1f;
        }

        @Override
        public void notifyDataSetChanged() {
            indicatorAdapter.notifyDataSetChanged();
            pagerAdapter.notifyDataSetChanged();
        }

        @Override
        public RecyclerView.Adapter getPagerAdapter() {
            return pagerAdapter;
        }

        @Override
        public Indicator.IndicatorAdapter getIndicatorAdapter() {
            return indicatorAdapter;
        }

    }
}
