package com.ocean.dsgoods.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.NavPagerAdapter;
import com.ocean.dsgoods.view.NavitationScrollLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Created by James on 2020/6/30.
 */
public class BillTypeFragment extends BaseFragment {
    @BindView(R.id.tab_bill)
    NavitationScrollLayout tabBill;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    private int type;

    public BillTypeFragment() {
    }
    @SuppressLint("ValidFragment")
    public BillTypeFragment(int type) {
        this.type=type;
    }
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_bill_type;
    }

    @Override
    protected void initViews() {
        List<Fragment> fragments=new ArrayList<>();
        String[] titles = new String[8];
        titles[0]="全部";
        titles[1]="新建";
        titles[2]="确认";
        titles[3]="调度";
        titles[4]="出发";
        titles[5]="到达";
        titles[6]="装车";
        titles[7]="完成";
        for (int i = 0; i <titles.length ; i++) {
            fragments.add(new BillListFragment(i));

        }
        NavPagerAdapter viewPagerAdapter = new NavPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.setData(fragments);
        vpContent.setAdapter(viewPagerAdapter);
        tabBill.setViewPager(getActivity(), titles, vpContent, R.color.colorGray, R.color.colorMain, 14, 14, 17, true, R.color.colorMain, 0f, 15f, 15f, 46);
//        navitationScrollLayout.setBgLine(this, 1, R.color.themecolor);
        tabBill.setNavLine(getActivity(), 2, R.color.colorMain);
    }

    @Override
    protected void initDatas() {

    }

}
