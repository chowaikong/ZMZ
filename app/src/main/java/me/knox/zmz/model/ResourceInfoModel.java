package me.knox.zmz.model;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import me.knox.zmz.App;
import me.knox.zmz.contract.ResourceInfoContract;
import me.knox.zmz.entity.ResourceInfo;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public class ResourceInfoModel implements ResourceInfoContract.Model {

  @Inject
  public ResourceInfoModel() {
  }

  @Override public Flowable<JsonResponse<ResourceInfo>> getResourceInfo(int id) {
    return App.getInstance().getApi().getResourceInfo(id).observeOn(AndroidSchedulers.mainThread(), true);
  }
}
