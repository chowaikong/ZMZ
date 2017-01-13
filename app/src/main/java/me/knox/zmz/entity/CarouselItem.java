package me.knox.zmz.entity;

import android.support.v7.widget.LinearSnapHelper;
import com.genius.groupie.Item;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutHorizontalRvBinding;
import me.knox.zmz.ui.adapter.ScheduleUpdateAdapter;

/**
 * Created by KNOX.
 */

public class CarouselItem extends Item<LayoutHorizontalRvBinding> {

  private ScheduleUpdateAdapter mUpdatesAdapter;

  public CarouselItem(me.knox.zmz.ui.adapter.ScheduleUpdateAdapter updatesAdapter) {
    mUpdatesAdapter = updatesAdapter;
  }

  @Override public int getLayout() {
    return R.layout.layout_horizontal_rv;
  }

  @Override public void bind(LayoutHorizontalRvBinding viewBinding, int position) {
    viewBinding.rvHorizontal.setAdapter(mUpdatesAdapter);
    new LinearSnapHelper().attachToRecyclerView(viewBinding.rvHorizontal);
  }
}
