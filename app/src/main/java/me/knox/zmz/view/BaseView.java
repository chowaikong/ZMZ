package me.knox.zmz.view;

/**
 * Created by KNOX.
 */

@FunctionalInterface
public interface BaseView {

  /**
   * deal with error(s)
   * @param error info from remote server
   * @param objects throwable etc...
   */
  void error(String error, Object... objects);

}
