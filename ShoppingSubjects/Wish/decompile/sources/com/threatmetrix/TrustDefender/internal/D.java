package com.threatmetrix.TrustDefender.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrength;
import android.telephony.TelephonyManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class D {

    /* renamed from: new reason: not valid java name */
    private static final String f81new = TL.m331if(D.class);

    /* renamed from: com.threatmetrix.TrustDefender.internal.D$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: new reason: not valid java name */
        static final /* synthetic */ int[] f82new = new int[L.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.threatmetrix.TrustDefender.internal.P$L[] r0 = com.threatmetrix.TrustDefender.internal.P.L.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f82new = r0
                int[] r0 = f82new     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.threatmetrix.TrustDefender.internal.P$L r1 = com.threatmetrix.TrustDefender.internal.P.L.WCDMA     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f82new     // Catch:{ NoSuchFieldError -> 0x001f }
                com.threatmetrix.TrustDefender.internal.P$L r1 = com.threatmetrix.TrustDefender.internal.P.L.GSM     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f82new     // Catch:{ NoSuchFieldError -> 0x002a }
                com.threatmetrix.TrustDefender.internal.P$L r1 = com.threatmetrix.TrustDefender.internal.P.L.LTE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f82new     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.threatmetrix.TrustDefender.internal.P$L r1 = com.threatmetrix.TrustDefender.internal.P.L.CDMA     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.D.AnonymousClass2.<clinit>():void");
        }
    }

    D() {
    }

    @TargetApi(18)
    /* renamed from: for reason: not valid java name */
    static Map<String, String> m33for(Context context) {
        HashMap hashMap = new HashMap();
        List<CellInfo> list = m37new(context);
        if (list != null && list.size() > 0) {
            for (CellInfo cellInfo : list) {
                if (cellInfo.isRegistered()) {
                    switch (AnonymousClass2.f82new[m36int(cellInfo).ordinal()]) {
                        case 1:
                            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                            m35if(cellInfoWcdma.getCellSignalStrength(), cellInfoWcdma.getCellIdentity().toString(), hashMap);
                            break;
                        case 2:
                            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                            m35if(cellInfoGsm.getCellSignalStrength(), cellInfoGsm.getCellIdentity().toString(), hashMap);
                            break;
                        case 3:
                            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                            m35if(cellInfoLte.getCellSignalStrength(), cellInfoLte.getCellIdentity().toString(), hashMap);
                            break;
                        case 4:
                            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                            m35if(cellInfoCdma.getCellSignalStrength(), cellInfoCdma.getCellIdentity().toString(), hashMap);
                            break;
                    }
                    if (hashMap.size() < 3) {
                        return null;
                    }
                }
            }
        }
        return hashMap;
    }

    @TargetApi(18)
    /* renamed from: if reason: not valid java name */
    static String m34if(Context context) {
        StringBuilder sb = new StringBuilder();
        List<CellInfo> list = m37new(context);
        L l = L.LTE;
        if (list != null && list.size() > 0) {
            boolean z = true;
            for (CellInfo cellInfo : list) {
                if (cellInfo.isRegistered()) {
                    L l2 = m36int(cellInfo);
                    if (z) {
                        z = false;
                    } else if (l.f486try <= l2.f486try) {
                    }
                    switch (AnonymousClass2.f82new[l2.ordinal()]) {
                        case 1:
                            CellIdentityWcdma cellIdentity = ((CellInfoWcdma) cellInfo).getCellIdentity();
                            int cid = cellIdentity.getCid();
                            int mcc = cellIdentity.getMcc();
                            int mnc = cellIdentity.getMnc();
                            int lac = cellIdentity.getLac();
                            if (!(cid == Integer.MAX_VALUE || mcc == Integer.MAX_VALUE || mnc == Integer.MAX_VALUE || lac == Integer.MAX_VALUE)) {
                                sb.append(l2.f485else);
                                sb.append(":");
                                sb.append(String.valueOf(cid));
                                sb.append(":");
                                sb.append(String.valueOf(mcc));
                                sb.append(":");
                                sb.append(String.valueOf(mnc));
                                sb.append(":");
                                sb.append(String.valueOf(lac));
                                break;
                            }
                        case 2:
                            CellIdentityGsm cellIdentity2 = ((CellInfoGsm) cellInfo).getCellIdentity();
                            int cid2 = cellIdentity2.getCid();
                            int mcc2 = cellIdentity2.getMcc();
                            int mnc2 = cellIdentity2.getMnc();
                            int lac2 = cellIdentity2.getLac();
                            if (!(cid2 == Integer.MAX_VALUE || mcc2 == Integer.MAX_VALUE || mnc2 == Integer.MAX_VALUE || lac2 == Integer.MAX_VALUE)) {
                                sb.append(l2.f485else);
                                sb.append(":");
                                sb.append(String.valueOf(cid2));
                                sb.append(":");
                                sb.append(String.valueOf(mcc2));
                                sb.append(":");
                                sb.append(String.valueOf(mnc2));
                                sb.append(":");
                                sb.append(String.valueOf(lac2));
                                break;
                            }
                        case 3:
                            CellIdentityLte cellIdentity3 = ((CellInfoLte) cellInfo).getCellIdentity();
                            int ci = cellIdentity3.getCi();
                            int mcc3 = cellIdentity3.getMcc();
                            int mnc3 = cellIdentity3.getMnc();
                            int tac = cellIdentity3.getTac();
                            if (!(ci == Integer.MAX_VALUE || mcc3 == Integer.MAX_VALUE || mnc3 == Integer.MAX_VALUE || tac == Integer.MAX_VALUE)) {
                                sb.append(l2.f485else);
                                sb.append(":");
                                sb.append(String.valueOf(ci));
                                sb.append(":");
                                sb.append(String.valueOf(mcc3));
                                sb.append(":");
                                sb.append(String.valueOf(mnc3));
                                sb.append(":");
                                sb.append(String.valueOf(tac));
                                break;
                            }
                        case 4:
                            CellIdentityCdma cellIdentity4 = ((CellInfoCdma) cellInfo).getCellIdentity();
                            int basestationId = cellIdentity4.getBasestationId();
                            int systemId = cellIdentity4.getSystemId();
                            int networkId = cellIdentity4.getNetworkId();
                            if (!(basestationId == Integer.MAX_VALUE || systemId == Integer.MAX_VALUE || networkId == Integer.MAX_VALUE)) {
                                sb.append(l2.f485else);
                                sb.append(":");
                                sb.append(String.valueOf(basestationId));
                                sb.append(":");
                                sb.append(String.valueOf(systemId));
                                sb.append(":");
                                sb.append(String.valueOf(networkId));
                                break;
                            }
                    }
                    l = l2;
                }
            }
        }
        return sb.toString();
    }

    @TargetApi(18)
    /* renamed from: new reason: not valid java name */
    private static List<CellInfo> m37new(Context context) {
        if (!Y.m194int()) {
            return null;
        }
        try {
            Object systemService = context.getSystemService("phone");
            if (systemService != null) {
                if (systemService instanceof TelephonyManager) {
                    TelephonyManager telephonyManager = (TelephonyManager) systemService;
                    if (C0012I.f388for >= W.f404long) {
                        return telephonyManager.getAllCellInfo();
                    }
                    return null;
                }
            }
            return null;
        } catch (SecurityException unused) {
        } catch (Exception e) {
            TL.m338new(f81new, e.toString());
        }
    }

    /* renamed from: int reason: not valid java name */
    private static L m36int(CellInfo cellInfo) {
        if (Y.m186byte() && (cellInfo instanceof CellInfoWcdma)) {
            return L.WCDMA;
        }
        if (Y.m187case() && (cellInfo instanceof CellInfoGsm)) {
            return L.GSM;
        }
        if (Y.m197try() && (cellInfo instanceof CellInfoLte)) {
            return L.LTE;
        }
        if (!Y.m188char() || !(cellInfo instanceof CellInfoCdma)) {
            return L.UNKOWN;
        }
        return L.CDMA;
    }

    @TargetApi(18)
    /* renamed from: if reason: not valid java name */
    private static void m35if(CellSignalStrength cellSignalStrength, String str, Map<String, String> map) {
        if (cellSignalStrength.getAsuLevel() != 99) {
            map.put("sl_ASU", String.valueOf(cellSignalStrength.getAsuLevel()));
        }
        map.put("ss_dBm", String.valueOf(cellSignalStrength.getDbm()));
        map.put("sl_int", String.valueOf(cellSignalStrength.getLevel()));
        map.put("cii", str);
    }
}
