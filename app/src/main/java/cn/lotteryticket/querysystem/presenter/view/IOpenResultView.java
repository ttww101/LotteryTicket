package cn.lotteryticket.querysystem.presenter.view;

import cn.lotteryticket.querysystem.base.ILoadingView;
import cn.lotteryticket.querysystem.bean.TicketOpenData;
import cn.lotteryticket.querysystem.bean.TicketRegular;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date: 2017/9/8 15:02
 */

public interface IOpenResultView extends ILoadingView {
    void getSingleOpenResultSuccess(TicketOpenData ticketOpenData);

    void getRegularSuccess(TicketRegular ticketRegular);
}
