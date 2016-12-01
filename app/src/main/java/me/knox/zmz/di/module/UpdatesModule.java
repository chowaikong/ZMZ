package me.knox.zmz.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.knox.zmz.contract.UpdatesContract;
import me.knox.zmz.model.UpdatesModel;

/**
 * Created by KNOX.
 */

@Module(includes = AppModule.class)
public class UpdatesModule {

  private UpdatesContract.View mView;

  public UpdatesModule(UpdatesContract.View view) {
    mView = view;
  }

  @Provides @Singleton UpdatesContract.Model providesUpdatesModel() {
    return new UpdatesModel();
  }

  @Provides @Singleton UpdatesContract.View providesUpdatesView() {
    return mView;
  }
}
