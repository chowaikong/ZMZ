package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewProvider;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemSubtitleBinding;
import me.knox.zmz.entity.Subtitle;

/**
 * Created by KNOX.
 */

public class SubtitleItemProvider extends ItemViewProvider<Subtitle, DataBindingViewHolder<ItemSubtitleBinding>> {
  @NonNull @Override protected DataBindingViewHolder<ItemSubtitleBinding> onCreateViewHolder(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(ItemSubtitleBinding.inflate(inflater, parent, false));
  }

  @Override
  protected void onBindViewHolder(@NonNull DataBindingViewHolder<ItemSubtitleBinding> holder,
      @NonNull Subtitle subtitle) {
    holder.getBinding().setSubtitle(subtitle);
    holder.getBinding().executePendingBindings();
  }
}
