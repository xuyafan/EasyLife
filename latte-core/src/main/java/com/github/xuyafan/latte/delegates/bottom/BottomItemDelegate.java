package com.github.xuyafan.latte.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.github.xuyafan.latte.R;
import com.github.xuyafan.latte.delegates.LatteDelegate;

/**
 * author： xuyafan
 * description:
 */

public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {


    private static final long EXIT_TIME = 2000L;
    private long mExitTime = 0;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
                if (mExitTime != 0) mExitTime = 0;
            }
            return true;
        }
        return false;
    }
}
