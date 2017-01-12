package me.knox.zmz.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemResourceBinding;
import me.knox.zmz.entity.Resource;

/**
 * Created by KNOX.
 */

public class ResourcesAdapter extends BaseRVAdapter<ItemResourceBinding, Resource.Data> {

  public ResourcesAdapter(List<Resource.Data> mDataList) {
    super(mDataList);
  }

  @Override public DataBindingViewHolder<ItemResourceBinding> onCreateViewHolder(ViewGroup parent,
      int viewType) {
    return new DataBindingViewHolder<>(
        ItemResourceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(DataBindingViewHolder<ItemResourceBinding> holder, int position) {
    holder.getBinding().setResource(mDataList.get(holder.getAdapterPosition()));
    holder.getBinding().executePendingBindings();
  }
}
