package me.knox.zmz.di.component;

import dagger.Component;
import javax.inject.Singleton;
import me.knox.zmz.di.module.ResourcesModule;
import me.knox.zmz.ui.fragment.ResourcesFragment;

/**
 * Created by KNOX.
 */

@Singleton
@Component(modules = ResourcesModule.class)
public interface ResourcesComponent {
  void inject(ResourcesFragment fragment);
}
