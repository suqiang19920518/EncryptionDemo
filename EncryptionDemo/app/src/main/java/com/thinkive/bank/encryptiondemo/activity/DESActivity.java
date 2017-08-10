package com.thinkive.bank.encryptiondemo.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thinkive.bank.encryptiondemo.R;
import com.thinkive.bank.encryptiondemo.util.DESUtils;
import com.thinkive.bank.encryptiondemo.util.ResourcesUtils;

/**
 * @author: sq
 * @date: 2017/8/9
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 演示界面——DES加解密
 */
public class DESActivity extends BaseActivity {

    private Button mBtnDESOperation;
    private EditText mEtContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_des;
    }

    @Override
    protected void findViews() {
        mBtnDESOperation = ((Button) findViewById(R.id.btn_operate));
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
        mBtnDESOperation.setOnClickListener(this);
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
                    mBtnDESOperation.setEnabled(true);
                } else {
                    mBtnDESOperation.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_operate) {
            String message = mBtnDESOperation.getText().toString();
            if (message.equals(ResourcesUtils.getString(this, R.string.tip_encode1))) {  //DES加密
                String encodeResult = DESUtils.encode(mEtContent.getText().toString());
//                String encodeResult = DESUtils.encode1(mEtContent.getText().toString());
                mEtContent.setText(encodeResult);
                mBtnDESOperation.setText(R.string.tip_decode1);
            } else {   //DES解密
                String decodeResult = DESUtils.decode(mEtContent.getText().toString());
//                String decodeResult = DESUtils.decode1(mEtContent.getText().toString());
                mEtContent.setText(decodeResult);
                mBtnDESOperation.setText(R.string.tip_encode1);
            }
        }
    }
}
