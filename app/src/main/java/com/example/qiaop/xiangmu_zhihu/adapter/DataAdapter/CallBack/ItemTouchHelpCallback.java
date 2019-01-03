package com.example.qiaop.xiangmu_zhihu.adapter.DataAdapter.CallBack;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class ItemTouchHelpCallback extends ItemTouchHelper.Callback {
    private OnItemTouchCallbackListener onItemTouchCallbackListener;

    public void setOnItemTouchCallbackListener(OnItemTouchCallbackListener onItemTouchCallbackListener) {
        this.onItemTouchCallbackListener = onItemTouchCallbackListener;
    }

    /*
    * 当Item被滑动的时候是否可以被拖拽
    * */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
    /*
    * Item是否可以被左右滑动(h:上下滑动 , v:左右滑动)
    * */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /*
    * 当用户滑动或者拖拽的时候
    * */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            //flag 如果值是0  相当于这个值被关闭
            int dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP |ItemTouchHelper.DOWN;
            int swipeFlag = 0;
            return makeMovementFlags(dragFlag,swipeFlag);
        }else if (layoutManager instanceof LinearLayoutManager){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int orientation = linearLayoutManager.getOrientation();
            int dragFlag = 0;
            int swipeFlag = 0;
            if (orientation == LinearLayoutManager.HORIZONTAL){ //横向的条目
                swipeFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }else if (orientation == LinearLayoutManager.VERTICAL){//纵向的条目
                dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
            return makeMovementFlags(dragFlag,swipeFlag);
        }
        return 0;
    }
    /*
    * 当Item拖拽的时候回调
    * */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        if (onItemTouchCallbackListener !=null){
            onItemTouchCallbackListener.Onmove(viewHolder.getAdapterPosition(),viewHolder1.getAdapterPosition());
        }

        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (onItemTouchCallbackListener != null){
            onItemTouchCallbackListener.Onswiped(viewHolder.getAdapterPosition());
        }

    }
    public interface OnItemTouchCallbackListener{
        //当Item被左右滑动的时候回调
        void Onswiped(int adapterPosition);
        boolean Onmove(int adapterPosition, int adapterPosition1);
    }
}
