package me.knox.zmz.mvp.model;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import me.knox.zmz.App;
import me.knox.zmz.mvp.contract.ResourcesContract;
import me.knox.zmz.entity.Resource;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public class ResourcesModel implements ResourcesContract.Model {

  @Inject public ResourcesModel() {
  }

  @Override public Flowable<JsonResponse<Resource>> getResources() {
    return App.getInstance()
        .getApi()
        .getResources()
        .observeOn(AndroidSchedulers.mainThread(), true);
  }
}
