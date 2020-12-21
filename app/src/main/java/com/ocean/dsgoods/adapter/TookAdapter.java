package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.OperationTrackActivity;
import com.ocean.dsgoods.activity.SignForActivity;
import com.ocean.dsgoods.entity.PickupData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TookAdapter extends RecyclerView.Adapter {


    private Context context;
    private int type;
    private List<PickupData.ListBean> listBeans;

    public TookAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    public void setDatas(List<PickupData.ListBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_took, parent, false);
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

        viewHolder.tvThdh.setText(listBeans.get(position).getWa_num());
        viewHolder.tvAddrS.setText(listBeans.get(position).getStart_city());
        viewHolder.tvAddrE.setText(listBeans.get(position).getEnd_city());
        viewHolder.tvSh.setText(listBeans.get(position).getShipper_name());
        viewHolder.tvCy.setText(listBeans.get(position).getTlogistics_name());
        viewHolder.tvHwjs.setText(listBeans.get(position).getGoods_jnum());
        viewHolder.tvHwsl.setText(listBeans.get(position).getGoodsNum());
        viewHolder.tvFhsj.setText(listBeans.get(position).getSend_time());
        viewHolder.tvYqddsj.setText(listBeans.get(position).getArrivalTime());
        viewHolder.tvStatus.setText(listBeans.get(position).getStatus_name());

        switch (listBeans.get(position).getStatus()) {
            //"1新建   2确认  3驳回 4分配 5交货出发 6交货完成 7干线签收 8干线出发 9干线途中 10干线到达  11派送出发 12签收 13回单确认 14回单上缴  15完成 16作废"
            case "1":

                break;
            case "2":

                break;
            case "3":

                break;
            case "4":

                break;
            case "5":
                viewHolder.layoutVis.setVisibility(View.VISIBLE);
                break;
            case "6":
                viewHolder.layoutVis.setVisibility(View.VISIBLE);
                break;
            case "7":
                viewHolder.layoutVis.setVisibility(View.VISIBLE);
                break;
            case "8":
                viewHolder.layoutVis.setVisibility(View.VISIBLE);
                break;
            case "9":
                viewHolder.layoutVis.setVisibility(View.VISIBLE);
                break;
            case "10":
                viewHolder.layoutVis.setVisibility(View.VISIBLE);
                break;
            case "11":
                viewHolder.layoutVis.setVisibility(View.VISIBLE);
                break;
            case "12":

                break;
            case "13":

                break;
            case "14":

                break;
            case "15":

                break;
            case "16":

                break;
        }
        viewHolder.signFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignForActivity.actionStart(context, listBeans.get(position).getWa_id());
            }
        });
        viewHolder.tvSeeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OperationTrackActivity.actionStart(context, "2", listBeans.get(position).getWa_id());
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

        @BindView(R.id.tv_thdh)
        TextView tvThdh;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.tv_sure)
        TextView tvSure;
        @BindView(R.id.tv_addr_s)
        TextView tvAddrS;
        @BindView(R.id.iv_grass)
        ImageView ivGrass;
        @BindView(R.id.tv_addr_e)
        TextView tvAddrE;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_sh)
        TextView tvSh;
        @BindView(R.id.tv_cy)
        TextView tvCy;
        @BindView(R.id.tv_hwjs)
        TextView tvHwjs;
        @BindView(R.id.tv_hwsl)
        TextView tvHwsl;
        @BindView(R.id.tv_fhsj)
        TextView tvFhsj;
        @BindView(R.id.tv_yqddsj)
        TextView tvYqddsj;
        @BindView(R.id.tv_Details)
        TextView tvDetails;
        @BindView(R.id.tv_see_map)
        TextView tvSeeMap;
        @BindView(R.id.sign_for)
        TextView signFor;
        @BindView(R.id.layout_vis)
        LinearLayout layoutVis;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}