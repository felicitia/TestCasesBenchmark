package com.google.android.gms.gcm;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class Task implements ReflectedParcelable {
    public static final int EXTRAS_LIMIT_BYTES = 10240;
    public static final int NETWORK_STATE_ANY = 2;
    public static final int NETWORK_STATE_CONNECTED = 0;
    public static final int NETWORK_STATE_UNMETERED = 1;
    protected static final long UNINITIALIZED = -1;
    private final Bundle extras;
    private final String gcmTaskService;
    private final boolean isPersisted;
    private final int requiredNetworkState;
    private final boolean requiresCharging;
    private final String tag;
    private final boolean updateCurrent;
    private final Set<Uri> zzau;
    private final boolean zzav;
    private final i zzaw;

    public static abstract class a {
        protected int a;
        protected String b;
        protected String c;
        protected boolean d;
        protected boolean e;
        protected boolean f;
        protected Set<Uri> g;
        protected i h;
        protected Bundle i;
    }

    @Deprecated
    Task(Parcel parcel) {
        Log.e("Task", "Constructing a Task object using a parcel.");
        this.gcmTaskService = parcel.readString();
        this.tag = parcel.readString();
        boolean z = true;
        this.updateCurrent = parcel.readInt() == 1;
        if (parcel.readInt() != 1) {
            z = false;
        }
        this.isPersisted = z;
        this.requiredNetworkState = 2;
        this.zzau = Collections.emptySet();
        this.requiresCharging = false;
        this.zzav = false;
        this.zzaw = i.a;
        this.extras = null;
    }

    Task(a aVar) {
        this.gcmTaskService = aVar.b;
        this.tag = aVar.c;
        this.updateCurrent = aVar.d;
        this.isPersisted = aVar.e;
        this.requiredNetworkState = aVar.a;
        this.zzau = aVar.g;
        this.requiresCharging = aVar.f;
        this.zzav = false;
        this.extras = aVar.i;
        this.zzaw = aVar.h != null ? aVar.h : i.a;
    }

    /* access modifiers changed from: private */
    public static void zzd(Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("Null URI");
        }
        String scheme = uri.getScheme();
        String host = uri.getHost();
        if (TextUtils.isEmpty(host) || "null".equals(host)) {
            throw new IllegalArgumentException("URI hostname is required");
        }
        try {
            int port = uri.getPort();
            if ("tcp".equals(scheme)) {
                if (port <= 0 || port > 65535) {
                    int port2 = uri.getPort();
                    StringBuilder sb = new StringBuilder(38);
                    sb.append("Invalid required URI port: ");
                    sb.append(port2);
                    throw new IllegalArgumentException(sb.toString());
                }
            } else if (!"ping".equals(scheme)) {
                String str = "Unsupported required URI scheme: ";
                String valueOf = String.valueOf(scheme);
                throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            } else if (port != -1) {
                throw new IllegalArgumentException("Ping does not support port numbers");
            }
        } catch (NumberFormatException e) {
            String str2 = "Invalid port number: ";
            String valueOf2 = String.valueOf(e.getMessage());
            throw new IllegalArgumentException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
        }
    }

    public static void zzg(Bundle bundle) {
        if (bundle != null) {
            Parcel obtain = Parcel.obtain();
            bundle.writeToParcel(obtain, 0);
            int dataSize = obtain.dataSize();
            obtain.recycle();
            if (dataSize > 10240) {
                StringBuilder sb = new StringBuilder(55);
                sb.append("Extras exceeding maximum size(10240 bytes): ");
                sb.append(dataSize);
                throw new IllegalArgumentException(sb.toString());
            }
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (!((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof Boolean))) {
                    if (obj instanceof Bundle) {
                        zzg((Bundle) obj);
                    } else {
                        throw new IllegalArgumentException("Only the following extra parameter types are supported: Integer, Long, Double, String, Boolean, and nested Bundles with the same restrictions.");
                    }
                }
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public Bundle getExtras() {
        return this.extras;
    }

    public int getRequiredNetwork() {
        return this.requiredNetworkState;
    }

    public boolean getRequiresCharging() {
        return this.requiresCharging;
    }

    public String getServiceName() {
        return this.gcmTaskService;
    }

    public String getTag() {
        return this.tag;
    }

    public boolean isPersisted() {
        return this.isPersisted;
    }

    public boolean isUpdateCurrent() {
        return this.updateCurrent;
    }

    public void toBundle(Bundle bundle) {
        bundle.putString("tag", this.tag);
        bundle.putBoolean("update_current", this.updateCurrent);
        bundle.putBoolean("persisted", this.isPersisted);
        bundle.putString(NotificationCompat.CATEGORY_SERVICE, this.gcmTaskService);
        bundle.putInt("requiredNetwork", this.requiredNetworkState);
        if (!this.zzau.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (Uri uri : this.zzau) {
                arrayList.add(uri.toString());
            }
            bundle.putStringArrayList("reachabilityUris", arrayList);
        }
        bundle.putBoolean("requiresCharging", this.requiresCharging);
        bundle.putBoolean("requiresIdle", false);
        bundle.putBundle("retryStrategy", this.zzaw.a(new Bundle()));
        bundle.putBundle("extras", this.extras);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.gcmTaskService);
        parcel.writeString(this.tag);
        parcel.writeInt(this.updateCurrent ? 1 : 0);
        parcel.writeInt(this.isPersisted ? 1 : 0);
    }
}
