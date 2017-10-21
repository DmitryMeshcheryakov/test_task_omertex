package by.insight.test_task_omertex.presenter;

import android.os.Bundle;

import by.insight.test_task_omertex.App;
import by.insight.test_task_omertex.presenter.base.BasePresenter;
import by.insight.test_task_omertex.presenter.vo.ResultObject;
import by.insight.test_task_omertex.view.DetailView;
import by.insight.test_task_omertex.view.fragment.base.View;


public class DetailPresenter extends BasePresenter {

    private static final String BUNDLE_DETAIL_KEY = "DETAIL_KEY";

    private DetailView mDetailView;

    private ResultObject mResultObject;

    public void onCreate(DetailView detailView, ResultObject resultObject) {
        App.getComponent().inject(this);
        this.mDetailView = detailView;
        this.mResultObject = resultObject;
    }


    public void onCreateView() {
        if (mResultObject != null) {
            mDetailView.showProgressBar();
            mDetailView.showImage(mResultObject.getPhotoDTO().getUrls().getSmall());
            mDetailView.showPlaceholderAPI(mResultObject.getPlaceholderModelDTO());
            mDetailView.showUnsplashAPI(mResultObject.getPhotoDTO());
            mDetailView.hideProgressBar();
        }

    }

    public void onSaveInstanceState(Bundle outState) {
        if (mResultObject != null)
            outState.putSerializable(BUNDLE_DETAIL_KEY, new ResultObject(mResultObject.getPhotoDTO(), mResultObject.getPlaceholderModelDTO()));
    }


    protected View getDetailView() {
        return mDetailView;
    }
}
