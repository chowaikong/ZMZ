package me.knox.zmz.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.knox.zmz.mvp.contract.ResourcesContract;
import me.knox.zmz.mvp.model.ResourcesModel;

/**
 * Created by KNOX.
 */

@Module(includes = AppModule.class)
public class ResourcesModule {

  private ResourcesContract.View mView;

  public ResourcesModule(ResourcesContract.View view) {
    mView = view;
  }

  @Provides @Singleton ResourcesContract.Model providesResourcesContractModel() {
    return new ResourcesModel();
  }

  @Provides @Singleton ResourcesContract.View providesResourcesContractView() {
    return mView;
  }
}
