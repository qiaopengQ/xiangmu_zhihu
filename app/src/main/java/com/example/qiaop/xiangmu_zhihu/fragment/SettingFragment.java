package com.example.qiaop.xiangmu_zhihu.fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiaop.xiangmu_zhihu.MainActivity;
import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.base.fragment.SimpleFragment;
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.GreenDaoBiaoshi;
import com.example.qiaop.xiangmu_zhihu.utils.MyDbBiaoshiUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends SimpleFragment implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox cbSettingCache;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox cbSettingImage;
    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox cbSettingNight;
    @BindView(R.id.ll_setting_feedback)
    LinearLayout llSettingFeedback;
    @BindView(R.id.tv_setting_clear)
    TextView tvSettingClear;
    @BindView(R.id.ll_setting_clear)
    LinearLayout llSettingClear;
    @BindView(R.id.tv_setting_update)
    TextView tvSettingUpdate;
    @BindView(R.id.ll_setting_update)
    LinearLayout llSettingUpdate;
    Unbinder unbinder;
    private boolean isCache = false;
    private boolean isNoimage = false;
    private boolean isNight = false;
    //设置

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initData() {
        List<GreenDaoBiaoshi> identification2 = MyDbBiaoshiUtils.getInstance().identification();
        cbSettingCache.setChecked(identification2.get(0).getIsCache());
        cbSettingImage.setChecked(identification2.get(0).getIsNoimage());
        cbSettingNight.setChecked(identification2.get(0).getIsNight());
        cbSettingCache.setOnCheckedChangeListener(this);
        cbSettingImage.setOnCheckedChangeListener(this);
        cbSettingNight.setOnCheckedChangeListener(this);
        /*List<GreenDaoBiaoshi> identification = MyDbBiaoshiUtils.getInstance().identification();
        Log.e("标识数据库", "identification:" + identification);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_setting_cache:
                break;
            case R.id.cb_setting_image:
                if (cbSettingImage.isChecked()){
                List<GreenDaoBiaoshi> identification = MyDbBiaoshiUtils.getInstance().identification();
                boolean isimg = identification.get(0).getIsNoimage();
                isimg = true;
                MyDbBiaoshiUtils.getInstance().update(new GreenDaoBiaoshi((long) 1, isCache, isimg, isNight));
                }else {
                    List<GreenDaoBiaoshi> identification = MyDbBiaoshiUtils.getInstance().identification();
                    boolean isimg = identification.get(0).getIsNoimage();
                    isimg = false;
                    MyDbBiaoshiUtils.getInstance().update(new GreenDaoBiaoshi((long) 1, isCache, isimg, isNight));
                }
                break;
            case R.id.cb_setting_night:
                if (cbSettingNight.isChecked()){
                    List<GreenDaoBiaoshi> identification = MyDbBiaoshiUtils.getInstance().identification();
                    boolean isnig = identification.get(0).getIsNight();
                    isnig = true;
                    int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                    MainActivity.mDelegate.setLocalNightMode(currentNightMode == Configuration.UI_MODE_NIGHT_NO ?
                            AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                    // 同样需要调用recreate方法使之生效
                    getActivity().recreate();
                    MyDbBiaoshiUtils.getInstance().update(new GreenDaoBiaoshi((long) 1, isCache, isNoimage, isnig));
                }else {
                    List<GreenDaoBiaoshi> identification = MyDbBiaoshiUtils.getInstance().identification();
                    boolean isnig = identification.get(0).getIsNight();
                    isnig = false;
                    int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                    MainActivity.mDelegate.setLocalNightMode(currentNightMode == Configuration.UI_MODE_NIGHT_NO ?
                            AppCompatDelegate.MODE_NIGHT_NO : AppCompatDelegate.MODE_NIGHT_NO);
                    // 同样需要调用recreate方法使之生效
                    getActivity().recreate();
                    MyDbBiaoshiUtils.getInstance().update(new GreenDaoBiaoshi((long) 1, isCache, isNoimage, isnig));
                }
                break;
        }
    }
}
