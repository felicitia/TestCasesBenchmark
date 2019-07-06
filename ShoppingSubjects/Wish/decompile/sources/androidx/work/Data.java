package androidx.work;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class Data {
    public static final Data EMPTY = new Builder().build();
    /* access modifiers changed from: private */
    public Map<String, Object> mValues;

    public static final class Builder {
        private Map<String, Object> mValues = new HashMap();

        public Builder putString(String str, String str2) {
            this.mValues.put(str, str2);
            return this;
        }

        public Builder putAll(Data data) {
            putAll(data.mValues);
            return this;
        }

        public Builder putAll(Map<String, Object> map) {
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    this.mValues.put(str, null);
                } else {
                    Class<double[]> cls = value.getClass();
                    if (cls == Boolean.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == String.class || cls == Boolean[].class || cls == Integer[].class || cls == Long[].class || cls == Float[].class || cls == Double[].class || cls == String[].class) {
                        this.mValues.put(str, value);
                    } else if (cls == boolean[].class) {
                        this.mValues.put(str, Data.convertPrimitiveBooleanArray((boolean[]) value));
                    } else if (cls == int[].class) {
                        this.mValues.put(str, Data.convertPrimitiveIntArray((int[]) value));
                    } else if (cls == long[].class) {
                        this.mValues.put(str, Data.convertPrimitiveLongArray((long[]) value));
                    } else if (cls == float[].class) {
                        this.mValues.put(str, Data.convertPrimitiveFloatArray((float[]) value));
                    } else if (cls == double[].class) {
                        this.mValues.put(str, Data.convertPrimitiveDoubleArray((double[]) value));
                    } else {
                        throw new IllegalArgumentException(String.format("Key %s has invalid type %s", new Object[]{str, cls}));
                    }
                }
            }
            return this;
        }

        public Data build() {
            return new Data(this.mValues);
        }
    }

    Data() {
    }

    Data(Map<String, ?> map) {
        this.mValues = new HashMap(map);
    }

    public String getString(String str, String str2) {
        Object obj = this.mValues.get(str);
        return obj instanceof String ? (String) obj : str2;
    }

    public Map<String, Object> getKeyValueMap() {
        return Collections.unmodifiableMap(this.mValues);
    }

    public int size() {
        return this.mValues.size();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0055 A[SYNTHETIC, Splitter:B:24:0x0055] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x007c A[SYNTHETIC, Splitter:B:39:0x007c] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:13:0x0043=Splitter:B:13:0x0043, B:28:0x005d=Splitter:B:28:0x005d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] toByteArray(androidx.work.Data r4) throws java.lang.IllegalStateException {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x004f }
            r2.<init>(r0)     // Catch:{ IOException -> 0x004f }
            int r1 = r4.size()     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            r2.writeInt(r1)     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
            java.util.Map<java.lang.String, java.lang.Object> r4 = r4.mValues     // Catch:{ IOException -> 0x0049, all -> 0x0047 }
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
            r4.printStackTrace()
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
            r4.printStackTrace()     // Catch:{ all -> 0x004c }
            if (r1 == 0) goto L_0x005d
            r1.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x005d
        L_0x0059:
            r4 = move-exception
            r4.printStackTrace()
        L_0x005d:
            r0.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x0065
        L_0x0061:
            r4 = move-exception
            r4.printStackTrace()
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
            r1.printStackTrace()
        L_0x0084:
            r0.close()     // Catch:{ IOException -> 0x0088 }
            goto L_0x008c
        L_0x0088:
            r0 = move-exception
            r0.printStackTrace()
        L_0x008c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.Data.toByteArray(androidx.work.Data):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x004e A[SYNTHETIC, Splitter:B:27:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0067 A[SYNTHETIC, Splitter:B:39:0x0067] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x0056=Splitter:B:31:0x0056, B:17:0x003b=Splitter:B:17:0x003b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.work.Data fromByteArray(byte[] r6) throws java.lang.IllegalStateException {
        /*
            int r0 = r6.length
            r1 = 10240(0x2800, float:1.4349E-41)
            if (r0 <= r1) goto L_0x000d
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
            r6.printStackTrace()
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
            r6.printStackTrace()     // Catch:{ all -> 0x0064 }
            if (r2 == 0) goto L_0x0056
            r2.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0056:
            r1.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r6 = move-exception
            r6.printStackTrace()
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
            r0.printStackTrace()
        L_0x006f:
            r1.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0077:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.Data.fromByteArray(byte[]):androidx.work.Data");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.mValues.equals(((Data) obj).mValues);
    }

    public int hashCode() {
        return this.mValues.hashCode() * 31;
    }

    /* access modifiers changed from: private */
    public static Boolean[] convertPrimitiveBooleanArray(boolean[] zArr) {
        Boolean[] boolArr = new Boolean[zArr.length];
        for (int i = 0; i < zArr.length; i++) {
            boolArr[i] = Boolean.valueOf(zArr[i]);
        }
        return boolArr;
    }

    /* access modifiers changed from: private */
    public static Integer[] convertPrimitiveIntArray(int[] iArr) {
        Integer[] numArr = new Integer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    /* access modifiers changed from: private */
    public static Long[] convertPrimitiveLongArray(long[] jArr) {
        Long[] lArr = new Long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    /* access modifiers changed from: private */
    public static Float[] convertPrimitiveFloatArray(float[] fArr) {
        Float[] fArr2 = new Float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return fArr2;
    }

    /* access modifiers changed from: private */
    public static Double[] convertPrimitiveDoubleArray(double[] dArr) {
        Double[] dArr2 = new Double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return dArr2;
    }
}
