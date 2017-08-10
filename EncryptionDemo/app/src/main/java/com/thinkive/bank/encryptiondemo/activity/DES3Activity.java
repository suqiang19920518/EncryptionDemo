package com.thinkive.bank.encryptiondemo.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thinkive.bank.encryptiondemo.R;
import com.thinkive.bank.encryptiondemo.util.DES3Utils;
import com.thinkive.bank.encryptiondemo.util.ResourcesUtils;

/**
 * @author: sq
 * @date: 2017/8/9
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 演示界面——3DES加解密
 */
public class DES3Activity extends BaseActivity {

    private Button mBtnDES3Operation;
    private EditText mEtContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_des3;
    }

    @Override
    protected void findViews() {
        mBtnDES3Operation = ((Button) findViewById(R.id.btn_operate));
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
        mBtnDES3Operation.setOnClickListener(this);
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
                    mBtnDES3Operation.setEnabled(true);
                } else {
                    mBtnDES3Operation.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_operate) {
            String message = mBtnDES3Operation.getText().toString();
            if (message.equals(ResourcesUtils.getString(this, R.string.tip_encode1))) {  //3DES加密
                String encodeResult = DES3Utils.encode(mEtContent.getText().toString());
//                String encodeResult = DES3Utils.encode1(mEtContent.getText().toString());
                mEtContent.setText(encodeResult);
                mBtnDES3Operation.setText(R.string.tip_decode1);
            } else {   //3DES解密
                String decodeResult = DES3Utils.decode(mEtContent.getText().toString());
//                String decodeResult = DES3Utils.decode1(mEtContent.getText().toString());
                mEtContent.setText(decodeResult);
                mBtnDES3Operation.setText(R.string.tip_encode1);
            }
        }
    }
}
