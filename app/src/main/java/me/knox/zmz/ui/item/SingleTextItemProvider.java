package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewProvider;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.LayoutSingleTextCardViewBinding;

/**
 * Created by KNOX.
 */

public class SingleTextItemProvider extends ItemViewProvider<String, DataBindingViewHolder<LayoutSingleTextCardViewBinding>> {
  @NonNull @Override
  protected DataBindingViewHolder<LayoutSingleTextCardViewBinding> onCreateViewHolder(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(LayoutSingleTextCardViewBinding.inflate(inflater, parent, false));
  }

  @Override protected void onBindViewHolder(
      @NonNull DataBindingViewHolder<LayoutSingleTextCardViewBinding> holder, @NonNull String s) {
    holder.getBinding().text.setText(s);
    holder.getBinding().executePendingBindings();
  }
}
