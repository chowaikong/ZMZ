package me.knox.zmz.ui.util;

import android.annotation.SuppressLint;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;
import me.knox.zmz.App;

/**
 * Created by KNOX.
 *
 * This is for just showing the last toast in the user interface,
 * prevent showing too many toasts at a time during application running.
 *
 */

public class Toaster {

  private static Toast sToast;

  @SuppressLint("ShowToast")
  public static void init() {
    sToast = Toast.makeText(App.getInstance(), null, Toast.LENGTH_SHORT);
  }

  public static void show(CharSequence text) {
    if (sToast != null && !TextUtils.isEmpty(text)) {
      sToast.setText(text);
      sToast.show();
    }
  }

  public static void show(@StringRes int stringId) {
    if (sToast != null && stringId > 0) {
      sToast.setText(stringId);
      sToast.show();
    }
  }
}
