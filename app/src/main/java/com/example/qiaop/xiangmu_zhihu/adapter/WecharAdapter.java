package com.example.qiaop.xiangmu_zhihu.adapter;

import android.content.Context;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.GreenDaoBiaoshi;
import com.example.qiaop.xiangmu_zhihu.beans.WecharListBean;
import com.example.qiaop.xiangmu_zhihu.utils.MyDbBiaoshiUtils;

import java.util.List;

public class WecharAdapter extends RecyclerView.Adapter<WecharAdapter.ViewHolder>{
    private List<WecharListBean.NewslistBean> wecharlist;
    private Context context;

    public WecharAdapter(List<WecharListBean.NewslistBean> wecharlist, Context context) {
        this.wecharlist = wecharlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_wechar, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv.setText(wecharlist.get(position).getTitle());
        holder.tv_description.setText(wecharlist.get(position).getDescription());
        holder.tv_ctime.setText(wecharlist.get(position).getCtime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickwechar.Click(wecharlist.get(position).getUrl(),wecharlist.get(position).getTitle(),wecharlist.get(position).getPicUrl());
            }
        });
        List<GreenDaoBiaoshi> identification = MyDbBiaoshiUtils.getInstance().identification();
        boolean isNoimage = identification.get(0).getIsNoimage();
        if (isNoimage){
            Toast.makeText(context, "无图模式", Toast.LENGTH_SHORT).show();
            holder.img.setImageResource(R.mipmap.start_geek);
        }else {
            Glide.with(context).load(wecharlist.get(position).getPicUrl()).into(holder.img);
        }
        Log.e("WecharAdapter", "identification:" + isNoimage);

    }

    @Override
    public int getItemCount() {
        return wecharlist.size();
    }
    public void setData(List<WecharListBean.NewslistBean> list){
        wecharlist.clear();
        wecharlist = list;
        notifyDataSetChanged();
    }

    public interface OnClickList {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv;
        private TextView tv_ctime;
        private TextView tv_description;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv = itemView.findViewById(R.id.tv);
            tv_ctime = itemView.findViewById(R.id.tv_ctime);
            tv_description = itemView.findViewById(R.id.tv_description);
        }
    }
    OnClickwechar clickwechar;

    public void setClickwechar(OnClickwechar clickwechar) {
        this.clickwechar = clickwechar;
    }

    public interface OnClickwechar{
        void Click(String url,String title,String image);
    }
}
