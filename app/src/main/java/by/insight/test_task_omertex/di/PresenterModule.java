package by.insight.test_task_omertex.di;



import javax.inject.Singleton;
import by.insight.test_task_omertex.model.base.Model;
import by.insight.test_task_omertex.model.base.ModelImpl;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module

public class PresenterModule {

    @Provides
    @Singleton
    Model provideDataRepository() {
        return new ModelImpl();
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
