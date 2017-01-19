package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewProvider;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemCategoryHeaderBinding;
import me.knox.zmz.entity.Category;

/**
 * Created by KNOX.
 */

public class CategoryItemProvider extends ItemViewProvider<Category, DataBindingViewHolder<ItemCategoryHeaderBinding>> {

  @NonNull @Override protected DataBindingViewHolder<ItemCategoryHeaderBinding> onCreateViewHolder(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(ItemCategoryHeaderBinding.inflate(inflater, parent, false));
  }

  @Override
  protected void onBindViewHolder(@NonNull DataBindingViewHolder<ItemCategoryHeaderBinding> holder,
      @NonNull Category category) {
    holder.getBinding().setCategoryHeader(category.mTitle);
  }
}
