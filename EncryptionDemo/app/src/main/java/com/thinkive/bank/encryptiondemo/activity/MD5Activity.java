package com.thinkive.bank.encryptiondemo.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thinkive.bank.encryptiondemo.R;
import com.thinkive.bank.encryptiondemo.util.MD5Utils;

/**
 * @author: sq
 * @date: 2017/8/9
 * @corporation: 深圳市思迪信息技术股份有限公司
 * @description: 演示界面——MD5加密
 */
public class MD5Activity extends BaseActivity {

    private Button mBtnMD5Operation;
    private EditText mEtContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_md5;
    }

    @Override
    protected void findViews() {
        mBtnMD5Operation = ((Button) findViewById(R.id.btn_operate));
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
        mBtnMD5Operation.setOnClickListener(this);
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
                    mBtnMD5Operation.setEnabled(true);
                } else {
                    mBtnMD5Operation.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_operate) { //MD5加密
            String md5Code = MD5Utils.GetMD5Code(mEtContent.getText().toString());
//            String md5Code = MD5Utils.md5Encode(mEtContent.getText().toString());
            mEtContent.setText(md5Code);
            mBtnMD5Operation.setEnabled(false);
        }
    }
}
