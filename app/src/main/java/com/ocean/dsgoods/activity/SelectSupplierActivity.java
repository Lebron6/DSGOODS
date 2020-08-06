package com.ocean.dsgoods.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ocean.dsgoods.R;
import com.ocean.dsgoods.adapter.SortAdapter;
import com.ocean.dsgoods.entity.SortModelInfo;
import com.ocean.dsgoods.tools.CharacterParser;
import com.ocean.dsgoods.tools.PinyinComparator;
import com.ocean.dsgoods.view.ClearEditText;
import com.ocean.dsgoods.view.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2020/7/29.
 * 选择供应商
 */
public class SelectSupplierActivity extends BaseActivity {

    @BindView(R.id.view_status_bar)
    TextView viewStatusBar;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.filter_edit)
    ClearEditText filterEdit;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.lv_supplier)
    ListView lvSupplier;
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.sidebar)
    SideBar sidebar;
    private CharacterParser characterParser;
    private List<SortModelInfo> SourceDateList;

    private PinyinComparator pinyinComparator;
    private SortAdapter adapter;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SelectSupplierActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_select_supplier;
    }

    @Override
    protected void initViews() {
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();
        SourceDateList = filledData();
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
        lvSupplier.setAdapter(adapter);
        sidebar.setTextView(dialog);
        filterEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {

                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    lvSupplier.setSelection(position);
                }

            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.tv_search)
    public void onViewClicked() {
    }

    private List<SortModelInfo> filledData() {
        List<SortModelInfo> mSortList = new ArrayList<SortModelInfo>();

        for (int i = 0; i < 20; i++) {
            SortModelInfo sortModelInfo = new SortModelInfo();
            if (i<10){
                sortModelInfo.setName("胡");
            }else{
                sortModelInfo.setName("谷");
            }

            sortModelInfo.setId(i);
            sortModelInfo.setKpi("86");
            sortModelInfo.setNum("0204"+i);
            sortModelInfo.setDispatch("允许");
            String pinyin = characterParser.getSelling(sortModelInfo.getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            if (sortString.matches("[A-Z]")) {
                sortModelInfo.setSortLetters(sortString.toUpperCase());
            } else {
                sortModelInfo.setSortLetters("#");
            }

            mSortList.add(sortModelInfo);
        }
        return mSortList;
    }

    private void filterData(String filterStr) {
        List<SortModelInfo> filterDateList = new ArrayList<SortModelInfo>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModelInfo sortModelInfo : SourceDateList) {
                String name = sortModelInfo.getName();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModelInfo);
                }
            }
        }

        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

}
