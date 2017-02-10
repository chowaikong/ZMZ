package me.knox.zmz.ui.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by KNOX.
 */

public abstract class OnScrollingListener extends RecyclerView.OnScrollListener {

  private int mLastItemCount;

  public abstract void loadMore();

  public abstract void stateChanged(int state);

  @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    int itemCount, lastPosition;
    if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
      LinearLayoutManager linearLayoutManager =
          (LinearLayoutManager) recyclerView.getLayoutManager();
      itemCount = linearLayoutManager.getItemCount();
      lastPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
    } else {
      ZLog.e("The OnScrollingListener only support LinearLayoutManager");
      return;
    }

    if (mLastItemCount != itemCount && lastPosition == itemCount - 1) {
      mLastItemCount = itemCount;
      this.loadMore();
    }
  }

  @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
    super.onScrollStateChanged(recyclerView, newState);
    this.stateChanged(newState);
  }
}
