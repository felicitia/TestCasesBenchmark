package com.salesforce.marketingcloud.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArrayMap;
import com.salesforce.marketingcloud.e.f;
import com.salesforce.marketingcloud.i;
import com.salesforce.marketingcloud.j;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@RestrictTo({Scope.LIBRARY})
public class c extends i {
    /* access modifiers changed from: private */
    public static final String a = j.a(c.class);
    private final ArrayMap<a, Set<b>> b = new ArrayMap<>();
    private final Map<a, Bundle> c = new ArrayMap(1);
    private final Context d;
    private BroadcastReceiver e;

    private class a extends BroadcastReceiver {
        private a() {
        }

        public void onReceive(Context context, Intent intent) {
            String a2;
            String str;
            if (intent == null) {
                a2 = c.a;
                str = "Received null intent";
            } else {
                String action = intent.getAction();
                if (action == null) {
                    a2 = c.a;
                    str = "Received null action";
                } else {
                    a a3 = a.a(action);
                    if (a3 != null) {
                        c.this.a(a3, intent.getExtras());
                    }
                    return;
                }
            }
            j.a(a2, str, new Object[0]);
        }
    }

    public c(Context context) {
        this.d = context;
    }

    public static void a(@NonNull Context context, @NonNull a aVar, @Nullable Bundle bundle) {
        f.a(context, "Context is null");
        f.a(aVar, "Behavior is null");
        Intent intent = new Intent(aVar.n);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void a(@NonNull a aVar, @Nullable Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putLong("timestamp", System.currentTimeMillis());
        j.b(a, "Behavior found: %s", aVar.name());
        synchronized (this.b) {
            Set set = (Set) this.b.get(aVar);
            if (set != null && !set.isEmpty()) {
                for (b a2 : (b[]) set.toArray(new b[set.size()])) {
                    a2.a(aVar, bundle);
                }
            }
        }
        if (aVar.o) {
            synchronized (this.c) {
                this.c.put(aVar, bundle);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(com.salesforce.marketingcloud.InitializationStatus.a aVar) {
        this.e = new a();
        IntentFilter intentFilter = new IntentFilter();
        for (a aVar2 : a.values()) {
            intentFilter.addAction(aVar2.n);
        }
        LocalBroadcastManager.getInstance(this.d).registerReceiver(this.e, intentFilter);
    }

    public void a(b bVar) {
        synchronized (this.b) {
            for (Entry value : this.b.entrySet()) {
                ((Set) value.getValue()).remove(bVar);
            }
        }
    }

    public void a(@NonNull b bVar, @NonNull EnumSet<a> enumSet) {
        f.a(bVar, "BehaviorListener is null");
        f.a(enumSet, "Behavior set is null");
        synchronized (this.b) {
            j.b(a, "Registering %s for behaviors: %s", bVar.getClass().getName(), enumSet.toString());
            Iterator it = enumSet.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                Set set = (Set) this.b.get(aVar);
                if (set == null) {
                    set = new HashSet();
                    this.b.put(aVar, set);
                }
                set.add(bVar);
            }
        }
        synchronized (this.c) {
            Iterator it2 = enumSet.iterator();
            while (it2.hasNext()) {
                a aVar2 = (a) it2.next();
                if (aVar2.o && this.c.containsKey(aVar2)) {
                    bVar.a(aVar2, (Bundle) this.c.get(aVar2));
                }
            }
        }
    }

    public final void a(boolean z) {
        if (this.d != null) {
            LocalBroadcastManager.getInstance(this.d).unregisterReceiver(this.e);
        }
    }

    @NonNull
    public final String b() {
        return "BehaviorManager";
    }
}
