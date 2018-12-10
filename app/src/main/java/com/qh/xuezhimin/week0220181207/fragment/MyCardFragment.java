package com.qh.xuezhimin.week0220181207.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qh.xuezhimin.week0220181207.R;
import com.qh.xuezhimin.week0220181207.activity.CaptureActivity;
import com.qh.xuezhimin.week0220181207.activity.LoginActivity;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCardFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ImageView mMyCardScan;
    private ImageView mMyCardQRCode;
    /**
     * 退出登录
     */
    private Button mBtnBackLogin;
    private TextView mMyCardUsername;
    //
    private String name;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            mMyCardQRCode.setImageBitmap(bitmap);
        }
    };
    private SharedPreferences config;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_card, container, false);
        initView(view);
        //传值
        passValues();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(name, 400);
                Message message = handler.obtainMessage();
                message.obj = bitmap;
                handler.sendMessage(message);
            }
        }).start();


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(name, 400);
                Message message = handler.obtainMessage();
                message.obj = bitmap;
                handler.sendMessage(message);
            }
        }).start();
        super.onCreate(savedInstanceState);

    }

    //sp传值
    private void passValues() {
        //将name值从 sp中取出
        config = getActivity().getSharedPreferences("config", MODE_PRIVATE);
        name = config.getString("u_name", "");
        mMyCardUsername.setText(name);

    }

    private void initView(View view) {
        mMyCardScan = view.findViewById(R.id.my_card_scan);
        mMyCardScan.setOnClickListener(this);
        mMyCardQRCode = view.findViewById(R.id.my_card_QRCode);
        mBtnBackLogin = view.findViewById(R.id.btn_back_login);
        mBtnBackLogin.setOnClickListener(this);
        mMyCardUsername = view.findViewById(R.id.my_card_username);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //扫一扫
            case R.id.my_card_scan:
                startActivity(new Intent(getContext(), CaptureActivity.class));
                break;
            //登录退出
            case R.id.btn_back_login:
                //清除用户名和密码
                config.edit().remove("u_name").commit();
                config.edit().remove("p_wd").commit();
                //跳回登录页面
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}
