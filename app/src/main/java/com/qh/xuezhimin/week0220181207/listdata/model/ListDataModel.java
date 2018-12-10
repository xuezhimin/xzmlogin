package com.qh.xuezhimin.week0220181207.listdata.model;

import com.google.gson.Gson;
import com.qh.xuezhimin.week0220181207.bean.Root;
import com.qh.xuezhimin.week0220181207.httputils.Utils;

public class ListDataModel {

    public static Root getListData() {
        String listShowData = Utils.get("http://www.xieast.com/api/news/news.php");
        Gson gson = new Gson();
        Root root = gson.fromJson(listShowData, Root.class);
        return root;

    }

}
