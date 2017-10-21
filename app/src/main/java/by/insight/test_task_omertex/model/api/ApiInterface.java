package by.insight.test_task_omertex.model.api;

import java.util.List;

import by.insight.test_task_omertex.model.unsplash_model.PhotoDTO;
import by.insight.test_task_omertex.model.PlaceholderModelDTO;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface ApiInterface {

    @GET("/posts")
    Observable<List<PlaceholderModelDTO>> getPlaceholder(
            @Query("_page") int page,
            @Query("_limit") int limit
    );

    @GET("photos")
    Observable<List<PhotoDTO>> getPhotos(@Query("page") int page,
                                         @Query("client_id") String client_id,
                                         @Query("per_page") int per_page,
                                         @Query("order_by") String order_by);
}


