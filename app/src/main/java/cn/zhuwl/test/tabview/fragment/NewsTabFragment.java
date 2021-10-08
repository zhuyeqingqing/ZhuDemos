package cn.zhuwl.test.tabview.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import cn.zhuwl.test.R;


public class NewsTabFragment extends Fragment {
    public static final String INTENT_STRING_TABNAME = "intent_String_tabName";
    public static final String INTENT_INT_POSITION = "intent_int_position";
    private String mNewsClassifyName;
    private int position;
    private TextView textView;
    private ProgressBar progressBar;

    public NewsTabFragment(String classifyName){
        mNewsClassifyName = classifyName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_news, container, false);
        TextView tv_Name = rootView.findViewById(R.id.tv_news);
        //Todo
        tv_Name.setText(mNewsClassifyName);
        return rootView;

    }
}
