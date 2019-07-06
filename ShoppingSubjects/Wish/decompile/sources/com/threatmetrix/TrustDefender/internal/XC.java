package com.threatmetrix.TrustDefender.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class XC {

    /* renamed from: int reason: not valid java name */
    private static final String f634int = TL.m331if(XC.class);

    XC() {
    }

    @TargetApi(22)
    /* renamed from: int reason: not valid java name */
    static Map<String, String> m402int(Context context, boolean z) {
        HashMap hashMap = new HashMap();
        if (C0012I.f388for >= 22 && Y.m190else()) {
            ArrayList arrayList = new ArrayList();
            SubscriptionManager from = SubscriptionManager.from(context);
            if (from == null) {
                return hashMap;
            }
            try {
                List<SubscriptionInfo> activeSubscriptionInfoList = from.getActiveSubscriptionInfoList();
                if (activeSubscriptionInfoList != null && activeSubscriptionInfoList.size() > 0) {
                    for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                        int simSlotIndex = subscriptionInfo.getSimSlotIndex();
                        NK.m225new(subscriptionInfo.getCarrierName().toString(), z, "cna".concat(String.valueOf(simSlotIndex)), hashMap);
                        NK.m225new(subscriptionInfo.getDisplayName().toString(), z, "dna".concat(String.valueOf(simSlotIndex)), hashMap);
                        NK.m225new(String.valueOf(subscriptionInfo.getIccId()), z, "ssa".concat(String.valueOf(simSlotIndex)), hashMap);
                        NK.m225new(subscriptionInfo.getNumber(), z, "na".concat(String.valueOf(simSlotIndex)), hashMap);
                        NK.m225new(subscriptionInfo.getCountryIso(), z, "ca".concat(String.valueOf(simSlotIndex)), hashMap);
                        hashMap.put("ra".concat(String.valueOf(simSlotIndex)), subscriptionInfo.getDataRoaming() == 0 ? "disabled" : "enabled");
                        StringBuilder sb = new StringBuilder();
                        sb.append(subscriptionInfo.getIccId());
                        sb.append(subscriptionInfo.getNumber());
                        arrayList.add(sb.toString());
                    }
                }
                int i = 0;
                Method method = D2.m42do(SubscriptionManager.class, "getAllSubscriptionInfoList", new Class[0]);
                if (method != null) {
                    Object obj = D2.m39do((Object) from, method, new Object[0]);
                    if (obj != null && (obj instanceof List)) {
                        List<SubscriptionInfo> list = (List) obj;
                        if (activeSubscriptionInfoList == null || activeSubscriptionInfoList.size() < list.size()) {
                            for (SubscriptionInfo subscriptionInfo2 : list) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(subscriptionInfo2.getIccId());
                                sb2.append(subscriptionInfo2.getNumber());
                                if (!arrayList.contains(sb2.toString())) {
                                    NK.m225new(subscriptionInfo2.getCarrierName().toString(), z, "cno".concat(String.valueOf(i)), hashMap);
                                    NK.m225new(subscriptionInfo2.getDisplayName().toString(), z, "dno".concat(String.valueOf(i)), hashMap);
                                    NK.m225new(String.valueOf(subscriptionInfo2.getIccId()), z, "sso".concat(String.valueOf(i)), hashMap);
                                    NK.m225new(subscriptionInfo2.getNumber(), z, "no".concat(String.valueOf(i)), hashMap);
                                    NK.m225new(subscriptionInfo2.getCountryIso(), z, "co".concat(String.valueOf(i)), hashMap);
                                    hashMap.put("ro".concat(String.valueOf(i)), subscriptionInfo2.getDataRoaming() == 0 ? "disabled" : "enabled");
                                    i++;
                                }
                            }
                        }
                    }
                }
            } catch (SecurityException unused) {
            } catch (Exception e) {
                TL.m338new(f634int, e.toString());
            }
        }
        return hashMap;
    }
}
