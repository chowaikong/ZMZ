package me.knox.zmz.misc;

import me.drakeet.multitype.GlobalMultiTypePool;
import me.knox.zmz.entity.Category;
import me.knox.zmz.ui.item.CategoryItemProvider;
import me.knox.zmz.ui.item.SingleTextItemProvider;

/**
 * Created by KNOX.
 */

public class MultiTypeInstaller {

  public static void install() {
    GlobalMultiTypePool.register(Category.class, new CategoryItemProvider());
    GlobalMultiTypePool.register(String.class, new SingleTextItemProvider());
  }
}
