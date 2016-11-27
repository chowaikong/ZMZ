package me.knox.zmz.contract;

import android.content.Context;
import io.reactivex.Flowable;
import java.util.List;
import me.knox.zmz.entity.Hot;
import me.knox.zmz.network.JsonResponse;

/**
 * Created by KNOX on 2016-11-27.
 */

public interface HotListContract {


  interface Model {
    Flowable<JsonResponse<List<Hot>>> getHot();
  }

  interface View {
    void obtainHotListSuccess(List<Hot> hotList);

    void obtainHotListFailed(String error);

    Context getContext();

  }

  interface Presenter {
    void getHot();
  }
}
