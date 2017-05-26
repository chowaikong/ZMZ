package me.knox.zmz.mvp.contract;

import io.reactivex.Flowable;
import me.knox.zmz.entity.SubtitleList;
import me.knox.zmz.network.JsonResponse;
import me.knox.zmz.view.BaseView;

/**
 * Created by KNOX.
 */

public interface SubtitleListContract {

  @FunctionalInterface
  interface Model {
    Flowable<JsonResponse<SubtitleList>> getSubtitleList();
  }

  interface View extends BaseView{
    void obtainSubtitleListSuccess(SubtitleList subtitleList);
  }

  @FunctionalInterface
  interface Presenter {
    void getSubtitleList();
  }
}
