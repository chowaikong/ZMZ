package me.knox.zmz.ui.util;

import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * Created by KNOX.
 */

public class ImageLoader {

  public static void displayImage(ImageView view, String url) {
    Glide.with(view.getContext()).load(url).crossFade().into(view);
  }
}
