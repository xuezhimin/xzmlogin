package com.qh.xuezhimin.week0220181207.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.qh.xuezhimin.week0220181207.R;
import com.qh.xuezhimin.week0220181207.fragment.MyCardFragment;
import com.qh.xuezhimin.week0220181207.login.LoginPresenter;
import com.qh.xuezhimin.week0220181207.login.LoginView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    /**
     * 请输入您的手机号
     */
    private EditText mEditMobile;
    /**
     * 请输入您的密码
     */
    private EditText mEditPwd;
    /**
     * 记住密码
     */
    private CheckBox mCbRemPwd;
    /**
     * 自动登录
     */
    private CheckBox mCbAutoLogin;
    /**
     * 登录
     */
    private Button mBtnLogin;
    private LoginPresenter mLoginPresenter;
    /**
     * 输入的用户名和密码
     */
    private String name;
    private String pwd;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //1.(2)实例化p层
        mLoginPresenter = new LoginPresenter(this);
        //记住密码操作
        remPwdView();

    }


    //1.(1)控件初始化
    private void initView() {
        mEditMobile = findViewById(R.id.edit_mobile);
        mEditPwd = findViewById(R.id.edit_pwd);
        mCbRemPwd = findViewById(R.id.cb_rem_pwd);
        mCbAutoLogin = findViewById(R.id.cb_auto_login);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
    }


    //2.点击登录
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                name = mEditMobile.getText().toString();
                pwd = mEditPwd.getText().toString();
                //登录成功之后  记住密码
                if (mCbRemPwd.isChecked()) {
                    SharedPreferences.Editor edit = sp.edit();
                    //存值
                    edit.putString("u_name", name);
                    edit.putString("p_wd", pwd);
                    edit.putBoolean("isChecked", true).commit();
                } else {
                    // 未点击记住密码的操作  clear清除，清除之后不要忘了commit
                    edit = sp.edit();
                    edit.clear().commit();
                }
                //p层
                mLoginPresenter.login(name, pwd);
                break;
        }
    }


    //3.记住密码  sp
    private void remPwdView() {
        sp = getSharedPreferences("config", MODE_PRIVATE);
        // 先看下SharePreference中是否记住 记住密码操作
        boolean isChecked = sp.getBoolean("isChecked", false);
        if (isChecked) {
            mCbRemPwd.setChecked(true);
            // 如果记住密码了，就从SharePreference中取出用户名密码
            mEditMobile.setText(sp.getString("u_name", ""));
            mEditPwd.setText(sp.getString("p_wd", ""));
        }


        //自动登录
        mCbAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mCbRemPwd.setChecked(true);
                }
            }
        });

        //记住密码
        mCbRemPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    mCbAutoLogin.setChecked(false);
                }
            }
        });

    }


    //1.(3)登录接口实现的方法
    @Override
    public void onSuccess(String result) {
        Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();
        //登录之后，页面跳转
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);

    }
    @Override
    public void onFail(String msg) {
        Toast.makeText(getBaseContext(), msg + "", Toast.LENGTH_SHORT).show();
    }


}
