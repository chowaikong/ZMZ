package me.knox.zmz.di.module;

import android.content.Context;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import javax.inject.Named;
import me.knox.zmz.BuildConfig;
import me.knox.zmz.misc.Constants;
import me.knox.zmz.network.Api;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.internal.Util;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

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

  @Provides Retrofit provideRetrofit(HttpUrl baseUrl, @Named("OkHttp") OkHttpClient client,
      Gson gson) {
    return new Retrofit.Builder().client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build();
  }

  @Provides @Named("OkHttp") OkHttpClient provideOkHttp(Context app,
      HttpLoggingInterceptor httpLoggingInterceptor, Interceptor interceptor) {
    File cacheDir = new File(app.getCacheDir(), "http");
    Cache cache = new Cache(cacheDir, 1024 * 1024 * 50);
    return new OkHttpClient.Builder().cache(cache)
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
          Util.md5Hex(BuildConfig.CID + "$$" + BuildConfig.ACCESS_KEY + "&&" + timestamp);
      HttpUrl.Builder newBuilder = httpUrl.newBuilder()
          .addQueryParameter(Constants.CID, BuildConfig.CID)
          .addQueryParameter(Constants.ACCESS_KEY, accessKey)
          .addQueryParameter(Constants.TIME_STAMP, timestamp)
          .addQueryParameter(Constants.CLIENT, "2");
      return chain.proceed(builder.url(newBuilder.build()).build());
    };
  }

  @Provides HttpLoggingInterceptor provideLoggingInterceptor() {
    HttpLoggingInterceptor loggingInterceptor =
        new HttpLoggingInterceptor(message -> Timber.d(message));
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return loggingInterceptor;
  }
}
