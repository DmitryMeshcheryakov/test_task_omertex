package by.insight.test_task_omertex.presenter.base;

import javax.inject.Inject;


import by.insight.test_task_omertex.App;
import by.insight.test_task_omertex.model.base.Model;
import by.insight.test_task_omertex.view.fragment.base.View;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BasePresenter implements Presenter {

    @Inject
    protected Model mModel;

    @Inject
    CompositeDisposable mCompositeDisposable;

    protected BasePresenter() {
        App.getComponent().inject(this);
    }

    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
    }

    protected abstract View getDetailView();

    protected void showError(Throwable e) {
        getDetailView().showError(e.getMessage());
    }

}
