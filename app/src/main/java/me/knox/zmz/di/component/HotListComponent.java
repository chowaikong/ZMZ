package me.knox.zmz.di.component;

import dagger.Component;
import javax.inject.Singleton;
import me.knox.zmz.di.module.HotListModule;
import me.knox.zmz.ui.activity.MainActivity;

/**
 * Created by KNOX.
 */

@Singleton
@Component(modules = HotListModule.class)
public interface HotListComponent {

  void inject(MainActivity mainActivity);
}
