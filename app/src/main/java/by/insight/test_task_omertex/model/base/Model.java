package by.insight.test_task_omertex.model.base;


import java.util.List;
import by.insight.test_task_omertex.model.unsplash_model.PhotoDTO;
import by.insight.test_task_omertex.model.PlaceholderModelDTO;
import io.reactivex.Observable;

public interface Model {

    Observable<List<PlaceholderModelDTO>> getPlaceholder(
            int page,
            int limit
    );
    Observable<List<PhotoDTO>> getPhotos(int page,
                                         String client_id,
                                         int per_page,
                                         String order_by);
}
