package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.AlreadyScanGoodsListActivity;
import com.ocean.dsgoods.activity.ManualBindChosePackageActivity;
import com.ocean.dsgoods.entity.AlreadyAddGoods;
import com.ocean.dsgoods.entity.ManualBindData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManualBindPackageAdapter extends RecyclerView.Adapter {


    private Context context;
    List<ManualBindData.ListBean> listBeans;
    private String wa_id;

    public ManualBindPackageAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<ManualBindData.ListBean> listBeans, String wa_id) {
        this.listBeans = listBeans;
        this.wa_id = wa_id;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_manual_bind_package, parent, false);
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
        viewHolder.tvHwmc.setText("货物名称：" + listBeans.get(position).getName());
        viewHolder.tvHwxh.setText(listBeans.get(position).getGood_type());
        viewHolder.tvHwjs.setText(listBeans.get(position).getPnum());
        viewHolder.tvHwsl.setText(listBeans.get(position).getNum());
        viewHolder.tvSmjs.setText(listBeans.get(position).getAlready_jnum());
        viewHolder.tvSmsl.setText(listBeans.get(position).getAlready_num());
        viewHolder.lXzbz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ManualBindChosePackageActivity.actionStart(context, listBeans.get(position).getG_id(), wa_id);
            }
        });

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


        @BindView(R.id.tv_hwmc)
        TextView tvHwmc;
        @BindView(R.id.tv_hwxh)
        TextView tvHwxh;
        @BindView(R.id.tv_hwjs)
        TextView tvHwjs;
        @BindView(R.id.tv_smjs)
        TextView tvSmjs;
        @BindView(R.id.tv_hwsl)
        TextView tvHwsl;
        @BindView(R.id.tv_smsl)
        TextView tvSmsl;
        @BindView(R.id.l_xzbz)
        LinearLayout lXzbz;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}