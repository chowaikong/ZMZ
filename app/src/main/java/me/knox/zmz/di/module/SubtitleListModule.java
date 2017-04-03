package me.knox.zmz.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.knox.zmz.mvp.contract.SubtitleListContract;
import me.knox.zmz.mvp.model.SubtitleListModel;

/**
 * Created by KNOX.
 */

@Module(includes = AppModule.class)
public class SubtitleListModule {

  private SubtitleListContract.View mView;

  public SubtitleListModule(SubtitleListContract.View view) {
    mView = view;
  }

  @Provides @Singleton SubtitleListContract.Model providesSubtitleListContractModel() {
    return new SubtitleListModel();
  }

  @Provides @Singleton SubtitleListContract.View providesSubtitleListContractView() {
    return mView;
  }
}
