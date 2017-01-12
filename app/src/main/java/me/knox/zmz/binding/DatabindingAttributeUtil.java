package me.knox.zmz.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import me.knox.zmz.ui.util.ImageLoader;
import me.knox.zmz.ui.util.TimeUtil;

/**
 * Created by KNOX.
 */

public class DatabindingAttributeUtil {

  @BindingAdapter("img")
  public static void displayImage(ImageView imageView, String url) {
    ImageLoader.displayImage(imageView, url);
  }

  @BindingAdapter("type")
  public static void showType(TextView textView, String type) {
    switch (type) {
      case "t_review":
        textView.setText("剧评");
        break;
      case "news":
        textView.setText("新闻");
        break;
      case "m_review":
        textView.setText("影评");
        break;
      case "guide":
        textView.setText("导视");
        break;
      case "recom":
        textView.setText("片单推荐");
        break;
      case "new_review":
        textView.setText("新剧评测");
        break;
      default:
        break;
    }
  }

  @BindingAdapter("date")
  public static void displayTime(TextView textView, long time) {
    textView.setText(TimeUtil.convert2Date(time * 1000));
  }

  @BindingAdapter("minute")
  public static void displayMinute(TextView textView, long time) {
    textView.setText(TimeUtil.convert2Minute(time * 1000));
  }
}
