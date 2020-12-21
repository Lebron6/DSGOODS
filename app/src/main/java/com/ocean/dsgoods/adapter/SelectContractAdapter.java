package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.WebViewActivity;
import com.ocean.dsgoods.entity.ContractData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectContractAdapter extends RecyclerView.Adapter {



    private Context context;
    List<ContractData.ListBean> ordersBean;

    public SelectContractAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ContractData.ListBean> ordersBean) {
        this.ordersBean = ordersBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_contract, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        viewHolder.tvLsh.setText(ordersBean.get(position).getConstract_sn());
        viewHolder.tvCyf.setText(ordersBean.get(position).getName());
        viewHolder.tvYxq.setText(ordersBean.get(position).getStartTime() + "-" + ordersBean.get(position).getEndTime());
        viewHolder.ivPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.actionStart(context,"合同详情",ordersBean.get(position).getFilePath());
            }
        });

    }

    @Override
    public int getItemCount() {
        return ordersBean.size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_lsh)
        TextView tvLsh;
        @BindView(R.id.iv_pdf)
        ImageView ivPdf;
        @BindView(R.id.tv_cyf)
        TextView tvCyf;
        @BindView(R.id.tv_yxq)
        TextView tvYxq;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}