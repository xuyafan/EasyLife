package com.github.xuyafan.easylife;

import android.support.v7.app.AppCompatActivity;

import com.github.xuyafan.latte.activities.ProxyActivity;
import com.github.xuyafan.latte.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new EasylifeDelegate();
    }
}
