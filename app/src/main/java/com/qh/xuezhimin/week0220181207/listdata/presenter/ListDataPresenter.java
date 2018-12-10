package com.qh.xuezhimin.week0220181207.listdata.presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.qh.xuezhimin.week0220181207.bean.Root;
import com.qh.xuezhimin.week0220181207.listdata.model.ListDataModel;
import com.qh.xuezhimin.week0220181207.listdata.view.ListDataView;

public class ListDataPresenter {

    private ListDataView listDataView;

    public ListDataPresenter(ListDataView listDataView) {
        this.listDataView = listDataView;
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Root root = (Root) msg.obj;
            listDataView.onDataSucess(root.getData());
        }
    };


    public void getData() {
        new Thread() {
            @Override
            public void run() {
                Root listData = ListDataModel.getListData();
                Message mMessage = mHandler.obtainMessage();
                mMessage.obj = listData;
                mHandler.sendMessage(mMessage);
            }
        }.start();
    }

}
