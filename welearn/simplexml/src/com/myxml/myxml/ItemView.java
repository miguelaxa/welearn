package com.myxml.myxml;

import com.myxml.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemView extends LinearLayout {

	public ItemView(Context context, Item item) {
		super(context);
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater lf;
		lf = (LayoutInflater) getContext().getSystemService(infService);
		lf.inflate(R.layout.row, this, true);

		setTextView((TextView)findViewById(R.id.rss_item_title));
		setlinkView((TextView)findViewById(R.id.linkt));
		Log.i("Examples", item.getTitle());
		getTextView().setText(item.getTitle());
		getlinkView().setText(item.getLink());
	}

	public void setTextView(TextView textView) {
		m_TextView = textView;
	}
	public void setlinkView(TextView linkView) {
		m_linkView = linkView;
	}

	public TextView getTextView() {
		return m_TextView;
	}
	public TextView getlinkView() {
		return m_linkView;
	}
	private TextView m_TextView;
	private TextView m_linkView;
}
