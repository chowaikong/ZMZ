package me.knox.zmz.contract;

import io.reactivex.Flowable;
import java.util.List;
import me.knox.zmz.entity.Resource;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public interface ResourcesContract {

  interface Model {
    Flowable<JsonResponse<Resource>> getResources(int page);
  }

  interface View {
    void obtainResourcesSucceed(List<Resource.Data> resources);

    void obtainResourcesFailed(String error);
  }

  interface Presenter {
    void getResources(int page);
  }
}
