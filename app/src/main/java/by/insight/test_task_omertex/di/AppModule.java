package by.insight.test_task_omertex.di;

import android.app.Application;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private Application mApp;

    public AppModule(Application app) {
        this.mApp = app;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return mApp;
    }
}
