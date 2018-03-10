package com.github.xuyafan.latte.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.github.xuyafan.latte.R;
import com.github.xuyafan.latte.util.dimen.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * author： xuyafan
 * description:
 */

public class LatteLoader {

    public static final LoaderStyle DEFAULT_LOADER_TYPE = LoaderStyle.BallClipRotatePulseIndicator;
    //默认宽高比
    private static final int LOADER_SIZE_SCALE =8;
    //偏移量
    private static final int LOADER_OFFSET_SCALE =10;
    private static final ArrayList<AppCompatDialog> LOADERS =new ArrayList<>();

    public static void showLoading(Context context,String type){

        final AppCompatDialog dialog =new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight =DimenUtil.getScreenHeight();

        final Window dialogWindow =dialog.getWindow();
        if(dialogWindow!=null){
            WindowManager.LayoutParams lp =dialogWindow.getAttributes();
            lp.width =deviceWidth/LOADER_SIZE_SCALE;
            lp.height=deviceHeight/LOADER_SIZE_SCALE;
            lp.height=lp.height+deviceHeight/LOADER_OFFSET_SCALE;
            lp.gravity= Gravity.CENTER;
        }

        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context,Enum<LoaderStyle> styleEnum){
        showLoading(context,styleEnum.name());
    }

    public static void showLoading(Context context){
        showLoading(context, DEFAULT_LOADER_TYPE.name());
    }

    public static void stopLoading(){
        for(AppCompatDialog dialog:LOADERS){
            if(dialog!=null){
                if(dialog.isShowing()){
                    dialog.cancel();
//                    dialog.dismiss(); cancel 可以写回调
                }
            }
        }
    }


}
