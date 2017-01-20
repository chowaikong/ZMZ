package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewProvider;
import me.knox.zmz.databinding.ItemNewsBinding;
import me.knox.zmz.entity.News;
import me.knox.zmz.ui.activity.NewsInfoActivity;

/**
 * Created by KNOX.
 */

public class NewsItemProvider extends ItemViewProvider<News, NewsItemProvider.ViewHolder> {

  @NonNull @Override protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    return new ViewHolder(ItemNewsBinding.inflate(inflater, parent, false));
  }

  //@NonNull @Override protected DataBindingViewHolder<LayoutVerticalRvBinding> onCreateViewHolder(
  //    @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
  //  return new DataBindingViewHolder<>(LayoutVerticalRvBinding.inflate(inflater, parent, false));
  //}

  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull News news) {
    holder.mBinding.setNews(news);
  }

  //@Override
  //protected void onBindViewHolder(@NonNull DataBindingViewHolder<LayoutVerticalRvBinding> holder,
  //    @NonNull NewsList newsList) {
  //  holder.getBinding().rvVertical.addItemDecoration(mDecoration);
  //  mAdapter.setData(newsList.getList());
  //  holder.getBinding().rvVertical.setAdapter(mAdapter);
  //  holder.getBinding().rvVertical.addOnItemTouchListener(new RecyclerItemClickListener(
  //      holder.getBinding().getRoot().getContext(),
  //      (view, position) -> NewsInfoActivity.start(view.getContext(), newsList.getList().get(position))));
  //  holder.getBinding().executePendingBindings();
  //}

  static class ViewHolder extends RecyclerView.ViewHolder {

    private ItemNewsBinding mBinding;

    ViewHolder(ItemNewsBinding binding) {
      super(binding.getRoot());
      mBinding = binding;
      mBinding.getRoot().setOnClickListener(
          v -> NewsInfoActivity.start(v.getContext(), mBinding.getNews()));
    }
  }
}
