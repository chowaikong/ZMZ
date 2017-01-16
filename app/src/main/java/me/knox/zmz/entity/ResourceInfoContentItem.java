package me.knox.zmz.entity;

import com.genius.groupie.Item;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutSingleTextCardViewBinding;

/**
 * Created by KNOX.
 */

public class ResourceInfoContentItem extends Item<LayoutSingleTextCardViewBinding> {

  private String mContent;

  public ResourceInfoContentItem(String content) {
    mContent = content;
  }

  @Override public int getLayout() {
    return R.layout.layout_single_text_card_view;
  }

  @Override public void bind(LayoutSingleTextCardViewBinding viewBinding, int position) {
    viewBinding.text.setText(mContent);
  }
}
