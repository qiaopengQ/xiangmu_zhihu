package com.example.qiaop.xiangmu_zhihu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.adapter.zhihu.RecycleAdapter;
import com.example.qiaop.xiangmu_zhihu.beans.GankListBean;

import java.util.List;

public class GankAdapter_IOS extends RecyclerView.Adapter<GankAdapter_IOS.ViewHolder>{
    private List<GankListBean.ResultsBean> list;
    private Context context;

    public GankAdapter_IOS(List<GankListBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public GankAdapter_IOS.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_gankios, parent, false);
        GankAdapter_IOS.ViewHolder viewHolder = new GankAdapter_IOS.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GankAdapter_IOS.ViewHolder holder, final int position) {
        holder.tv_desc.setText(list.get(position).getDesc());
        holder.tv_who.setText(list.get(position).getWho());
        holder.tv_time.setText(list.get(position).getPublishedAt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickList.Click(list.get(position).getUrl(),list.get(position).getDesc());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_desc;
        private TextView tv_who;
        private TextView tv_time;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_who = itemView.findViewById(R.id.tv_who);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }


    OnClickList onClickList;

    public void setOnClickList(OnClickList onClickList) {
        this.onClickList = onClickList;
    }

    public interface OnClickList{
        void Click(String url,String desc);
    }

}
