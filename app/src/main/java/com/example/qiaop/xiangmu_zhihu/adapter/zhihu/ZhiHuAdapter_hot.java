package com.example.qiaop.xiangmu_zhihu.adapter.zhihu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.GreenDaoBiaoshi;
import com.example.qiaop.xiangmu_zhihu.beans.HotListBean;
import com.example.qiaop.xiangmu_zhihu.utils.MyDbBiaoshiUtils;

import java.util.List;

public class ZhiHuAdapter_hot extends RecyclerView.Adapter<ZhiHuAdapter_hot.ViewHolder>{
    private List<HotListBean.RecentBean> hot;
    private Context context;

    public ZhiHuAdapter_hot(List<HotListBean.RecentBean> hot, Context context) {
        this.hot = hot;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_hot, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv.setText(hot.get(position).getTitle());
        List<GreenDaoBiaoshi> identification = MyDbBiaoshiUtils.getInstance().identification();
        boolean isNoimage = identification.get(0).getIsNoimage();
        if (isNoimage){
            holder.img.setImageResource(R.mipmap.start_geek);
        }else {
            Glide.with(context).load(hot.get(position).getThumbnail()).into(holder.img);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickList.Click(hot.get(position).getNews_id(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hot.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv = itemView.findViewById(R.id.tv);
        }
    }
    ClickList clickList;

    public void setClickList(ClickList clickList) {
        this.clickList = clickList;
    }

    public interface ClickList{
        void Click(int id,int position);
    }
}
