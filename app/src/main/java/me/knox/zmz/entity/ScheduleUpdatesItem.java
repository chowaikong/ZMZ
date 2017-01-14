package me.knox.zmz.entity;

import com.genius.groupie.Item;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutHorizontalRvBinding;
import me.knox.zmz.ui.adapter.ScheduleUpdateAdapter;

/**
 * Created by KNOX.
 */

public class ScheduleUpdatesItem extends Item<LayoutHorizontalRvBinding> {

  private ScheduleUpdateAdapter mUpdatesAdapter;

  public ScheduleUpdatesItem(me.knox.zmz.ui.adapter.ScheduleUpdateAdapter updatesAdapter) {
    mUpdatesAdapter = updatesAdapter;
  }

  @Override public int getLayout() {
    return R.layout.layout_horizontal_rv;
  }

  @Override public void bind(LayoutHorizontalRvBinding viewBinding, int position) {
    viewBinding.rvHorizontal.setAdapter(mUpdatesAdapter);
  }
}
