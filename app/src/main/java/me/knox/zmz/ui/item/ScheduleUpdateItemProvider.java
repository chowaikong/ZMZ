package me.knox.zmz.ui.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import me.drakeet.multitype.ItemViewBinder;
import me.knox.zmz.databinding.LayoutHorizontalRvBinding;
import me.knox.zmz.entity.ScheduleUpdateList;
import me.knox.zmz.ui.adapter.ScheduleUpdateAdapter;

/**
 * Created by KNOX.
 */

public class ScheduleUpdateItemProvider extends
    ItemViewBinder<ScheduleUpdateList, ScheduleUpdateItemProvider.ViewHolder> {

  @NonNull @Override protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    return new ViewHolder(LayoutHorizontalRvBinding.inflate(inflater, parent, false));
  }

  @Override protected void onBindViewHolder(@NonNull ViewHolder holder,
      @NonNull ScheduleUpdateList scheduleUpdateList) {
    holder.mAdapter.setData(scheduleUpdateList.getList());
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    private ScheduleUpdateAdapter mAdapter;

    ViewHolder(LayoutHorizontalRvBinding binding) {
      super(binding.getRoot());
      mAdapter = new ScheduleUpdateAdapter(new ArrayList<>());
      LinearSnapHelper snapHelper = new LinearSnapHelper();
      snapHelper.attachToRecyclerView(binding.rvHorizontal);
      binding.rvHorizontal.setAdapter(mAdapter);
    }
  }
}
