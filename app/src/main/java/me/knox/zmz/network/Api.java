package me.knox.zmz.network;

import io.reactivex.Flowable;
import java.util.List;
import java.util.Map;
import me.knox.zmz.entity.Hot;
import me.knox.zmz.entity.News;
import me.knox.zmz.entity.NewsInfo;
import me.knox.zmz.entity.Resource;
import me.knox.zmz.entity.ResourceInfo;
import me.knox.zmz.entity.ScheduleUpdate;
import me.knox.zmz.entity.Update;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by KNOX.
 */

public interface Api {

  @GET("resource/today") Flowable<JsonResponse<List<Update>>> getUpdates();

  @GET("resource/top") Flowable<JsonResponse<List<Hot>>> getHotList();

  @GET("tv/schedule") Flowable<JsonResponse<Map<String, List<ScheduleUpdate>>>> getScheduleUpdates(
      @Query("start") String start, @Query("end") String end);

  @GET("resource/fetchlist")
  Flowable<JsonResponse<Resource>> getResources(@Query("page") int page);

  @GET("article/fetchlist")
  Flowable<JsonResponse<List<News>>> getNews(@Query("page") int page);

  @GET("resource/getinfo")
  Flowable<JsonResponse<ResourceInfo>> getResourceInfo(@Query("id") int id, @Query("share") int isSharable);

  @GET("article/getinfo")
  Flowable<JsonResponse<NewsInfo>> getNewsInfo(@Query("id") int id);
}
