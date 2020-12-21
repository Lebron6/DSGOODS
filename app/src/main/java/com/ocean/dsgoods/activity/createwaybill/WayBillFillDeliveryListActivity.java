package com.ocean.dsgoods.activity.createwaybill;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.BaseActivity;
import com.ocean.dsgoods.activity.createbill.SetTransportationActivity;
import com.ocean.dsgoods.adapter.AddInitTwoAdapter;
import com.ocean.dsgoods.adapter.FillBillAdapter;
import com.ocean.dsgoods.adapter.WillBillOneAdapter;
import com.ocean.dsgoods.api.BaseUrl;
import com.ocean.dsgoods.api.HttpUtil;
import com.ocean.dsgoods.entity.AddInitOne;
import com.ocean.dsgoods.entity.AddInitTwo;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.tools.PreferenceUtils;
import com.ocean.dsgoods.tools.RecyclerViewHelper;
import com.ocean.dsgoods.tools.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/6/30.
 * 新建运单-填写提货清单
 */
public class WayBillFillDeliveryListActivity extends BaseActivity {

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
    @BindView(R.id.rv_fill_bill)
    RecyclerView rvFillBill;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;
    @BindView(R.id.btn_last)
    Button btnLast;
    @BindView(R.id.btn_next)
    Button btnNext;
    private AddInitTwoAdapter adapter;
    public static String T_ID="T_ID";
    public static String PLW_ID="PLW_ID";

    public static void actionStart(Context context,String t_id,String plw_id) {
        Intent intent = new Intent(context, WayBillFillDeliveryListActivity.class);
        intent.putExtra(T_ID,t_id);
        intent.putExtra(PLW_ID,plw_id);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_fill_delivery_list;
    }

    @Override
    protected void initViews() {
        adapter = new AddInitTwoAdapter(this);
    }

    @Override
    protected void initDatas() {
        getData();
    }
    private void getData() {
        HttpUtil.createRequest(TAG, BaseUrl.getInstence().addInitTwo()).addInitTwo(PreferenceUtils.getInstance().getUserToken(),getIntent().getStringExtra(T_ID),getIntent().getStringExtra(PLW_ID)).enqueue(new Callback<AddInitTwo>() {
            @Override
            public void onResponse(Call<AddInitTwo> call, Response<AddInitTwo> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 1) {
                        adapter.setDatas(response.body());
                    } else {
                        ToastUtil.showToast(response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<AddInitTwo> call, Throwable t) {
                ToastUtil.showToast("网络异常:获取列表数据失败");
            }
        });
    }


    @OnClick({R.id.btn_last, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_last:
                finish();
                break;
            case R.id.btn_next:
//                SetTransportationActivity.actionStart(this, new Gson().toJson(goods));
                break;
        }
    }
}
