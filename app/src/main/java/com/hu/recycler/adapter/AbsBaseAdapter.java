package com.hu.recycler.adapter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.hu.recycler.adapter.factory.AbsViewHolderFactory;
import com.hu.recycler.bean.itemtype.ItemType;
import com.hu.recycler.viewholder.FooterViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基与RecyclerView.Adapter的BaseAdapter。<br/>
 * 其中泛型T，当已知的T类型是相同的类型，可直接将类型传入， 当类型不确定，需使用Object类型，
 * 之后根据需要强制类型转换
 * @author hch
 * @param <T>
 * @date 2015年11月23日
 * @param <T>
 *            Bean
 */
public abstract class AbsBaseAdapter<T extends Object> extends Adapter<ViewHolder> {

	private List<T> list;
	AbsViewHolderFactory factory;

	/**
	 * 构造器
	 * 
	 * @param list
	 *            数据的list
	 * @param factory
	 *            ViewHolder的工厂类
	 */
	public AbsBaseAdapter(@NonNull List<T> list, @NonNull AbsViewHolderFactory factory) {
		this.list = list;
		this.factory = factory;
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
		if (viewHolder != null) {
			if (FooterViewHolder.class.getName().equals(viewHolder.getClass().getName())) {
				if (list.size() < 10) {
					((FooterViewHolder) viewHolder).rlFooter.setVisibility(View.GONE);
				} else {
					((FooterViewHolder) viewHolder).llLoading.setVisibility(View.GONE);
					((FooterViewHolder) viewHolder).tvTips.setVisibility(View.VISIBLE);
				}
			} else {
				bindViewHolders(viewHolder, list.get(position), position);
			}
		}
	}

	/**
	 * 绑定ViewHolder
	 * 
	 * @param viewHolder
	 *            对应的ViewHolder
	 * @param bean
	 *            数据
	 * @param position
	 *            位置
	 */
	public abstract void bindViewHolders(ViewHolder viewHolder, T bean, int position);

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		try {
			return factory.createViewHolder(parent.getContext(), viewType);
		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

}
