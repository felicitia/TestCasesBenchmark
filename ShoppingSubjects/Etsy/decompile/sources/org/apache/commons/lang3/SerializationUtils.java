package org.apache.commons.lang3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;

public class SerializationUtils {

    static class ClassLoaderAwareObjectInputStream extends ObjectInputStream {
        private ClassLoader classLoader;

        public ClassLoaderAwareObjectInputStream(InputStream inputStream, ClassLoader classLoader2) throws IOException {
            super(inputStream);
            this.classLoader = classLoader2;
        }

        /* access modifiers changed from: protected */
        public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
            String name = objectStreamClass.getName();
            try {
                return Class.forName(name, false, this.classLoader);
            } catch (ClassNotFoundException unused) {
                return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x004f A[SYNTHETIC, Splitter:B:30:0x004f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T extends java.io.Serializable> T clone(T r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L_0x0004
            return r0
        L_0x0004:
            byte[] r1 = serialize(r3)
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r1)
            org.apache.commons.lang3.SerializationUtils$ClassLoaderAwareObjectInputStream r1 = new org.apache.commons.lang3.SerializationUtils$ClassLoaderAwareObjectInputStream     // Catch:{ ClassNotFoundException -> 0x0044, IOException -> 0x003b }
            java.lang.Class r3 = r3.getClass()     // Catch:{ ClassNotFoundException -> 0x0044, IOException -> 0x003b }
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0044, IOException -> 0x003b }
            r1.<init>(r2, r3)     // Catch:{ ClassNotFoundException -> 0x0044, IOException -> 0x003b }
            java.lang.Object r3 = r1.readObject()     // Catch:{ ClassNotFoundException -> 0x0036, IOException -> 0x0033, all -> 0x0030 }
            java.io.Serializable r3 = (java.io.Serializable) r3     // Catch:{ ClassNotFoundException -> 0x0036, IOException -> 0x0033, all -> 0x0030 }
            if (r1 == 0) goto L_0x002f
            r1.close()     // Catch:{ IOException -> 0x0026 }
            goto L_0x002f
        L_0x0026:
            r3 = move-exception
            org.apache.commons.lang3.SerializationException r0 = new org.apache.commons.lang3.SerializationException
            java.lang.String r1 = "IOException on closing cloned object data InputStream."
            r0.<init>(r1, r3)
            throw r0
        L_0x002f:
            return r3
        L_0x0030:
            r3 = move-exception
            r0 = r1
            goto L_0x004d
        L_0x0033:
            r3 = move-exception
            r0 = r1
            goto L_0x003c
        L_0x0036:
            r3 = move-exception
            r0 = r1
            goto L_0x0045
        L_0x0039:
            r3 = move-exception
            goto L_0x004d
        L_0x003b:
            r3 = move-exception
        L_0x003c:
            org.apache.commons.lang3.SerializationException r1 = new org.apache.commons.lang3.SerializationException     // Catch:{ all -> 0x0039 }
            java.lang.String r2 = "IOException while reading cloned object data"
            r1.<init>(r2, r3)     // Catch:{ all -> 0x0039 }
            throw r1     // Catch:{ all -> 0x0039 }
        L_0x0044:
            r3 = move-exception
        L_0x0045:
            org.apache.commons.lang3.SerializationException r1 = new org.apache.commons.lang3.SerializationException     // Catch:{ all -> 0x0039 }
            java.lang.String r2 = "ClassNotFoundException while reading cloned object data"
            r1.<init>(r2, r3)     // Catch:{ all -> 0x0039 }
            throw r1     // Catch:{ all -> 0x0039 }
        L_0x004d:
            if (r0 == 0) goto L_0x005c
            r0.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x005c
        L_0x0053:
            r3 = move-exception
            org.apache.commons.lang3.SerializationException r0 = new org.apache.commons.lang3.SerializationException
            java.lang.String r1 = "IOException on closing cloned object data InputStream."
            r0.<init>(r1, r3)
            throw r0
        L_0x005c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.SerializationUtils.clone(java.io.Serializable):java.io.Serializable");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x002a A[SYNTHETIC, Splitter:B:22:0x002a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void serialize(java.io.Serializable r2, java.io.OutputStream r3) {
        /*
            if (r3 != 0) goto L_0x000a
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "The OutputStream must not be null"
            r2.<init>(r3)
            throw r2
        L_0x000a:
            r0 = 0
            java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0021 }
            r1.<init>(r3)     // Catch:{ IOException -> 0x0021 }
            r1.writeObject(r2)     // Catch:{ IOException -> 0x001c, all -> 0x0019 }
            if (r1 == 0) goto L_0x0018
            r1.close()     // Catch:{ IOException -> 0x0018 }
        L_0x0018:
            return
        L_0x0019:
            r2 = move-exception
            r0 = r1
            goto L_0x0028
        L_0x001c:
            r2 = move-exception
            r0 = r1
            goto L_0x0022
        L_0x001f:
            r2 = move-exception
            goto L_0x0028
        L_0x0021:
            r2 = move-exception
        L_0x0022:
            org.apache.commons.lang3.SerializationException r3 = new org.apache.commons.lang3.SerializationException     // Catch:{ all -> 0x001f }
            r3.<init>(r2)     // Catch:{ all -> 0x001f }
            throw r3     // Catch:{ all -> 0x001f }
        L_0x0028:
            if (r0 == 0) goto L_0x002d
            r0.close()     // Catch:{ IOException -> 0x002d }
        L_0x002d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.SerializationUtils.serialize(java.io.Serializable, java.io.OutputStream):void");
    }

    public static byte[] serialize(Serializable serializable) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        serialize(serializable, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0035 A[SYNTHETIC, Splitter:B:28:0x0035] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object deserialize(java.io.InputStream r2) {
        /*
            if (r2 != 0) goto L_0x000a
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "The InputStream must not be null"
            r2.<init>(r0)
            throw r2
        L_0x000a:
            r0 = 0
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ ClassNotFoundException -> 0x002c, IOException -> 0x0025 }
            r1.<init>(r2)     // Catch:{ ClassNotFoundException -> 0x002c, IOException -> 0x0025 }
            java.lang.Object r2 = r1.readObject()     // Catch:{ ClassNotFoundException -> 0x0020, IOException -> 0x001d, all -> 0x001a }
            if (r1 == 0) goto L_0x0019
            r1.close()     // Catch:{ IOException -> 0x0019 }
        L_0x0019:
            return r2
        L_0x001a:
            r2 = move-exception
            r0 = r1
            goto L_0x0033
        L_0x001d:
            r2 = move-exception
            r0 = r1
            goto L_0x0026
        L_0x0020:
            r2 = move-exception
            r0 = r1
            goto L_0x002d
        L_0x0023:
            r2 = move-exception
            goto L_0x0033
        L_0x0025:
            r2 = move-exception
        L_0x0026:
            org.apache.commons.lang3.SerializationException r1 = new org.apache.commons.lang3.SerializationException     // Catch:{ all -> 0x0023 }
            r1.<init>(r2)     // Catch:{ all -> 0x0023 }
            throw r1     // Catch:{ all -> 0x0023 }
        L_0x002c:
            r2 = move-exception
        L_0x002d:
            org.apache.commons.lang3.SerializationException r1 = new org.apache.commons.lang3.SerializationException     // Catch:{ all -> 0x0023 }
            r1.<init>(r2)     // Catch:{ all -> 0x0023 }
            throw r1     // Catch:{ all -> 0x0023 }
        L_0x0033:
            if (r0 == 0) goto L_0x0038
            r0.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0038:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.SerializationUtils.deserialize(java.io.InputStream):java.lang.Object");
    }

    public static Object deserialize(byte[] bArr) {
        if (bArr != null) {
            return deserialize((InputStream) new ByteArrayInputStream(bArr));
        }
        throw new IllegalArgumentException("The byte[] must not be null");
    }
}
