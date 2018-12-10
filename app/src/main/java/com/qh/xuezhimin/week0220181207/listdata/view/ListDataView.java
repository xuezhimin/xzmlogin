package com.qh.xuezhimin.week0220181207.listdata.view;

import com.qh.xuezhimin.week0220181207.bean.Data;

import java.util.List;

public interface ListDataView {

    void onDataSucess(List<Data> entityList);

    void onFailer(Exception e);

}
