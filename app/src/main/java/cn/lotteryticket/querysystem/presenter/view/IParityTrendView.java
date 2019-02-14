package cn.lotteryticket.querysystem.presenter.view;

import java.util.List;

import cn.lotteryticket.querysystem.base.ILoadingView;
import cn.lotteryticket.querysystem.bean.TicketOpenData;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date: 2017/9/14 14:59
 */

public interface IParityTrendView extends ILoadingView {
    void onGetHistoryRecentTicketListSuccess(List<TicketOpenData> list);
}
