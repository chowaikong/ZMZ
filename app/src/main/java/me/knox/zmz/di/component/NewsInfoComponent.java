package me.knox.zmz.di.component;

import dagger.Component;
import javax.inject.Singleton;
import me.knox.zmz.di.module.NewsInfoModule;
import me.knox.zmz.ui.activity.NewsInfoActivity;

/**
 * Created by KNOX.
 */

@Singleton
@Component(modules = NewsInfoModule.class)
public interface NewsInfoComponent {
  void inject(NewsInfoActivity newsInfoActivity);
}
