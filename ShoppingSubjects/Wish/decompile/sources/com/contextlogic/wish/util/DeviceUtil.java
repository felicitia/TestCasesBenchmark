package com.contextlogic.wish.util;

import android.os.Build;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetAdvertisingIdService;
import com.contextlogic.wish.api.service.standalone.GetAdvertisingIdService.SuccessCallback;
import com.contextlogic.wish.http.HttpCookieManager;
import java.util.ArrayList;

public class DeviceUtil {
    /* access modifiers changed from: private */
    public static String sAdvertisingId = initializeAdvertisingId();
    private static ArrayList<String> sCapabilities = initializeCapabilities();

    public static String getClientId() {
        return "androidapp";
    }

    public static String getClientUploadSource() {
        return "6";
    }

    private static ArrayList<String> initializeCapabilities() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(Integer.toString(2));
        arrayList.add(Integer.toString(3));
        arrayList.add(Integer.toString(4));
        arrayList.add(Integer.toString(6));
        arrayList.add(Integer.toString(7));
        arrayList.add(Integer.toString(9));
        arrayList.add(Integer.toString(11));
        arrayList.add(Integer.toString(12));
        arrayList.add(Integer.toString(13));
        arrayList.add(Integer.toString(15));
        arrayList.add(Integer.toString(21));
        arrayList.add(Integer.toString(24));
        arrayList.add(Integer.toString(25));
        arrayList.add(Integer.toString(28));
        arrayList.add(Integer.toString(35));
        arrayList.add(Integer.toString(37));
        arrayList.add(Integer.toString(39));
        arrayList.add(Integer.toString(40));
        arrayList.add(Integer.toString(43));
        arrayList.add(Integer.toString(46));
        arrayList.add(Integer.toString(47));
        arrayList.add(Integer.toString(49));
        arrayList.add(Integer.toString(50));
        arrayList.add(Integer.toString(51));
        arrayList.add(Integer.toString(52));
        arrayList.add(Integer.toString(53));
        arrayList.add(Integer.toString(55));
        arrayList.add(Integer.toString(57));
        arrayList.add(Integer.toString(58));
        arrayList.add(Integer.toString(60));
        arrayList.add(Integer.toString(61));
        arrayList.add(Integer.toString(64));
        arrayList.add(Integer.toString(65));
        arrayList.add(Integer.toString(67));
        arrayList.add(Integer.toString(68));
        arrayList.add(Integer.toString(70));
        arrayList.add(Integer.toString(71));
        arrayList.add(Integer.toString(78));
        arrayList.add(Integer.toString(74));
        arrayList.add(Integer.toString(76));
        arrayList.add(Integer.toString(77));
        arrayList.add(Integer.toString(80));
        arrayList.add(Integer.toString(82));
        arrayList.add(Integer.toString(83));
        arrayList.add(Integer.toString(85));
        arrayList.add(Integer.toString(86));
        arrayList.add(Integer.toString(90));
        arrayList.add(Integer.toString(93));
        arrayList.add(Integer.toString(96));
        arrayList.add(Integer.toString(100));
        arrayList.add(Integer.toString(101));
        arrayList.add(Integer.toString(94));
        arrayList.add(Integer.toString(95));
        arrayList.add(Integer.toString(102));
        arrayList.add(Integer.toString(106));
        arrayList.add(Integer.toString(108));
        arrayList.add(Integer.toString(109));
        arrayList.add(Integer.toString(110));
        arrayList.add(Integer.toString(111));
        arrayList.add(Integer.toString(114));
        arrayList.add(Integer.toString(115));
        arrayList.add(Integer.toString(103));
        arrayList.add(Integer.toString(117));
        arrayList.add(Integer.toString(118));
        arrayList.add(Integer.toString(119));
        arrayList.add(Integer.toString(122));
        arrayList.add(Integer.toString(124));
        arrayList.add(Integer.toString(125));
        arrayList.add(Integer.toString(128));
        arrayList.add(Integer.toString(129));
        arrayList.add(Integer.toString(126));
        return arrayList;
    }

    private static String initializeAdvertisingId() {
        new GetAdvertisingIdService().requestService(new SuccessCallback() {
            public void onSuccess(String str) {
                DeviceUtil.sAdvertisingId = str;
                HttpCookieManager.getInstance().updateAdvertisingId(str);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
            }
        });
        return null;
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getAdvertisingId() {
        return sAdvertisingId;
    }

    public static ArrayList<String> getCapabilities() {
        return sCapabilities;
    }
}
