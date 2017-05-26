package me.knox.zmz.mvp.contract;

import io.reactivex.Flowable;
import java.util.List;
import me.knox.zmz.entity.Hot;
import me.knox.zmz.network.JsonResponse;
import me.knox.zmz.view.BaseView;

/**
 * Created by KNOX.
 */

public interface HotListContract {

  @FunctionalInterface
  interface Model {
    Flowable<JsonResponse<List<Hot>>> getHot();
  }

  interface View extends BaseView{
    void obtainHotListSuccess(List<Hot> hotList);
  }

  @FunctionalInterface
  interface Presenter {
    void getHot();
  }
}
