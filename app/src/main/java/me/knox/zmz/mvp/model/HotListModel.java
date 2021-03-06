package me.knox.zmz.mvp.model;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.util.List;
import javax.inject.Inject;
import me.knox.zmz.App;
import me.knox.zmz.mvp.contract.HotListContract;
import me.knox.zmz.entity.Hot;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public class HotListModel implements HotListContract.Model {

  @Inject
  public HotListModel() {
    // empty constructor for injection
  }

  @Override public Flowable<JsonResponse<List<Hot>>> getHot() {
    return App.getInstance().getApi().getHotList().observeOn(AndroidSchedulers.mainThread(), true);
  }
}
