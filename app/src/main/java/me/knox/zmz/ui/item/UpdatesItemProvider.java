package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewProvider;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemUpdateBinding;
import me.knox.zmz.entity.Update;

/**
 * Created by KNOX.
 */

public class UpdatesItemProvider extends ItemViewProvider<Update, DataBindingViewHolder<ItemUpdateBinding>> {

  @NonNull @Override protected DataBindingViewHolder<ItemUpdateBinding> onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(ItemUpdateBinding.inflate(inflater, parent, false));
  }

  @Override
  protected void onBindViewHolder(@NonNull DataBindingViewHolder<ItemUpdateBinding> holder,
      @NonNull Update update) {
    holder.getBinding().setUpdate(update);
    holder.getBinding().executePendingBindings();
  }
}
