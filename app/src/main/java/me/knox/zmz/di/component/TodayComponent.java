package me.knox.zmz.di.component;

import dagger.Component;
import javax.inject.Singleton;
import me.knox.zmz.di.module.HotListModule;
import me.knox.zmz.di.module.NewsListModule;
import me.knox.zmz.ui.fragment.TodayFragment;

/**
 * Created by KNOX.
 */

@Singleton
@Component(modules = {HotListModule.class, NewsListModule.class})
public interface TodayComponent {
  void inject(TodayFragment fragment);
}
