package com.thinkive.bank.encryptiondemo.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thinkive.bank.encryptiondemo.R;
import com.thinkive.bank.encryptiondemo.util.RSAUtils;
import com.thinkive.bank.encryptiondemo.util.ResourcesUtils;

/**
 * @author: sq
 * @date: 2017/8/9
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 演示界面——RSA加解密
 */
public class RSAActivity extends BaseActivity {

    private Button mBtnRSAOperation;
    private EditText mEtContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rsa;
    }

    @Override
    protected void findViews() {
        mBtnRSAOperation = ((Button) findViewById(R.id.btn_operate));
        mEtContent = ((EditText) findViewById(R.id.et_result));
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
        mBtnRSAOperation.setOnClickListener(this);
        mEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    mBtnRSAOperation.setEnabled(true);
                } else {
                    mBtnRSAOperation.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_operate) {
            String message = mBtnRSAOperation.getText().toString();
            if (message.equals(ResourcesUtils.getString(this, R.string.tip_encode1))) {  //RSA加密
                String encodeResult = RSAUtils.encode(mEtContent.getText().toString());
                mEtContent.setText(encodeResult);
                mBtnRSAOperation.setText(R.string.tip_decode1);
            } else {   //RSA解密
                String decodeResult = RSAUtils.decode(mEtContent.getText().toString());
                mEtContent.setText(decodeResult);
                mBtnRSAOperation.setText(R.string.tip_encode1);
            }
        }
    }
}
