package me.knox.zmz.mvp.contract;

import io.reactivex.Flowable;
import java.util.List;
import me.knox.zmz.entity.Resource;
import me.knox.zmz.network.JsonResponse;
import me.knox.zmz.view.BaseView;

/**
 * Created by KNOX.
 */

public interface ResourcesContract {

  @FunctionalInterface
  interface Model {
    Flowable<JsonResponse<Resource>> getResources();
  }

  interface View extends BaseView {
    void obtainResourcesSucceed(List<Resource.Data> resources);
  }

  @FunctionalInterface
  interface Presenter {
    void getResources();
  }
}
