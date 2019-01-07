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
import com.example.qiaop.xiangmu_zhihu.beans.SectionListBean;
import com.example.qiaop.xiangmu_zhihu.utils.MyDbBiaoshiUtils;

import java.util.List;

public class ZhiHuAdapter extends RecyclerView.Adapter<ZhiHuAdapter.ViewHolder>{
    private List<SectionListBean.DataBean> data;
    private Context context;

    public ZhiHuAdapter(List<SectionListBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_sections, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        List<GreenDaoBiaoshi> identification = MyDbBiaoshiUtils.getInstance().identification();
        boolean isNoimage = identification.get(0).getIsNoimage();
        if (isNoimage){
            holder.img.setImageResource(R.mipmap.start_geek);
        }else {
            Glide.with(context).load(data.get(position).getThumbnail()).into(holder.img);
        }
        holder.tv1.setText(data.get(position).getName());
        holder.tv2.setText(data.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.Click(data.get(position).getName(),data.get(position).getId(),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv1;
        private TextView tv2;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
        }
    }
    ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void Click(String title,int id,int position);
    }
}
