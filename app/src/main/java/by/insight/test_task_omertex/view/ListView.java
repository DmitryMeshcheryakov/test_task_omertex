package by.insight.test_task_omertex.view;

import java.util.List;

import by.insight.test_task_omertex.presenter.vo.ResultObject;
import by.insight.test_task_omertex.view.fragment.base.View;

public interface ListView extends View {

    void showList(List<ResultObject> resultListingMovies);

    void showDetail(ResultObject resultObject);

    void showEmptyList();

    void showProgressBar();

    void hideProgressBar();
}
