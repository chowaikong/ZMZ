package me.knox.zmz.entity;

import android.support.annotation.NonNull;
import com.genius.groupie.Item;
import me.knox.zmz.R;
import me.knox.zmz.databinding.ItemCategoryHeaderBinding;

/**
 * Created by KNOX.
 */

public class CategoryHeader extends Item<ItemCategoryHeaderBinding> {

  @NonNull private String mName;

  public CategoryHeader(@NonNull String mName) {
    this.mName = mName;
  }

  @Override public int getLayout() {
    return R.layout.item_category_header;
  }

  @Override public void bind(ItemCategoryHeaderBinding viewBinding, int position) {
    viewBinding.setCategoryHeader(mName);
    viewBinding.executePendingBindings();
  }
}
