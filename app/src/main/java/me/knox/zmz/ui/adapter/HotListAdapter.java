package me.knox.zmz.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemHotBinding;
import me.knox.zmz.entity.Hot;

/**
 * Created by KNOX.
 */

public class HotListAdapter extends BaseRVAdapter<ItemHotBinding, Hot> {

  public HotListAdapter(List<Hot> mDataList) {
    super(mDataList);
  }

  @Override
  public DataBindingViewHolder<ItemHotBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
    return new DataBindingViewHolder<>(
        ItemHotBinding.inflate(LayoutInflater.from(parent.getContext()), null, false));
  }

  @Override
  public void onBindViewHolder(DataBindingViewHolder<ItemHotBinding> holder, int position) {
    holder.getBinding().setHot(mDataList.get(holder.getAdapterPosition()));
    holder.getBinding().executePendingBindings();
  }
}
