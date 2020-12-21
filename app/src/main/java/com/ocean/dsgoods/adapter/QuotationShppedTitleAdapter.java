package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.entity.QuotationData;
import com.ocean.dsgoods.entity.ShppedQuotationData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuotationShppedTitleAdapter extends RecyclerView.Adapter {



    private Context context;
    private ShppedQuotationData datas;

    public QuotationShppedTitleAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(ShppedQuotationData datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quotation_title, parent, false);
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
        viewHolder.tvTitle.setText(datas.getList().getHeader().get(position));
    }

    @Override
    public int getItemCount() {
        if (datas != null && datas.getList().getHeader() != null) {
            return datas.getList().getHeader().size();
        } else {
            return 0;
        }

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
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}