package me.knox.zmz.mvp.model;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import me.knox.zmz.App;
import me.knox.zmz.mvp.contract.SubtitleListContract;
import me.knox.zmz.entity.SubtitleList;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public class SubtitleListModel implements SubtitleListContract.Model {

  @Inject
  public SubtitleListModel() {
    // empty constructor for injection
  }

  @Override public Flowable<JsonResponse<SubtitleList>> getSubtitleList() {
    return App.getInstance()
        .getApi()
        .getSubtitleList()
        .observeOn(AndroidSchedulers.mainThread(), true);
  }
}
