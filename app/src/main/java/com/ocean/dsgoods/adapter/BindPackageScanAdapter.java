package com.ocean.dsgoods.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.ScanGoodInfo;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BindPackageScanAdapter extends RecyclerView.Adapter {


    private Activity context;
    List<ScanGoodInfo.ListBean> datas;
    private String g_id;
    private String wa_id;

    public BindPackageScanAdapter(Activity context) {
        this.context = context;
    }

    public void setDatas(List<ScanGoodInfo.ListBean> datas,String g_id,String wa_id) {
        this.datas = datas;
        this.g_id = g_id;
        this.wa_id = wa_id;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bind_package_scan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        viewHolder.tvBzbh.setText(datas.get(position).getNum_sn());
        viewHolder.tvHwsl.setText(datas.get(position).getNum());
        if (datas.get(position).getIs_bind().equals("1")) {   //1已经绑定 2已经解绑
            viewHolder.tvSett.setText("解除绑定");
            viewHolder.tvSett.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HttpUtil.createRequest(BaseUrl.getInstence().unbindGood()).unbindGood(PreferenceUtils.getInstance().getUserToken(), datas.get(position).getNum_sn(),g_id,wa_id).enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.body().getCode() == 1) {
                                ToastUtil.showToast("解绑成功");
                                datas.get(position).setIs_bind("2");
                                notifyDataSetChanged();
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
        } else {
            viewHolder.tvSett.setText("未绑定");
        }
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

        @BindView(R.id.tv_bzbh)
        TextView tvBzbh;
        @BindView(R.id.tv_hwsl)
        TextView tvHwsl;
        @BindView(R.id.tv_sett)
        TextView tvSett;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}