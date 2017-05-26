package me.knox.zmz.ui.item;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewBinder;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemSearchResultBinding;
import me.knox.zmz.entity.Search;
import me.knox.zmz.ui.activity.NewsInfoActivity;
import me.knox.zmz.ui.activity.ResourceInfoActivity;

/**
 * Created by KNOX.
 */

public class SearchItemProvider extends
    ItemViewBinder<Search, DataBindingViewHolder<ItemSearchResultBinding>> {
  @NonNull @Override protected DataBindingViewHolder<ItemSearchResultBinding> onCreateViewHolder(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(ItemSearchResultBinding.inflate(inflater, parent, false));
  }

  @Override
  protected void onBindViewHolder(@NonNull DataBindingViewHolder<ItemSearchResultBinding> holder,
      @NonNull Search search) {
    final Context context = holder.getBinding().getRoot().getContext();
    holder.getBinding().setSearch(search);
    holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        final String type = search.getType();
        final int id = search.getId();
        switch (type) {
          case "resource":
            ResourceInfoActivity.startWithoutTransition(context, id);
            break;
          case "article":
            NewsInfoActivity.startWithoutTransition((Activity) context, id);
            break;
        }
      }
    });
    holder.getBinding().executePendingBindings();
  }
}
