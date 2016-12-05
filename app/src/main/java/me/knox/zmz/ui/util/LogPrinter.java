package me.knox.zmz.ui.util;

import com.socks.library.KLog;
import me.knox.zmz.BuildConfig;

/**
 * Created by KNOX.
 */

public class LogPrinter {

  private static final String TAG = "LogPrinter";

  public static void init() {
    KLog.init(BuildConfig.DEBUG, TAG);
  }

  public static void v(String msg) {
    KLog.v(msg);
  }

  public static void v(Object... objects) {
    KLog.v(objects);
  }

  public static void i(String msg) {
    KLog.i(msg);
  }

  public static void i(Object... objects) {
    KLog.i(objects);
  }

  public static void e(String msg) {
    KLog.e(msg);
  }

  public static void e(Object... objects) {
    KLog.e(objects);
  }

  public static void json(String json) {
    KLog.json(json);
  }
}
