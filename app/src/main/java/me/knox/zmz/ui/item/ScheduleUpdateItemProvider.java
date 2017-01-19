package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import me.drakeet.multitype.ItemViewProvider;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.LayoutHorizontalRvBinding;
import me.knox.zmz.entity.ScheduleUpdateList;
import me.knox.zmz.ui.activity.ResourceInfoActivity;
import me.knox.zmz.ui.adapter.ScheduleUpdateAdapter;
import me.knox.zmz.ui.util.RecyclerItemClickListener;

/**
 * Created by KNOX.
 */

public class ScheduleUpdateItemProvider extends ItemViewProvider<ScheduleUpdateList, DataBindingViewHolder<LayoutHorizontalRvBinding>> {

  @NonNull @Override protected DataBindingViewHolder<LayoutHorizontalRvBinding> onCreateViewHolder(
      @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(LayoutHorizontalRvBinding.inflate(inflater, parent, false));
  }

  @Override
  protected void onBindViewHolder(@NonNull DataBindingViewHolder<LayoutHorizontalRvBinding> holder,
      @NonNull ScheduleUpdateList scheduleUpdateList) {
    ScheduleUpdateAdapter adapter = new ScheduleUpdateAdapter(new ArrayList<>());
    adapter.setData(scheduleUpdateList.getList());
    holder.getBinding().rvHorizontal.setAdapter(adapter);
    holder.getBinding().rvHorizontal.addOnItemTouchListener(new RecyclerItemClickListener(
        holder.getBinding().getRoot().getContext(), (view, position) -> {
          int id = scheduleUpdateList.getList().get(position).getId();
          String poster = scheduleUpdateList.getList().get(position).getPoster();
          ResourceInfoActivity.start(view.getContext(), id, poster);
        }));
    holder.getBinding().executePendingBindings();
  }
}
