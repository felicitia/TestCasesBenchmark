package com.facebook.appevents;

import android.content.Context;
import android.util.Log;
import com.facebook.appevents.internal.b;
import com.facebook.f;
import com.facebook.internal.z;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;

/* compiled from: AppEventStore */
class d {
    private static final String a = "com.facebook.appevents.d";

    /* compiled from: AppEventStore */
    private static class a extends ObjectInputStream {
        public a(InputStream inputStream) throws IOException {
            super(inputStream);
        }

        /* access modifiers changed from: protected */
        public ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
            ObjectStreamClass readClassDescriptor = super.readClassDescriptor();
            if (readClassDescriptor.getName().equals("com.facebook.appevents.AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1")) {
                return ObjectStreamClass.lookup(SerializationProxyV1.class);
            }
            return readClassDescriptor.getName().equals("com.facebook.appevents.AppEventsLogger$AppEvent$SerializationProxyV1") ? ObjectStreamClass.lookup(SerializationProxyV1.class) : readClassDescriptor;
        }
    }

    d() {
    }

    public static synchronized void a(AccessTokenAppIdPair accessTokenAppIdPair, f fVar) {
        synchronized (d.class) {
            b.a();
            PersistedEvents a2 = a();
            if (a2.containsKey(accessTokenAppIdPair)) {
                a2.get(accessTokenAppIdPair).addAll(fVar.b());
            } else {
                a2.addEvents(accessTokenAppIdPair, fVar.b());
            }
            a(a2);
        }
    }

    public static synchronized void a(b bVar) {
        synchronized (d.class) {
            b.a();
            PersistedEvents a2 = a();
            for (AccessTokenAppIdPair accessTokenAppIdPair : bVar.a()) {
                a2.addEvents(accessTokenAppIdPair, bVar.a(accessTokenAppIdPair).b());
            }
            a(a2);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r4v0, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r4v2 */
    /* JADX WARNING: type inference failed for: r2v4, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r4v5, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r4v7 */
    /* JADX WARNING: type inference failed for: r2v15 */
    /* JADX WARNING: type inference failed for: r4v10 */
    /* JADX WARNING: type inference failed for: r4v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x008a A[Catch:{ Exception -> 0x002e }] */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.facebook.appevents.PersistedEvents a() {
        /*
            java.lang.Class<com.facebook.appevents.d> r0 = com.facebook.appevents.d.class
            monitor-enter(r0)
            com.facebook.appevents.internal.b.a()     // Catch:{ all -> 0x0091 }
            android.content.Context r1 = com.facebook.f.f()     // Catch:{ all -> 0x0091 }
            r2 = 0
            java.lang.String r3 = "AppEventsLogger.persistedevents"
            java.io.FileInputStream r3 = r1.openFileInput(r3)     // Catch:{ FileNotFoundException -> 0x0074, Exception -> 0x003c, all -> 0x003a }
            com.facebook.appevents.d$a r4 = new com.facebook.appevents.d$a     // Catch:{ FileNotFoundException -> 0x0074, Exception -> 0x003c, all -> 0x003a }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x0074, Exception -> 0x003c, all -> 0x003a }
            r5.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0074, Exception -> 0x003c, all -> 0x003a }
            r4.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0074, Exception -> 0x003c, all -> 0x003a }
            java.lang.Object r3 = r4.readObject()     // Catch:{ FileNotFoundException -> 0x0075, Exception -> 0x0038 }
            com.facebook.appevents.PersistedEvents r3 = (com.facebook.appevents.PersistedEvents) r3     // Catch:{ FileNotFoundException -> 0x0075, Exception -> 0x0038 }
            com.facebook.internal.z.a(r4)     // Catch:{ all -> 0x0091 }
            java.lang.String r2 = "AppEventsLogger.persistedevents"
            java.io.File r1 = r1.getFileStreamPath(r2)     // Catch:{ Exception -> 0x002e }
            r1.delete()     // Catch:{ Exception -> 0x002e }
            goto L_0x0036
        L_0x002e:
            r1 = move-exception
            java.lang.String r2 = a     // Catch:{ all -> 0x0091 }
            java.lang.String r4 = "Got unexpected exception when removing events file: "
            android.util.Log.w(r2, r4, r1)     // Catch:{ all -> 0x0091 }
        L_0x0036:
            r2 = r3
            goto L_0x0088
        L_0x0038:
            r3 = move-exception
            goto L_0x003e
        L_0x003a:
            r3 = move-exception
            goto L_0x005e
        L_0x003c:
            r3 = move-exception
            r4 = r2
        L_0x003e:
            java.lang.String r5 = a     // Catch:{ all -> 0x005b }
            java.lang.String r6 = "Got unexpected exception while reading events: "
            android.util.Log.w(r5, r6, r3)     // Catch:{ all -> 0x005b }
            com.facebook.internal.z.a(r4)     // Catch:{ all -> 0x0091 }
            java.lang.String r3 = "AppEventsLogger.persistedevents"
            java.io.File r1 = r1.getFileStreamPath(r3)     // Catch:{ Exception -> 0x0052 }
            r1.delete()     // Catch:{ Exception -> 0x0052 }
            goto L_0x0088
        L_0x0052:
            r1 = move-exception
            java.lang.String r3 = a     // Catch:{ all -> 0x0091 }
            java.lang.String r4 = "Got unexpected exception when removing events file: "
        L_0x0057:
            android.util.Log.w(r3, r4, r1)     // Catch:{ all -> 0x0091 }
            goto L_0x0088
        L_0x005b:
            r2 = move-exception
            r3 = r2
            r2 = r4
        L_0x005e:
            com.facebook.internal.z.a(r2)     // Catch:{ all -> 0x0091 }
            java.lang.String r2 = "AppEventsLogger.persistedevents"
            java.io.File r1 = r1.getFileStreamPath(r2)     // Catch:{ Exception -> 0x006b }
            r1.delete()     // Catch:{ Exception -> 0x006b }
            goto L_0x0073
        L_0x006b:
            r1 = move-exception
            java.lang.String r2 = a     // Catch:{ all -> 0x0091 }
            java.lang.String r4 = "Got unexpected exception when removing events file: "
            android.util.Log.w(r2, r4, r1)     // Catch:{ all -> 0x0091 }
        L_0x0073:
            throw r3     // Catch:{ all -> 0x0091 }
        L_0x0074:
            r4 = r2
        L_0x0075:
            com.facebook.internal.z.a(r4)     // Catch:{ all -> 0x0091 }
            java.lang.String r3 = "AppEventsLogger.persistedevents"
            java.io.File r1 = r1.getFileStreamPath(r3)     // Catch:{ Exception -> 0x0082 }
            r1.delete()     // Catch:{ Exception -> 0x0082 }
            goto L_0x0088
        L_0x0082:
            r1 = move-exception
            java.lang.String r3 = a     // Catch:{ all -> 0x0091 }
            java.lang.String r4 = "Got unexpected exception when removing events file: "
            goto L_0x0057
        L_0x0088:
            if (r2 != 0) goto L_0x008f
            com.facebook.appevents.PersistedEvents r2 = new com.facebook.appevents.PersistedEvents     // Catch:{ all -> 0x0091 }
            r2.<init>()     // Catch:{ all -> 0x0091 }
        L_0x008f:
            monitor-exit(r0)
            return r2
        L_0x0091:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.d.a():com.facebook.appevents.PersistedEvents");
    }

    private static void a(PersistedEvents persistedEvents) {
        Context f = f.f();
        ObjectOutputStream objectOutputStream = null;
        try {
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new BufferedOutputStream(f.openFileOutput("AppEventsLogger.persistedevents", 0)));
            try {
                objectOutputStream2.writeObject(persistedEvents);
                z.a((Closeable) objectOutputStream2);
            } catch (Exception e) {
                e = e;
                objectOutputStream = objectOutputStream2;
                try {
                    Log.w(a, "Got unexpected exception while persisting events: ", e);
                    try {
                        f.getFileStreamPath("AppEventsLogger.persistedevents").delete();
                    } catch (Exception unused) {
                    }
                    z.a((Closeable) objectOutputStream);
                } catch (Throwable th) {
                    th = th;
                    z.a((Closeable) objectOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                objectOutputStream = objectOutputStream2;
                z.a((Closeable) objectOutputStream);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            Log.w(a, "Got unexpected exception while persisting events: ", e);
            f.getFileStreamPath("AppEventsLogger.persistedevents").delete();
            z.a((Closeable) objectOutputStream);
        }
    }
}
