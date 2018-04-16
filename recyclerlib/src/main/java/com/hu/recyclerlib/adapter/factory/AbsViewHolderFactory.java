package com.hu.recyclerlib.adapter.factory;

import java.lang.reflect.InvocationTargetException;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * ViewHolder的工厂类
 * @author hch
 * @date 2015年11月23日
 */
public abstract class AbsViewHolderFactory {
	/**
	 * 创建ViewHolder
	 * @param context
	 * @param itemType 每个Item的类型
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public final ViewHolder createViewHolder(Context context, int itemType)
			throws ClassNotFoundException, NoSuchMethodException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		View view = LayoutInflater.from(context).inflate(getLayoutId(itemType), null);
		return getViewHolder(itemType).getConstructor(View.class)
				.newInstance(view);
	}

	public final ViewHolder createViewHolder(Context context,Class<? extends ViewHolder> viewholderClass,@LayoutRes int layoutId)
			throws NoSuchMethodException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		View view = LayoutInflater.from(context).inflate(layoutId, null);
		return viewholderClass.getConstructor(View.class)
				.newInstance(view);
	}

	/**
	 * 根据对应的itemType，返回对应的item布局id
	 * @param itemType itemType
	 * @return layoutID
	 */
	public abstract int getLayoutId(int itemType);

	/**
	 * 根据对应的itemType，返回对应的ViewHolder
	 * @param itemType itemType
	 * @return viewHolder
	 */
	public abstract Class<? extends ViewHolder> getViewHolder(int itemType);
}