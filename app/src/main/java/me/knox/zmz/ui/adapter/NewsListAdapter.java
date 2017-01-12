package me.knox.zmz.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemNewsBinding;
import me.knox.zmz.entity.News;

/**
 * Created by KNOX.
 */

public class NewsListAdapter extends BaseRVAdapter<ItemNewsBinding, News> {

  public NewsListAdapter(List<News> mDataList) {
    super(mDataList);
  }

  @Override
  public DataBindingViewHolder<ItemNewsBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
    return new DataBindingViewHolder<>(
        ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(DataBindingViewHolder<ItemNewsBinding> holder, int position) {
    holder.getBinding().setNews(mDataList.get(holder.getAdapterPosition()));
    holder.getBinding().executePendingBindings();
  }
}
