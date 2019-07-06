package android.arch.persistence.room;

import android.arch.persistence.db.c;
import android.arch.persistence.db.d;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.annotation.VisibleForTesting;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

@RestrictTo({Scope.LIBRARY_GROUP})
public class RoomSQLiteQuery implements c, d {
    private static final int BLOB = 5;
    @VisibleForTesting
    static final int DESIRED_POOL_SIZE = 10;
    private static final int DOUBLE = 3;
    private static final int LONG = 2;
    private static final int NULL = 1;
    @VisibleForTesting
    static final int POOL_LIMIT = 15;
    private static final int STRING = 4;
    @VisibleForTesting
    static final TreeMap<Integer, RoomSQLiteQuery> sQueryPool = new TreeMap<>();
    @VisibleForTesting
    int mArgCount;
    private final int[] mBindingTypes;
    @VisibleForTesting
    final byte[][] mBlobBindings;
    @VisibleForTesting
    final int mCapacity;
    @VisibleForTesting
    final double[] mDoubleBindings;
    @VisibleForTesting
    final long[] mLongBindings;
    private volatile String mQuery;
    @VisibleForTesting
    final String[] mStringBindings;

    public void close() {
    }

    public static RoomSQLiteQuery copyFrom(d dVar) {
        RoomSQLiteQuery acquire = acquire(dVar.getSql(), dVar.getArgCount());
        dVar.bindTo(new c(acquire) {
            final /* synthetic */ RoomSQLiteQuery a;

            public void close() {
            }

            {
                this.a = r1;
            }

            public void bindNull(int i) {
                this.a.bindNull(i);
            }

            public void bindLong(int i, long j) {
                this.a.bindLong(i, j);
            }

            public void bindDouble(int i, double d) {
                this.a.bindDouble(i, d);
            }

            public void bindString(int i, String str) {
                this.a.bindString(i, str);
            }

            public void bindBlob(int i, byte[] bArr) {
                this.a.bindBlob(i, bArr);
            }
        });
        return acquire;
    }

    public static RoomSQLiteQuery acquire(String str, int i) {
        synchronized (sQueryPool) {
            Entry ceilingEntry = sQueryPool.ceilingEntry(Integer.valueOf(i));
            if (ceilingEntry != null) {
                sQueryPool.remove(ceilingEntry.getKey());
                RoomSQLiteQuery roomSQLiteQuery = (RoomSQLiteQuery) ceilingEntry.getValue();
                roomSQLiteQuery.init(str, i);
                return roomSQLiteQuery;
            }
            RoomSQLiteQuery roomSQLiteQuery2 = new RoomSQLiteQuery(i);
            roomSQLiteQuery2.init(str, i);
            return roomSQLiteQuery2;
        }
    }

    private RoomSQLiteQuery(int i) {
        this.mCapacity = i;
        int i2 = i + 1;
        this.mBindingTypes = new int[i2];
        this.mLongBindings = new long[i2];
        this.mDoubleBindings = new double[i2];
        this.mStringBindings = new String[i2];
        this.mBlobBindings = new byte[i2][];
    }

    /* access modifiers changed from: 0000 */
    public void init(String str, int i) {
        this.mQuery = str;
        this.mArgCount = i;
    }

    public void release() {
        synchronized (sQueryPool) {
            sQueryPool.put(Integer.valueOf(this.mCapacity), this);
            prunePoolLocked();
        }
    }

    private static void prunePoolLocked() {
        if (sQueryPool.size() > 15) {
            int size = sQueryPool.size() - 10;
            Iterator it = sQueryPool.descendingKeySet().iterator();
            while (true) {
                int i = size - 1;
                if (size > 0) {
                    it.next();
                    it.remove();
                    size = i;
                } else {
                    return;
                }
            }
        }
    }

    public String getSql() {
        return this.mQuery;
    }

    public int getArgCount() {
        return this.mArgCount;
    }

    public void bindTo(c cVar) {
        for (int i = 1; i <= this.mArgCount; i++) {
            switch (this.mBindingTypes[i]) {
                case 1:
                    cVar.bindNull(i);
                    break;
                case 2:
                    cVar.bindLong(i, this.mLongBindings[i]);
                    break;
                case 3:
                    cVar.bindDouble(i, this.mDoubleBindings[i]);
                    break;
                case 4:
                    cVar.bindString(i, this.mStringBindings[i]);
                    break;
                case 5:
                    cVar.bindBlob(i, this.mBlobBindings[i]);
                    break;
            }
        }
    }

    public void bindNull(int i) {
        this.mBindingTypes[i] = 1;
    }

    public void bindLong(int i, long j) {
        this.mBindingTypes[i] = 2;
        this.mLongBindings[i] = j;
    }

    public void bindDouble(int i, double d) {
        this.mBindingTypes[i] = 3;
        this.mDoubleBindings[i] = d;
    }

    public void bindString(int i, String str) {
        this.mBindingTypes[i] = 4;
        this.mStringBindings[i] = str;
    }

    public void bindBlob(int i, byte[] bArr) {
        this.mBindingTypes[i] = 5;
        this.mBlobBindings[i] = bArr;
    }

    public void copyArgumentsFrom(RoomSQLiteQuery roomSQLiteQuery) {
        int argCount = roomSQLiteQuery.getArgCount() + 1;
        System.arraycopy(roomSQLiteQuery.mBindingTypes, 0, this.mBindingTypes, 0, argCount);
        System.arraycopy(roomSQLiteQuery.mLongBindings, 0, this.mLongBindings, 0, argCount);
        System.arraycopy(roomSQLiteQuery.mStringBindings, 0, this.mStringBindings, 0, argCount);
        System.arraycopy(roomSQLiteQuery.mBlobBindings, 0, this.mBlobBindings, 0, argCount);
        System.arraycopy(roomSQLiteQuery.mDoubleBindings, 0, this.mDoubleBindings, 0, argCount);
    }

    public void clearBindings() {
        Arrays.fill(this.mBindingTypes, 1);
        Arrays.fill(this.mStringBindings, null);
        Arrays.fill(this.mBlobBindings, null);
        this.mQuery = null;
    }
}
