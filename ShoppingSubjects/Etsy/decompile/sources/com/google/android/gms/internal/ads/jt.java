package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.JsonWriter;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

@bu
public final class jt {
    private static Object a = new Object();
    @GuardedBy("sLock")
    private static boolean b = false;
    @GuardedBy("sLock")
    private static boolean c = false;
    private static Clock d = DefaultClock.getInstance();
    private static final Set<String> e = new HashSet(Arrays.asList(new String[0]));
    private final List<String> f;

    public jt() {
        this(null);
    }

    public jt(@Nullable String str) {
        List<String> list;
        if (!c()) {
            list = new ArrayList<>();
        } else {
            String uuid = UUID.randomUUID().toString();
            if (str == null) {
                String[] strArr = new String[1];
                String str2 = "network_request_";
                String valueOf = String.valueOf(uuid);
                strArr[0] = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
                list = Arrays.asList(strArr);
            } else {
                String[] strArr2 = new String[2];
                String str3 = "ad_request_";
                String valueOf2 = String.valueOf(str);
                strArr2[0] = valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3);
                String str4 = "network_request_";
                String valueOf3 = String.valueOf(uuid);
                strArr2[1] = valueOf3.length() != 0 ? str4.concat(valueOf3) : new String(str4);
                list = Arrays.asList(strArr2);
            }
        }
        this.f = list;
    }

    public static void a() {
        synchronized (a) {
            b = false;
            c = false;
            ka.e("Ad debug logging enablement is out of date.");
        }
    }

    static final /* synthetic */ void a(int i, Map map, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name(ResponseConstants.PARAMS).beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name(ResponseConstants.CODE).value((long) i);
        jsonWriter.endObject();
        a(jsonWriter, map);
        jsonWriter.endObject();
    }

    private static void a(JsonWriter jsonWriter, @Nullable Map<String, ?> map) throws IOException {
        if (map != null) {
            jsonWriter.name("headers").beginArray();
            Iterator it = map.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Entry entry = (Entry) it.next();
                String str = (String) entry.getKey();
                if (!e.contains(str)) {
                    if (!(entry.getValue() instanceof List)) {
                        if (!(entry.getValue() instanceof String)) {
                            ka.c("Connection headers should be either Map<String, String> or Map<String, List<String>>");
                            break;
                        }
                        jsonWriter.beginObject();
                        jsonWriter.name(ResponseConstants.NAME).value(str);
                        jsonWriter.name(ResponseConstants.VALUE).value((String) entry.getValue());
                        jsonWriter.endObject();
                    } else {
                        for (String str2 : (List) entry.getValue()) {
                            jsonWriter.beginObject();
                            jsonWriter.name(ResponseConstants.NAME).value(str);
                            jsonWriter.name(ResponseConstants.VALUE).value(str2);
                            jsonWriter.endObject();
                        }
                    }
                }
            }
            jsonWriter.endArray();
        }
    }

    static final /* synthetic */ void a(String str, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name(ResponseConstants.PARAMS).beginObject();
        if (str != null) {
            jsonWriter.name("error_description").value(str);
        }
        jsonWriter.endObject();
    }

    private final void a(String str, jz jzVar) {
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        try {
            jsonWriter.beginObject();
            jsonWriter.name("timestamp").value(d.currentTimeMillis());
            jsonWriter.name(NotificationCompat.CATEGORY_EVENT).value(str);
            jsonWriter.name("components").beginArray();
            for (String value : this.f) {
                jsonWriter.value(value);
            }
            jsonWriter.endArray();
            jzVar.a(jsonWriter);
            jsonWriter.endObject();
            jsonWriter.flush();
            jsonWriter.close();
        } catch (IOException e2) {
            ka.b("unable to log", e2);
        }
        c(stringWriter.toString());
    }

    static final /* synthetic */ void a(String str, String str2, Map map, byte[] bArr, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name(ResponseConstants.PARAMS).beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name("uri").value(str);
        jsonWriter.name("verb").value(str2);
        jsonWriter.endObject();
        a(jsonWriter, map);
        if (bArr != null) {
            jsonWriter.name("body").value(Base64Utils.encode(bArr));
        }
        jsonWriter.endObject();
    }

    public static void a(boolean z) {
        synchronized (a) {
            b = true;
            c = z;
        }
    }

    static final /* synthetic */ void a(byte[] bArr, JsonWriter jsonWriter) throws IOException {
        String str;
        jsonWriter.name(ResponseConstants.PARAMS).beginObject();
        int length = bArr.length;
        String encode = Base64Utils.encode(bArr);
        if (length < 10000) {
            str = "body";
        } else {
            encode = jp.a(encode);
            if (encode != null) {
                str = "bodydigest";
            }
            jsonWriter.name("bodylength").value((long) length);
            jsonWriter.endObject();
        }
        jsonWriter.name(str).value(encode);
        jsonWriter.name("bodylength").value((long) length);
        jsonWriter.endObject();
    }

    public static boolean a(Context context) {
        if (VERSION.SDK_INT < 17) {
            return false;
        }
        if (!((Boolean) ajh.f().a(akl.bh)).booleanValue()) {
            return false;
        }
        try {
            return Global.getInt(context.getContentResolver(), "development_settings_enabled", 0) != 0;
        } catch (Exception e2) {
            ka.c("Fail to determine debug setting.", e2);
            return false;
        }
    }

    private final void b(@Nullable String str) {
        a("onNetworkRequestError", (jz) new jy(str));
    }

    private final void b(String str, String str2, @Nullable Map<String, ?> map, @Nullable byte[] bArr) {
        a("onNetworkRequest", (jz) new ju(str, str2, map, bArr));
    }

    private final void b(@Nullable Map<String, ?> map, int i) {
        a("onNetworkResponse", (jz) new jw(i, map));
    }

    public static boolean b() {
        boolean z;
        synchronized (a) {
            z = b;
        }
        return z;
    }

    private static synchronized void c(String str) {
        synchronized (jt.class) {
            ka.d("GMA Debug BEGIN");
            int i = 0;
            while (i < str.length()) {
                int i2 = i + 4000;
                String str2 = "GMA Debug CONTENT ";
                String valueOf = String.valueOf(str.substring(i, Math.min(i2, str.length())));
                ka.d(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                i = i2;
            }
            ka.d("GMA Debug FINISH");
        }
    }

    public static boolean c() {
        boolean z;
        synchronized (a) {
            z = b && c;
        }
        return z;
    }

    public final void a(@Nullable String str) {
        if (c() && str != null) {
            a(str.getBytes());
        }
    }

    public final void a(String str, String str2, @Nullable Map<String, ?> map, @Nullable byte[] bArr) {
        if (c()) {
            b(str, str2, map, bArr);
        }
    }

    public final void a(HttpURLConnection httpURLConnection, int i) {
        String str;
        if (c()) {
            b(httpURLConnection.getHeaderFields() == null ? null : new HashMap(httpURLConnection.getHeaderFields()), i);
            if (i < 200 || i >= 300) {
                try {
                    str = httpURLConnection.getResponseMessage();
                } catch (IOException e2) {
                    String str2 = "Can not get error message from error HttpURLConnection\n";
                    String valueOf = String.valueOf(e2.getMessage());
                    ka.e(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    str = null;
                }
                b(str);
            }
        }
    }

    public final void a(HttpURLConnection httpURLConnection, @Nullable byte[] bArr) {
        if (c()) {
            b(new String(httpURLConnection.getURL().toString()), new String(httpURLConnection.getRequestMethod()), httpURLConnection.getRequestProperties() == null ? null : new HashMap(httpURLConnection.getRequestProperties()), bArr);
        }
    }

    public final void a(@Nullable Map<String, ?> map, int i) {
        if (c()) {
            b(map, i);
            if (i < 200 || i >= 300) {
                b(null);
            }
        }
    }

    public final void a(byte[] bArr) {
        a("onNetworkResponseBody", (jz) new jx(bArr));
    }
}
