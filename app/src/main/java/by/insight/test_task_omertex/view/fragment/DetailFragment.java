package by.insight.test_task_omertex.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.ybq.android.spinkit.SpinKitView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import by.insight.test_task_omertex.App;
import by.insight.test_task_omertex.CustomTextView;
import by.insight.test_task_omertex.R;
import by.insight.test_task_omertex.model.PlaceholderModelDTO;
import by.insight.test_task_omertex.model.unsplash_model.PhotoDTO;
import by.insight.test_task_omertex.presenter.DetailPresenter;
import by.insight.test_task_omertex.presenter.base.BasePresenter;
import by.insight.test_task_omertex.presenter.vo.ResultObject;
import by.insight.test_task_omertex.tools.InitUtil;
import by.insight.test_task_omertex.view.DetailView;
import by.insight.test_task_omertex.view.fragment.base.BaseFragment;


public class DetailFragment extends BaseFragment implements DetailView {

    private static final String BUNDLE_DETAIL_FRAGMENT_KEY = "BUNDLE_DETAIL_FRAGMENT_KEY";

    @Inject
    protected DetailPresenter mDetailPresenter;

    @Bind(R.id.image_detail)
    ImageView mImageDetail;
    @Bind(R.id.toolbar_detail)
    Toolbar mToolbarDetail;
    @Bind(R.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout mCollapsingToolbarDetail;
    @Bind(R.id.app_bar_layout_detail)
    AppBarLayout mAppBarLayoutDetail;
    @Bind(R.id.placeholder_title)
    CustomTextView mPlaceholderTitle;
    @Bind(R.id.placeholder_api_body)
    CustomTextView mPlaceholderApiBody;
    @Bind(R.id.placeholder_api_card)
    CardView mPlaceholderApiCard;
    @Bind(R.id.separator)
    View mSeparator;
    @Bind(R.id.unsplash_api_title)
    CustomTextView mUnsplashApiTitle;
    @Bind(R.id.unsplash_api_body)
    CustomTextView mUnsplashApiBody;
    @Bind(R.id.scroll_detail)
    NestedScrollView mScrollDetail;
    @Bind(R.id.progress_bar_detail)
    SpinKitView mProgressBarDetail;

    private RequestOptions mRequestOptions;


    public static DetailFragment newInstance(ResultObject resultObject) {
        DetailFragment myFragment = new DetailFragment();

        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_DETAIL_FRAGMENT_KEY, resultObject);
        myFragment.setArguments(args);

        return myFragment;
    }


    protected BasePresenter getPresenter() {
        return mDetailPresenter;
    }

    private ResultObject getResultObject() {
        return (ResultObject) getArguments().getSerializable(BUNDLE_DETAIL_FRAGMENT_KEY);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        mDetailPresenter.onCreate(this, getResultObject());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        initToolbar();

        mRequestOptions = new RequestOptions().optionalCenterInside();
        mDetailPresenter.onCreateView();
        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mDetailPresenter.onSaveInstanceState(outState);
    }

    private void initToolbar() {
        InitUtil.initToolbar(mToolbarDetail, getActivity());
        mCollapsingToolbarDetail.setTitle(" ");
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    private void makeToast(String text) {
        Snackbar.make(getView(), text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        mProgressBarDetail.setVisibility(View.VISIBLE);
       mScrollDetail.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBarDetail.setVisibility(View.INVISIBLE);
        mScrollDetail.setVisibility(View.VISIBLE);
    }

    @Override
    public void showImage(String url) {
        Glide.with(getContext())
                .load(url)
                .apply(mRequestOptions)
                .into(mImageDetail);
    }

    @Override
    public void showPlaceholderAPI(PlaceholderModelDTO modelDTO) {
        String body = getResources().getString(R.string.placeholder_body);
        mPlaceholderApiBody.setText(String.format(body, modelDTO.getTitle(), modelDTO.getBody()));
    }

    @Override
    public void showUnsplashAPI(PhotoDTO mPhoto) {
        String body = getResources().getString(R.string.unsplash_body);
        mUnsplashApiBody.setText(String.format(body, mPhoto.getUser().getName(),
                mPhoto.getUser().getLocation()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
