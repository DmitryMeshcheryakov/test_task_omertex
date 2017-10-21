package by.insight.test_task_omertex.di;



import by.insight.test_task_omertex.presenter.DetailPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    DetailPresenter provideDetailPresenter() {
        return new DetailPresenter();
    }

}
