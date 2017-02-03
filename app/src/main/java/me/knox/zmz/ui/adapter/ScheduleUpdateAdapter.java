package me.knox.zmz.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemScheduleUpdateBinding;
import me.knox.zmz.entity.ScheduleUpdate;
import me.knox.zmz.ui.activity.ResourceInfoActivity;

/**
 * Created by KNOX.
 */

public class ScheduleUpdateAdapter extends BaseRVAdapter<ItemScheduleUpdateBinding, ScheduleUpdate> {

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
    holder.getBinding().getRoot().setOnClickListener(v -> {
      Activity activity = (Activity) v.getContext();
      int id = mDataList.get(position).getId();
      String poster = mDataList.get(holder.getAdapterPosition()).getPoster();
      ResourceInfoActivity.startWithPairTransition(activity, id, poster, holder.getBinding().poster,
          holder.getBinding().title);
    });
    holder.getBinding().executePendingBindings();
  }
}
