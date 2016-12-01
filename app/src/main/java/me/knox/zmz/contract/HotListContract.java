package me.knox.zmz.contract;

import io.reactivex.Flowable;
import java.util.List;
import me.knox.zmz.entity.Hot;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX.
 */

public interface HotListContract {


  interface Model {
    Flowable<JsonResponse<List<Hot>>> getHot();
  }

  interface View {
    void obtainHotListSuccess(List<Hot> hotList);

    void obtainHotListFailed(String error);

  }

  interface Presenter {
    void getHot();
  }
}
