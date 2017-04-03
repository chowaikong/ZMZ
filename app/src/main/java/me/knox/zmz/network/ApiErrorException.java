package me.knox.zmz.network;

import android.util.Log;
import io.reactivex.functions.Consumer;
import java.io.IOException;
import java.net.SocketTimeoutException;
import me.knox.zmz.R;
import me.knox.zmz.ui.util.Toaster;
import me.knox.zmz.view.BaseView;

/**
 * Created by KNOX.
 */

public class ApiErrorException implements Consumer<Throwable>{

  private BaseView mBaseView;

  public ApiErrorException(BaseView baseView) {
    mBaseView = baseView;
  }

  @Override public void accept(Throwable throwable) throws Exception {
    Log.e("ApiErrorException", throwable.toString());

    if (mBaseView != null) {
      mBaseView.error(throwable.toString());
    }

    if (throwable instanceof SocketTimeoutException) {
      Toaster.show(R.string.server_error);
    }

    if (throwable instanceof IOException) {
      Toaster.show(R.string.something_wrong_happened);
    }
  }
}
