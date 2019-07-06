package androidx.work;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class Data {
    public static final Data a = new a().a();
    Map<String, Object> b;

    public static final class a {
        private Map<String, Object> a = new HashMap();

        @NonNull
        public a a(@NonNull String str, String str2) {
            this.a.put(str, str2);
            return this;
        }

        @NonNull
        public a a(@NonNull Data data) {
            a(data.b);
            return this;
        }

        @NonNull
        public a a(@NonNull Map<String, Object> map) {
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    this.a.put(str, null);
                } else {
                    Class<double[]> cls = value.getClass();
                    if (cls == Boolean.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == String.class || cls == Boolean[].class || cls == Integer[].class || cls == Long[].class || cls == Float[].class || cls == Double[].class || cls == String[].class) {
                        this.a.put(str, value);
                    } else if (cls == boolean[].class) {
                        this.a.put(str, Data.a((boolean[]) value));
                    } else if (cls == int[].class) {
                        this.a.put(str, Data.a((int[]) value));
                    } else if (cls == long[].class) {
                        this.a.put(str, Data.a((long[]) value));
                    } else if (cls == float[].class) {
                        this.a.put(str, Data.a((float[]) value));
                    } else if (cls == double[].class) {
                        this.a.put(str, Data.a((double[]) value));
                    } else {
                        throw new IllegalArgumentException(String.format("Key %s has invalid type %s", new Object[]{str, cls}));
                    }
                }
            }
            return this;
        }

        @NonNull
        public Data a() {
            return new Data(this.a);
        }
    }

    Data() {
    }

    public Data(@NonNull Data data) {
        this.b = new HashMap(data.b);
    }

    Data(Map<String, ?> map) {
        this.b = new HashMap(map);
    }

    @Nullable
    public String a(@NonNull String str) {
        Object obj = this.b.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    @NonNull
    public Map<String, Object> a() {
        return Collections.unmodifiableMap(this.b);
    }

    @VisibleForTesting
    public int b() {
        return this.b.size();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0055 A[SYNTHETIC, Splitter:B:24:0x0055] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x007c A[SYNTHETIC, Splitter:B:39:0x007c] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:13:0x0043=Splitter:B:13:0x0043, B:28:0x005d=Splitter:B:28:0x005d} */
    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(@android.support.annotation.NonNull androidx.work.Data r4) throws java.lang.IllegalStateException {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x004f }
            r2.<init>(r0)     // Catch:{ IOException -> 0x004f }
            int r1 = r4.b()     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            r2.writeInt(r1)     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            java.util.Map<java.lang.String, java.lang.Object> r4 = r4.b     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            java.util.Set r4 = r4.entrySet()     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
        L_0x001c:
            boolean r1 = r4.hasNext()     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            if (r1 == 0) goto L_0x0039
            java.lang.Object r1 = r4.next()     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            java.lang.Object r3 = r1.getKey()     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            r2.writeUTF(r3)     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            r2.writeObject(r1)     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            goto L_0x001c
        L_0x0039:
            if (r2 == 0) goto L_0x0043
            r2.close()     // Catch:{ IOException -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r4 = move-exception
            com.google.a.a.a.a.a.a.a(r4)
        L_0x0043:
            r0.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x0065
        L_0x0047:
            r4 = move-exception
            goto L_0x007a
        L_0x0049:
            r4 = move-exception
            r1 = r2
            goto L_0x0050
        L_0x004c:
            r4 = move-exception
            r2 = r1
            goto L_0x007a
        L_0x004f:
            r4 = move-exception
        L_0x0050:
            com.google.a.a.a.a.a.a.a(r4)     // Catch:{ all -> 0x004c }
            if (r1 == 0) goto L_0x005d
            r1.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x005d
        L_0x0059:
            r4 = move-exception
            com.google.a.a.a.a.a.a.a(r4)
        L_0x005d:
            r0.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x0065
        L_0x0061:
            r4 = move-exception
            com.google.a.a.a.a.a.a.a(r4)
        L_0x0065:
            int r4 = r0.size()
            r1 = 10240(0x2800, float:1.4349E-41)
            if (r4 <= r1) goto L_0x0075
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "Data cannot occupy more than 10240KB when serialized"
            r4.<init>(r0)
            throw r4
        L_0x0075:
            byte[] r4 = r0.toByteArray()
            return r4
        L_0x007a:
            if (r2 == 0) goto L_0x0084
            r2.close()     // Catch:{ IOException -> 0x0080 }
            goto L_0x0084
        L_0x0080:
            r1 = move-exception
            com.google.a.a.a.a.a.a.a(r1)
        L_0x0084:
            r0.close()     // Catch:{ IOException -> 0x0088 }
            goto L_0x008c
        L_0x0088:
            r0 = move-exception
            com.google.a.a.a.a.a.a.a(r0)
        L_0x008c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.Data.a(androidx.work.Data):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x004e A[SYNTHETIC, Splitter:B:27:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0067 A[SYNTHETIC, Splitter:B:39:0x0067] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x0056=Splitter:B:31:0x0056, B:17:0x003b=Splitter:B:17:0x003b} */
    @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.work.Data a(@android.support.annotation.NonNull byte[] r6) throws java.lang.IllegalStateException {
        /*
            r0 = 10240(0x2800, float:1.4349E-41)
            int r1 = r6.length
            if (r1 <= r0) goto L_0x000d
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "Data cannot occupy more than 10240KB when serialized"
            r6.<init>(r0)
            throw r6
        L_0x000d:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            r1.<init>(r6)
            r6 = 0
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ IOException | ClassNotFoundException -> 0x0045, all -> 0x0041 }
            r2.<init>(r1)     // Catch:{ IOException | ClassNotFoundException -> 0x0045, all -> 0x0041 }
            int r6 = r2.readInt()     // Catch:{ IOException | ClassNotFoundException -> 0x003f }
        L_0x0021:
            if (r6 <= 0) goto L_0x0031
            java.lang.String r3 = r2.readUTF()     // Catch:{ IOException | ClassNotFoundException -> 0x003f }
            java.lang.Object r4 = r2.readObject()     // Catch:{ IOException | ClassNotFoundException -> 0x003f }
            r0.put(r3, r4)     // Catch:{ IOException | ClassNotFoundException -> 0x003f }
            int r6 = r6 + -1
            goto L_0x0021
        L_0x0031:
            if (r2 == 0) goto L_0x003b
            r2.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r6 = move-exception
            com.google.a.a.a.a.a.a.a(r6)
        L_0x003b:
            r1.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x005e
        L_0x003f:
            r6 = move-exception
            goto L_0x0049
        L_0x0041:
            r0 = move-exception
            r2 = r6
            r6 = r0
            goto L_0x0065
        L_0x0045:
            r2 = move-exception
            r5 = r2
            r2 = r6
            r6 = r5
        L_0x0049:
            com.google.a.a.a.a.a.a.a(r6)     // Catch:{ all -> 0x0064 }
            if (r2 == 0) goto L_0x0056
            r2.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r6 = move-exception
            com.google.a.a.a.a.a.a.a(r6)
        L_0x0056:
            r1.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r6 = move-exception
            com.google.a.a.a.a.a.a.a(r6)
        L_0x005e:
            androidx.work.Data r6 = new androidx.work.Data
            r6.<init>(r0)
            return r6
        L_0x0064:
            r6 = move-exception
        L_0x0065:
            if (r2 == 0) goto L_0x006f
            r2.close()     // Catch:{ IOException -> 0x006b }
            goto L_0x006f
        L_0x006b:
            r0 = move-exception
            com.google.a.a.a.a.a.a.a(r0)
        L_0x006f:
            r1.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r0 = move-exception
            com.google.a.a.a.a.a.a.a(r0)
        L_0x0077:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.Data.a(byte[]):androidx.work.Data");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.b.equals(((Data) obj).b);
    }

    public int hashCode() {
        return 31 * this.b.hashCode();
    }

    static Boolean[] a(boolean[] zArr) {
        Boolean[] boolArr = new Boolean[zArr.length];
        for (int i = 0; i < zArr.length; i++) {
            boolArr[i] = Boolean.valueOf(zArr[i]);
        }
        return boolArr;
    }

    static Integer[] a(int[] iArr) {
        Integer[] numArr = new Integer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    static Long[] a(long[] jArr) {
        Long[] lArr = new Long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    static Float[] a(float[] fArr) {
        Float[] fArr2 = new Float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return fArr2;
    }

    static Double[] a(double[] dArr) {
        Double[] dArr2 = new Double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return dArr2;
    }
}
