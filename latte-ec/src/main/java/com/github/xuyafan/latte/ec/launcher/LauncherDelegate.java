package com.github.xuyafan.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.github.xuyafan.latte.app.AccountManager;
import com.github.xuyafan.latte.app.IUserChecker;
import com.github.xuyafan.latte.delegates.LatteDelegate;
import com.github.xuyafan.latte.ec.R;
import com.github.xuyafan.latte.ec.R2;
import com.github.xuyafan.latte.ui.launcher.ScrollLauncherTag;
import com.github.xuyafan.latte.util.storage.LattePreference;
import com.github.xuyafan.latte.util.timer.BaseTimerTask;
import com.github.xuyafan.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author： xuyafan
 * description:
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {


    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;
    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScoll();
        }
    }


    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceStat, View rootView) {
        initTimer();
    }

    /**
     * 判断计时完毕操作
     */
    private void checkIsShowScoll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHE_APP.name())) {
            getSupportDelegate().start(new LauncherScollDelegate(), SINGLETASK);
        } else {
            //检查用户是否登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLaunchFinish(OnLaunchFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLaunchFinish(OnLaunchFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScoll();
                        }
                    }
                }
            }
        });
    }
}
