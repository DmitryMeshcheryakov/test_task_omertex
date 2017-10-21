package by.insight.test_task_omertex.di.view;


import by.insight.test_task_omertex.presenter.ListPresenter;
import by.insight.test_task_omertex.view.ListView;
import dagger.Module;
import dagger.Provides;

@Module
public class ViewListModule {

    private ListView mListView;

    public ViewListModule(ListView view) {
        this.mListView = view;
    }

    @Provides
    ListPresenter provideListPresenter() {
        return new ListPresenter(mListView);
    }

}
