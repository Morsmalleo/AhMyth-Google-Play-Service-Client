package com.android.background.services.helpers;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AppsListManager {

    public static JSONObject getAppLists(Context context){

        try {
            JSONObject AppLists = new JSONObject();
            JSONArray appInfoList = new JSONArray();

            List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);

            for(int i=0;i < packs.size();i++) {

                PackageInfo packageInfo = packs.get(i);

                try {
                    String appName = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
                    String packageName = packageInfo.packageName;
                    String versionName = packageInfo.versionName;

                    JSONObject app = new JSONObject();

                    app.put("appName", appName);
                    app.put("packageName", packageName);
                    app.put("versionName", versionName);

                    appInfoList.put(app);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            AppLists.put("appsList", appInfoList);

            Log.d("MADARA", AppLists.toString());

            return AppLists;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
