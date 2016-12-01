package me.knox.zmz.ui.util;

import android.support.annotation.StringRes;
import android.widget.Toast;
import me.knox.zmz.App;

/**
 * Created by KNOX.
 */

public class Toaster {

  public static void showShortToast(CharSequence text) {
    Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT).show();
  }

  public static void showLongToast(CharSequence text) {
    Toast.makeText(App.getInstance(), text, Toast.LENGTH_LONG).show();
  }

  public static void showShortToast(@StringRes int stringId) {
    Toast.makeText(App.getInstance(), stringId, Toast.LENGTH_SHORT).show();
  }

  public static void showLongToast(@StringRes int stringId) {
    Toast.makeText(App.getInstance(), stringId, Toast.LENGTH_LONG).show();
  }
}
