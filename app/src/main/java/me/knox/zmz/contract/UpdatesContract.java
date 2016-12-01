package me.knox.zmz.contract;

import io.reactivex.Flowable;
import java.util.List;
import me.knox.zmz.entity.Update;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public interface UpdatesContract {

  interface Model {
    Flowable<JsonResponse<List<Update>>> getUpdates();
  }

  interface View {
    void obtainUpdatesSucceed(List<Update> updates);

    void obtainUpdatesFailed(String error);
  }

  interface Presenter {
    void getUpdates();
  }
}
