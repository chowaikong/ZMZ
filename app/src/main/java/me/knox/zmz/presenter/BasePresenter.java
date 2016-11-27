package me.knox.zmz.presenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by KNOX on 2016-11-27.
 */

public class BasePresenter {
  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  public Disposable getCompositeDisposable() {
    return mCompositeDisposable;
  }

  /**
   * 统计订阅
   *
   * @param disposable 订阅事件
   */
  public void addDisposable(Disposable disposable) {
    if (mCompositeDisposable != null) {
      mCompositeDisposable.add(disposable);
    }
  }

  /**
   * 取消订阅
   */
  public void dispose() {
    if (mCompositeDisposable != null && mCompositeDisposable.isDisposed()) {
      mCompositeDisposable.clear();
    }
  }
}
