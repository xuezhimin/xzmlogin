package com.qh.xuezhimin.week0220181207.login;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.qh.xuezhimin.week0220181207.bean.User;

public class LoginPresenter {

    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            User loginData = (User) msg.obj;
            //Log.d("xzm", "" + loginData.getMsg());
            int code = loginData.getCode();
            String msg1 = loginData.getMsg();
            switch (code) {
                case 100:
                    loginView.onSuccess(msg1);
                    break;
                case 101:
                    loginView.onFail(msg1);
                    break;
            }

        }
    };

    public void login(final String name, final String pwd) {
        new Thread() {
            @Override
            public void run() {
                User loginData = LoginModel.login(name, pwd);
                Message mMessage = mHandler.obtainMessage();
                mMessage.obj = loginData;
                mHandler.sendMessage(mMessage);
            }
        }.start();
    }

}
