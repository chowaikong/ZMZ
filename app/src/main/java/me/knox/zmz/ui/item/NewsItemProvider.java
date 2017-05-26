package me.knox.zmz.ui.item;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewBinder;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemNewsBinding;
import me.knox.zmz.entity.News;
import me.knox.zmz.ui.activity.NewsInfoActivity;

/**
 * Created by KNOX.
 */

public class NewsItemProvider
    extends ItemViewBinder<News, DataBindingViewHolder<ItemNewsBinding>> {

  @NonNull @Override protected DataBindingViewHolder<ItemNewsBinding> onCreateViewHolder(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(ItemNewsBinding.inflate(inflater, parent, false));
  }

  @Override protected void onBindViewHolder(@NonNull DataBindingViewHolder<ItemNewsBinding> holder,
      @NonNull News news) {
    holder.getBinding().setNews(news);
    holder.getBinding().getRoot().setOnClickListener(v -> {
      Activity activity = (Activity) v.getContext();
      NewsInfoActivity.startWithTransition(activity, holder.getBinding().getNews(),
          holder.getBinding().poster, holder.getBinding().title);
    });
    holder.getBinding().executePendingBindings();
  }
}
