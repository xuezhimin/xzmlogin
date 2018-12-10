package com.qh.xuezhimin.week0220181207.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.qh.xuezhimin.week0220181207.R;
import com.qh.xuezhimin.week0220181207.fragment.MyCardFragment;
import com.qh.xuezhimin.week0220181207.fragment.MyDataFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    private ViewPager mViewPager;
    /**
     * 我的数据
     */
    private TextView mTxtMyData;
    /**
     * 我的名片
     */
    private TextView mTxtMyCard;
    //fragment集合
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mTxtMyData = findViewById(R.id.txt_my_data);
        mTxtMyCard = findViewById(R.id.txt_my_card);
        mTxtMyData.setOnClickListener(this);
        mTxtMyCard.setOnClickListener(this);


        fragments = new ArrayList<>();
        fragments.add(new MyDataFragment());
        fragments.add(new MyCardFragment());


        //viewpager和fragment切换
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_my_data:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.txt_my_card:
                mViewPager.setCurrentItem(1);
                break;

        }
    }
}
