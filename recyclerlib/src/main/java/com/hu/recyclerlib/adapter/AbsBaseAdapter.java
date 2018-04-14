package com.hu.recyclerlib.adapter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.hu.recyclerlib.R;
import com.hu.recyclerlib.adapter.factory.AbsViewHolderFactory;
import com.hu.recyclerlib.itemtype.ItemType;
import com.hu.recyclerlib.viewholder.FooterViewHolder;
import com.hu.recyclerlib.viewholder.NoneViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基与RecyclerView.Adapter的BaseAdapter。<br/>
 * 其中泛型T，当已知的T类型是相同的类型，可直接将类型传入， 当类型不确定，需使用Object类型，
 * 之后根据需要强制类型转换
 *
 * @param <T>
 * @param <T> Bean
 * @author hch
 * @date 2015年11月23日
 */
public abstract class AbsBaseAdapter<T extends Object> extends Adapter<ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private final RecyclerView recyclerView;
    List<T> list;
    private AbsViewHolderFactory factory;
    OnItemClickListener onItemClickListener;
    OnItemLongClickListener onItemLongClickListener;

    /**
     * 构造器
     *
     * @param list    数据的list
     * @param factory ViewHolder的工厂类
     */
    public AbsBaseAdapter(@NonNull List<T> list, @NonNull AbsViewHolderFactory factory, RecyclerView recyclerView) {
        this.list = list;
        this.factory = factory;
        this.recyclerView = recyclerView;
    }

    /**
     * 为了添加加载更多,item数量要加1
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getClass().getAnnotation(ItemType.class).value();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        bindViewHolders(viewHolder, list.get(position), position);
        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(this);
        }
        if (onItemLongClickListener != null) {
            viewHolder.itemView.setOnLongClickListener(this);
        }
    }

    /**
     * 绑定ViewHolder
     *
     * @param viewHolder 对应的ViewHolder
     * @param bean       数据
     * @param position   位置
     */
    public abstract void bindViewHolders(ViewHolder viewHolder, T bean, int position);

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            return factory.createViewHolder(parent.getContext(), viewType);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new NoneViewHolder(LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.layout_listview_none, null));
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(recyclerView.getChildAdapterPosition(v), v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return onItemLongClickListener != null && onItemLongClickListener.onItemLongClick(recyclerView.getChildAdapterPosition(v), v);
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(int position, View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
