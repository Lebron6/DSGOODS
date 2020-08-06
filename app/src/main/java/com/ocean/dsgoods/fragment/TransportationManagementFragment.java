package com.ocean.dsgoods.fragment;

import android.view.View;
import android.widget.LinearLayout;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.activity.BillOfLodingActivity;
import com.ocean.dsgoods.activity.ContractManagementAvtivity;
import com.ocean.dsgoods.activity.GoodsManagementActivity;
import com.ocean.dsgoods.activity.NewReturnPlanActivity;
import com.ocean.dsgoods.activity.ReturnBoxAvtivity;
import com.ocean.dsgoods.activity.createbill.FillBasicInformationActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by James on 2020/6/29.
 * 运输管理
 */
public class TransportationManagementFragment extends BaseFragment {


    @BindView(R.id.layout_New_delivery_plan)
    LinearLayout layoutNewDeliveryPlan;
    @BindView(R.id.layout_New_return_plan)
    LinearLayout layoutNewReturnPlan;
    @BindView(R.id.layout_New_waybill)
    LinearLayout layoutNewWaybill;
    @BindView(R.id.layout_pol)
    LinearLayout layoutPol;
    @BindView(R.id.layout_I_shipped_it)
    LinearLayout layoutIShippedIt;
    @BindView(R.id.layout_I_took_it)
    LinearLayout layoutITookIt;
    @BindView(R.id.layout_Bill_of_lading_management)
    LinearLayout layoutBillOfLadingManagement;
    @BindView(R.id.layout_Freight_inquiry)
    LinearLayout layoutFreightInquiry;
    @BindView(R.id.layout_Invoice_Inquiry)
    LinearLayout layoutInvoiceInquiry;
    @BindView(R.id.layout_Freight_settlement)
    LinearLayout layoutFreightSettlement;
    @BindView(R.id.layout_contract_management)
    LinearLayout layoutContractManagement;
    @BindView(R.id.layout_Goods_management)
    LinearLayout layoutGoodsManagement;
    @BindView(R.id.layout_Code_scanning_packing)
    LinearLayout layoutCodeScanningPacking;
    @BindView(R.id.layout_return_box)
    LinearLayout layoutReturnBox;
    Unbinder unbinder;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_transportation_management;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.layout_New_delivery_plan, R.id.layout_New_return_plan, R.id.layout_New_waybill, R.id.layout_pol, R.id.layout_I_shipped_it, R.id.layout_I_took_it, R.id.layout_Bill_of_lading_management, R.id.layout_Freight_inquiry, R.id.layout_Invoice_Inquiry, R.id.layout_Freight_settlement, R.id.layout_contract_management, R.id.layout_Goods_management, R.id.layout_Code_scanning_packing, R.id.layout_return_box})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_New_delivery_plan:

                FillBasicInformationActivity.actionStart(getActivity());
                break;
            case R.id.layout_New_return_plan:
                NewReturnPlanActivity.actionStart(getActivity());
                break;
            case R.id.layout_New_waybill:
                break;
            case R.id.layout_pol:
                break;
            case R.id.layout_I_shipped_it:

                break;
            case R.id.layout_I_took_it:
                break;
            case R.id.layout_Bill_of_lading_management:
                BillOfLodingActivity.actionStart(getActivity());
                break;
            case R.id.layout_Freight_inquiry:
                break;
            case R.id.layout_Invoice_Inquiry:
                break;
            case R.id.layout_Freight_settlement:
                break;
            case R.id.layout_contract_management:
                ContractManagementAvtivity.actionStart(getActivity());
                break;
            case R.id.layout_Goods_management:
                GoodsManagementActivity.actionStart(getActivity());
                break;
            case R.id.layout_Code_scanning_packing:
                break;
            case R.id.layout_return_box:
                ReturnBoxAvtivity.actionStart(getActivity());
                break;
        }
    }
}
