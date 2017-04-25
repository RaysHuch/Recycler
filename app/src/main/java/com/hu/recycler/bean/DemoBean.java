package com.hu.recycler.bean;

import com.hu.recyclerlib.itemtype.ItemType;

@ItemType(0)
public class DemoBean{
	public String text1;
	public String text2;
	public String btnText;

	public DemoBean() {
		// TODO Auto-generated constructor stub
	}

	public DemoBean(String text1, String text2, String btnText) {
		this.text1 = text1;
		this.text2 = text2;
		this.btnText = btnText;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public String getBtnText() {
		return btnText;
	}

	public void setBtnText(String btnText) {
		this.btnText = btnText;
	}

}
