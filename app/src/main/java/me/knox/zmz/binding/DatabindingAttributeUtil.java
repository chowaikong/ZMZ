package me.knox.zmz.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import me.knox.zmz.ui.util.ImageLoader;

/**
 * Created by KNOX.
 */

public class DatabindingAttributeUtil {

  @BindingAdapter("img")
  public static void displayImage(ImageView imageView, String url) {
    ImageLoader.displayImage(imageView, url);
  }
}
