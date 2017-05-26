package me.knox.zmz.ui.item;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import me.drakeet.multitype.ItemViewBinder;
import me.knox.zmz.binding.DataBindingViewHolder;
import me.knox.zmz.databinding.ItemUpdateBinding;
import me.knox.zmz.databinding.LayoutDownloadUpdateBinding;
import me.knox.zmz.entity.Update;
import me.knox.zmz.ui.util.Toaster;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by KNOX.
 */

public class UpdatesItemProvider extends
    ItemViewBinder<Update, DataBindingViewHolder<ItemUpdateBinding>> {

  @NonNull @Override protected DataBindingViewHolder<ItemUpdateBinding> onCreateViewHolder(@NonNull LayoutInflater inflater,
      @NonNull ViewGroup parent) {
    return new DataBindingViewHolder<>(ItemUpdateBinding.inflate(inflater, parent, false));
  }

  @Override
  protected void onBindViewHolder(@NonNull DataBindingViewHolder<ItemUpdateBinding> holder,
      @NonNull Update update) {
    holder.getBinding().setUpdate(update);
    Context context = holder.getBinding().getRoot().getContext();
    BottomSheetDialog dialog = new BottomSheetDialog(context);
    LayoutDownloadUpdateBinding binding = LayoutDownloadUpdateBinding.inflate(LayoutInflater.from(context));
    dialog.setContentView(binding.getRoot());
    dialog.setOnShowListener(listener -> {
      BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) listener;
      FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(
          android.support.design.R.id.design_bottom_sheet);
      if (bottomSheet != null) {
        BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_COLLAPSED);
        BottomSheetBehavior.from(bottomSheet).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
          @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
              dialog.dismiss();
            }
            if (newState == BottomSheetBehavior.STATE_DRAGGING && dialog.isShowing()) {
              BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
          }

          @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {
          }
        });
      }
    });
    binding.setUpdate(update);
    ClipboardManager clipboardManager =
        (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
    if (binding.tvFirst.getVisibility() == View.VISIBLE) {
      binding.tvFirst.setOnClickListener(v -> {
        clipboardManager.setPrimaryClip(ClipData.newPlainText(context.getPackageName(), binding.tvFirst.getText().toString()));
        Toaster.show("已复制");
      });
    }
    if (binding.tvSecond.getVisibility() == View.VISIBLE) {
      binding.tvSecond.setOnClickListener(v -> {
        clipboardManager.setPrimaryClip(ClipData.newPlainText(context.getPackageName(), binding.tvSecond.getText().toString()));
        Toaster.show("已复制");
      });
    }
    holder.getBinding().ivMore.setOnClickListener(v -> {
      if (dialog.isShowing()) return;
      dialog.show();
    });
    holder.getBinding().executePendingBindings();
  }
}
