package me.knox.zmz.di.component;

import dagger.Component;
import javax.inject.Singleton;
import me.knox.zmz.di.module.SearchModule;
import me.knox.zmz.ui.activity.SearchActivity;

/**
 * Created by KNOX.
 */

@Singleton
@Component(modules = SearchModule.class)
public interface SearchComponent {
  void inject(SearchActivity activity);
}
