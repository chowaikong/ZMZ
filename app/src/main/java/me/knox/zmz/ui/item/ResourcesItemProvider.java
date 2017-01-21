package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewProvider;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemResourceBinding;
import me.knox.zmz.entity.Resource;
import me.knox.zmz.ui.activity.ResourceInfoActivity;

/**
 * Created by KNOX.
 */

public class ResourcesItemProvider extends ItemViewProvider<Resource.Data, DataBindingViewHolder<ItemResourceBinding>> {

  @NonNull @Override protected DataBindingViewHolder<ItemResourceBinding> onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(ItemResourceBinding.inflate(inflater, parent, false));
  }

  @Override
  protected void onBindViewHolder(@NonNull DataBindingViewHolder<ItemResourceBinding> holder,
      @NonNull Resource.Data data) {
    holder.getBinding().setResource(data);
    holder.getBinding().getRoot().setOnClickListener(v -> {
      int id = holder.getBinding().getResource().getId();
      String poster = holder.getBinding().getResource().getPoster();
      ResourceInfoActivity.start(v.getContext(), id, poster);
    });
    holder.getBinding().executePendingBindings();
  }
}
