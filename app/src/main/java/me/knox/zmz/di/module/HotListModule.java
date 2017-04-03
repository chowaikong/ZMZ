package me.knox.zmz.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.knox.zmz.mvp.contract.HotListContract;
import me.knox.zmz.mvp.model.HotListModel;

/**
 * Created by KNOX on 2016-11-27.
 */

@Module
public class HotListModule {

  private HotListContract.View mView;

  public HotListModule(HotListContract.View view) {
    mView = view;
  }

  @Provides @Singleton HotListContract.Model providesHotListModel() {
    return new HotListModel();
  }

  @Provides @Singleton HotListContract.View providesHotListView() {
    return mView;
  }
}
