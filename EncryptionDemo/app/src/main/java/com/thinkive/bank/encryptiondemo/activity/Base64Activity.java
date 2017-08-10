package com.thinkive.bank.encryptiondemo.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thinkive.bank.encryptiondemo.R;
import com.thinkive.bank.encryptiondemo.util.Base64Utils;
import com.thinkive.bank.encryptiondemo.util.ResourcesUtils;

/**
 * @author: sq
 * @date: 2017/8/8
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 演示界面——base64编解码
 */
public class Base64Activity extends BaseActivity {
    private Button mBtnBase64Operation;
    private EditText mEtContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base64;
    }

    @Override
    protected void findViews() {
        mBtnBase64Operation = ((Button) findViewById(R.id.btn_operate));
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
        mBtnBase64Operation.setOnClickListener(this);
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
                    mBtnBase64Operation.setEnabled(true);
                } else {
                    mBtnBase64Operation.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_operate) {
            String message = mBtnBase64Operation.getText().toString();
            if (message.equals(ResourcesUtils.getString(this, R.string.tip_encode))) {  //base64编码
                String encodeResult = Base64Utils.encode(mEtContent.getText().toString().getBytes());
                mEtContent.setText(encodeResult);
                mBtnBase64Operation.setText(R.string.tip_decode);
            } else {   //base64解码
                byte[] decodeResult = Base64Utils.decode(mEtContent.getText().toString());
                mEtContent.setText(new String(decodeResult));
                mBtnBase64Operation.setText(R.string.tip_encode);
            }
        }
    }
}
