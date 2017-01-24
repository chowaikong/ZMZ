package me.knox.zmz.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import javax.inject.Inject;
import me.knox.zmz.R;
import me.knox.zmz.contract.NewsInfoContract;
import me.knox.zmz.databinding.ActivityNewsInfoBinding;
import me.knox.zmz.di.component.DaggerNewsInfoComponent;
import me.knox.zmz.di.module.NewsInfoModule;
import me.knox.zmz.entity.News;
import me.knox.zmz.entity.NewsInfo;
import me.knox.zmz.presenter.NewsInfoPresenter;
import me.knox.zmz.ui.util.Toaster;
import me.knox.zmz.ui.util.ZLog;

/**
 * Created by KNOX.
 */

public class NewsInfoActivity extends BaseBindingActivity<ActivityNewsInfoBinding>
    implements NewsInfoContract.View {

  private static final String NEWS = "news";

  @Inject NewsInfoPresenter mNewsInfoPresenter;

  public static void start(Context context, News news) {
    Intent intent = new Intent(context, NewsInfoActivity.class);
    intent.putExtra(NEWS, news);
    context.startActivity(intent);
  }

  @Override
  protected ActivityNewsInfoBinding setDataBindingContentView(@Nullable Bundle savedInstanceState) {
    return DataBindingUtil.setContentView(this, R.layout.activity_news_info);
  }

  @Override protected void initView() {
    setupToolbar(mDataBinding.toolbar);
  }

  @Override protected void initData() {
    DaggerNewsInfoComponent.builder().newsInfoModule(new NewsInfoModule(this)).build().inject(this);

    if (getIntent() == null) return;
    News news = getIntent().getParcelableExtra(NEWS);
    if (news == null) return;
    mDataBinding.setPoster(news.getPoster());
    mDataBinding.setIntro(news.getIntro());
    mNewsInfoPresenter.getNewsInfo(news.getId());
  }

  @Override protected void initListener() {
  }

  @Override public void obtainNewsInfoSucceed(NewsInfo newsInfo) {
    if (isFinishing()) return;
    mDataBinding.setInfo(newsInfo);
    mDataBinding.progress.bar.setVisibility(View.GONE);
  }

  @Override public void error(String error, Object... objects) {
    if (isFinishing()) return;
    ZLog.e(error);
    Toaster.show(getString(R.string.something_wrong_happened));
  }
}
