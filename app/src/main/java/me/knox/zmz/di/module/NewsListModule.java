package me.knox.zmz.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.knox.zmz.mvp.contract.NewsListContract;
import me.knox.zmz.mvp.model.NewsListModel;

/**
 * Created by KNOX.
 */

@Module
public class NewsListModule {
  private NewsListContract.View mView;

  public NewsListModule(NewsListContract.View view) {
    mView = view;
  }

  @Singleton @Provides NewsListContract.View providesNewsListContractView() {
    return mView;
  }

  @Singleton @Provides NewsListContract.Model providesNewsListContractModel() {
    return new NewsListModel();
  }
}
