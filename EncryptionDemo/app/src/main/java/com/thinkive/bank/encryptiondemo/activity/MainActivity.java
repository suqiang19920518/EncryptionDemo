package com.thinkive.bank.encryptiondemo.activity;

import android.view.View;
import android.widget.Button;

import com.thinkive.bank.encryptiondemo.R;

/**
 * @author: sq
 * @date: 2017/8/9
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 主界面
 */
public class MainActivity extends BaseActivity {
    private Button mBtnBase64;
    private Button mBtnMD5;
    private Button mBtnAES;
    private Button mBtnDES;
    private Button mBtn3DES;
    private Button mBtnRSA;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews() {
        mBtnBase64 = ((Button) findViewById(R.id.btn_base64));
        mBtnMD5 = ((Button) findViewById(R.id.btn_MD5));
        mBtnDES = ((Button) findViewById(R.id.btn_DES));
        mBtn3DES = ((Button) findViewById(R.id.btn_3DES));
        mBtnAES = ((Button) findViewById(R.id.btn_AES));
        mBtnRSA = ((Button) findViewById(R.id.btn_RSA));
    }

    @Override
    protected void initObjects() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListeners() {
        mBtnBase64.setOnClickListener(this);
        mBtnMD5.setOnClickListener(this);
        mBtnDES.setOnClickListener(this);
        mBtn3DES.setOnClickListener(this);
        mBtnAES.setOnClickListener(this);
        mBtnRSA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_base64:
                startActivity(Base64Activity.class);
                break;
            case R.id.btn_MD5:
                startActivity(MD5Activity.class);
                break;
            case R.id.btn_DES:
                startActivity(DESActivity.class);
                break;
            case R.id.btn_3DES:
                startActivity(DES3Activity.class);
                break;
            case R.id.btn_AES:
                startActivity(AESActivity.class);
                break;
            case R.id.btn_RSA:
                startActivity(RSAActivity.class);
                break;
        }
    }
}
