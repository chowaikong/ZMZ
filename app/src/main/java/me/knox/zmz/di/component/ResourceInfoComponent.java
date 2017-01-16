package me.knox.zmz.di.component;

import dagger.Component;
import javax.inject.Singleton;
import me.knox.zmz.di.module.ResourceInfoModule;
import me.knox.zmz.ui.activity.ResourceInfoActivity;

/**
 * Created by KNOX.
 */

@Singleton
@Component(modules = ResourceInfoModule.class)
public interface ResourceInfoComponent {
  void inject(ResourceInfoActivity resourceInfoActivity);
}
