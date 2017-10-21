package by.insight.test_task_omertex.di;


import javax.inject.Named;
import javax.inject.Singleton;
import by.insight.test_task_omertex.Constant;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class ModelModule {


    @Provides
    @Singleton
    @Named(Constant.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(Constant.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }

}
