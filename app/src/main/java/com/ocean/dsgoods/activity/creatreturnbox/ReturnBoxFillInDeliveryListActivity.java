package com.ocean.dsgoods.activity.creatreturnbox;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.BaseActivity;
import com.ocean.dsgoods.adapter.ReturnBoxFillBillAdapter;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by James on 2020/7/31.
 * 填写提货清单
 */
public class ReturnBoxFillInDeliveryListActivity extends BaseActivity {
    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.txt_one)
    TextView txtOne;
    @BindView(R.id.txt_two)
    TextView txtTwo;
    @BindView(R.id.txt_three)
    TextView txtThree;
    @BindView(R.id.rv_return_box)
    RecyclerView rvReturnBox;
    @BindView(R.id.btn_last)
    Button btnLast;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ReturnBoxFillInDeliveryListActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_return_box_fill_delivery_list;
    }

    @Override
    protected void initViews() {
        ReturnBoxFillBillAdapter adapter = new ReturnBoxFillBillAdapter(this);
        RecyclerViewHelper.initRecyclerViewV(this, rvReturnBox, false, adapter);
    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.btn_last, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_last:
                break;
            case R.id.btn_next:
                ReturnBoxSetTransportationActivity.actionStart(this);
                break;
        }
    }
}
