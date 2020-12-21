package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.WayList;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippedAdapter extends RecyclerView.Adapter {



    private Context context;
    private int type;
    private List<WayList.ListBean> listBeans;

    public ShippedAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    public void setDatas(List<WayList.ListBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_y_shipped, parent, false);
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
        viewHolder.tvHwjs.setText(listBeans.get(position).getGoods_jnum() + "");
        viewHolder.tvHwsl.setText(listBeans.get(position).getGoodsNum() + "");
        viewHolder.tvFhsj.setText(listBeans.get(position).getSend_time());
        viewHolder.tvYqddsj.setText(listBeans.get(position).getArrivalTime());
        viewHolder.tvSl.setVisibility(View.GONE);
        switch (listBeans.get(position).getStatus()) {
            case 1://受理
                viewHolder.tvStatus.setText("受理");
                viewHolder.tvSl.setVisibility(View.VISIBLE);
                viewHolder.ivGrass.setBackgroundResource(R.mipmap.sop_one);
                break;
            case 2://分配
                viewHolder.tvStatus.setText("分配");
                viewHolder.ivGrass.setBackgroundResource(R.mipmap.sop_two);
                break;
            case 3://驳回
                viewHolder.tvStatus.setText("驳回");
                viewHolder.ivGrass.setBackgroundResource(R.mipmap.icon_six);
                break;
            case 4://途中
                viewHolder.tvStatus.setText("途中");
                viewHolder.ivGrass.setBackgroundResource(R.mipmap.sop_three);
                break;
            case 12://回单确认
                viewHolder.tvStatus.setText("回单确认");
                viewHolder.ivGrass.setBackgroundResource(R.mipmap.sop_four);
                break;
            case 14://完成
                viewHolder.tvStatus.setText("完成");
                break;
            case 15://完成
                viewHolder.tvStatus.setText("完成");
                break;
            case 16://作废
                viewHolder.tvStatus.setText("作废");
                break;
        }
        viewHolder.tvSl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtil.createRequest(BaseUrl.getInstence().shipperConfirm()).shipperConfirm(PreferenceUtils.getInstance().getUserToken(), listBeans.get(position).getWa_id()).enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.body().getCode() == 1) {
                            listBeans.get(position).setStatus(2);
                            listBeans.get(position).setStatus_name("分配");
                            setDatas(listBeans);
                        } else {
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        ToastUtil.showToast("网络异常:操作失败");
                    }
                });

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
        @BindView(R.id.txt_num)
        TextView txtNum;
        @BindView(R.id.tv_thdh)
        TextView tvThdh;
        @BindView(R.id.tv_sl)
        TextView tvSl;
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

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}