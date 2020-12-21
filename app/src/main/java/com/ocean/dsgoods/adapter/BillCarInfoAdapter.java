package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.LoadingListActivity;
import com.ocean.dsgoods.entity.OperateTrackData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillCarInfoAdapter extends RecyclerView.Adapter {


    private Context context;
    OperateTrackData operateTrackData;

    public BillCarInfoAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(OperateTrackData operateTrackData) {
        this.operateTrackData = operateTrackData;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bill_car_info, parent, false);
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
        viewHolder.tvSj.setText(operateTrackData.getData().get(position).getName());
        viewHolder.tvCph.setText(operateTrackData.getData().get(position).getNum());
        viewHolder.tvQrTime.setText(operateTrackData.getData().get(position).getAcceptTime());
        viewHolder.tvCfTime.setText(operateTrackData.getData().get(position).getSetoutTime());
        viewHolder.tvDdTime.setText(operateTrackData.getData().get(position).getArriveTime());
        viewHolder.tvZcTime.setText(operateTrackData.getData().get(position).getLoadingTime());
        viewHolder.tvWcTime.setText(operateTrackData.getData().get(position).getFinishTime());

        viewHolder.tvQr.setTextColor(context.getResources().getColor(R.color.colorGray));
        viewHolder.tvCf.setTextColor(context.getResources().getColor(R.color.colorGray));
        viewHolder.tvDd.setTextColor(context.getResources().getColor(R.color.colorGray));
        viewHolder.tvZc.setTextColor(context.getResources().getColor(R.color.colorGray));
        viewHolder.tvWc.setTextColor(context.getResources().getColor(R.color.colorGray));

        viewHolder.tvQrTime.setTextColor(context.getResources().getColor(R.color.colorGray));
        viewHolder.tvCfTime.setTextColor(context.getResources().getColor(R.color.colorGray));
        viewHolder.tvDdTime.setTextColor(context.getResources().getColor(R.color.colorGray));
        viewHolder.tvZcTime.setTextColor(context.getResources().getColor(R.color.colorGray));
        viewHolder.tvWcTime.setTextColor(context.getResources().getColor(R.color.colorGray));

        viewHolder.ivOne.setBackgroundResource(R.drawable.bg_circle_gray_shollw);
        viewHolder.ivTwo.setBackgroundResource(R.drawable.bg_circle_gray_shollw);
        viewHolder.ivThree.setBackgroundResource(R.drawable.bg_circle_gray_shollw);
        viewHolder.ivFour.setBackgroundResource(R.drawable.bg_circle_gray_shollw);
        viewHolder.ivFive.setBackgroundResource(R.drawable.bg_circle_gray_shollw);
        switch (operateTrackData.getData().get(position).getStatus()) {
            case "1":
                viewHolder.tvQr.setTextColor(context.getResources().getColor(R.color.colorMain));
                viewHolder.tvQrTime.setTextColor(context.getResources().getColor(R.color.colorMain));
                viewHolder.ivOne.setBackgroundResource(R.drawable.bg_circle_main);
                break;
            case "4":
                viewHolder.tvCf.setTextColor(context.getResources().getColor(R.color.colorMain));
                viewHolder.tvCfTime.setTextColor(context.getResources().getColor(R.color.colorMain));
                viewHolder.ivTwo.setBackgroundResource(R.drawable.bg_circle_main);
                break;
            case "5":
                viewHolder.tvDd.setTextColor(context.getResources().getColor(R.color.colorMain));
                viewHolder.tvDdTime.setTextColor(context.getResources().getColor(R.color.colorMain));
                viewHolder.ivThree.setBackgroundResource(R.drawable.bg_circle_main);
                break;
            case "6":
                viewHolder.tvZc.setTextColor(context.getResources().getColor(R.color.colorMain));
                viewHolder.tvZcTime.setTextColor(context.getResources().getColor(R.color.colorMain));
                viewHolder.ivFour.setBackgroundResource(R.drawable.bg_circle_main);
                break;
            case "9":
                viewHolder.tvWc.setTextColor(context.getResources().getColor(R.color.colorMain));
                viewHolder.tvWcTime.setTextColor(context.getResources().getColor(R.color.colorMain));
                viewHolder.ivFive.setBackgroundResource(R.drawable.bg_circle_main);
                break;
        }
        viewHolder.ivZcqd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingListActivity.actionStart(context,operateTrackData.getData().get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return operateTrackData.getData().size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_sj)
        TextView tvSj;
        @BindView(R.id.tv_cph)
        TextView tvCph;
        @BindView(R.id.iv_zcqd)
        ImageView ivZcqd;
        @BindView(R.id.tv_qr)
        TextView tvQr;
        @BindView(R.id.tv_cf)
        TextView tvCf;
        @BindView(R.id.tv_dd)
        TextView tvDd;
        @BindView(R.id.tv_zc)
        TextView tvZc;
        @BindView(R.id.tv_wc)
        TextView tvWc;
        @BindView(R.id.iv_one)
        ImageView ivOne;
        @BindView(R.id.iv_two)
        ImageView ivTwo;
        @BindView(R.id.iv_three)
        ImageView ivThree;
        @BindView(R.id.iv_four)
        ImageView ivFour;
        @BindView(R.id.iv_five)
        ImageView ivFive;
        @BindView(R.id.tv_qr_time)
        TextView tvQrTime;
        @BindView(R.id.tv_cf_time)
        TextView tvCfTime;
        @BindView(R.id.tv_dd_time)
        TextView tvDdTime;
        @BindView(R.id.tv_zc_time)
        TextView tvZcTime;
        @BindView(R.id.tv_wc_time)
        TextView tvWcTime;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}