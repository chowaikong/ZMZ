package me.knox.zmz.entity;

import com.genius.groupie.Item;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutVerticalRvBinding;
import me.knox.zmz.ui.adapter.ResourcesAdapter;
import me.knox.zmz.ui.widget.VerticalSpaceItemDecoration;

/**
 * Created by KNOX.
 */

public class ResourcesItem extends Item<LayoutVerticalRvBinding> {

  private ResourcesAdapter mResourcesAdapter;

  public ResourcesItem(ResourcesAdapter resourcesAdapter) {
    mResourcesAdapter = resourcesAdapter;
  }

  @Override public int getLayout() {
    return R.layout.layout_vertical_rv;
  }

  @Override public void bind(LayoutVerticalRvBinding viewBinding, int position) {
    viewBinding.rvVertical.addItemDecoration(new VerticalSpaceItemDecoration(30));
    viewBinding.rvVertical.setAdapter(mResourcesAdapter);
  }
}
