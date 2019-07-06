package com.salesforce.marketingcloud.messages.a;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.util.ArraySet;
import com.salesforce.marketingcloud.j;
import com.salesforce.marketingcloud.messages.c.b;
import com.salesforce.marketingcloud.messages.c.b.C0170b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Deprecated
public class c implements C0170b {
    private static final String a = j.a(c.class);
    private final b b;
    private final Set<a> c = new ArraySet();

    public interface a {
        void a(@NonNull List<b> list);
    }

    @RestrictTo({Scope.LIBRARY})
    public c(b bVar) {
        this.b = bVar;
    }

    @RestrictTo({Scope.LIBRARY})
    public void a(@NonNull List<com.salesforce.marketingcloud.messages.c.a> list) {
        if (this.c.size() > 0) {
            List emptyList = Collections.emptyList();
            if (list.size() > 0) {
                emptyList = new ArrayList(list.size());
                for (com.salesforce.marketingcloud.messages.c.a aVar : list) {
                    emptyList.add((b) aVar);
                }
            }
            synchronized (this.c) {
                for (a aVar2 : this.c) {
                    if (aVar2 != null) {
                        try {
                            aVar2.a(emptyList);
                        } catch (Exception e) {
                            j.c(a, e, "%s threw an exception while processing the cloud pages response", aVar2.getClass().getName());
                        }
                    }
                }
            }
        }
    }
}
