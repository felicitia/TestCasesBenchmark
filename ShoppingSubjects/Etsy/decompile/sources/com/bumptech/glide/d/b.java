package com.bumptech.glide.d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ManifestParser */
public final class b {
    private final Context a;

    public b(Context context) {
        this.a = context;
    }

    public List<a> a() {
        ArrayList arrayList = new ArrayList();
        try {
            ApplicationInfo applicationInfo = this.a.getPackageManager().getApplicationInfo(this.a.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                for (String str : applicationInfo.metaData.keySet()) {
                    if ("GlideModule".equals(applicationInfo.metaData.get(str))) {
                        arrayList.add(a(str));
                    }
                }
            }
            return arrayList;
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e);
        }
    }

    private static a a(String str) {
        try {
            Class cls = Class.forName(str);
            try {
                Object newInstance = cls.newInstance();
                if (newInstance instanceof a) {
                    return (a) newInstance;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Expected instanceof GlideModule, but found: ");
                sb.append(newInstance);
                throw new RuntimeException(sb.toString());
            } catch (InstantiationException e) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unable to instantiate GlideModule implementation for ");
                sb2.append(cls);
                throw new RuntimeException(sb2.toString(), e);
            } catch (IllegalAccessException e2) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Unable to instantiate GlideModule implementation for ");
                sb3.append(cls);
                throw new RuntimeException(sb3.toString(), e2);
            }
        } catch (ClassNotFoundException e3) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e3);
        }
    }
}
