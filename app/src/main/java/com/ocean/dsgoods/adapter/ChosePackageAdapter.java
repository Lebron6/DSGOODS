package com.ocean.dsgoods.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChosePackageAdapter extends RecyclerView.Adapter {


    private Activity context;
    List<ScanGoodInfo.ListBean> datas;
    private String g_id;
    private String wa_id;

    public ChosePackageAdapter(Activity context) {
        this.context = context;
    }

    public void setDatas(List<ScanGoodInfo.ListBean> datas, String g_id, String wa_id) {
        this.datas = datas;
        this.g_id = g_id;
        this.wa_id = wa_id;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chose_package, parent, false);
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
        datas.get(position).setWa_id(wa_id);
        datas.get(position).setG_id(g_id);
        viewHolder.tvBzbh.setText(datas.get(position).getNum_sn());
        viewHolder.tvHwsl.setText(datas.get(position).getNum());
        if (datas.get(position).getIs_bind().equals("1")){
            viewHolder.cbSelect.setChecked(true);
        }else{
            viewHolder.cbSelect.setChecked(false);
        }
        viewHolder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true){
                    datas.get(position).setIs_bind("1");
                }else{
                    datas.get(position).setIs_bind("2");
                }
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


        @BindView(R.id.tv_bzbh)
        TextView tvBzbh;
        @BindView(R.id.tv_hwsl)
        TextView tvHwsl;
        @BindView(R.id.cb_select)
        CheckBox cbSelect;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}