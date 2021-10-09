package cn.zhuwl.test.tabview.fragment;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
        TextView tv_news_shan = rootView.findViewById(R.id.tv_news_shan);
        int[] colors = new int[]{getResources().getColor(R.color.tab_text_start_color,null),
                getResources().getColor(R.color.tab_text_end_color,null),
                getResources().getColor(R.color.tab_indicator_color,null)};

        tv_Name.setText(mNewsClassifyName);
        tv_news_shan.setText(mNewsClassifyName);
        LinearGradient linearGradient = new LinearGradient(0f,0f,100,0f,colors,new float[]{0f,0.5f, 1f},
                Shader.TileMode.CLAMP);
        tv_Name.setTextSize(18);
        tv_Name.getPaint().setShader(linearGradient);
        tv_Name.requestLayout();
        tv_Name.invalidate();
        return rootView;

    }
}
