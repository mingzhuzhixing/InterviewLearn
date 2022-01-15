package com.v.webview_module;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.v.webview_module.constant.Constants;
import com.v.webview_module.databinding.ActivityWebViewMainBinding;
import com.v.webview_module.fragment.WebViewFragment;

public class WebViewMainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityWebViewMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view_main);

        boolean showBar = getIntent().getBooleanExtra(Constants.EXTRA_SHOW_ACTION_BAR, false);
        mBinding.rlActionBar.setVisibility(showBar ? View.VISIBLE : View.GONE);
        mBinding.tvTitle.setText(getIntent().getStringExtra(Constants.EXTRA_TITLE));
        mBinding.ivBack.setOnClickListener(this);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayout, WebViewFragment.newInstance(getIntent().getStringExtra(Constants.EXTRA_URL), true));
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            onBackPressed();
        }
    }

    /**
     * 更新标题
     *
     * @param title 标题
     */
    public void updateTitle(String title) {
        if (mBinding != null) {
            mBinding.tvTitle.setText(title);
        }
    }
}