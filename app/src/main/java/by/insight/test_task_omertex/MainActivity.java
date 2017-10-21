package by.insight.test_task_omertex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import by.insight.test_task_omertex.presenter.vo.ResultObject;
import by.insight.test_task_omertex.view.fragment.DetailFragment;
import by.insight.test_task_omertex.view.fragment.ListFragment;

public class MainActivity extends AppCompatActivity implements ActivityCallback {

    private static String TAG = "TAG";

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null)
            replaceFragment(new ListFragment(), false);

    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void startDetailFragment(ResultObject resultObject) {
              replaceFragment(DetailFragment.newInstance(resultObject), true);
    }


}
