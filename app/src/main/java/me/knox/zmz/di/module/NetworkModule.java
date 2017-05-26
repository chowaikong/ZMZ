package me.knox.zmz.di.module;

import android.content.Context;
import android.util.Log;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import me.knox.zmz.BuildConfig;
import me.knox.zmz.misc.Constants;
import me.knox.zmz.network.Api;
import me.knox.zmz.network.ResponseTypeAdapterFactory;
import me.knox.zmz.ui.util.MD5Util;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KNOX.
 */

@Module(includes = AppModule.class) public class NetworkModule {

  @Provides Api provideApi(Retrofit retrofit) {
    return retrofit.create(Api.class);
  }

  @Provides HttpUrl provideBaseUrl() {
    return HttpUrl.parse(BuildConfig.HOST);
  }

  @Provides public Gson provideGson() {
    return new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .registerTypeAdapterFactory(new ResponseTypeAdapterFactory())
        .create();
  }

  @Provides Retrofit provideRetrofit(HttpUrl baseUrl, @Named("OkHttp") OkHttpClient client,
      Gson gson) {
    return new Retrofit.Builder().client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build();
  }

  @Provides @Named("OkHttp") OkHttpClient provideOkHttp(Context app,
      LoggingInterceptor httpLoggingInterceptor, Interceptor interceptor) {
    File cacheDir = new File(app.getCacheDir(), "http");
    Cache cache = new Cache(cacheDir, 1024 * 1024 * 50);
    return new OkHttpClient.Builder().cache(cache)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(interceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build();
  }

  @Provides Interceptor provideInterceptor() {
    return chain -> {
      okhttp3.Request.Builder builder = chain.request().newBuilder();
      HttpUrl httpUrl = chain.request().url();
      String timestamp = Long.toString(System.currentTimeMillis() / 1000);
      String accessKey =
          MD5Util.md5Hex(BuildConfig.CID + "$$" + BuildConfig.ACCESS_KEY + "&&" + timestamp);
      HttpUrl.Builder newBuilder = httpUrl.newBuilder()
          .addQueryParameter(Constants.CID, BuildConfig.CID)
          .addQueryParameter(Constants.ACCESS_KEY, accessKey)
          .addQueryParameter(Constants.TIME_STAMP, timestamp)
          .addQueryParameter(Constants.CLIENT, "2");
      return chain.proceed(builder.url(newBuilder.build()).build());
    };
  }

  @Provides LoggingInterceptor provideLoggingInterceptor() {
    return new LoggingInterceptor.Builder()
        .loggable(BuildConfig.DEBUG)
        .setLevel(Level.BODY)
        .log(Log.INFO)
        .request("Request")
        .response("Response")
        .addHeader("version", BuildConfig.VERSION_NAME)
        .build();
  }
}
