package cn.zhuwl.test.tabview.fragment;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import cn.zhuwl.test.R;
import cn.zhuwl.test.Util.DisplayUtil;
import cn.zhuwl.test.bean.TabModle;
import cn.zhuwl.test.tabview.ColorBar;
import cn.zhuwl.test.tabview.Indicator;
import cn.zhuwl.test.tabview.IndicatorViewPager;
import cn.zhuwl.test.tabview.OnTransitionTextListener;
import cn.zhuwl.test.tabview.ScrollIndicatorView;
import cn.zhuwl.test.tabview.SolveConflictLayout;
import cn.zhuwl.test.tabview.TabConstant;

public class TabHomeFragment extends Fragment {
    private IndicatorViewPager indicatorViewPager;
    ArrayList<TabModle> mTabModels = new ArrayList<>();
    private String[] mNewsClassifies;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsClassifies = getContext().getResources().getStringArray(R.array.news_classifies);
        for (int i = 0; i < mNewsClassifies.length; i++) {
            TabModle tabModle = new TabModle();
            tabModle.setName(mNewsClassifies[i]);
            tabModle.setId(i);
            mTabModels.add(tabModle);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_tab_home, container, false);
        SolveConflictLayout solveConflictLayout = contentView.findViewById(R.id.solve_conflict_layout);
        ViewPager2 viewPager = solveConflictLayout.getmViewPager2();
        ScrollIndicatorView indicator = (ScrollIndicatorView) contentView.findViewById(R.id.fragment_home_indicator);
        //indicator.setScrollBar(new ColorBar(getActivity(), 0xFF0091FF, 4));
        indicator.setScrollBar(new ColorBar(getActivity(), Color.RED, 6));

        indicator.setOnTransitionListener(new OnTransitionTextListener().
                setColor(Color.RED, Color.BLACK)
                .setSize(TabConstant.TAB_SELECT_SIZE, TabConstant.TAB_UNSELECT_SIZE));
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setOnIndicatorItemClickListener(new Indicator.OnIndicatorItemClickListener() {
            @Override
            public boolean onItemClick(View clickItemView, int position) {
                return false;
            }
        });
        indicatorViewPager.setAdapter(new MyAdapter(getChildFragmentManager(), TabHomeFragment.this));
        return contentView;
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
        public MyAdapter(FragmentManager fragmentManager, Fragment fragment) {
            super(fragmentManager, fragment);
        }


        @Override
        public int getCount() {
            return mTabModels.size();
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(mTabModels.get(position).getName());

            int witdh = getTextWidth(textView);
            //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
            //1.3f是根据上面字体大小变化的倍数1.3f设置
            textView.setWidth((int) (witdh * 1.3f) + 32);
            if (position == 0) {
                textView.setPadding(DisplayUtil.dipToPix(getContext(), 8), 0,
                        DisplayUtil.dipToPix(getContext(), 8), 0);
            } else if (position == mTabModels.size() - 1) {
                textView.setPadding(DisplayUtil.dipToPix(getContext(), 8), 0,
                        DisplayUtil.dipToPix(getContext(), 16), 0);
            } else {
                textView.setPadding(DisplayUtil.dipToPix(getContext(), 8), 0,
                        DisplayUtil.dipToPix(getContext(), 8), 0);
            }

            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            try {
                NewsTabFragment mainFragment = new NewsTabFragment(mTabModels.get(position).getName());
                Bundle bundle = new Bundle();
                //bundle.putString(SecondLayerFragment.INTENT_STRING_TABNAME, tabName);
                bundle.putInt(NewsTabFragment.INTENT_INT_POSITION, position);
                mainFragment.setArguments(bundle);
                return mainFragment;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private int getTextWidth(TextView textView) {
            if (textView == null) {
                return 0;
            }
            Rect bounds = new Rect();
            String text = textView.getText().toString();
            Paint paint = textView.getPaint();
            paint.getTextBounds(text, 0, text.length(), bounds);
            int width =  bounds.width();
            return width;
        }

    }
}
