package me.knox.zmz.ui.widget;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import me.knox.zmz.ui.util.LogPrinter;

/**
 * Created by KNOX.
 */

public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {

  private int mLastItemCount;

  public abstract void loadMore();

  @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    int itemCount, lastPosition;
    if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
      LinearLayoutManager linearLayoutManager =
          (LinearLayoutManager) recyclerView.getLayoutManager();
      itemCount = linearLayoutManager.getItemCount();
      lastPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
    } else {
      LogPrinter.e("The OnLoadMoreListener only support LinearLayoutManager");
      return;
    }

    if (mLastItemCount != itemCount && lastPosition == itemCount - 1) {
      mLastItemCount = itemCount;
      this.loadMore();
    }
  }
}
