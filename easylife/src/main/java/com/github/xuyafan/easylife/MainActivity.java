package com.github.xuyafan.easylife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.github.xuyafan.latte.activities.ProxyActivity;
import com.github.xuyafan.latte.delegates.LatteDelegate;
import com.github.xuyafan.latte.ec.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏ActionBar
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new SignUpDelegate();
    }
}
