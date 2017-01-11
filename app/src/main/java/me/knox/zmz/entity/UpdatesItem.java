package me.knox.zmz.entity;

import android.support.v7.widget.DividerItemDecoration;
import com.genius.groupie.Item;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutVerticalRvBinding;
import me.knox.zmz.ui.adapter.UpdatesAdapter;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by KNOX.
 */

public class UpdatesItem extends Item<LayoutVerticalRvBinding> {

  private UpdatesAdapter mUpdatesAdapter;

  public UpdatesItem(UpdatesAdapter updatesAdapter) {
    mUpdatesAdapter = updatesAdapter;
  }

  @Override public int getLayout() {
    return R.layout.layout_vertical_rv;
  }

  @Override public void bind(LayoutVerticalRvBinding viewBinding, int position) {
    viewBinding.rvVertical.addItemDecoration(new DividerItemDecoration(viewBinding.rvVertical.getContext(), VERTICAL));
    viewBinding.rvVertical.setAdapter(mUpdatesAdapter);
    viewBinding.executePendingBindings();
  }

  public UpdatesAdapter getUpdatesAdapter() {
    return mUpdatesAdapter;
  }
}
