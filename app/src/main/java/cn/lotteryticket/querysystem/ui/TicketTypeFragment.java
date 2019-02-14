package cn.lotteryticket.querysystem.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.standards.library.listview.ListGroupPresenter;
import com.standards.library.listview.listview.RecycleListViewImpl;
import com.standards.library.listview.manager.BaseGroupListManager;

import cn.lotteryticket.querysystem.R;
import cn.lotteryticket.querysystem.adapter.TicketTypeAdapter;
import cn.lotteryticket.querysystem.base.BaseFuncFragment;
import cn.lotteryticket.querysystem.bean.TicketType;
import cn.lotteryticket.querysystem.bean.type.TicketTypeEnum;
import cn.lotteryticket.querysystem.group.LoadingPage;
import cn.lotteryticket.querysystem.group.Scene;
import cn.lotteryticket.querysystem.manager.TicketTypeManager;
import cn.lotteryticket.querysystem.ui.widget.RecycleViewDivider;
import cn.lotteryticket.querysystem.utils.LaunchUtil;


/**
 * @author xiaolong
 * @version v1.0
 * @function <发现页普通页面>
 * @date 2016/8/9-14:10
 */
public class TicketTypeFragment extends BaseFuncFragment {
    private TicketTypeAdapter ticketTypeAdapter;
    private ListGroupPresenter presenter;
    private BaseGroupListManager manager;
    private RecycleListViewImpl recycleListView;
    private TicketTypeEnum mTicketTypeEnum;

    public static TicketTypeFragment getNewInstance(TicketTypeEnum ticketTypeEnum) {
        TicketTypeFragment fragment = new TicketTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ticketListType", ticketTypeEnum);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void getExtra() {
        if (getArguments() != null) {
            mTicketTypeEnum = (TicketTypeEnum) getArguments().getSerializable("ticketListType");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ticket_type;
    }

    @Override
    public void init() {
        recycleListView = new RecycleListViewImpl(true, false, false);
        RelativeLayout rlContent = findView(R.id.rlContent);
        LoadingPage loadingPage = new LoadingPage(getActivity(), Scene.DEFAULT);
        ticketTypeAdapter = new TicketTypeAdapter(getActivity());
        manager = new TicketTypeManager(mTicketTypeEnum);
        presenter = ListGroupPresenter.create(getActivity(), recycleListView, manager, ticketTypeAdapter, loadingPage);
        recycleListView.getRecyclerView().addItemDecoration(new RecycleViewDivider(getActivity(),
                LinearLayoutManager.HORIZONTAL, 2, getResources().getColor(R.color.main_divider_color)));
        rlContent.addView(presenter.getRootView(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void setListener() {
        ticketTypeAdapter.setOnItemClickListener(view ->
                LaunchUtil.launchActivity(getActivity(), OpenResultActivity.class,
                        OpenResultActivity.buildBundle((TicketType) view.getTag())));
    }

}
