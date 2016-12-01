package me.knox.zmz.di.component;

import dagger.Component;
import javax.inject.Singleton;
import me.knox.zmz.di.module.UpdatesModule;
import me.knox.zmz.ui.fragment.UpdatesFragment;

/**
 * Created by KNOX.
 */

@Singleton
@Component(modules = UpdatesModule.class)
public interface UpdatesCpmponent {

  void inject(UpdatesFragment updatesFragment);
}
