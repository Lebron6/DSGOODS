package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.creatreturnbox.ReturnBoxFillBasicInformationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreatReturnBoxAdapter extends RecyclerView.Adapter {



    private Context context;

    public CreatReturnBoxAdapter(Context context) {
        this.context = context;
    }

//    public void setDatas(HangData.OrdersBean ordersBean) {
//        this.datas = ordersBean;
//        notifyDataSetChanged();
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_return_plan, parent, false);
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

viewHolder.tvCreatReturnBox.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ReturnBoxFillBasicInformationActivity.actionStart(context);
    }
});
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_logistics_company)
        TextView tvLogisticsCompany;
        @BindView(R.id.tv_take_company)
        TextView tvTakeCompany;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.txt_num)
        TextView txtNum;
        @BindView(R.id.tv_box_num)
        TextView tvBoxNum;
        @BindView(R.id.tv_creat_return_box)
        TextView tvCreatReturnBox;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}