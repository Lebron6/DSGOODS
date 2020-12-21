package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.createwaybill.WayBillFillBasicInformationActivity;
import com.ocean.dsgoods.callback.OnWarehouseSelectImp;
import com.ocean.dsgoods.entity.AddInitOne;
import com.ocean.dsgoods.tools.RecyclerViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WillBillOneAdapter extends RecyclerView.Adapter {


    private Context context;
    List<AddInitOne.ListBean> datas;

    public WillBillOneAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<AddInitOne.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_way_bill_one, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        viewHolder.tvCompanyName.setText(datas.get(position).getName());
        viewHolder.tvSqlName.setText(datas.get(position).getPl_warehouse().get(0).getName());
        viewHolder.tvBillNum.setText(datas.get(position).getPl_warehouse().get(0).getDp_num());
        viewHolder.tvCompanyName.setText(datas.get(position).getName());
        viewHolder.layoutChoseSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.rvList.setVisibility(viewHolder.rvList.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });
        WarehouseAdapter adapter=new WarehouseAdapter(context, new OnWarehouseSelectImp() {
            @Override
            public void select(int childPosition) {
                viewHolder.rvList.setVisibility(View.GONE);
                viewHolder.tvSqlName.setText(datas.get(position).getPl_warehouse().get(childPosition).getName());
                viewHolder.tvBillNum.setText(datas.get(position).getPl_warehouse().get(childPosition).getDp_num());
            }
        });
        adapter.setDatas(datas.get(position).getPl_warehouse());
        RecyclerViewHelper.initRecyclerViewV(context, viewHolder.rvList, false, adapter);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private OnItemClickLitener mOnItemClickLitener;


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_company_name)
        TextView tvCompanyName;
        @BindView(R.id.tv_sql_name)
        TextView tvSqlName;
        @BindView(R.id.layout_chose_sql)
        RelativeLayout layoutChoseSql;
        @BindView(R.id.rv_list)
        RecyclerView rvList;
        @BindView(R.id.txt_bill_num)
        TextView txtBillNum;
        @BindView(R.id.tv_bill_num)
        TextView tvBillNum;
        @BindView(R.id.btn_sure)
        Button btnSure;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}