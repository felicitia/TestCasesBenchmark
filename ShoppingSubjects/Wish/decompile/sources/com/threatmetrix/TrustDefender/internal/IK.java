package com.threatmetrix.TrustDefender.internal;

import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import com.threatmetrix.TrustDefender.THMStatusCode;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map.Entry;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Handshake;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

class IK extends JR {

    /* renamed from: do reason: not valid java name */
    private static final String f179do = TL.m331if(IK.class);

    /* renamed from: case reason: not valid java name */
    private Call f180case = null;

    /* renamed from: char reason: not valid java name */
    private Request f181char = null;

    /* renamed from: else reason: not valid java name */
    private Response f182else = null;

    /* renamed from: try reason: not valid java name */
    private final OkHttpClient f183try;

    IK(RI ri, Y y, List<String> list) {
        super(ri, y, list);
        this.f183try = ri.f520do;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final long m85if(String str) {
        m80if(str, null);
        if (this.f182else == null || this.f252if != THMStatusCode.THM_OK) {
            return -1;
        }
        return (long) this.f182else.code();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final long m83for(String str, X x) {
        m80if(str, x);
        if (this.f182else == null || this.f252if != THMStatusCode.THM_OK) {
            return -1;
        }
        return (long) this.f182else.code();
    }

    /* renamed from: if reason: not valid java name */
    private void m80if(String str, X x) {
        List list;
        Builder url = new Builder().url(str);
        String str2 = this.f251for.m66do();
        if (str2 != null) {
            this.f254new.put("User-Agent", str2);
        }
        for (Entry entry : this.f254new.entrySet()) {
            if (entry.getValue() == null) {
                String str3 = f179do;
                StringBuilder sb = new StringBuilder("null value for ");
                sb.append((String) entry.getKey());
                TL.m338new(str3, sb.toString());
            } else {
                url.addHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }
        boolean z = false;
        if (x != null) {
            FormBody.Builder builder = new FormBody.Builder();
            for (String str4 : x.keySet()) {
                String str5 = (String) x.get(str4);
                if (NK.m203byte(str5)) {
                    Integer num = (Integer) x.f606do.get(str4);
                    if (num != null && str5.length() > num.intValue()) {
                        str5 = str5.substring(0, num.intValue());
                    }
                    if (num == null && x.f607if != 0 && str5.length() > x.f607if) {
                        str5 = str5.substring(0, x.f607if);
                    }
                    builder.add(str4, str5);
                }
            }
            url.post(builder.build());
        }
        synchronized (this) {
            this.f181char = url.build();
        }
        try {
            this.f180case = this.f183try.newCall(this.f181char);
            this.f182else = FirebasePerfOkHttpClient.execute(this.f180case);
            Handshake handshake = this.f182else.handshake();
            if (handshake != null) {
                z = true;
            }
            if (handshake != null) {
                list = handshake.peerCertificates();
            } else {
                list = null;
            }
            this.f252if = m115do(z, list, f179do);
        } catch (IOException e) {
            m121if((Exception) e);
            if (this.f253int == null || !this.f253int.m443for()) {
                TL.m337int(f179do, "Failed to retrieve URI", e);
                return;
            }
            TL.m338new(f179do, "Connection interrupted due to cancel!");
            if (this.f180case != null) {
                this.f180case.cancel();
            }
        } catch (RuntimeException e2) {
            TL.m337int(f179do, "Caught runtime exception:", e2);
            this.f252if = THMStatusCode.THM_Connection_Error;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: int reason: not valid java name */
    public final String m87int() {
        if (this.f181char != null) {
            return this.f181char.url().toString();
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final String m84for() {
        if (this.f181char != null) {
            return this.f181char.url().host();
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: new reason: not valid java name */
    public final void m88new() {
        if (this.f180case != null) {
            this.f180case.cancel();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: do reason: not valid java name */
    public final InputStream m82do() throws IOException {
        if (this.f182else == null) {
            return null;
        }
        return this.f182else.body().byteStream();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final void m86if() {
        if (this.f182else != null) {
            this.f182else.body().close();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: case reason: not valid java name */
    public final int m81case() {
        if (this.f182else != null) {
            return this.f182else.code();
        }
        return -1;
    }
}
