package by.insight.test_task_omertex.view.adapter;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;
import by.insight.test_task_omertex.R;
import by.insight.test_task_omertex.presenter.ListPresenter;
import by.insight.test_task_omertex.presenter.vo.ResultObject;


public class ListAdapter extends BaseAdapter<ResultObject> {
    private ListPresenter mListPresenter;
    private Context mContext;
    private final RequestOptions mRequestOptions;


    public ListAdapter(List<ResultObject> list, ListPresenter presenter, Context context) {
        super(list);
        this.mContext = context;
        this.mListPresenter = presenter;
        mRequestOptions = new RequestOptions().centerCrop();
        mRequestOptions.placeholder(R.drawable.ic_lock);
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        ResultObject resultObject = mList.get(i);
        viewHolder.mTextView.setText(resultObject.getPlaceholderModelDTO().getTitle());
        Glide.with(mContext)
                .load(resultObject.getPhotoDTO().getUrls().getSmall())
                .apply(mRequestOptions)
                .into(viewHolder.mImageView);
        viewHolder.mImageView.setOnClickListener(v ->
                mListPresenter.onItemClick(resultObject));
    }

    public void addItemsAdapter(List<ResultObject> items) {
        this.mList.addAll(items);
        notifyDataSetChanged();
    }

    public boolean isEmptyAdapter()
    {
        return mList.size() == 0;
    }

}
