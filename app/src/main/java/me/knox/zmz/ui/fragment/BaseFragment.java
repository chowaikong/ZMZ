package me.knox.zmz.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by KNOX.
 */

public class BaseFragment extends Fragment {

  public AppCompatActivity getAppCompatActivity() {
    return (AppCompatActivity) getActivity();
  }

  public boolean isFragmentNotAvailable() {
    return getActivity() == null || getActivity().isFinishing() || isDetached();
  }
}
