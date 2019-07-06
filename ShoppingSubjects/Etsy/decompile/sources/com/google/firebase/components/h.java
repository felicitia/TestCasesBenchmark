package com.google.firebase.components;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class h {
    private final Context a;
    private final b b;

    static class a implements b {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final List<String> a(Context context) {
            Bundle b = b(context);
            if (b == null) {
                Log.w("ComponentDiscovery", "Could not retrieve metadata, returning empty list of registrars.");
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            for (String str : b.keySet()) {
                if ("com.google.firebase.components.ComponentRegistrar".equals(b.get(str)) && str.startsWith("com.google.firebase.components:")) {
                    arrayList.add(str.substring(31));
                }
            }
            return arrayList;
        }

        private static Bundle b(Context context) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    Log.w("ComponentDiscovery", "Context has no PackageManager.");
                    return null;
                }
                ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, ComponentDiscoveryService.class), 128);
                if (serviceInfo != null) {
                    return serviceInfo.metaData;
                }
                Log.w("ComponentDiscovery", "ComponentDiscoveryService has no service info.");
                return null;
            } catch (NameNotFoundException unused) {
                Log.w("ComponentDiscovery", "Application info not found.");
                return null;
            }
        }
    }

    @VisibleForTesting
    interface b {
        List<String> a(Context context);
    }

    public h(Context context) {
        this(context, new a(0));
    }

    @VisibleForTesting
    private h(Context context, b bVar) {
        this.a = context;
        this.b = bVar;
    }

    public final List<e> a() {
        return a(this.b.a(this.a));
    }

    private static List<e> a(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            try {
                Class cls = Class.forName(str);
                if (!e.class.isAssignableFrom(cls)) {
                    Log.w("ComponentDiscovery", String.format("Class %s is not an instance of %s", new Object[]{str, "com.google.firebase.components.ComponentRegistrar"}));
                } else {
                    arrayList.add((e) cls.newInstance());
                }
            } catch (ClassNotFoundException e) {
                Log.w("ComponentDiscovery", String.format("Class %s is not an found.", new Object[]{str}), e);
            } catch (IllegalAccessException e2) {
                Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", new Object[]{str}), e2);
            } catch (InstantiationException e3) {
                Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", new Object[]{str}), e3);
            }
        }
        return arrayList;
    }
}
