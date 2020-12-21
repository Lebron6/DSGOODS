package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.entity.Address;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectAddressAdapter extends RecyclerView.Adapter {



    private Context context;
    private List<Address.ListBean> listBeans;

    public SelectAddressAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<Address.ListBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_address, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
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
        viewHolder.tvAddr.setText(listBeans.get(position).getAddress());
        viewHolder.tvCompany.setText(listBeans.get(position).getName());
        viewHolder.tvPhone.setText(listBeans.get(position).getTel());
        viewHolder.tvName.setText(listBeans.get(position).getLiaison());

    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_addr)
        TextView tvAddr;
        @BindView(R.id.tv_company)
        TextView tvCompany;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}