package cn.lotteryticket.querysystem.presenter;

import android.app.Activity;

import com.google.gson.Gson;
import com.standards.library.cache.SPHelp;

import java.util.List;

import cn.lotteryticket.querysystem.BuildConfig;
import cn.lotteryticket.querysystem.base.BasePresenter;
import cn.lotteryticket.querysystem.bean.TicketType;
import cn.lotteryticket.querysystem.manager.TicketTypeDataManager;
import cn.lotteryticket.querysystem.presenter.view.IFollowAddView;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date: 2017/09/11 11:10:50
 */

public class FollowAddPresenter extends BasePresenter<IFollowAddView> {

    public FollowAddPresenter(Activity activity) {
        super(activity);
    }

    public void getMyFollowList() {
        addSubscribe(TicketTypeDataManager.getTicketDataManager().getMyFollowData().subscribe(getSubscriber(ticketTypeList ->
                mView.onGetTicketListSuccess(ticketTypeList)
        )));
    }

    public void cacheList(List<TicketType> ticketTypes) {
        SPHelp.setUserParam(BuildConfig.KEY_MY_FOLLOW, new Gson().toJson(ticketTypes));
    }
}
