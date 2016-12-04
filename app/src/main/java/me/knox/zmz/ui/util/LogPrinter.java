package me.knox.zmz.ui.util;

import me.knox.zmz.BuildConfig;
import timber.log.Timber;

/**
 * Created by KNOX.
 */

public class LogPrinter {

  private static final String TAG = "LogPrinter";

  public static void init() {
    //LogConfiguration config = new LogConfiguration.Builder()
    //    .tag("LogPrinter")     // Specify TAG, default: "X-LOG"
    //    .t()                   // Enable thread info, disabled by default
    //    .b()                   // Enable border, disabled by default
    //    .build();
    //XLog.init(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE, config);
    //XLog.init(LogLevel.ALL);
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  public static void v(String msg) {
    //XLog.v(msg);
    Timber.v(msg, TAG);
  }

  public static void v(Object... objects) {
    //XLog.v(objects);
  }

  public static void i(String msg) {
    //XLog.i(msg);
    Timber.i(msg);
  }

  public static void i(Object... objects) {
    //XLog.i(objects);
  }

  public static void e(String msg) {
    //XLog.e(msg);
    Timber.e(msg);
  }

  public static void e(Object... objects) {
    //XLog.e(objects);
  }

  public static void json(String json) {
    //XLog.json(json);
    Timber.i(json);
  }
}
