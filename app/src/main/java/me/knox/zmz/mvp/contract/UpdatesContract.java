package me.knox.zmz.mvp.contract;

import io.reactivex.Flowable;
import java.util.List;
import me.knox.zmz.entity.Update;
import me.knox.zmz.network.JsonResponse;
import me.knox.zmz.view.BaseView;

/**
 * Created by KNOX.
 */

public interface UpdatesContract {

  @FunctionalInterface
  interface Model {
    Flowable<JsonResponse<List<Update>>> getUpdates();
  }

  interface View extends BaseView {
    void obtainUpdatesSucceed(List<Update> updates);
  }

  @FunctionalInterface
  interface Presenter {
    void getUpdates();
  }
}
