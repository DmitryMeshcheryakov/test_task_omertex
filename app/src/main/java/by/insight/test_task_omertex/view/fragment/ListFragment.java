package by.insight.test_task_omertex.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.ybq.android.spinkit.SpinKitView;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;
import by.insight.test_task_omertex.ActivityCallback;
import by.insight.test_task_omertex.R;
import by.insight.test_task_omertex.di.view.DaggerViewListComponent;
import by.insight.test_task_omertex.di.view.ViewListComponent;
import by.insight.test_task_omertex.di.view.ViewListModule;
import by.insight.test_task_omertex.presenter.ListPresenter;
import by.insight.test_task_omertex.presenter.base.BasePresenter;
import by.insight.test_task_omertex.presenter.vo.ResultObject;
import by.insight.test_task_omertex.tools.InitUtil;
import by.insight.test_task_omertex.view.ListView;
import by.insight.test_task_omertex.view.adapter.ListAdapter;
import by.insight.test_task_omertex.view.fragment.base.BaseFragment;


public class ListFragment extends BaseFragment implements ListView {

    @Inject
    protected ListPresenter presenter;

    @Bind(R.id.toolbar_list)
    Toolbar mToolbarList;
    @Bind(R.id.appBarLayout_list)
    AppBarLayout mAppBarLayoutList;
    @Bind(R.id.rv_list)
    RecyclerView mRecyclerView;
    @Bind(R.id.coordinator_layout_list)
    CoordinatorLayout mCoordinatorLayoutList;
    @Bind(R.id.progress_bar)
    SpinKitView mProgressBar;

    private ListAdapter mListAdapter;

    private LinearLayoutManager mLinearLayoutManager;

    private ActivityCallback mActivityCallback;

    private ViewListComponent mViewListComponent;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mActivityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement mActivityCallback");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (mViewListComponent == null) {
            mViewListComponent = DaggerViewListComponent
                    .builder()
                    .viewListModule(new ViewListModule(this))
                    .build();
        }
        mViewListComponent.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list, container, false);
        ButterKnife.bind(this, view);
        setRetainInstance(true);
        setHasOptionsMenu(true);

        initToolbar();
        initRecyclerView();
        presenter.setUpLoadMoreListener(mRecyclerView, mLinearLayoutManager);
        presenter.onCreateView(savedInstanceState);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (presenter != null & mListAdapter.isEmptyAdapter()) {
            presenter.load();
        }
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mListAdapter = new ListAdapter(new ArrayList<>(), presenter, getContext());
        mRecyclerView.setAdapter(mListAdapter);
    }

    private void initToolbar() {
        InitUtil.initToolbar(mToolbarList, getActivity());
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void showList(List<ResultObject> resultList) {
                mListAdapter.addItemsAdapter(resultList);
    }

    @Override
    public void showDetail(ResultObject resultObject) {
           mActivityCallback.startDetailFragment(resultObject);
    }

    @Override
    public void showEmptyList() {
        makeToast("Empty List!");
    }

    @Override
    public void showProgressBar() {
       mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void makeToast(String text) {
        Snackbar.make(mRecyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}
