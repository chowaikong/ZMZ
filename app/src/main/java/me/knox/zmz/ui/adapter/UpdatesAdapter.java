package me.knox.zmz.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemUpdateBinding;
import me.knox.zmz.entity.Update;

/**
 * Created by KNOX.
 */

public class UpdatesAdapter extends BaseRVAdapter<ItemUpdateBinding, Update> {

  public UpdatesAdapter(List<Update> mDataList) {
    super(mDataList);
  }

  @Override public DataBindingViewHolder<ItemUpdateBinding> onCreateViewHolder(ViewGroup parent,
      int viewType) {
    return new DataBindingViewHolder<>(
        ItemUpdateBinding.inflate(LayoutInflater.from(parent.getContext()), null, false));
  }

  @Override
  public void onBindViewHolder(DataBindingViewHolder<ItemUpdateBinding> holder, int position) {
    holder.getBinding().setUpdate(mDataList.get(holder.getAdapterPosition()));
    holder.getBinding().executePendingBindings();
  }
}
