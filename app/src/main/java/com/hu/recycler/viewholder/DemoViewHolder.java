package com.hu.recycler.viewholder;

import com.hu.recycler.R;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DemoViewHolder extends ViewHolder {
	public TextView textView1;
	public TextView textView2;
	public Button button1;

	public DemoViewHolder(View itemView) {
		super(itemView);
		textView1 = (TextView) itemView.findViewById(R.id.textView1);
		textView2 = (TextView) itemView.findViewById(R.id.textView2);
		button1 = (Button) itemView.findViewById(R.id.button1);
	}

}
