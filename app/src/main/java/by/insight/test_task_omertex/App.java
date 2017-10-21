package by.insight.test_task_omertex;

import android.app.Application;
import by.insight.test_task_omertex.di.AppComponent;
import by.insight.test_task_omertex.di.AppModule;
import by.insight.test_task_omertex.di.DaggerAppComponent;
import by.insight.test_task_omertex.di.ModelModule;
import by.insight.test_task_omertex.di.NetworkModule;
import by.insight.test_task_omertex.di.PresenterModule;
import by.insight.test_task_omertex.di.ViewModule;

public class App extends Application {
    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .modelModule(new ModelModule())
                .presenterModule(new PresenterModule())
                .viewModule(new ViewModule())
                .networkModule(new NetworkModule())
                .build();
    }

}
