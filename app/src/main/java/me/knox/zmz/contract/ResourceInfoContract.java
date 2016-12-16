package me.knox.zmz.contract;

import io.reactivex.Flowable;
import me.knox.zmz.entity.ResourceInfo;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public interface ResourceInfoContract {

  interface Model {
    Flowable<JsonResponse<ResourceInfo>> getResourceInfo(int id);
  }

  interface View {
    void obtainResourceInfoSucceed(ResourceInfo resourceInfo);

    void obtainResourceInfoFailed(String error);
  }
  interface Presenter {
    void getResourceInfo(int id);
  }
}
