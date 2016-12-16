package me.knox.zmz.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.knox.zmz.contract.ResourceInfoContract;
import me.knox.zmz.model.ResourceInfoModel;

/**
 * Created by KNOX.
 */

@Module(includes = AppModule.class)
public class ResourceInfoModule {
  private ResourceInfoContract.View mView;

  public ResourceInfoModule(ResourceInfoContract.View view) {
    mView = view;
  }

  @Singleton @Provides ResourceInfoContract.View providesResourceInfoContractView() {
    return mView;
  }

  @Singleton @Provides ResourceInfoContract.Model providesResourceInfoContractModel() {
    return new ResourceInfoModel();
  }
}
