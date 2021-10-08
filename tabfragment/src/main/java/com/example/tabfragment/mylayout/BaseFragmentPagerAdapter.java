package com.example.tabfragment.mylayout;

import android.util.Log;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    public BaseFragmentPagerAdapter(FragmentManager fm, List<? extends Fragment> fragments) {
        super(fm);
        mFragmentManager = fm;
        mFragments.addAll(fragments);
    }

    public void setItems(List<Fragment> fragments) {
        mFragments.clear();
        mFragments.addAll(fragments);
        notifyDataSetChanged();
    }

    public void removeAllItems() {
        mFragments.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {

        return mFragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        if (!mFragments.contains(object))
            return PagerAdapter.POSITION_NONE;

        return mFragments.indexOf(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object != null && position == mFragments.size()) {
            try {
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.remove((Fragment) object);
                fragmentTransaction.commitAllowingStateLoss();
            } catch (Exception e) {
            }
            super.destroyItem(container, position, object);
        }
    }

    //solve java.lang.NullPointerException: Attempt to invoke virtual method
    // 'void android.support.v4.app.FragmentManagerImpl.performPendingDeferredStart
    // (android.support.v4.app.Fragment)' on a null object reference
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        try {
            super.setPrimaryItem(container, position, object);
        } catch (Exception e) {
            Log.e("Baser", "catch NullPointerException about performPendingDeferredStart", e);
        }
    }

}
