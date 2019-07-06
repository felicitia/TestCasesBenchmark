package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@bu
public final class anx {
    private final Map<any, anz> a = new HashMap();
    private final LinkedList<any> b = new LinkedList<>();
    @Nullable
    private ams c;

    static Set<String> a(zzjj zzjj) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(zzjj.extras.keySet());
        Bundle bundle = zzjj.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            hashSet.addAll(bundle.keySet());
        }
        return hashSet;
    }

    private static void a(Bundle bundle, String str) {
        while (true) {
            String[] split = str.split("/", 2);
            if (split.length != 0) {
                String str2 = split[0];
                if (split.length == 1) {
                    bundle.remove(str2);
                    return;
                }
                bundle = bundle.getBundle(str2);
                if (bundle != null) {
                    str = split[1];
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private static void a(String str, any any) {
        if (gv.a(2)) {
            gv.a(String.format(str, new Object[]{any}));
        }
    }

    private static String[] a(String str) {
        try {
            String[] split = str.split("\u0000");
            for (int i = 0; i < split.length; i++) {
                split[i] = new String(Base64.decode(split[i], 0), "UTF-8");
            }
            return split;
        } catch (UnsupportedEncodingException unused) {
            return new String[0];
        }
    }

    static zzjj b(zzjj zzjj) {
        zzjj d = d(zzjj);
        String str = "_skipMediation";
        Bundle bundle = d.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        if (bundle != null) {
            bundle.putBoolean(str, true);
        }
        d.extras.putBoolean(str, true);
        return d;
    }

    private final String b() {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                sb.append(Base64.encodeToString(((any) it.next()).toString().getBytes("UTF-8"), 0));
                if (it.hasNext()) {
                    sb.append("\u0000");
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    private static boolean b(String str) {
        try {
            return Pattern.matches((String) ajh.f().a(akl.ba), str);
        } catch (RuntimeException e) {
            ao.i().a((Throwable) e, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }

    @VisibleForTesting
    private static zzjj c(zzjj zzjj) {
        String[] split;
        zzjj d = d(zzjj);
        for (String str : ((String) ajh.f().a(akl.aW)).split(",")) {
            a(d.zzaqg, str);
            String str2 = "com.google.ads.mediation.admob.AdMobAdapter/";
            if (str.startsWith(str2)) {
                a(d.extras, str.replaceFirst(str2, ""));
            }
        }
        return d;
    }

    @VisibleForTesting
    private static String c(String str) {
        try {
            Matcher matcher = Pattern.compile("([^/]+/[0-9]+).*").matcher(str);
            if (matcher.matches()) {
                return matcher.group(1);
            }
        } catch (RuntimeException unused) {
        }
        return str;
    }

    @VisibleForTesting
    private static zzjj d(zzjj zzjj) {
        Parcel obtain = Parcel.obtain();
        zzjj.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        zzjj zzjj2 = (zzjj) zzjj.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return ((Boolean) ajh.f().a(akl.aN)).booleanValue() ? zzjj2.zzhv() : zzjj2;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public final aoa a(zzjj zzjj, String str) {
        if (b(str)) {
            return null;
        }
        int i = new dt(this.c.a()).a().n;
        zzjj c2 = c(zzjj);
        String c3 = c(str);
        any any = new any(c2, c3, i);
        anz anz = (anz) this.a.get(any);
        if (anz == null) {
            a("Interstitial pool created at %s.", any);
            anz = new anz(c2, c3, i);
            this.a.put(any, anz);
        }
        this.b.remove(any);
        this.b.add(any);
        anz.g();
        while (true) {
            if (this.b.size() <= ((Integer) ajh.f().a(akl.aX)).intValue()) {
                break;
            }
            any any2 = (any) this.b.remove();
            anz anz2 = (anz) this.a.get(any2);
            a("Evicting interstitial queue for %s.", any2);
            while (anz2.d() > 0) {
                aoa a2 = anz2.a((zzjj) null);
                if (a2.e) {
                    aob.a().c();
                }
                a2.a.zzdj();
            }
            this.a.remove(any2);
        }
        while (anz.d() > 0) {
            aoa a3 = anz.a(c2);
            if (a3.e) {
                if (ao.l().currentTimeMillis() - a3.d > 1000 * ((long) ((Integer) ajh.f().a(akl.aZ)).intValue())) {
                    a("Expired interstitial at %s.", any);
                    aob.a().b();
                }
            }
            String str2 = a3.b != null ? " (inline) " : MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
            StringBuilder sb = new StringBuilder(34 + String.valueOf(str2).length());
            sb.append("Pooled interstitial");
            sb.append(str2);
            sb.append("returned at %s.");
            a(sb.toString(), any);
            return a3;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final void a() {
        if (this.c != null) {
            for (Entry entry : this.a.entrySet()) {
                any any = (any) entry.getKey();
                anz anz = (anz) entry.getValue();
                if (gv.a(2)) {
                    int d = anz.d();
                    int e = anz.e();
                    if (e < d) {
                        gv.a(String.format("Loading %s/%s pooled interstitials for %s.", new Object[]{Integer.valueOf(d - e), Integer.valueOf(d), any}));
                    }
                }
                int f = 0 + anz.f();
                while (true) {
                    if (anz.d() >= ((Integer) ajh.f().a(akl.aY)).intValue()) {
                        break;
                    }
                    a("Pooling and loading one new interstitial for %s.", any);
                    if (anz.a(this.c)) {
                        f++;
                    }
                }
                aob.a().a(f);
            }
            if (this.c != null) {
                Editor edit = this.c.a().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit();
                edit.clear();
                for (Entry entry2 : this.a.entrySet()) {
                    any any2 = (any) entry2.getKey();
                    anz anz2 = (anz) entry2.getValue();
                    if (anz2.h()) {
                        edit.putString(any2.toString(), new aoc(anz2).a());
                        a("Saved interstitial queue for %s.", any2);
                    }
                }
                edit.putString("PoolKeys", b());
                edit.apply();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(ams ams) {
        if (this.c == null) {
            this.c = ams.b();
            if (this.c != null) {
                SharedPreferences sharedPreferences = this.c.a().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
                while (this.b.size() > 0) {
                    any any = (any) this.b.remove();
                    anz anz = (anz) this.a.get(any);
                    a("Flushing interstitial queue for %s.", any);
                    while (anz.d() > 0) {
                        anz.a((zzjj) null).a.zzdj();
                    }
                    this.a.remove(any);
                }
                try {
                    HashMap hashMap = new HashMap();
                    for (Entry entry : sharedPreferences.getAll().entrySet()) {
                        if (!((String) entry.getKey()).equals("PoolKeys")) {
                            aoc a2 = aoc.a((String) entry.getValue());
                            any any2 = new any(a2.a, a2.b, a2.c);
                            if (!this.a.containsKey(any2)) {
                                this.a.put(any2, new anz(a2.a, a2.b, a2.c));
                                hashMap.put(any2.toString(), any2);
                                a("Restored interstitial queue for %s.", any2);
                            }
                        }
                    }
                    for (String str : a(sharedPreferences.getString("PoolKeys", ""))) {
                        any any3 = (any) hashMap.get(str);
                        if (this.a.containsKey(any3)) {
                            this.b.add(any3);
                        }
                    }
                } catch (IOException | RuntimeException e) {
                    ao.i().a(e, "InterstitialAdPool.restore");
                    gv.c("Malformed preferences value for InterstitialAdPool.", e);
                    this.a.clear();
                    this.b.clear();
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void b(zzjj zzjj, String str) {
        if (this.c != null) {
            int i = new dt(this.c.a()).a().n;
            zzjj c2 = c(zzjj);
            String c3 = c(str);
            any any = new any(c2, c3, i);
            anz anz = (anz) this.a.get(any);
            if (anz == null) {
                a("Interstitial pool created at %s.", any);
                anz = new anz(c2, c3, i);
                this.a.put(any, anz);
            }
            anz.a(this.c, zzjj);
            anz.g();
            a("Inline entry added to the queue at %s.", any);
        }
    }
}
