package me.knox.zmz.view;

/**
 * Created by KNOX.
 */

public interface BaseView {

  /**
   * deal with error(s)
   * @param objects info from remote server, throwable etc...
   */
  void error(Object... objects);

}
