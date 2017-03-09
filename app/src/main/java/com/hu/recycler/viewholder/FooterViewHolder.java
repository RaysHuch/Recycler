package com.hu.recycler.viewholder;

import com.hu.recycler.R;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FooterViewHolder extends ViewHolder {
	public RelativeLayout rlFooter;
	public LinearLayout llLoading;
	public TextView tvTips;

	public FooterViewHolder(View view) {
		super(view);
		rlFooter = (RelativeLayout) view.findViewById(R.id.rlFooter);
		llLoading = (LinearLayout) view.findViewById(R.id.llLoading);
		tvTips = (TextView) view.findViewById(R.id.tvTips);
	}
}