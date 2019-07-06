package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.ao;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@bu
public final class afz {
    private final Object a = new Object();
    private int b;
    private List<afy> c = new LinkedList();

    @Nullable
    public final afy a() {
        synchronized (this.a) {
            afy afy = null;
            if (this.c.size() == 0) {
                gv.b("Queue empty");
                return null;
            }
            int i = 0;
            if (this.c.size() >= 2) {
                int i2 = Integer.MIN_VALUE;
                int i3 = 0;
                for (afy afy2 : this.c) {
                    int i4 = afy2.i();
                    if (i4 > i2) {
                        i = i3;
                        afy = afy2;
                        i2 = i4;
                    }
                    i3++;
                }
                this.c.remove(i);
                return afy;
            }
            afy afy3 = (afy) this.c.get(0);
            afy3.e();
            return afy3;
        }
    }

    public final boolean a(afy afy) {
        synchronized (this.a) {
            return this.c.contains(afy);
        }
    }

    public final boolean b(afy afy) {
        synchronized (this.a) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                afy afy2 = (afy) it.next();
                if (!((Boolean) ajh.f().a(akl.W)).booleanValue() || ao.i().l().b()) {
                    if (((Boolean) ajh.f().a(akl.Y)).booleanValue() && !ao.i().l().d() && afy != afy2 && afy2.d().equals(afy.d())) {
                        it.remove();
                        return true;
                    }
                } else if (afy != afy2 && afy2.b().equals(afy.b())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public final void c(afy afy) {
        synchronized (this.a) {
            if (this.c.size() >= 10) {
                int size = this.c.size();
                StringBuilder sb = new StringBuilder(41);
                sb.append("Queue is full, current size = ");
                sb.append(size);
                gv.b(sb.toString());
                this.c.remove(0);
            }
            int i = this.b;
            this.b = i + 1;
            afy.a(i);
            this.c.add(afy);
        }
    }
}
