package by.insight.test_task_omertex.presenter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import org.reactivestreams.Publisher;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import by.insight.test_task_omertex.App;
import by.insight.test_task_omertex.Constant;
import by.insight.test_task_omertex.model.unsplash_model.PhotoDTO;
import by.insight.test_task_omertex.model.PlaceholderModelDTO;
import by.insight.test_task_omertex.presenter.base.BasePresenter;
import by.insight.test_task_omertex.presenter.vo.ResultObject;
import by.insight.test_task_omertex.view.ListView;
import by.insight.test_task_omertex.view.fragment.base.View;
import io.reactivex.BackpressureStrategy;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.PublishProcessor;


public class ListPresenter extends BasePresenter {

    private static final String BUNDLE_LIST_PRESENTER_KEY = "BUNDLE_LIST_PRESENTER_KEY";
    private static final String BUNDLE_PAGE_PRESENTER_KEY = "BUNDLE_PAGE_PRESENTER_KEY";

    private List<ResultObject> mResultObjects;

    private boolean mLoading = false;
    private int mPageNumber = 1;
    private final int VISIBLE_THRESHOLD = 1;
    private int mLastVisibleItem, mTotalItemCount;
    private PublishProcessor<Integer> mPaginator = PublishProcessor.create();

    private ListView mListView;

    @Inject
    public ListPresenter() {
    }

    public ListPresenter(ListView view) {
        App.getComponent().inject(this);
        this.mListView = view;
    }

    protected View getDetailView() {
        return mListView;
    }

    private void handleError(Throwable throwable) {
        showError(throwable);
    }

    private io.reactivex.Observable<List<PlaceholderModelDTO>> loadPlaceholder(int pageNumber) {
        return mModel.getPlaceholder(pageNumber, Constant.SUM_OBJECTS);
    }

    private io.reactivex.Observable<List<PhotoDTO>> loadUnsplash(int pageNumber) {
        return mModel.getPhotos(pageNumber, Constant.TOKEN_UNSPLASH, Constant.SUM_OBJECTS, Constant.ORDER_BY_POPULAR);
    }

    private Publisher<List<ResultObject>> loadContents(int pageNumber) {
        return io.reactivex.Observable.zip(loadUnsplash(pageNumber),
                loadPlaceholder(pageNumber), (photoDTOs, placeholderModelDTOs) -> {
                    List<ResultObject> list = new ArrayList<>();
                    for (int i = 0; i < Constant.SUM_OBJECTS; i++) {
                        list.add(new ResultObject(photoDTOs.get(i), placeholderModelDTOs.get(i)));
                    }
                    return list;
                }).toFlowable(BackpressureStrategy.BUFFER);
    }

    public void load() {
        mListView.showProgressBar();
        Disposable disposable = mPaginator
                .onBackpressureDrop()
                .concatMap(integer -> {
                    mLoading = true;
                    return loadContents(integer);
                })

                .subscribe(resultList -> {
                    if (resultList != null && !resultList.isEmpty()) {
                        if (mResultObjects != null) {
                            mResultObjects.addAll(resultList);
                        } else {
                            mResultObjects = resultList;
                        }
                        mListView.hideProgressBar();
                        mListView.showList(resultList);
                        mLoading = false;

                    } else {
                        mListView.showEmptyList();
                    }
                }, this::handleError);


        addDisposable(disposable);
        mPaginator.onNext(mPageNumber);
    }


    public void setUpLoadMoreListener(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mTotalItemCount = linearLayoutManager.getItemCount();
                mLastVisibleItem = linearLayoutManager
                        .findLastVisibleItemPosition();
                if (!mLoading
                        && mTotalItemCount <= (mLastVisibleItem + VISIBLE_THRESHOLD)) {
                    mPageNumber++;
                    mPaginator.onNext(mPageNumber);
                    mLoading = true;
                }
            }
        });
    }


    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mResultObjects = (List<ResultObject>) savedInstanceState.getSerializable(BUNDLE_LIST_PRESENTER_KEY);
            mPageNumber = (int) savedInstanceState.getSerializable(BUNDLE_PAGE_PRESENTER_KEY);
        }
        if (isRepoListNotEmpty()) {
            mListView.showList(mResultObjects);

        }
    }

    private boolean isRepoListNotEmpty() {
        return (mResultObjects != null && !mResultObjects.isEmpty());
    }

    public void onSaveInstanceState(Bundle outState) {
        if (isRepoListNotEmpty()) {
            outState.putSerializable(BUNDLE_LIST_PRESENTER_KEY, new ArrayList<>(mResultObjects));
            outState.putSerializable(BUNDLE_PAGE_PRESENTER_KEY, mPageNumber);
        }
    }

    public void onItemClick(ResultObject resultObject) {
        mListView.showDetail(resultObject);
    }
}
