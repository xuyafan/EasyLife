package com.github.xuyafan.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.github.xuyafan.latte.delegates.LatteDelegate;
import com.github.xuyafan.latte.ec.R;
import com.github.xuyafan.latte.ui.launcher.LauncherHolderCreator;
import com.github.xuyafan.latte.ui.launcher.ScrollLauncherTag;
import com.github.xuyafan.latte.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * author： xuyafan
 * description:
 */

public class LauncherScollDelegate extends LatteDelegate implements OnItemClickListener {

    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ConvenientBanner<Integer> mConvenientBanner = null;

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);

        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(true);

    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceStat, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //启动页的最后一页
        if (position == INTEGERS.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHE_APP.name(), true);

            //检查是否登录

        }

    }
}
