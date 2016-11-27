package me.knox.zmz.di.component;

import dagger.Component;
import javax.inject.Singleton;
import me.knox.zmz.di.module.HotListModule;
import me.knox.zmz.ui.activity.MainActivity;

/**
 * Created by KNOX on 2016-11-27.
 */

@Singleton
@Component(modules = HotListModule.class)
public interface HotListComponent {

  void inject(MainActivity mainActivity);
}
