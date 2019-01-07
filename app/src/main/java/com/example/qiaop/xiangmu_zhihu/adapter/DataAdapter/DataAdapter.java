package com.example.qiaop.xiangmu_zhihu.adapter.DataAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.qiaop.xiangmu_zhihu.R;
import com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans.Greendaolistbeans;
import com.example.qiaop.xiangmu_zhihu.utils.MyDbUtils;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    private List<String> strings ;
    private Context context;

    public DataAdapter(List<String> strings, Context context) {
        this.strings = strings;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_data, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tv_data.setText(strings.get(i));
        final List<Greendaolistbeans> select = MyDbUtils.getInstance().select();
        boolean isBiaoshi = select.get(i).getIsBiaoshi();
        viewHolder.switchCompat.setChecked(isBiaoshi);
        viewHolder.switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                strings.get(viewHolder.getAdapterPosition());
                viewHolder.switchCompat.setSelected(isChecked);

                Long id = select.get(i).getId();
                String name = select.get(i).getName();
                Log.e("DataAdapter",  "isChecked:" + isChecked+i);
                MyDbUtils.getInstance().update(new Greendaolistbeans(id,name,isChecked));

            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_data;
        private SwitchCompat switchCompat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_data = itemView.findViewById(R.id.tv_data);
            switchCompat = itemView.findViewById(R.id.switchCompat);
        }
    }
}
