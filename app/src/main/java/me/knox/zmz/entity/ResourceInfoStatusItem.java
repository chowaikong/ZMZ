package me.knox.zmz.entity;

import com.genius.groupie.Item;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutStatusCardViewBinding;

/**
 * Created by KNOX.
 */

public class ResourceInfoStatusItem extends Item<LayoutStatusCardViewBinding> {

  private ResourceInfo mResourceInfo;

  public ResourceInfoStatusItem(ResourceInfo resourceInfo) {
    mResourceInfo = resourceInfo;
  }

  @Override public int getLayout() {
    return R.layout.layout_status_card_view;
  }

  @Override public void bind(LayoutStatusCardViewBinding viewBinding, int position) {
    viewBinding.setInfo(mResourceInfo);
  }
}
