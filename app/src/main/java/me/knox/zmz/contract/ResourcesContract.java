package me.knox.zmz.contract;

import io.reactivex.Flowable;
import java.util.List;
import me.knox.zmz.entity.Resource;
import me.knox.zmz.network.JsonResponse;
import me.knox.zmz.view.BaseView;

/**
 * Created by KNOX.
 */

public interface ResourcesContract {

  interface Model {
    Flowable<JsonResponse<Resource>> getResources(int page);
  }

  interface View extends BaseView {
    void obtainResourcesSucceed(List<Resource.Data> resources);
  }

  interface Presenter {
    void getResources(int page);
  }
}
