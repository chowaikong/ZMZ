package me.knox.zmz.model;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import me.knox.zmz.App;
import me.knox.zmz.contract.ResourcesContract;
import me.knox.zmz.entity.Resource;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public class ResourcesModel implements ResourcesContract.Model {

  @Inject public ResourcesModel() {
  }

  @Override public Flowable<JsonResponse<Resource>> getResources(int page) {
    return App.getInstance()
        .getApi()
        .getResources(page)
        .observeOn(AndroidSchedulers.mainThread(), true);
  }
}
