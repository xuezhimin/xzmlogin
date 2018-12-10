package com.qh.xuezhimin.week0220181207.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.qh.xuezhimin.week0220181207.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class CaptureActivity extends AppCompatActivity implements QRCodeView.Delegate {

    private ZXingView mZxingview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        initView();
    }


    private void initView() {
        mZxingview = findViewById(R.id.zxingview);
        mZxingview.setDelegate(this);

        //动态权限
        String[] p = new String[]{Manifest.permission.CAMERA};
        ActivityCompat.requestPermissions(CaptureActivity.this, p, 1);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            int result = grantResults[0];
            if (result == PackageManager.PERMISSION_GRANTED) {
                //权限同意
                mZxingview.startSpot();
            } else {
                //权限拒绝
                Toast.makeText(CaptureActivity.this, "请同意打开摄像头权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(CaptureActivity.this, result, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }


}
