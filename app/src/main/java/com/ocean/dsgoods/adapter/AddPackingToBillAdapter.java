package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.AlreadyScanGoodsListActivity;
import com.ocean.dsgoods.callback.OnCancelBindImp;
import com.ocean.dsgoods.callback.OnManualClickImp;
import com.ocean.dsgoods.callback.OnScanClickImp;
import com.ocean.dsgoods.entity.BindWillList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPackingToBillAdapter extends RecyclerView.Adapter {

OnScanClickImp scanClickImp;
    OnCancelBindImp onCancelBindImp;
OnManualClickImp manualClickImp;
    private Context context;
    List<BindWillList.ListBean> listBeans;

    public AddPackingToBillAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<BindWillList.ListBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    public void setScanClick(OnScanClickImp scanClickImp){
        this.scanClickImp=scanClickImp;
    }
    public void setManualClick(OnManualClickImp manualClickImp){
        this.manualClickImp=manualClickImp;
    }

    public void setCancelBindImp(OnCancelBindImp onCancelBindImp){
        this.onCancelBindImp=onCancelBindImp;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_packing_to_bill, parent, false);
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
        viewHolder.tvYdh.setText(listBeans.get(position).getWa_num());
        viewHolder.tvAddrS.setText(listBeans.get(position).getStartCity());
        viewHolder.tvAddrE.setText(listBeans.get(position).getEndCity());
        viewHolder.tvHwjs.setText(listBeans.get(position).getPnum());
        viewHolder.tvHwsl.setText(listBeans.get(position).getNum());
        viewHolder.tvShdw.setText(listBeans.get(position).getShippers_name());
        viewHolder.tvCydw.setText(listBeans.get(position).getT_name());
        viewHolder.tvYqddsj.setText(listBeans.get(position).getArrivalTime());
        viewHolder.tvStatus.setText(listBeans.get(position).getStatus_name());

        viewHolder.tvSmbd.setOnClickListener(new View.OnClickListener() {//扫码绑定
            @Override
            public void onClick(View view) {
                scanClickImp.scan(position);
            }
        });

        viewHolder.tvSgbd.setOnClickListener(new View.OnClickListener() {//手工绑定
            @Override
            public void onClick(View view) {
                manualClickImp.manua(position);
            }
        });

        viewHolder.tvJcbd.setOnClickListener(new View.OnClickListener() {//解除绑定
            @Override
            public void onClick(View view) {
                onCancelBindImp.cancelBind(position);
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

        @BindView(R.id.tv_ydh)
        TextView tvYdh;
        @BindView(R.id.tv_addr_s)
        TextView tvAddrS;
        @BindView(R.id.iv_grass)
        ImageView ivGrass;
        @BindView(R.id.tv_addr_e)
        TextView tvAddrE;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_shdw)
        TextView tvShdw;
        @BindView(R.id.tv_cydw)
        TextView tvCydw;
        @BindView(R.id.tv_hwjs)
        TextView tvHwjs;
        @BindView(R.id.tv_jiaohuodz)
        TextView tvJiaohuodz;
        @BindView(R.id.tv_hwsl)
        TextView tvHwsl;
        @BindView(R.id.tv_yqddsj)
        TextView tvYqddsj;
        @BindView(R.id.tv_smbd)
        TextView tvSmbd;
        @BindView(R.id.tv_sgbd)
        TextView tvSgbd;
        @BindView(R.id.tv_jcbd)
        TextView tvJcbd;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}