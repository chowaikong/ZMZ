package me.knox.zmz.network;

import io.reactivex.functions.Consumer;
import java.io.IOException;
import me.knox.zmz.R;
import me.knox.zmz.ui.util.LogPrinter;
import me.knox.zmz.ui.util.Toaster;
import me.knox.zmz.view.BaseView;

/**
 * Created by KNOX on 2016-11-27.
 */

public class ApiErrorException implements Consumer<Throwable>{

  private BaseView mBaseView;

  public ApiErrorException(BaseView baseView) {
    mBaseView = baseView;
  }

  @Override public void accept(Throwable throwable) throws Exception {
    LogPrinter.e("ApiErrorException", throwable.toString());

    if (mBaseView != null) {
      mBaseView.error(throwable.toString());
    }

    if (throwable instanceof IOException) {
      Toaster.showShortToast(R.string.something_wrong_happened);
    }
  }
}
