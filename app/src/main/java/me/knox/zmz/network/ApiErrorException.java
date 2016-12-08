package me.knox.zmz.network;

import io.reactivex.functions.Consumer;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import me.knox.zmz.R;
import me.knox.zmz.ui.util.LogPrinter;
import me.knox.zmz.ui.util.Toaster;

/**
 * Created by KNOX on 2016-11-27.
 */

public class ApiErrorException implements Consumer<Throwable>{

  @Override public void accept(Throwable throwable) throws Exception {
    LogPrinter.e("ApiErrorException", throwable.toString());

    if (throwable instanceof UnknownHostException
        || throwable instanceof ConnectException
        || throwable instanceof SocketTimeoutException) {
      Toaster.showShortToast(R.string.network_error);
    } else {
      Toaster.showShortToast(R.string.something_wrong_happened);
    }
  }
}
