package by.insight.test_task_omertex.model.base;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import by.insight.test_task_omertex.App;
import by.insight.test_task_omertex.Constant;
import by.insight.test_task_omertex.model.unsplash_model.PhotoDTO;
import by.insight.test_task_omertex.model.PlaceholderModelDTO;
import by.insight.test_task_omertex.model.api.ApiInterface;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;



public class ModelImpl implements Model {

    private final ObservableTransformer schedulersTransformer;

    @Inject
    @Named(Constant.API_INTERFACE_PLACEHOLDER)
    ApiInterface mApiInterfacePlace;

    @Inject
    @Named(Constant.API_INTERFACE_UNSPLASH)
    ApiInterface mApiInterfaceUnspalsh;

    @Inject
    @Named(Constant.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Constant.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        App.getComponent().inject(this);
        schedulersTransformer = o -> (o)
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread);
    }

    @SuppressWarnings("unchecked")
    private <T> ObservableTransformer<T, T> applySchedulers() {
        return (ObservableTransformer<T, T>) schedulersTransformer;
    }

    @Override
    public Observable<List<PlaceholderModelDTO>> getPlaceholder(int page, int limit) {
        return mApiInterfacePlace.getPlaceholder(page, limit)
                .compose(applySchedulers());
    }

    @Override
    public Observable<List<PhotoDTO>> getPhotos(int page, String client_id, int per_page, String order_by) {
        return mApiInterfaceUnspalsh.getPhotos(page, client_id, per_page, order_by)
                .compose(applySchedulers());
    }


}
