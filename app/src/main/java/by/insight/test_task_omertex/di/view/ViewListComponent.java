package by.insight.test_task_omertex.di.view;


import javax.inject.Singleton;
import by.insight.test_task_omertex.view.fragment.ListFragment;
import dagger.Component;

@Singleton
@Component(modules = {ViewListModule.class})
public interface ViewListComponent {

   void inject(ListFragment listFragment);


}
