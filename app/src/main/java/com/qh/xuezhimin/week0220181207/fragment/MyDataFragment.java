package com.qh.xuezhimin.week0220181207.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.qh.xuezhimin.week0220181207.R;
import com.qh.xuezhimin.week0220181207.adapter.MyListDataAdapter;
import com.qh.xuezhimin.week0220181207.bean.Data;
import com.qh.xuezhimin.week0220181207.listdata.presenter.ListDataPresenter;
import com.qh.xuezhimin.week0220181207.listdata.view.ListDataView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyDataFragment extends Fragment implements ListDataView {

    private ListView mListView;
    private List<Data> mListData;
    private MyListDataAdapter mMyListDataAdapter;
    private ListDataPresenter mListDataPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_data, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mListView = view.findViewById(R.id.list_view);
        mListData = new ArrayList<>();

        //适配器
        mMyListDataAdapter = new MyListDataAdapter(mListData, getContext());
        mListView.setAdapter(mMyListDataAdapter);

        //实例化p层
        mListDataPresenter = new ListDataPresenter(this);
        mListDataPresenter.getData();

    }


    //数据接口实现的方法
    @Override
    public void onDataSucess(List<Data> entityList) {
        if (entityList != null) {
            mListData.clear();
            mListData.addAll(entityList);
            mMyListDataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailer(Exception e) {
        Toast.makeText(getContext(), e + "", Toast.LENGTH_SHORT).show();
    }
}
