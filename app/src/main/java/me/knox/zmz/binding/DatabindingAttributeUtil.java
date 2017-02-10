package me.knox.zmz.binding;

import android.databinding.BindingAdapter;
import android.os.Build;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import me.knox.zmz.ui.util.GlideImageGetter;
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

  @BindingAdapter("htmlText")
  public static void displayHtmlText(TextView textView, String html) {
    if (html == null) return;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      textView.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY, new GlideImageGetter(textView.getContext(), textView), null));
    } else {
      textView.setText(Html.fromHtml(html));
    }
  }

  @BindingAdapter("type")
  public static void transformType(TextView textView, String type) {
    switch (type) {
      case "resource":
        textView.setText("影视资源");
        break;
      case "subtitle":
        textView.setText("字幕");
        break;
      case "article":
        textView.setText("资讯");
        break;
      default:
        break;
    }
  }

  @BindingAdapter("channel")
  public static void transfromChannel(TextView textView, String channel) {
    switch (channel) {
      case "tv" :
        textView.setText("电视剧");
        break;
      case "movie" :
        textView.setText("电影");
        break;
      default:
        break;
    }
  }
}
