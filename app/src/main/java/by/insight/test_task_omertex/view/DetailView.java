package by.insight.test_task_omertex.view;

import by.insight.test_task_omertex.model.unsplash_model.PhotoDTO;
import by.insight.test_task_omertex.model.PlaceholderModelDTO;
import by.insight.test_task_omertex.view.fragment.base.View;



public interface DetailView extends View {

    void showProgressBar();

    void hideProgressBar();

    void showImage(String url);

    void showPlaceholderAPI(PlaceholderModelDTO modelDTO);

   void showUnsplashAPI(PhotoDTO mPhoto);

}
