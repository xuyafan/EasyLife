package com.github.xuyafan.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.xuyafan.latte.app.AccountManager;
import com.github.xuyafan.latte.ec.database.DatabaseManager;
import com.github.xuyafan.latte.ec.database.UserProfile;
import com.github.xuyafan.latte.util.log.LatteLogger;

/**
 * author： xuyafan
 * description:
 */

public class SignHandler {

    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long id = profileJson.getLong("id");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(id, name, avatar, gender, address);
        long insert = DatabaseManager.getInstance().getDao().insert(profile);
        LatteLogger.d("插入用户信息,long id :" + insert);


        AccountManager.setSignState(true);
        signListener.onSignInSuccess();

    }

    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }
}
