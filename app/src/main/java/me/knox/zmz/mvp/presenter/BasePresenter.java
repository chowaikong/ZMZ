package me.knox.zmz.mvp.presenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by KNOX.
 */

public class BasePresenter {
  private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  public Disposable getCompositeDisposable() {
    return mCompositeDisposable;
  }

  /**
   * add disposable to CompositeDisposable
   */
  public void addDisposable(Disposable disposable) {
    mCompositeDisposable.add(disposable);
  }

  /**
   * clear all disposables that added to CompositeDisposable
   */
  public void dispose() {
    if (mCompositeDisposable.isDisposed()) {
      mCompositeDisposable.clear();
    }
  }
}
