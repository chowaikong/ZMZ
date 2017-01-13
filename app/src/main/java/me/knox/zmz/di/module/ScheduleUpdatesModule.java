package me.knox.zmz.di.module;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.knox.zmz.contract.ScheduleUpdatesContract;
import me.knox.zmz.model.ScheduleUpdatesModel;

/**
 * Created by KNOX.
 */

@Module
public class ScheduleUpdatesModule {

  private ScheduleUpdatesContract.View mView;

  public ScheduleUpdatesModule(ScheduleUpdatesContract.View view) {
    mView = view;
  }

  @Singleton @Provides ScheduleUpdatesContract.Model providesScheduleUpdatesContractModel() {
    return new ScheduleUpdatesModel();
  }

  @Singleton @Provides ScheduleUpdatesContract.View providesScheduleUpdatesContractView() {
    return mView;
  }
}
