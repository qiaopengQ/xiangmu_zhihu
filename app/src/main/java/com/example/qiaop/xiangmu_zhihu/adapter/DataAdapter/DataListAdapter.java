package com.example.qiaop.xiangmu_zhihu.adapter.DataAdapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.beans.DataListBean;
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.GreenDaoBiaoshi;
import com.example.qiaop.xiangmu_zhihu.utils.MyDbBiaoshiUtils;

import java.util.List;

import javax.microedition.khronos.opengles.GL;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ViewHolder> {
    private List<DataListBean.RESULTBean.NewsListBean> newsListBeans;
    private Context context;

    public DataListAdapter(List<DataListBean.RESULTBean.NewsListBean> newsListBeans, Context context) {
        this.newsListBeans = newsListBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_datalist, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_dataname.setText(newsListBeans.get(i).getTitle());
        viewHolder.tv_datatime.setText(newsListBeans.get(i).getPublishTime());
        viewHolder.tv_source.setText(newsListBeans.get(i).getSource());
        List<GreenDaoBiaoshi> identification = MyDbBiaoshiUtils.getInstance().identification();
        boolean isNoimage = identification.get(0).getIsNoimage();
        if (isNoimage){
            viewHolder.img_data.setImageResource(R.mipmap.start_geek);
        }else {
            Glide.with(context).load(newsListBeans.get(i).getNewsImg()).into(viewHolder.img_data);
        }
    }

    @Override
    public int getItemCount() {
        return newsListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_data;
        private TextView tv_dataname;
        private TextView tv_datatime;
        private TextView tv_source;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_data = itemView.findViewById(R.id.img_data);
            tv_dataname = itemView.findViewById(R.id.tv_dataname);
            tv_datatime = itemView.findViewById(R.id.tv_datatime);
            tv_source = itemView.findViewById(R.id.tv_source);
        }
    }
}
