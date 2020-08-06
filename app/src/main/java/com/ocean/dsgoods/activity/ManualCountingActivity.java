package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.SelectTurnoverBoxAdapter;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by James on 2020/7/31.
 * 手动点货
 */
public class ManualCountingActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.txt_select_goods)
    TextView txtSelectGoods;
    @BindView(R.id.iv_down)
    ImageView ivDown;
    @BindView(R.id.layout_select_goods)
    RelativeLayout layoutSelectGoods;
    @BindView(R.id.layout_chose_t)
    LinearLayout layoutChoseT;
    @BindView(R.id.et_goods_num)
    EditText etGoodsNum;
    @BindView(R.id.layout_chose_j)
    LinearLayout layoutChoseJ;
    @BindView(R.id.et_goods_js)
    EditText etGoodsJs;
    @BindView(R.id.layout_t)
    LinearLayout layoutT;
    @BindView(R.id.rb_f)
    RadioButton rbF;
    @BindView(R.id.rb_s)
    RadioButton rbS;
    @BindView(R.id.rg_type)
    RadioGroup rgType;
    @BindView(R.id.layout_top)
    RelativeLayout layoutTop;
    @BindView(R.id.layout_center)
    LinearLayout layoutCenter;
    @BindView(R.id.rv_num)
    RecyclerView rvNum;
    @BindView(R.id.btn_sure)
    Button btnSure;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ManualCountingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_manual_counting;
    }

    @Override
    protected void initViews() {
        SelectTurnoverBoxAdapter adapter = new SelectTurnoverBoxAdapter(this);
        RecyclerViewHelper.initRecyclerViewV(this, rvNum, false, adapter);
        adapter.setOnItemClickLitener(new SelectTurnoverBoxAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
//                ContractDetailsActivity.actionStart(getActivity());
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.layout_select_goods, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_select_goods:
                SelectGoodsActivity.actionStart(this);
                break;
            case R.id.btn_sure:
                break;
        }
    }
}
