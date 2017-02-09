package me.knox.zmz.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.knox.zmz.contract.SearchContract;
import me.knox.zmz.model.SearchModel;

/**
 * Created by KNOX.
 */

@Module(includes = AppModule.class)
public class SearchModule {

  private SearchContract.Model mModel;
  private SearchContract.View mView;

  public SearchModule(SearchContract.View view) {
    mView = view;
  }

  @Provides @Singleton SearchContract.Model providesSearchContractModel() {
    return new SearchModel();
  }

  @Provides @Singleton SearchContract.View providesSearchContractView() {
    return mView;
  }
}
