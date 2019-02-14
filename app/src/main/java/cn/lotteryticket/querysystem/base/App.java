package cn.lotteryticket.querysystem.base;

import android.app.Application;
import android.content.Context;

import com.standards.library.app.AppContext;
import com.standards.library.app.ReturnCode;
import com.standards.library.app.ReturnCodeConfig;
import com.standards.library.cache.DataProvider;
import com.standards.library.network.NetworkConfig;
import com.standards.library.util.LogUtil;

import cn.jpush.android.api.JPushInterface;
import cn.lotteryticket.querysystem.BuildConfig;
import cn.lotteryticket.querysystem.Logger;


/**
 * <请描述这个类是干什么的>
 *
 * @data: 16/9/19 下午2:40
 * @version: V1.0
 */
public class App extends Application {
    private static final String TAG = "JIGUANG-Example";
    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        Logger.d(TAG, "[ExampleApplication] onCreate");
        super.onCreate();

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

        AppContext.getInstance().init(this);
        sContext = getContext();
        LogUtil.init(BuildConfig.DEBUG_LOG, "lucky");
        DataProvider.init(this);
        NetworkConfig.setBaseUrl(BuildConfig.HOST_URL);
        ReturnCodeConfig.getInstance().initReturnCode(ReturnCode.CODE_SUCCESS, ReturnCode.CODE_EMPTY);
    }

}
