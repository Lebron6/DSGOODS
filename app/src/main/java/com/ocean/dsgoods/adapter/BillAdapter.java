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
import com.ocean.dsgoods.entity.BillList;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillAdapter extends RecyclerView.Adapter {



    private Context context;
    private List<BillList.ListBean> listBeans;

    public BillAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<BillList.ListBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bill, parent, false);
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
        viewHolder.tvSure.setVisibility(View.GONE);
        viewHolder.tvDelete.setVisibility(View.GONE);
        viewHolder.tvAddrS.setText(listBeans.get(position).getStart_city());
        viewHolder.tvAddrE.setText(listBeans.get(position).getEnd_city());
        viewHolder.tvThdh.setText(listBeans.get(position).getSerial_num());
        viewHolder.tvCyf.setText(listBeans.get(position).getName());
        viewHolder.tvThsj.setText(listBeans.get(position).getStart_time() + "-" + listBeans.get(position).getEnd_time());
        viewHolder.tvStatus.setText(listBeans.get(position).getStatus_name());
        switch (listBeans.get(position).getStatus()) {
            case "1"://新建
                if (listBeans.get(position).getDp_type().equals("1")) {//3PL发布
                    viewHolder.tvSure.setVisibility(View.VISIBLE);
                    viewHolder.tvDelete.setVisibility(View.GONE);

                } else {//收发货端发布
                    viewHolder.tvSure.setVisibility(View.GONE);
                    viewHolder.tvDelete.setVisibility(View.VISIBLE);
                }

                viewHolder.ivGrass.setBackgroundResource(R.mipmap.sop_one);
                break;
            case "2"://确认
                viewHolder.ivGrass.setBackgroundResource(R.mipmap.sop_two);
                break;
            case "3"://调度
                viewHolder.ivGrass.setBackgroundResource(R.mipmap.sop_three);
                break;
            case "4"://出发
                viewHolder.ivGrass.setBackgroundResource(R.mipmap.sop_four);
                break;
            case "5"://到达
                viewHolder.ivGrass.setBackgroundResource(R.mipmap.sop_five);
                break;
            case "6"://装车
                viewHolder.ivGrass.setBackgroundResource(R.mipmap.sop_six);
                break;
            case "7"://驳回
                viewHolder.ivGrass.setBackgroundResource(R.mipmap.sop_nine);
                break;
            case "9"://完成
                viewHolder.ivGrass.setBackgroundResource(R.mipmap.sop_seven);
                break;
            case "10"://作废
                viewHolder.ivGrass.setBackgroundResource(R.mipmap.sop_eight);
                break;
        }
        viewHolder.tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtil.createRequest(BaseUrl.getInstence().pass_order()).orderSure(PreferenceUtils.getInstance().getUserToken(),listBeans.get(position).getDp_id()).enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.body().getCode()==1){
                           listBeans.get(position).setStatus("2");
                           listBeans.get(position).setStatus_name("确认");
                           setDatas(listBeans);
                        }else{
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        ToastUtil.showToast("网络异常:提单操作失败");
                    }
                });
            }
        });
        viewHolder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtil.createRequest(BaseUrl.getInstence().del_order()).orderDelete(PreferenceUtils.getInstance().getUserToken(),listBeans.get(position).getDp_id()).enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.body().getCode()==1){
                            listBeans.remove(position);
                            setDatas(listBeans);
                        }else{
                            ToastUtil.showToast(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        ToastUtil.showToast("网络异常:删除提单失败");
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
        @BindView(R.id.tv_cyf)
        TextView tvCyf;
        @BindView(R.id.tv_thsj)
        TextView tvThsj;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}