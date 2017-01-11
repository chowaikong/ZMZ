package me.knox.zmz;

import android.app.Application;
import javax.inject.Inject;
import me.knox.zmz.di.component.DaggerAppComponent;
import me.knox.zmz.di.module.AppModule;
import me.knox.zmz.network.Api;
import me.knox.zmz.ui.util.ZLog;
import me.knox.zmz.ui.util.Toaster;
import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by KNOX.
 */

public class App extends Application {

  private static App instance;
  @Inject Api mApi;

  @Override public void onCreate() {
    super.onCreate();

    instance = this;

    JodaTimeAndroid.init(this);

    ZLog.init();

    Toaster.init();

    DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);

  }

  public static App getInstance() {
    return instance;
  }

  public Api getApi() {
    return mApi;
  }
}
