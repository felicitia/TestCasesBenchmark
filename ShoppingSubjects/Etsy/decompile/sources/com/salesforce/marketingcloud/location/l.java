package com.salesforce.marketingcloud.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArraySet;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.j;
import java.util.List;
import java.util.Set;

class l extends k {
    final h b;
    /* access modifiers changed from: private */
    public final Set<j> c = new ArraySet();
    private final Set<g> d = new ArraySet();
    private b e;
    private int f;
    private int g;
    private String h;
    private int i;
    private Context j;
    private BroadcastReceiver k;

    private class a extends BroadcastReceiver {
        private a() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:25:0x0050  */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x005c  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0072  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x0096  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceive(android.content.Context r6, android.content.Intent r7) {
            /*
                r5 = this;
                r6 = 0
                if (r7 != 0) goto L_0x000d
                java.lang.String r7 = com.salesforce.marketingcloud.location.k.a
                java.lang.String r0 = "Received null intent"
            L_0x0007:
                java.lang.Object[] r6 = new java.lang.Object[r6]
                com.salesforce.marketingcloud.j.a(r7, r0, r6)
                return
            L_0x000d:
                java.lang.String r0 = r7.getAction()
                if (r0 != 0) goto L_0x0018
                java.lang.String r7 = com.salesforce.marketingcloud.location.k.a
                java.lang.String r0 = "Received null action"
                goto L_0x0007
            L_0x0018:
                int r1 = r0.hashCode()
                r2 = -284548713(0xffffffffef0a2197, float:-4.274954E28)
                r3 = 1
                r4 = -1
                if (r1 == r2) goto L_0x0042
                r2 = 557677285(0x213d7ae5, float:6.419834E-19)
                if (r1 == r2) goto L_0x0038
                r2 = 557783927(0x213f1b77, float:6.4749667E-19)
                if (r1 == r2) goto L_0x002e
                goto L_0x004c
            L_0x002e:
                java.lang.String r1 = "com.salesforce.marketingcloud.location.GEOFENCE_EVENT"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x004c
                r1 = r3
                goto L_0x004d
            L_0x0038:
                java.lang.String r1 = "com.salesforce.marketingcloud.location.GEOFENCE_ERROR"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x004c
                r1 = 2
                goto L_0x004d
            L_0x0042:
                java.lang.String r1 = "com.salesforce.marketingcloud.location.LOCATION_UPDATE"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x004c
                r1 = r6
                goto L_0x004d
            L_0x004c:
                r1 = r4
            L_0x004d:
                switch(r1) {
                    case 0: goto L_0x0096;
                    case 1: goto L_0x0072;
                    case 2: goto L_0x005c;
                    default: goto L_0x0050;
                }
            L_0x0050:
                java.lang.String r7 = com.salesforce.marketingcloud.location.k.a
                java.lang.String r1 = "Received unknown action: %s"
                java.lang.Object[] r2 = new java.lang.Object[r3]
                r2[r6] = r0
                com.salesforce.marketingcloud.j.b(r7, r1, r2)
                return
            L_0x005c:
                java.lang.String r6 = "extra_error_code"
                int r6 = r7.getIntExtra(r6, r4)
                java.lang.String r0 = "extra_error_message"
                java.lang.String r7 = r7.getStringExtra(r0)
                if (r6 == r4) goto L_0x00ac
                if (r7 == 0) goto L_0x00ac
                com.salesforce.marketingcloud.location.l r0 = com.salesforce.marketingcloud.location.l.this
                r0.b(r6, r7)
                return
            L_0x0072:
                java.lang.String r0 = "extra_transition"
                int r0 = r7.getIntExtra(r0, r4)
                if (r0 != r4) goto L_0x007b
                return
            L_0x007b:
                java.lang.String r1 = com.salesforce.marketingcloud.location.k.a
                java.lang.String r2 = "Received geofence transition %d"
                java.lang.Object[] r3 = new java.lang.Object[r3]
                java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
                r3[r6] = r4
                com.salesforce.marketingcloud.j.b(r1, r2, r3)
                com.salesforce.marketingcloud.location.l r6 = com.salesforce.marketingcloud.location.l.this
                java.lang.String r1 = "extra_fence_ids"
                java.util.ArrayList r7 = r7.getStringArrayListExtra(r1)
                r6.b(r0, r7)
                return
            L_0x0096:
                java.lang.String r0 = com.salesforce.marketingcloud.location.k.a
                java.lang.String r1 = "Received location update."
                java.lang.Object[] r6 = new java.lang.Object[r6]
                com.salesforce.marketingcloud.j.b(r0, r1, r6)
                com.salesforce.marketingcloud.location.l r6 = com.salesforce.marketingcloud.location.l.this
                java.lang.String r0 = "extra_location"
                android.os.Parcelable r7 = r7.getParcelableExtra(r0)
                android.location.Location r7 = (android.location.Location) r7
                r6.b(r7)
            L_0x00ac:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.location.l.a.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    l(@NonNull Context context, b bVar) {
        this.j = context;
        this.b = new h(context);
        this.e = bVar;
    }

    /* access modifiers changed from: protected */
    public void a(com.salesforce.marketingcloud.InitializationStatus.a aVar) {
        this.k = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.salesforce.marketingcloud.location.LOCATION_UPDATE");
        intentFilter.addAction("com.salesforce.marketingcloud.location.GEOFENCE_EVENT");
        intentFilter.addAction("com.salesforce.marketingcloud.location.GEOFENCE_ERROR");
        LocalBroadcastManager.getInstance(this.j).registerReceiver(this.k, intentFilter);
        aVar.a(this.b.a());
        aVar.b(this.b.b());
        aVar.a(!this.b.c());
    }

    public void a(@NonNull g gVar) {
        j.a(a, "registerForGeofenceRegionEvents(%s)", gVar.getClass().getName());
        if (gVar != null) {
            synchronized (this.d) {
                this.d.add(gVar);
            }
        }
    }

    public void a(@NonNull j jVar) {
        boolean z;
        if (jVar != null) {
            synchronized (this.c) {
                z = this.c.add(jVar) && this.c.size() == 1;
            }
            if (z) {
                this.f++;
                this.h = jVar.getClass().getName();
                this.b.a((a) new a() {
                    public void a() {
                        l.this.b.e();
                    }

                    public void a(int i) {
                        j.b(k.a, "Failed to connect to GmsLocationProvider for location update. [%d]", Integer.valueOf(i));
                        synchronized (l.this.c) {
                            for (j jVar : l.this.c) {
                                if (jVar != null) {
                                    jVar.b(i);
                                }
                            }
                            l.this.c.clear();
                        }
                    }
                });
            }
        }
    }

    public void a(boolean z) {
        if (this.b != null) {
            if (z) {
                this.b.f();
            }
            this.b.d();
        }
        if (this.j != null && this.k != null) {
            LocalBroadcastManager.getInstance(this.j).unregisterReceiver(this.k);
        }
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public void a(final f... fVarArr) {
        if (fVarArr == null || fVarArr.length == 0) {
            j.b(a, "monitorGeofences - No geofenceRegions provided.", new Object[0]);
            return;
        }
        j.a(a, "Monitoring %s fence(s).", Integer.valueOf(fVarArr.length));
        this.b.a((a) new a() {
            public void a() {
                l.this.b.a(fVarArr);
            }

            public void a(int i) {
                j.b(k.a, "Failed to connect to GmsLocationProvider for Geofence monitoring. [%d]", Integer.valueOf(i));
            }
        });
    }

    public void a(final String... strArr) {
        if (strArr == null || strArr.length == 0) {
            j.c(a, "unmonitorGeofences - No geofenceRegionIds provided.", new Object[0]);
        } else {
            this.b.a((a) new a() {
                public void a() {
                    l.this.b.a(strArr);
                }

                public void a(int i) {
                    j.b(k.a, "Failed to connect to GmsLocationProvider unmonitor Geofences. [%d]", Integer.valueOf(i));
                }
            });
        }
    }

    public boolean a() {
        return this.b.c();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void b(int i2, String str) {
        synchronized (this.d) {
            if (!this.d.isEmpty()) {
                for (g gVar : this.d) {
                    if (gVar != null) {
                        gVar.a(i2, str);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void b(int i2, @NonNull List<String> list) {
        j.a(a, "onGeofenceRegionEvent", new Object[0]);
        if (list == null || list.isEmpty()) {
            j.c(a, "No fenceIds were provided.", new Object[0]);
            return;
        }
        this.i++;
        synchronized (this.d) {
            if (!this.d.isEmpty()) {
                for (g gVar : this.d) {
                    if (gVar != null) {
                        for (String str : list) {
                            j.b(a, "Notifiying %s of geofence [%s] region event [d]", gVar.getClass().getName(), str, Integer.valueOf(i2));
                            gVar.a(str, i2);
                        }
                    }
                }
            } else {
                j.c(a, "Geofence region event occured with no one listening.", new Object[0]);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void b(Location location) {
        if (location != null) {
            this.g++;
            synchronized (this.c) {
                if (!this.c.isEmpty()) {
                    for (j jVar : this.c) {
                        if (jVar != null) {
                            jVar.a(location);
                        }
                    }
                    this.c.clear();
                }
            }
        }
    }

    public void b(@NonNull g gVar) {
        if (gVar != null) {
            synchronized (this.d) {
                this.d.remove(gVar);
            }
        }
    }

    public void b(@NonNull j jVar) {
        synchronized (this.c) {
            this.c.remove(jVar);
        }
    }
}
