package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.bumptech.glide.g.h;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

@TargetApi(19)
/* compiled from: SizeConfigStrategy */
public class i implements g {
    private static final Config[] a = {Config.ARGB_8888, null};
    private static final Config[] b = {Config.RGB_565};
    private static final Config[] c = {Config.ARGB_4444};
    private static final Config[] d = {Config.ALPHA_8};
    private final b e = new b();
    private final e<a, Bitmap> f = new e<>();
    private final Map<Config, NavigableMap<Integer, Integer>> g = new HashMap();

    /* renamed from: com.bumptech.glide.load.engine.bitmap_recycle.i$1 reason: invalid class name */
    /* compiled from: SizeConfigStrategy */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Config.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                android.graphics.Bitmap$Config[] r0 = android.graphics.Bitmap.Config.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001f }
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.RGB_565     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x002a }
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_4444     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ALPHA_8     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.bitmap_recycle.i.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: SizeConfigStrategy */
    static final class a implements h {
        private final b a;
        /* access modifiers changed from: private */
        public int b;
        private Config c;

        public a(b bVar) {
            this.a = bVar;
        }

        public void a(int i, Config config) {
            this.b = i;
            this.c = config;
        }

        public void a() {
            this.a.a(this);
        }

        public String toString() {
            return i.b(this.b, this.c);
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.b == aVar.b && (this.c != null ? this.c.equals(aVar.c) : aVar.c == null)) {
                z = true;
            }
            return z;
        }

        public int hashCode() {
            return (31 * this.b) + (this.c != null ? this.c.hashCode() : 0);
        }
    }

    /* compiled from: SizeConfigStrategy */
    static class b extends b<a> {
        b() {
        }

        public a a(int i, Config config) {
            a aVar = (a) c();
            aVar.a(i, config);
            return aVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public a b() {
            return new a(this);
        }
    }

    public void a(Bitmap bitmap) {
        a a2 = this.e.a(h.a(bitmap), bitmap.getConfig());
        this.f.a(a2, bitmap);
        NavigableMap a3 = a(bitmap.getConfig());
        Integer num = (Integer) a3.get(Integer.valueOf(a2.b));
        Integer valueOf = Integer.valueOf(a2.b);
        int i = 1;
        if (num != null) {
            i = 1 + num.intValue();
        }
        a3.put(valueOf, Integer.valueOf(i));
    }

    public Bitmap a(int i, int i2, Config config) {
        int a2 = h.a(i, i2, config);
        Bitmap bitmap = (Bitmap) this.f.a(a(this.e.a(a2, config), a2, config));
        if (bitmap != null) {
            a(Integer.valueOf(h.a(bitmap)), bitmap.getConfig());
            bitmap.reconfigure(i, i2, bitmap.getConfig() != null ? bitmap.getConfig() : Config.ARGB_8888);
        }
        return bitmap;
    }

    private a a(a aVar, int i, Config config) {
        Config[] b2 = b(config);
        int i2 = 0;
        int length = b2.length;
        while (i2 < length) {
            Config config2 = b2[i2];
            Integer num = (Integer) a(config2).ceilingKey(Integer.valueOf(i));
            if (num == null || num.intValue() > i * 8) {
                i2++;
            } else {
                if (num.intValue() == i) {
                    if (config2 == null) {
                        if (config == null) {
                            return aVar;
                        }
                    } else if (config2.equals(config)) {
                        return aVar;
                    }
                }
                this.e.a(aVar);
                return this.e.a(num.intValue(), config2);
            }
        }
        return aVar;
    }

    public Bitmap a() {
        Bitmap bitmap = (Bitmap) this.f.a();
        if (bitmap != null) {
            a(Integer.valueOf(h.a(bitmap)), bitmap.getConfig());
        }
        return bitmap;
    }

    private void a(Integer num, Config config) {
        NavigableMap a2 = a(config);
        Integer num2 = (Integer) a2.get(num);
        if (num2.intValue() == 1) {
            a2.remove(num);
        } else {
            a2.put(num, Integer.valueOf(num2.intValue() - 1));
        }
    }

    private NavigableMap<Integer, Integer> a(Config config) {
        NavigableMap<Integer, Integer> navigableMap = (NavigableMap) this.g.get(config);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.g.put(config, treeMap);
        return treeMap;
    }

    public String b(Bitmap bitmap) {
        return b(h.a(bitmap), bitmap.getConfig());
    }

    public String b(int i, int i2, Config config) {
        return b(h.a(i, i2, config), config);
    }

    public int c(Bitmap bitmap) {
        return h.a(bitmap);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SizeConfigStrategy{groupedMap=");
        sb.append(this.f);
        sb.append(", sortedSizes=(");
        for (Entry entry : this.g.entrySet()) {
            sb.append(entry.getKey());
            sb.append('[');
            sb.append(entry.getValue());
            sb.append("], ");
        }
        if (!this.g.isEmpty()) {
            sb.replace(sb.length() - 2, sb.length(), "");
        }
        sb.append(")}");
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public static String b(int i, Config config) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(i);
        sb.append("](");
        sb.append(config);
        sb.append(")");
        return sb.toString();
    }

    private static Config[] b(Config config) {
        switch (AnonymousClass1.a[config.ordinal()]) {
            case 1:
                return a;
            case 2:
                return b;
            case 3:
                return c;
            case 4:
                return d;
            default:
                return new Config[]{config};
        }
    }
}
