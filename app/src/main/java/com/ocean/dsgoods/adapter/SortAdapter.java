package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.entity.SortModelInfo;

import java.util.List;


public class SortAdapter extends BaseAdapter implements SectionIndexer{
	private List<SortModelInfo> list = null;
	private Context mContext;
	
	public SortAdapter(Context mContext, List<SortModelInfo> list) {
		this.mContext = mContext;
		this.list = list;
	}
	
	public void updateListView(List<SortModelInfo> list){
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final SortModelInfo mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.item_select_supplier, null);
			viewHolder.tvName = (TextView) view.findViewById(R.id.tv_name);
			viewHolder.tvKPI = (TextView) view.findViewById(R.id.tv_kpi);
			viewHolder.tvNum = (TextView) view.findViewById(R.id.tv_num);
			viewHolder.tvDispatch = (TextView) view.findViewById(R.id.tv_dispatch);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.tvName.setText(list.get(position).getName());
		return view;
	}

	final static class ViewHolder {
		TextView tvName;
		TextView tvNum;
		TextView tvKPI;
		TextView tvDispatch;
	}

	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}

	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		
		return -1;
	}
	
	private String getAlpha(String str) {
		String  sortStr = str.trim().substring(0, 1).toUpperCase();
		// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}