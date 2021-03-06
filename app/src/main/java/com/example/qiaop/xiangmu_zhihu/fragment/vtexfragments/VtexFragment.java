package com.example.qiaop.xiangmu_zhihu.fragment.vtexfragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.adapter.FragmentAdapter;
import com.example.qiaop.xiangmu_zhihu.fragment.vtexfragments.activity.NodeActivity;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class VtexFragment extends Fragment implements View.OnClickListener {


    private TabLayout tab_vtex;
    private ImageView img_vtex;
    private ViewPager vp_vtex;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    public VtexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vtex, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tab_vtex = (TabLayout) view.findViewById(R.id.tab_vtex);
        img_vtex = (ImageView) view.findViewById(R.id.img_vtex);
        vp_vtex = (ViewPager) view.findViewById(R.id.vp_vtex);
        img_vtex.setOnClickListener(this);
        //"技术", "创意", "好玩", "Apple", "酷工作", "交易", "城市", "问与答", "最热", "全部", "R2"
        strings.add("技术");
        strings.add("创意");
        strings.add("好玩");
        strings.add("Apple");
        strings.add("酷工作");
        strings.add("交易");
        strings.add("问与答");
        strings.add("最热");
        strings.add("全部");
        strings.add("R2");
        //"tech", "creative", "play", "apple", "jobs", "deals", "city", "qna", "hot", "all", "r2"
        list.add("tech");
        list.add("creative");
        list.add("play");
        list.add("apple");
        list.add("jobs");
        list.add("deals");
        list.add("city");
        list.add("qna");
        list.add("hot");
        list.add("all");
        list.add("r2");
        for (int i = 0; i < strings.size(); i++) {
            fragments.add(new V2exPageFragment(list.get(i)));
        }
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(),fragments,strings);
        vp_vtex.setAdapter(fragmentAdapter);
        tab_vtex.setupWithViewPager(vp_vtex);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), NodeActivity.class);
        startActivity(intent);
    }
}
