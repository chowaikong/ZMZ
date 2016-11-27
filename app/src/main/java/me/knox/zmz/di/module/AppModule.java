package me.knox.zmz.di.module;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;

/**
 * Created by KNOX on 2016-11-27.
 */

@Module public class AppModule {

  private final Context mAppContext;

  public AppModule(@NonNull final Context context) {
    mAppContext = context.getApplicationContext();
  }

  @Provides public Context provideAppContext() {
    return mAppContext;
  }

  @Provides public Gson provideGson() {
    return new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
  }
}
