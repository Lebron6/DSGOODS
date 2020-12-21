package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.entity.Trail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackAdapter extends RecyclerView.Adapter {



    private Context context;
    private List<Trail.DataBean> datas;

    public TrackAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<Trail.DataBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_track, parent, false);
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

        viewHolder.tvTitle.setText("【" + datas.get(position).getCity() + "】" + datas.get(position).getTitle());
        viewHolder.tvTime.setText(datas.get(position).getTime());
        viewHolder.tvContent.setText(datas.get(position).getRemarks());

        if (datas.get(position).getSignuser()==null|| TextUtils.isEmpty(datas.get(position).getSignuser())){
            viewHolder.tvQsr.setVisibility(View.GONE);
        }else{
            viewHolder.tvQsr.setText("签收人:"+datas.get(position).getSignuser());
        }
        if (datas.get(position).getImg()==null|| TextUtils.isEmpty(datas.get(position).getImg())){
            viewHolder.tvCkfj.setVisibility(View.GONE);
        }else{
            viewHolder.tvCkfj.setVisibility(View.VISIBLE);
        }
        if (datas.get(position).getRemarks()==null|| TextUtils.isEmpty(datas.get(position).getRemarks())){
            viewHolder.tvContent.setVisibility(View.GONE);
        }else{
            viewHolder.tvContent.setVisibility(View.VISIBLE);
        }
        viewHolder.tvCkfj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_cir)
        ImageView ivCir;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.layout_top)
        RelativeLayout layoutTop;
        @BindView(R.id.view_line)
        View viewLine;
        @BindView(R.id.tv_qsr)
        TextView tvQsr;
        @BindView(R.id.tv_ckfj)
        TextView tvCkfj;
        @BindView(R.id.tv_content)
        TextView tvContent;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}