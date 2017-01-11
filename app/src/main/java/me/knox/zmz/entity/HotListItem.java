package me.knox.zmz.entity;

import com.genius.groupie.Item;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutHorizontalRvBinding;
import me.knox.zmz.ui.adapter.HotListAdapter;

/**
 * Created by KNOX.
 */

public class HotListItem extends Item<LayoutHorizontalRvBinding> {

  private HotListAdapter mHotListAdapter;

  public HotListItem(HotListAdapter hotListAdapter) {
    mHotListAdapter = hotListAdapter;
  }

  @Override public int getLayout() {
    return R.layout.layout_horizontal_rv;
  }

  @Override public void bind(LayoutHorizontalRvBinding viewBinding, int position) {
    viewBinding.rvHorizontal.setAdapter(mHotListAdapter);
    viewBinding.executePendingBindings();
  }
}
