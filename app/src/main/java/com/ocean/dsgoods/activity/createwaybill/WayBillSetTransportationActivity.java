package com.ocean.dsgoods.activity.createwaybill;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.BaseActivity;
import com.ocean.dsgoods.activity.CommitBillSuccessActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by James on 2020/6/30.
 * 新建运单-设置运输要求
 */
public class WayBillSetTransportationActivity extends BaseActivity {


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
    @BindView(R.id.rb_delivery)
    RadioButton rbDelivery;
    @BindView(R.id.rb_take_your_own)
    RadioButton rbTakeYourOwn;
    @BindView(R.id.rg_service_type)
    RadioGroup rgServiceType;
    @BindView(R.id.rb_simple)
    RadioButton rbSimple;
    @BindView(R.id.rb_harry)
    RadioButton rbHarry;
    @BindView(R.id.rb_jit)
    RadioButton rbJit;
    @BindView(R.id.rg_type)
    RadioGroup rgType;
    @BindView(R.id.layout_transport_prescription)
    LinearLayout layoutTransportPrescription;
    @BindView(R.id.laayout_car_requirement)
    LinearLayout laayoutCarRequirement;
    @BindView(R.id.txt_weigth)
    TextView txtWeigth;
    @BindView(R.id.et_weigth)
    EditText etWeigth;
    @BindView(R.id.txt_volume)
    TextView txtVolume;
    @BindView(R.id.et_volume)
    EditText etVolume;
    @BindView(R.id.txt_insurance_value)
    TextView txtInsuranceValue;
    @BindView(R.id.et_insurance_value)
    EditText etInsuranceValue;
    @BindView(R.id.layout_title)
    LinearLayout layoutTitle;
    @BindView(R.id.et_fils)
    EditText etFils;
    @BindView(R.id.layout_remarks)
    LinearLayout layoutRemarks;
    @BindView(R.id.et_remarks)
    EditText etRemarks;
    @BindView(R.id.btn_last)
    Button btnLast;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.layout_button)
    LinearLayout layoutButton;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, WayBillSetTransportationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_set_transportation;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.layout_transport_prescription, R.id.laayout_car_requirement, R.id.btn_last, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_transport_prescription:
                break;
            case R.id.laayout_car_requirement:
                break;
            case R.id.btn_last:
                break;
            case R.id.btn_commit:
                CommitBillSuccessActivity.actionStart(this);
                break;
        }
    }
}
