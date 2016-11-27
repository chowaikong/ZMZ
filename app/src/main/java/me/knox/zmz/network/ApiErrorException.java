package me.knox.zmz.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;
import io.reactivex.functions.Consumer;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import me.knox.zmz.R;

/**
 * Created by KNOX on 2016-11-27.
 */

public class ApiErrorException implements Consumer<Throwable>{

  private Context mContext;

  public ApiErrorException(@NonNull Context context) {
    mContext = context;
  }

  @Override public void accept(Throwable throwable) throws Exception {
    Log.e("ApiErrorException", throwable.toString());

    if (throwable instanceof UnknownHostException
        || throwable instanceof ConnectException
        || throwable instanceof SocketTimeoutException) {
      Toast.makeText(mContext, R.string.network_error, Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(mContext, R.string.something_wrong_happened, Toast.LENGTH_LONG).show();
    }
  }
}
