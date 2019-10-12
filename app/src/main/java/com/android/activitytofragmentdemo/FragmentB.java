package com.android.activitytofragmentdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * @ClassName: FragmentA
 * @Description: java类作用描述
 * @Author: shawnleng
 * @CreateDate: 2019/10/12 17:14
 */
public class FragmentB extends Fragment {
    private View viewRoot;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (viewRoot==null){
            viewRoot = inflater.inflate(R.layout.fragment_b, null);
            initData();
        }
        return viewRoot;
    }

    private void initData() {
        Bundle arguments = getArguments();
        if (arguments!=null){
            String data = arguments.getString("data");
            Toast.makeText(getActivity(),data,Toast.LENGTH_SHORT).show();
        }

    }
}
