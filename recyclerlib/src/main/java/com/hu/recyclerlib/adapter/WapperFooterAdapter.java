package com.hu.recyclerlib.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hu.recyclerlib.adapter.factory.AbsViewHolderFactory;
import com.hu.recyclerlib.viewholder.FooterViewHolder;

import java.util.List;

public abstract class WapperFooterAdapter extends AbsBaseAdapter {
    /**
     * 构造器
     *
     * @param list         数据的list
     * @param factory      ViewHolder的工厂类
     * @param recyclerView
     */
    public WapperFooterAdapter(@NonNull List list, @NonNull AbsViewHolderFactory factory, RecyclerView recyclerView) {
        super(list, factory, recyclerView);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder != null) {
            if (FooterViewHolder.class.getName().equals(viewHolder.getClass().getName())) {
                if (list.size() < 10) {
                    ((FooterViewHolder) viewHolder).rlFooter.setVisibility(View.GONE);
                } else {
                    ((FooterViewHolder) viewHolder).llLoading.setVisibility(View.GONE);
                    ((FooterViewHolder) viewHolder).tvTips.setVisibility(View.VISIBLE);
                }
            } else {
                super.onBindViewHolder(viewHolder, position);
            }
        }
        super.onBindViewHolder(viewHolder, position);
    }
}
