package me.knox.zmz.entity;

import com.genius.groupie.Item;
import java.util.List;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutHorizontalRvBinding;
import me.knox.zmz.ui.activity.ResourceInfoActivity;
import me.knox.zmz.ui.adapter.ScheduleUpdateAdapter;
import me.knox.zmz.ui.util.RecyclerItemClickListener;

/**
 * Created by KNOX.
 */

public class ScheduleUpdatesItem extends Item<LayoutHorizontalRvBinding> {

  private ScheduleUpdateAdapter mUpdatesAdapter;
  private List<ScheduleUpdate> mScheduleUpdates;

  public ScheduleUpdatesItem(ScheduleUpdateAdapter updatesAdapter) {
    mUpdatesAdapter = updatesAdapter;
  }

  @Override public int getLayout() {
    return R.layout.layout_horizontal_rv;
  }

  @Override public void bind(LayoutHorizontalRvBinding viewBinding, int position) {
    viewBinding.rvHorizontal.setAdapter(mUpdatesAdapter);

    if (mScheduleUpdates != null) {
      viewBinding.rvHorizontal.addOnItemTouchListener(new RecyclerItemClickListener(
          viewBinding.getRoot().getContext(),
          (view, index) -> {
            int id = mScheduleUpdates.get(index).getId();
            String poster = mScheduleUpdates.get(index).getPoster();
            ResourceInfoActivity.start(view.getContext(), id, poster);
          }));
    }
  }

  public void setScheduleUpdates(List<ScheduleUpdate> scheduleUpdates) {
    mScheduleUpdates = scheduleUpdates;
  }
}
