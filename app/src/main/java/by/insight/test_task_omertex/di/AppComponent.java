package by.insight.test_task_omertex.di;


import javax.inject.Singleton;
import by.insight.test_task_omertex.model.base.ModelImpl;
import by.insight.test_task_omertex.presenter.ListPresenter;
import by.insight.test_task_omertex.presenter.base.BasePresenter;
import by.insight.test_task_omertex.view.fragment.DetailFragment;
import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class,
        PresenterModule.class,
        ViewModule.class,
        NetworkModule.class,
        AppModule.class})
public interface AppComponent {

    void inject(ModelImpl dataRepository);

    void inject(BasePresenter basePresenter);

    void inject(ListPresenter listPresenter);

    void inject(DetailFragment detailFragment);

}
