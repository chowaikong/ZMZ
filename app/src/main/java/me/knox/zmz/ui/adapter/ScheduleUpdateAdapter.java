package me.knox.zmz.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemScheduleUpdateBinding;
import me.knox.zmz.entity.ScheduleUpdate;

/**
 * Created by KNOX.
 */

public class ScheduleUpdateAdapter
    extends BaseRVAdapter<ItemScheduleUpdateBinding, ScheduleUpdate> {

  public ScheduleUpdateAdapter(List<ScheduleUpdate> mDataList) {
    super(mDataList);
  }

  @Override
  public DataBindingViewHolder<ItemScheduleUpdateBinding> onCreateViewHolder(ViewGroup parent,
      int viewType) {
    return new DataBindingViewHolder<>(
        ItemScheduleUpdateBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override public void onBindViewHolder(DataBindingViewHolder<ItemScheduleUpdateBinding> holder,
      int position) {
    holder.getBinding().setUpdate(mDataList.get(holder.getAdapterPosition()));
    holder.getBinding().executePendingBindings();
  }
}
