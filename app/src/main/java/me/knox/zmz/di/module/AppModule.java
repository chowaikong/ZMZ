package me.knox.zmz.di.module;

import android.content.Context;
import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

/**
 * Created by KNOX.
 */

@Module public class AppModule {

  private final Context mAppContext;

  public AppModule(@NonNull final Context context) {
    mAppContext = context.getApplicationContext();
  }

  @Provides public Context provideAppContext() {
    return mAppContext;
  }
}
