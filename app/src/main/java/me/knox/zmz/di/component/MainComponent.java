package me.knox.zmz.di.component;

import dagger.Component;
import javax.inject.Singleton;
import me.knox.zmz.di.module.AppModule;
import me.knox.zmz.di.module.HotListModule;
import me.knox.zmz.di.module.NewsListModule;
import me.knox.zmz.di.module.ResourcesModule;
import me.knox.zmz.di.module.ScheduleUpdatesModule;
import me.knox.zmz.di.module.SubtitleListModule;
import me.knox.zmz.di.module.UpdatesModule;
import me.knox.zmz.ui.activity.MainActivity;

/**
 * Created by KNOX.
 */

@Singleton
@Component(modules = { AppModule.class, HotListModule.class, ScheduleUpdatesModule.class,
    NewsListModule.class, UpdatesModule.class, ResourcesModule.class, SubtitleListModule.class})
public interface MainComponent {
  void inject(MainActivity mainActivity);
}
