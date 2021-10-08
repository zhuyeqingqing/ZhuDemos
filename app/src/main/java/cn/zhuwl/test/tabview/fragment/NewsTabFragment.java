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
import androidx.recyclerview.widget.RecyclerView;

import cn.zhuwl.test.R;


public class NewsTabFragment extends TabBaseFragment {
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
        RecyclerView recyclerView1 = rootView.findViewById(R.id.news_tab_recycle);
        //Todo

        return rootView;

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            textView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }

        ;
    };


    @Override
    void lazyInit() {

    }
}
