package com.example.qiaop.xiangmu_zhihu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qiaop.xiangmu_zhihu.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
    //设置

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        return view;
    }

}
