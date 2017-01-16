package me.knox.zmz.entity;

import com.genius.groupie.Item;
import java.util.List;
import me.knox.zmz.R;
import me.knox.zmz.databinding.LayoutVerticalRvBinding;
import me.knox.zmz.ui.adapter.UpdatesAdapter;
import me.knox.zmz.ui.widget.VerticalSpaceItemDecoration;

/**
 * Created by KNOX.
 */

public class UpdatesItem extends Item<LayoutVerticalRvBinding> {

  private UpdatesAdapter mUpdatesAdapter;
  private List<Update> mUpdates;

  public UpdatesItem(UpdatesAdapter updatesAdapter) {
    mUpdatesAdapter = updatesAdapter;
  }

  @Override public int getLayout() {
    return R.layout.layout_vertical_rv;
  }

  @Override public void bind(LayoutVerticalRvBinding viewBinding, int position) {
    viewBinding.rvVertical.addItemDecoration(new VerticalSpaceItemDecoration(20));
    viewBinding.rvVertical.setAdapter(mUpdatesAdapter);
  }
}
