package android.arch.persistence.db;

import android.support.annotation.Nullable;

public final class SimpleSQLiteQuery implements d {
    @Nullable
    private final Object[] mBindArgs;
    private final String mQuery;

    public SimpleSQLiteQuery(String str, @Nullable Object[] objArr) {
        this.mQuery = str;
        this.mBindArgs = objArr;
    }

    public SimpleSQLiteQuery(String str) {
        this(str, null);
    }

    public String getSql() {
        return this.mQuery;
    }

    public void bindTo(c cVar) {
        bind(cVar, this.mBindArgs);
    }

    public int getArgCount() {
        if (this.mBindArgs == null) {
            return 0;
        }
        return this.mBindArgs.length;
    }

    public static void bind(c cVar, Object[] objArr) {
        if (objArr != null) {
            int length = objArr.length;
            int i = 0;
            while (i < length) {
                Object obj = objArr[i];
                i++;
                bind(cVar, i, obj);
            }
        }
    }

    private static void bind(c cVar, int i, Object obj) {
        if (obj == null) {
            cVar.bindNull(i);
        } else if (obj instanceof byte[]) {
            cVar.bindBlob(i, (byte[]) obj);
        } else if (obj instanceof Float) {
            cVar.bindDouble(i, (double) ((Float) obj).floatValue());
        } else if (obj instanceof Double) {
            cVar.bindDouble(i, ((Double) obj).doubleValue());
        } else if (obj instanceof Long) {
            cVar.bindLong(i, ((Long) obj).longValue());
        } else if (obj instanceof Integer) {
            cVar.bindLong(i, (long) ((Integer) obj).intValue());
        } else if (obj instanceof Short) {
            cVar.bindLong(i, (long) ((Short) obj).shortValue());
        } else if (obj instanceof Byte) {
            cVar.bindLong(i, (long) ((Byte) obj).byteValue());
        } else if (obj instanceof String) {
            cVar.bindString(i, (String) obj);
        } else if (obj instanceof Boolean) {
            cVar.bindLong(i, ((Boolean) obj).booleanValue() ? 1 : 0);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot bind ");
            sb.append(obj);
            sb.append(" at index ");
            sb.append(i);
            sb.append(" Supported types: null, byte[], float, double, long, int, short, byte,");
            sb.append(" string");
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
