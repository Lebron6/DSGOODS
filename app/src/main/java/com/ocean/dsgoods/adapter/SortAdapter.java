package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.entity.SortModelInfo;
import com.ocean.dsgoods.entity.SupplierList;
import com.ocean.dsgoods.tools.GlideCircleTransform;

import java.util.List;


public class SortAdapter extends BaseAdapter{
	private List<SupplierList.ListBean> list = null;
	private Context mContext;
	
	public SortAdapter(Context mContext, List<SupplierList.ListBean> list) {
		this.mContext = mContext;
		this.list = list;
	}
	
	public void updateListView(List<SupplierList.ListBean> list){
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
		final SupplierList.ListBean mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.item_select_supplier, null);
			viewHolder.tvName = (TextView) view.findViewById(R.id.tv_name);
			viewHolder.tvKPI = (TextView) view.findViewById(R.id.tv_kpi);
			viewHolder.tvNum = (TextView) view.findViewById(R.id.tv_num);
			viewHolder.tvDispatch = (TextView) view.findViewById(R.id.tv_dispatch);
			viewHolder.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
			viewHolder.tvNum.setText(list.get(position).getControl_no());
			if (list.get(position).getId_allow()==1){
				viewHolder.tvDispatch.setText("不允许");
			}else{
				viewHolder.tvDispatch.setText("允许");
			}
			viewHolder.tvKPI.setText(list.get(position).getKpi());
			viewHolder.tvName.setText(list.get(position).getName());
			Glide.with(mContext).load(list.get(position).getHeadimg()).bitmapTransform(new GlideCircleTransform(mContext)).into(viewHolder.ivIcon);
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
		ImageView ivIcon;
	}

}