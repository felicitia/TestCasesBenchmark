package io.branch.referral.network;

import android.content.Context;
import android.support.annotation.Nullable;
import io.branch.referral.Branch;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.m;
import io.branch.referral.z;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BranchRemoteInterface {

    public static class BranchRemoteException extends Exception {
        /* access modifiers changed from: private */
        public int branchErrorCode = -113;

        public BranchRemoteException(int i) {
            this.branchErrorCode = i;
        }
    }

    public static class a {
        /* access modifiers changed from: private */
        public final String a;
        /* access modifiers changed from: private */
        public final int b;

        public a(@Nullable String str, int i) {
            this.a = str;
            this.b = i;
        }
    }

    public abstract a a(String str) throws BranchRemoteException;

    public abstract a a(String str, JSONObject jSONObject) throws BranchRemoteException;

    public final z a(String str, JSONObject jSONObject, String str2, String str3) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (!a(jSONObject, str3)) {
            return new z(str2, -114);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(a(jSONObject));
        String sb2 = sb.toString();
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("getting ");
        sb3.append(sb2);
        m.b("BranchSDK", sb3.toString());
        try {
            a a2 = a(sb2);
            z a3 = a(a2.a, a2.b, str2);
            if (Branch.b() != null) {
                int currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
                Branch b = Branch.b();
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str2);
                sb4.append("-");
                sb4.append(Jsonkey.Branch_Round_Trip_Time.getKey());
                b.a(sb4.toString(), String.valueOf(currentTimeMillis2));
            }
            return a3;
        } catch (BranchRemoteException e) {
            if (e.branchErrorCode == -111) {
                z zVar = new z(str2, -111);
                if (Branch.b() != null) {
                    int currentTimeMillis3 = (int) (System.currentTimeMillis() - currentTimeMillis);
                    Branch b2 = Branch.b();
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(str2);
                    sb5.append("-");
                    sb5.append(Jsonkey.Branch_Round_Trip_Time.getKey());
                    b2.a(sb5.toString(), String.valueOf(currentTimeMillis3));
                }
                return zVar;
            }
            z zVar2 = new z(str2, -113);
            if (Branch.b() != null) {
                int currentTimeMillis4 = (int) (System.currentTimeMillis() - currentTimeMillis);
                Branch b3 = Branch.b();
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str2);
                sb6.append("-");
                sb6.append(Jsonkey.Branch_Round_Trip_Time.getKey());
                b3.a(sb6.toString(), String.valueOf(currentTimeMillis4));
            }
            return zVar2;
        } catch (Throwable th) {
            if (Branch.b() != null) {
                int currentTimeMillis5 = (int) (System.currentTimeMillis() - currentTimeMillis);
                Branch b4 = Branch.b();
                StringBuilder sb7 = new StringBuilder();
                sb7.append(str2);
                sb7.append("-");
                sb7.append(Jsonkey.Branch_Round_Trip_Time.getKey());
                b4.a(sb7.toString(), String.valueOf(currentTimeMillis5));
            }
            throw th;
        }
    }

    public final z a(JSONObject jSONObject, String str, String str2, String str3) {
        long currentTimeMillis = System.currentTimeMillis();
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (!a(jSONObject, str3)) {
            return new z(str2, -114);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("posting to ");
        sb.append(str);
        m.b("BranchSDK", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Post value = ");
        sb2.append(jSONObject.toString());
        m.b("BranchSDK", sb2.toString());
        try {
            a a2 = a(str, jSONObject);
            z a3 = a(a2.a, a2.b, str2);
            if (Branch.b() != null) {
                int currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
                Branch b = Branch.b();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str2);
                sb3.append("-");
                sb3.append(Jsonkey.Branch_Round_Trip_Time.getKey());
                b.a(sb3.toString(), String.valueOf(currentTimeMillis2));
            }
            return a3;
        } catch (BranchRemoteException e) {
            if (e.branchErrorCode == -111) {
                z zVar = new z(str2, -111);
                if (Branch.b() != null) {
                    int currentTimeMillis3 = (int) (System.currentTimeMillis() - currentTimeMillis);
                    Branch b2 = Branch.b();
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(str2);
                    sb4.append("-");
                    sb4.append(Jsonkey.Branch_Round_Trip_Time.getKey());
                    b2.a(sb4.toString(), String.valueOf(currentTimeMillis3));
                }
                return zVar;
            }
            z zVar2 = new z(str2, -113);
            if (Branch.b() != null) {
                int currentTimeMillis4 = (int) (System.currentTimeMillis() - currentTimeMillis);
                Branch b3 = Branch.b();
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str2);
                sb5.append("-");
                sb5.append(Jsonkey.Branch_Round_Trip_Time.getKey());
                b3.a(sb5.toString(), String.valueOf(currentTimeMillis4));
            }
            return zVar2;
        } catch (Throwable th) {
            if (Branch.b() != null) {
                int currentTimeMillis5 = (int) (System.currentTimeMillis() - currentTimeMillis);
                Branch b4 = Branch.b();
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str2);
                sb6.append("-");
                sb6.append(Jsonkey.Branch_Round_Trip_Time.getKey());
                b4.a(sb6.toString(), String.valueOf(currentTimeMillis5));
            }
            throw th;
        }
    }

    public static final BranchRemoteInterface a(Context context) {
        return new a(context);
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0026 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.branch.referral.z a(java.lang.String r3, int r4, java.lang.String r5) {
        /*
            r2 = this;
            io.branch.referral.z r0 = new io.branch.referral.z
            r0.<init>(r5, r4)
            java.lang.String r4 = "BranchSDK"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = "returned "
            r5.append(r1)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            io.branch.referral.m.b(r4, r5)
            if (r3 == 0) goto L_0x0050
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0026 }
            r4.<init>(r3)     // Catch:{ JSONException -> 0x0026 }
            r0.a(r4)     // Catch:{ JSONException -> 0x0026 }
            goto L_0x0050
        L_0x0026:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ JSONException -> 0x002f }
            r4.<init>(r3)     // Catch:{ JSONException -> 0x002f }
            r0.a(r4)     // Catch:{ JSONException -> 0x002f }
            goto L_0x0050
        L_0x002f:
            r3 = move-exception
            java.lang.Class r4 = r2.getClass()
            java.lang.String r4 = r4.getSimpleName()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = "JSON exception: "
            r5.append(r1)
            java.lang.String r3 = r3.getMessage()
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            io.branch.referral.m.b(r4, r3)
        L_0x0050:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.network.BranchRemoteInterface.a(java.lang.String, int, java.lang.String):io.branch.referral.z");
    }

    private boolean a(JSONObject jSONObject, String str) {
        try {
            if (!jSONObject.has(Jsonkey.UserData.getKey())) {
                jSONObject.put(Jsonkey.SDK.getKey(), "android2.18.1");
            }
            if (!str.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.BranchKey.getKey(), str);
                return true;
            }
        } catch (JSONException unused) {
        }
        return false;
    }

    private String a(JSONObject jSONObject) {
        StringBuilder sb = new StringBuilder();
        if (jSONObject != null) {
            JSONArray names = jSONObject.names();
            if (names != null) {
                int length = names.length();
                boolean z = true;
                int i = 0;
                while (i < length) {
                    try {
                        String string = names.getString(i);
                        if (z) {
                            sb.append("?");
                            z = false;
                        } else {
                            sb.append("&");
                        }
                        String string2 = jSONObject.getString(string);
                        sb.append(string);
                        sb.append("=");
                        sb.append(string2);
                        i++;
                    } catch (JSONException e) {
                        com.google.a.a.a.a.a.a.a(e);
                        return null;
                    }
                }
            }
        }
        return sb.toString();
    }
}
