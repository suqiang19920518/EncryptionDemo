package com.thinkive.bank.encryptiondemo.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thinkive.bank.encryptiondemo.R;
import com.thinkive.bank.encryptiondemo.util.AESUtils;
import com.thinkive.bank.encryptiondemo.util.ResourcesUtils;

/**
 * @author: sq
 * @date: 2017/8/9
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 演示界面——AES加解密
 */
public class AESActivity extends BaseActivity {

    private Button mBtnAESOperation;
    private EditText mEtContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aes;
    }

    @Override
    protected void findViews() {
        mBtnAESOperation = ((Button) findViewById(R.id.btn_operate));
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
        mBtnAESOperation.setOnClickListener(this);
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
                    mBtnAESOperation.setEnabled(true);
                } else {
                    mBtnAESOperation.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_operate) {
            String message = mBtnAESOperation.getText().toString();
            if (message.equals(ResourcesUtils.getString(this, R.string.tip_encode1))) {  //AES加密
                String encodeResult = AESUtils.encode(mEtContent.getText().toString());
                mEtContent.setText(encodeResult);
                mBtnAESOperation.setText(R.string.tip_decode1);
            } else {   //AES解密
                String decodeResult = AESUtils.decode(mEtContent.getText().toString());
                mEtContent.setText(decodeResult);
                mBtnAESOperation.setText(R.string.tip_encode1);
            }
        }
    }
}
