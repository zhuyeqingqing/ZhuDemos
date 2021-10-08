package cn.zhuwl.test;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import cn.zhuwl.test.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyFragment extends Fragment {
    private static final String DATA_URI =
            "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD//gA7Q1JFQVRPUjogZ2QtanBlZyB2MS4wICh1c2luZ"
                    + "yBJSkcgSlBFRyB2ODApLCBxdWFsaXR5ID0gNzUK/9sAQwAIBgYHBgUIBwcHCQkICgwUDQwLCwwZEhMPFB0aHx4"
                    + "dGhwcICQuJyAiLCMcHCg3KSwwMTQ0NB8nOT04MjwuMzQy/9sAQwEJCQkMCwwYDQ0YMiEcITIyMjIyMjIyMjIyM"
                    + "jIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIy/8AAEQgAZABkAwEiAAIRAQMRAf/EAB8AAAE"
                    + "FAQEBAQEBAAAAAAAAAAABAgMEBQYHCAkKC//EALUQAAIBAwMCBAMFBQQEAAABfQECAwAEEQUSITFBBhNRYQcic"
                    + "RQygZGhCCNCscEVUtHwJDNicoIJChYXGBkaJSYnKCkqNDU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN"
                    + "0dXZ3eHl6g4SFhoeIiYqSk5SVlpeYmZqio6Slpqeoqaqys7S1tre4ubrCw8TFxsfIycrS09TV1tfY2drh4uPk5"
                    + "ebn6Onq8fLz9PX29/j5+v/EAB8BAAMBAQEBAQEBAQEAAAAAAAABAgMEBQYHCAkKC//EALURAAIBAgQEAwQHBQQ"
                    + "EAAECdwABAgMRBAUhMQYSQVEHYXETIjKBCBRCkaGxwQkjM1LwFWJy0QoWJDThJfEXGBkaJicoKSo1Njc4OTpDR"
                    + "EVGR0hJSlNUVVZXWFlaY2RlZmdoaWpzdHV2d3h5eoKDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i"
                    + "5usLDxMXGx8jJytLT1NXW19jZ2uLj5OXm5+jp6vLz9PX29/j5+v/aAAwDAQACEQMRAD8A+f6KKKACiiigAoooo"
                    + "AKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAoorU8NabDrPirSNLuGkWC9vYbe"
                    + "RoyAwV3CkjIIzg+hoAy6K6HxJ4VvdH1bVxbWF++kWd/Pax3kkLFCEkKDLgBc8DPvWbpuh6trLMul6Xe3xT7wtb"
                    + "d5dv12g0AUKKmuLO6tLtrS5tpoblG2tDIhV1PoVPOauX3h7W9LtluNQ0fULSBjhZbi2eNSfYkAUAZtFX9O0LV9"
                    + "XSR9N0q+vUi/1jW1u8gT67QcVe8K+GJ/E3iNNK85bRFV5bqeYcQRIMuxHsB09cdKAMKiu9vtC8A3+h6jN4c13U"
                    + "ItRsI/N8nV/KjS7UH5hFjndjkKeT6dxj+B/B9z408RQ6dFIILYFTc3LfdiQsFHXqxJAUdyaAOaorU8S6bDo3ir"
                    + "V9Lt2kaCyvZreNpCCxVHKgnAAzgegrLoAKKKKACug8Cf8lD8Nf9hW1/8ARq1z9WLC+uNM1G2v7OTy7q1lSaF9o"
                    + "O11IKnB4OCB1oA96sNX8R6l8bPEGi6zJcv4cCXaXdq4IgjtdrFHx90E/L83U5PJrnPC0t7pnw/0i51nxjq2laT"
                    + "czzLp1losH76Rlf5y8igH72cKxPHTjiuJv/iR4w1PSbjS7vXbiSzuZHkmjAVd5dizDIAO0kn5c7e2MUzQPiD4r"
                    + "8L6e9ho2sS21q7FjFsRwCepG4Hb+GKAPT/Gt/Novxj8G6pDpF/qlwmkQO1rNFm6mb96pLKoP70DDHA4K1FfXv8"
                    + "Awk3hvxNN4e8aa8yxWbzXuka5AJgIx95VkOVRgemPm4/GvK9Q8Y+IdVu7C7vtVnmu9Pz9muTgSp82774G44PTJ"
                    + "OO1Xtb+JPjDxFpp07VNcnntGxviCJGHwc/NtUFufWgD1O41XQ/DfgPweJb7xjY2s2npKH0B4khknJzJvZuS+7s"
                    + "TjGMDrS6ZrNlqPxU1Oe10TUVur3wxLELXVrZYZb6cAHJVDgh0TqMZ5wBXk/h74heK/Ctk1no2szW1sxJ8ookig"
                    + "nqQHB2/hisqbXtWuNb/ALal1G5bU94kF0ZD5gYdCD2xQB6ZpXiLVfHPhTxXaeKbe3ksdL017i0mW0SE2lwpASN"
                    + "SoGM8jB54xWj4ObwYbTwhpen+MPsd59utby+s/wCzJne8uw6lY2l4UKpyo6jJLHNeb6/8QvFfiiwSx1jWZrm1Q"
                    + "7vK2IisexbaBu/HNYNhfXGmajbX9nJ5d1aypNC+0Ha6kFTg8HBA60AdR8ULTTrT4h6x/Z2qfb/Ou5pbj/R2i+z"
                    + "zGV90XP3tvHzDg5rj6sX99canqNzf3knmXV1K80z7QNzsSWOBwMknpVegAooooAKKKKACiiigAooooAKKKKACi"
                    + "iigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAoo"
                    + "ooA//2Q==";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        System.out.println("----------------onAttach");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File file1 = getActivity().getExternalFilesDir(null);
        File file2 = Environment.getExternalStorageDirectory();
        File file3 = getActivity().getFilesDir();
        File file4 = getActivity().getExternalFilesDir(null);
        File file5 = Environment.getDataDirectory();
        File file6 = getActivity().getDir("fir",Context.MODE_PRIVATE);
        File file7 = Environment.getExternalStoragePublicDirectory("");
        File file8 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);

        System.out.println("----------------file1:"+file1.getAbsolutePath());
        System.out.println("----------------file2:"+file2.getAbsolutePath());
        System.out.println("----------------file3:"+file3.getAbsolutePath());
        System.out.println("----------------file3:"+file4.getAbsolutePath());
        System.out.println("----------------file5:"+file5.getAbsolutePath());
        System.out.println("----------------file6:"+file6.getAbsolutePath());
        System.out.println("----------------file7:"+file7.getAbsolutePath());
        System.out.println("----------------file8:"+file8.getAbsolutePath());
        System.out.println("----------------onCreate");

        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(root,"/c/b.txt");
        if(!file.exists()){
            file.mkdirs();
        }
        try {
            FileReader fileReader = new FileReader(file);

            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(" fdas沙发的法定");
            fileWriter.close();
        } catch (IOException e){
            System.out.println(e);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("----------------onCreateView");
        View view = inflater.inflate(R.layout.myfragment_layout,null);
        ImageView imageView = view.findViewById(R.id.iv_first);
        ImageView iv_2 = view.findViewById(R.id.iv_2);
        ImageView iv_3 = view.findViewById(R.id.iv_3);
        ImageView iv_4 = view.findViewById(R.id.iv_4);
        //content://media/external/images/media/731

        ///storage/emulated/0/Pictures/4a8a08f0-9d37-3737-9564-9038408b5f33_1629772623216.jpg

        /*Glide.with(getActivity()).load("/storage/emulated/0/Pictures/4a8a08f0-9d37-3737-9564-9038408b5f33_1629772623216.jpg").
                into(imageView);
        Glide.with(getActivity()).load(new File("/storage/emulated/0/Pictures/4a8a08f0-9d37-3737-9564-9038408b5f33_1629772623216.jpg"))
                .into(iv_2);
        Uri uri = Uri.parse("/storage/emulated/0/Pictures/4a8a08f0-9d37-3737-9564-9038408b5f33.jpg");
        Log.i("hehe",uri.toString());
        Glide.with(getActivity()).load(uri).into(iv_3);*/
        //Glide.with(getActivity()).load("/storage/emulated/0/Pictures/4a8a08f0-9d37-3737-9564-9038408b5f33.jpg").into(iv_4);

        Glide.with(getActivity())
                .load(DATA_URI)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(iv_4);
        //getImagesInfo();
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("----------------onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("----------------onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("----------------onResume");
    }



    @Override
    public void onPause() {
        super.onPause();
        System.out.println("----------------onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("----------------onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("----------------onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("----------------onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("----------------onDetach");
    }
}
