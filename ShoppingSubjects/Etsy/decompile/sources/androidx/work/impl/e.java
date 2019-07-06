package androidx.work.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import androidx.work.ExistingWorkPolicy;
import androidx.work.i;
import androidx.work.impl.utils.b;
import androidx.work.k;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: WorkContinuationImpl */
public class e extends i {
    private final f a;
    private final String b;
    private final ExistingWorkPolicy c;
    private final List<? extends k> d;
    private final List<String> e;
    private final List<String> f;
    private final List<e> g;
    private boolean h;

    @NonNull
    public f a() {
        return this.a;
    }

    @Nullable
    public String b() {
        return this.b;
    }

    public ExistingWorkPolicy c() {
        return this.c;
    }

    @NonNull
    public List<? extends k> d() {
        return this.d;
    }

    @NonNull
    public List<String> e() {
        return this.e;
    }

    public boolean f() {
        return this.h;
    }

    public void g() {
        this.h = true;
    }

    public List<e> h() {
        return this.g;
    }

    e(@NonNull f fVar, @NonNull List<? extends k> list) {
        this(fVar, null, ExistingWorkPolicy.KEEP, list, null);
    }

    e(@NonNull f fVar, String str, ExistingWorkPolicy existingWorkPolicy, @NonNull List<? extends k> list, @Nullable List<e> list2) {
        this.a = fVar;
        this.b = str;
        this.c = existingWorkPolicy;
        this.d = list;
        this.g = list2;
        this.e = new ArrayList(this.d.size());
        this.f = new ArrayList();
        if (list2 != null) {
            for (e eVar : list2) {
                this.f.addAll(eVar.f);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String a2 = ((k) list.get(i)).a();
            this.e.add(a2);
            this.f.add(a2);
        }
    }

    public void i() {
        if (!this.h) {
            this.a.h().b(new b(this));
            return;
        }
        androidx.work.e.d("WorkContinuationImpl", String.format("Already enqueued work ids (%s)", new Object[]{TextUtils.join(", ", this.e)}), new Throwable[0]);
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public boolean j() {
        return a(this, new HashSet());
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    private static boolean a(@NonNull e eVar, @NonNull Set<String> set) {
        set.addAll(eVar.e());
        Set a2 = a(eVar);
        for (String contains : set) {
            if (a2.contains(contains)) {
                return true;
            }
        }
        List<e> h2 = eVar.h();
        if (h2 != null && !h2.isEmpty()) {
            for (e a3 : h2) {
                if (a(a3, set)) {
                    return true;
                }
            }
        }
        set.removeAll(eVar.e());
        return false;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static Set<String> a(e eVar) {
        HashSet hashSet = new HashSet();
        List<e> h2 = eVar.h();
        if (h2 != null && !h2.isEmpty()) {
            for (e e2 : h2) {
                hashSet.addAll(e2.e());
            }
        }
        return hashSet;
    }
}
