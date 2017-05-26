package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewBinder;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.LayoutInfoHeaderBinding;

/**
 * Created by KNOX.
 */

public class ResourceInfoHeaderItemProvider extends
    ItemViewBinder<String[], DataBindingViewHolder<LayoutInfoHeaderBinding>> {
  @NonNull @Override protected DataBindingViewHolder<LayoutInfoHeaderBinding> onCreateViewHolder(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(LayoutInfoHeaderBinding.inflate(inflater, parent, false));
  }

  @Override
  protected void onBindViewHolder(@NonNull DataBindingViewHolder<LayoutInfoHeaderBinding> holder,
      @NonNull String[] strings) {
    holder.getBinding().setCname(strings[0]);
    holder.getBinding().setEname(strings[1]);
    holder.getBinding().executePendingBindings();
  }
}
