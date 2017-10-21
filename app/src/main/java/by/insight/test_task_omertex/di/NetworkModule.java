package by.insight.test_task_omertex.di;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import by.insight.test_task_omertex.Constant;
import by.insight.test_task_omertex.model.api.ApiInterface;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class NetworkModule {

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(loggingInterceptor);
        client.retryOnConnectionFailure(true);
        client.connectTimeout(3, TimeUnit.SECONDS);
        client.readTimeout(3, TimeUnit.SECONDS);
        client.cache(cache);
        return client.build();
    }


    @Singleton
    @Provides
    @Named(Constant.RETROFIT_PLACEHOLDER)
    Retrofit retrofitPlaceholder(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL_PLACEHOLDER)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    @Named(Constant.RETROFIT_UNSPLASH)
    Retrofit retrofitUnsplash(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit
                .Builder()
                .baseUrl(Constant.BASE_URL_UNSPLASH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    @Named(Constant.API_INTERFACE_PLACEHOLDER)
    public ApiInterface provideApiInterfacePlaceholder(@Named(Constant.RETROFIT_PLACEHOLDER) Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Singleton
    @Provides
    @Named(Constant.API_INTERFACE_UNSPLASH)
    public ApiInterface provideApiInterfaceUnspalsh(@Named(Constant.RETROFIT_UNSPLASH) Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }
}
