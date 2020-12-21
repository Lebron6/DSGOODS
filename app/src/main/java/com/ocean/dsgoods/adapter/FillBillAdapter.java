package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.entity.GoodList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FillBillAdapter extends RecyclerView.Adapter {


    private Context context;
    List<GoodList.ListBean> datas;

    public FillBillAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<GoodList.ListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fill_bill, parent, false);
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
        viewHolder.ivShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.layoutC.setVisibility(viewHolder.layoutC.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                viewHolder.layoutT.setVisibility(viewHolder.layoutT.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });
        viewHolder.tvAdmissions.setText(datas.get(position).getTakeNum());
        viewHolder.tvGoodsName.setText(datas.get(position).getName());
        viewHolder.tvGoodsNum.setText(datas.get(position).getNum());
        if (datas.get(position).getPackType().equals("1")) {
            viewHolder.packageName.setText("围板箱");
        } else {
            viewHolder.packageName.setText("周转箱");
        }


        viewHolder.tvTypeNum.setText(Integer.valueOf(datas.get(position).getDefault_pnum()) + "");
        viewHolder.etGoodsNum.setText(Integer.valueOf(datas.get(position).getDefault_num()) + "");
        viewHolder.tvVolume.setText(datas.get(position).getDefault_volume());
        viewHolder.tvWeight.setText(datas.get(position).getDefault_weight());
        datas.get(position).setSingleNum(datas.get(position).getDefault_num());
        datas.get(position).setSinglePnum(datas.get(position).getDefault_pnum());
        datas.get(position).setSingleVolume(datas.get(position).getDefault_pnum()* datas.get(position).getVolume());
        datas.get(position).setSingleWeigth(datas.get(position).getDefault_num()* datas.get(position).getWeight());
        if (datas.get(position).getType() == 1) {
            viewHolder.cbSelectBill.setChecked(true);
        } else {
            viewHolder.cbSelectBill.setChecked(false);
        }
        viewHolder.cbSelectBill.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    datas.get(position).setType(1);
                } else {
                    datas.get(position).setType(2);
                }
            }
        });
        viewHolder.etGoodsNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable.toString())) {
                    viewHolder.tvWeight.setText("0");
                    viewHolder.tvTypeNum.setText("0");
                    datas.get(position).setSingleNum(0);
                    datas.get(position).setSingleNum(0);
                    datas.get(position).setSingleWeigth(0.0);
                    datas.get(position).setSingleVolume(0.0);
                } else {
                   Log.e("除数",Math.ceil(Double.valueOf(editable.toString()) / Double.valueOf(datas.get(position).getTakeNum()))+"") ;
                    viewHolder.tvTypeNum.setText(Math.ceil(Double.valueOf(editable.toString()) / Double.valueOf(datas.get(position).getTakeNum()))+"");
                    datas.get(position).setSingleNum(Integer.valueOf(editable.toString()));
                    datas.get(position).setSingleWeigth(Double.valueOf(editable.toString()) * datas.get(position).getWeight());
                    viewHolder.tvWeight.setText(Double.valueOf(editable.toString()) * datas.get(position).getWeight() + "");
                }

            }
        });
        viewHolder.tvTypeNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable.toString())) {
                    viewHolder.tvVolume.setText("0");
                    datas.get(position).setSinglePnum(0);
                } else {
//                    if (Integer.valueOf(viewHolder.etGoodsNum.getText().toString()) == (Double.valueOf(viewHolder.tvTypeNum.getText().toString())).intValue() * (Double.valueOf(datas.get(position).getTakeNum()).intValue())) {
//                        viewHolder.etGoodsNum.setText(Integer.valueOf(viewHolder.tvTypeNum.getText().toString()) * (Integer.valueOf(datas.get(position).getTakeNum())) + "");
//                    }
                    datas.get(position).setSinglePnum(new Double(editable.toString()).intValue());
datas.get(position).setSingleVolume((Double.valueOf(editable.toString())*datas.get(position).getVolume()) );
                    viewHolder.tvVolume.setText((Double.valueOf(editable.toString()) * datas.get(position).getVolume()) + "");


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private OnItemClickLitener mOnItemClickLitener;

    @OnClick(R.id.iv_show)
    public void onViewClicked() {
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_select_bill)
        CheckBox cbSelectBill;
        @BindView(R.id.txt_goods_num)
        TextView txtGoodsNum;
        @BindView(R.id.tv_goods_num)
        TextView tvGoodsNum;
        @BindView(R.id.iv_show)
        ImageView ivShow;
        @BindView(R.id.tv_goods_name)
        TextView tvGoodsName;
        @BindView(R.id.package_name)
        TextView packageName;
        @BindView(R.id.tv_weight)
        TextView tvWeight;
        @BindView(R.id.layout_t)
        LinearLayout layoutT;
        @BindView(R.id.tv_admissions)
        TextView tvAdmissions;
        @BindView(R.id.tv_volume)
        TextView tvVolume;
        @BindView(R.id.layout_c)
        LinearLayout layoutC;
        @BindView(R.id.et_goods_num)
        EditText etGoodsNum;
        @BindView(R.id.tv_type_num)
        EditText tvTypeNum;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}