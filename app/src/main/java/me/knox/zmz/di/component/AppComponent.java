package me.knox.zmz.di.component;

import dagger.Component;
import javax.inject.Singleton;
import me.knox.zmz.App;
import me.knox.zmz.di.module.AppModule;
import me.knox.zmz.di.module.NetworkModule;

/**
 * Created by KNOX.
 */

@Singleton
@Component(modules = { AppModule.class, NetworkModule.class })
public interface AppComponent {
  void inject(App app);
}
