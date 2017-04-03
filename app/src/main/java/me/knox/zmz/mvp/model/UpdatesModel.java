package me.knox.zmz.mvp.model;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.List;
import javax.inject.Inject;
import me.knox.zmz.App;
import me.knox.zmz.mvp.contract.UpdatesContract;
import me.knox.zmz.entity.Update;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public class UpdatesModel implements UpdatesContract.Model {

  @Inject
  public UpdatesModel() {
  }

  @Override public Flowable<JsonResponse<List<Update>>> getUpdates() {
    return App.getInstance().getApi().getUpdates().observeOn(AndroidSchedulers.mainThread(), true);
  }
}
