package cn.lotteryticket.querysystem.ui.web;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RelativeLayout;

import cn.lotteryticket.querysystem.R;
import cn.lotteryticket.querysystem.base.BasePresenter;
import cn.lotteryticket.querysystem.base.BaseTitleBar;
import cn.lotteryticket.querysystem.base.BaseTitleBarActivity;
import cn.lotteryticket.querysystem.group.LoadingPage;
import cn.lotteryticket.querysystem.group.Scene;
import cn.lotteryticket.querysystem.utils.LaunchUtil;

/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/12 15:55
 * @version: V1.0
 */
public class WebActivity<T extends BasePresenter> extends BaseTitleBarActivity<T> {
    private static final int REQUEST_CODE_LOGIN = 0x1111;
    protected BaseTitleBar titleBar;
    protected WebGroup webGroup;
    protected WebConfig config;

    public static Bundle buildBundle(WebConfig config) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("WebConfig", config);
        return bundle;
    }

    @Override
    public void getExtra() {
        config = (WebConfig) getIntent().getExtras().getSerializable("WebConfig");
        if (config == null) return;
    }

    @Override
    public void initTitleBar(BaseTitleBar titleBar) {
        this.titleBar = titleBar;
        titleBar.setTitleText(TextUtils.isEmpty(config.title) ? "" : config.title);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_web;
    }

    @Override
    public void init() {
//        mWebProgress = findView(R.id.webProgress);
        webGroup = WebGroup.create(this, config, new LoadingPage(this, Scene.DEFAULT));
        ((RelativeLayout) findView(R.id.webContainer)).addView(webGroup.getRootView());
        (findView(R.id.webContainer)).setPadding(0, 0, 0, 0);
    }

    @Override
    public void setListener() {
        webGroup.setOnShouldOverrideUrlListener(url -> LaunchUtil.launchDefaultWeb(this, url, ""));

        webGroup.setOnReceiveTitleListener(title -> {
            if (TextUtils.isEmpty(config.title)) {
                titleBar.setTitleText(title);
            }
        });
    }
}
