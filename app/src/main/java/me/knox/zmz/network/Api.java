package me.knox.zmz.network;

import io.reactivex.Flowable;
import java.util.List;
import me.knox.zmz.entity.Hot;
import me.knox.zmz.entity.Update;
import retrofit2.http.GET;

/**
 * Created by KNOX.
 */

public interface Api {

  @GET("resource/today")
  Flowable<JsonResponse<List<Update>>> getUpdates();

  @GET("resource/top")
  Flowable<JsonResponse<List<Hot>>> getHotList();
}
