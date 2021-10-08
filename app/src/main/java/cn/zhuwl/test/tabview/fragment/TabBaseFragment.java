package cn.zhuwl.test.tabview.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

abstract class TabBaseFragment extends Fragment {
	protected LayoutInflater inflater;
	private View contentView;
	private Context context;
	private ViewGroup container;
	private boolean isLoaded = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity().getApplicationContext();
	}

	@Override
	public void onResume() {
		super.onResume();
		//增加了Fragment是否可见的判断
		if (!isLoaded) {
			lazyInit();
			isLoaded = true;
		}
	}

	abstract void lazyInit();

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		isLoaded = false;
		contentView = null;
		container = null;
		inflater = null;
	}

	public Context getApplicationContext() {
		return context;
	}


	public void setContentView(View view) {
		contentView = view;
	}

	public View getContentView() {
		return contentView;
	}

	public <T extends View> T findViewById(@IdRes int id) {
		if (contentView != null)
			return contentView.findViewById(id);
		return null;
	}
}
