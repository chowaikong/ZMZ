package me.knox.zmz.entity;

import com.genius.groupie.Item;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutVerticalRvBinding;
import me.knox.zmz.ui.adapter.HotListAdapter;

/**
 * Created by KNOX.
 */

public class HotListItem extends Item<LayoutVerticalRvBinding> {

  private HotListAdapter mHotListAdapter;

  public HotListItem(HotListAdapter hotListAdapter) {
    mHotListAdapter = hotListAdapter;
  }

  @Override public int getLayout() {
    return R.layout.layout_vertical_rv;
  }

  @Override public void bind(LayoutVerticalRvBinding viewBinding, int position) {
    viewBinding.rvVertical.setAdapter(mHotListAdapter);
    viewBinding.executePendingBindings();
  }
}
