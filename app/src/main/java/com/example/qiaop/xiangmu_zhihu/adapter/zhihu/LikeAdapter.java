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
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.GreenDaocollect;
import com.example.qiaop.xiangmu_zhihu.utils.MyDbBiaoshiUtils;

import java.util.List;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.ViewHolder> {
    private List<GreenDaocollect> greenDaocollects;
    private Context context;

    public LikeAdapter(List<GreenDaocollect> greenDaocollects, Context context) {
        this.greenDaocollects = greenDaocollects;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_zhihu_like, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tv_like_title.setText(greenDaocollects.get(i).getTitle());
        viewHolder.tv_like_name.setText(greenDaocollects.get(i).getName());
        if (greenDaocollects.get(i).getImg()!=null){
            List<GreenDaoBiaoshi> identification = MyDbBiaoshiUtils.getInstance().identification();
            boolean isNoimage = identification.get(0).getIsNoimage();
            if (isNoimage){
                viewHolder.img_like.setImageResource(R.mipmap.start_geek);
            }else{
                Glide.with(context).load(greenDaocollects.get(i).getImg()).into(viewHolder.img_like);
            }
        }else if (greenDaocollects.get(i).getName().equals("来自干货集中营_Android")){
            viewHolder.img_like.setImageResource(R.mipmap.ic_android);
        }else if (greenDaocollects.get(i).getName().equals("来自干货集中营_IOS")){
            viewHolder.img_like.setImageResource(R.mipmap.ic_ios);
        }else if (greenDaocollects.get(i).getName().equals("来自干货集中营_前端")){
            viewHolder.img_like.setImageResource(R.mipmap.ic_web);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.Click(greenDaocollects.get(i).getUrl(),greenDaocollects.get(i).getImg(),greenDaocollects.get(i).getTitle(),greenDaocollects.get(i).getDataid(),i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return greenDaocollects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_like;
        private TextView tv_like_title;
        private TextView tv_like_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_like = itemView.findViewById(R.id.img_like);
            tv_like_title = itemView.findViewById(R.id.tv_like_title);
            tv_like_name = itemView.findViewById(R.id.tv_like_name);
        }
    }
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void Click(String url,String image,String title,int id,int position);
    }
}
