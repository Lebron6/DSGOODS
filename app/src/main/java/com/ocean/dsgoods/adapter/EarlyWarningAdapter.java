package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocean.dsgoods.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EarlyWarningAdapter extends RecyclerView.Adapter {


    private Context context;

    public EarlyWarningAdapter(Context context) {
        this.context = context;
    }

//    public void setDatas(HangData.OrdersBean ordersBean) {
//        this.datas = ordersBean;
//        notifyDataSetChanged();
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_early_warning, parent, false);
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
        switch (position) {
            case 0:
                viewHolder.tvType.setText("设备报警");
                viewHolder.tvNum.setText("62");
                break;
            case 1:
                viewHolder.tvType.setText("运单超时");
                viewHolder.tvNum.setText("1");
                break;
            case 2:
                viewHolder.tvType.setText("包装到期");
                viewHolder.tvNum.setText("1");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_type)
        TextView tvType;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}