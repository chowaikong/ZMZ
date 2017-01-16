package me.knox.zmz.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.knox.zmz.contract.NewsInfoContract;
import me.knox.zmz.model.NewsInfoModel;

/**
 * Created by KNOX.
 */

@Module(includes = AppModule.class)
public class NewsInfoModule {

  private NewsInfoContract.View mView;

  public NewsInfoModule(NewsInfoContract.View view) {
    mView = view;
  }

  @Provides @Singleton NewsInfoContract.View providesNewsInfoContractView() {
    return mView;
  }

  @Provides @Singleton NewsInfoContract.Model providesNewsInfoContractModel() {
    return new NewsInfoModel();
  }
}
