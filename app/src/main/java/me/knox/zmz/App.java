package me.knox.zmz;

import android.app.Application;
import javax.inject.Inject;
import me.knox.zmz.di.component.DaggerAppComponent;
import me.knox.zmz.di.module.AppModule;
import me.knox.zmz.network.Api;
import timber.log.Timber;

/**
 * Created by KNOX on 2016-11-27.
 */

public class App extends Application {

  private static App instance;
  @Inject Api mApi;

  @Override public void onCreate() {
    super.onCreate();

    instance = this;

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }

    DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);

  }

  public static App getInstance() {
    return instance;
  }

  public Api getApi() {
    return mApi;
  }
}
