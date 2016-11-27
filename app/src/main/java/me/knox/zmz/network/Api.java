package me.knox.zmz.network;

import io.reactivex.Flowable;
import java.util.List;
import me.knox.zmz.entity.Hot;
import retrofit2.http.GET;

/**
 * Created by KNOX on 2016-11-27.
 */

public interface Api {

  /**
   * 今日热门
   */
  @GET("resource/top")
  Flowable<JsonResponse<List<Hot>>> getHotList();
}
