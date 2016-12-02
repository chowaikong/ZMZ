package me.knox.zmz.di.component;

import dagger.Component;
import javax.inject.Singleton;
import me.knox.zmz.di.module.ScheduleUpdatesModule;
import me.knox.zmz.ui.fragment.TodayFragment;

/**
 * Created by KNOX.
 */

@Singleton
@Component(modules = ScheduleUpdatesModule.class)
public interface ScheduleUpdatesComponent {
  void inject(TodayFragment fragment);
}
