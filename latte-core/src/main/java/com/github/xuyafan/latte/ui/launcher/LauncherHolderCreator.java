package com.github.xuyafan.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * author： xuyafan
 * description:
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {
    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
