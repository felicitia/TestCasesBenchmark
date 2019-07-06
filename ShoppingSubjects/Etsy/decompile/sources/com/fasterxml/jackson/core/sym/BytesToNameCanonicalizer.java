package com.fasterxml.jackson.core.sym;

import android.support.v4.view.InputDeviceCompat;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public final class BytesToNameCanonicalizer {
    protected static final int DEFAULT_TABLE_SIZE = 64;
    static final int INITIAL_COLLISION_LEN = 32;
    static final int LAST_VALID_BUCKET = 254;
    static final int MAX_COLL_CHAIN_FOR_REUSE = 63;
    static final int MAX_COLL_CHAIN_LENGTH = 255;
    static final int MAX_ENTRIES_FOR_REUSE = 6000;
    protected static final int MAX_TABLE_SIZE = 65536;
    static final int MIN_HASH_SIZE = 16;
    private static final int MULT = 33;
    private static final int MULT2 = 65599;
    private static final int MULT3 = 31;
    protected int _collCount;
    protected int _collEnd;
    protected a[] _collList;
    private boolean _collListShared;
    protected int _count;
    private final int _hashSeed;
    protected final boolean _intern;
    protected int _longestCollisionList;
    protected int[] _mainHash;
    protected int _mainHashMask;
    private boolean _mainHashShared;
    protected Name[] _mainNames;
    private boolean _mainNamesShared;
    private transient boolean _needRehash;
    protected final BytesToNameCanonicalizer _parent;
    protected final AtomicReference<b> _tableInfo;

    static final class a {
        protected final Name a;
        protected final a b;
        private final int c;

        a(Name name, a aVar) {
            this.a = name;
            this.b = aVar;
            int i = 1;
            if (aVar != null) {
                i = 1 + aVar.c;
            }
            this.c = i;
        }

        public int a() {
            return this.c;
        }

        public Name a(int i, int i2, int i3) {
            if (this.a.hashCode() == i && this.a.equals(i2, i3)) {
                return this.a;
            }
            for (a aVar = this.b; aVar != null; aVar = aVar.b) {
                Name name = aVar.a;
                if (name.hashCode() == i && name.equals(i2, i3)) {
                    return name;
                }
            }
            return null;
        }

        public Name a(int i, int[] iArr, int i2) {
            if (this.a.hashCode() == i && this.a.equals(iArr, i2)) {
                return this.a;
            }
            for (a aVar = this.b; aVar != null; aVar = aVar.b) {
                Name name = aVar.a;
                if (name.hashCode() == i && name.equals(iArr, i2)) {
                    return name;
                }
            }
            return null;
        }
    }

    private static final class b {
        public final int a;
        public final int b;
        public final int[] c;
        public final Name[] d;
        public final a[] e;
        public final int f;
        public final int g;
        public final int h;

        public b(int i, int i2, int[] iArr, Name[] nameArr, a[] aVarArr, int i3, int i4, int i5) {
            this.a = i;
            this.b = i2;
            this.c = iArr;
            this.d = nameArr;
            this.e = aVarArr;
            this.f = i3;
            this.g = i4;
            this.h = i5;
        }

        public b(BytesToNameCanonicalizer bytesToNameCanonicalizer) {
            this.a = bytesToNameCanonicalizer._count;
            this.b = bytesToNameCanonicalizer._mainHashMask;
            this.c = bytesToNameCanonicalizer._mainHash;
            this.d = bytesToNameCanonicalizer._mainNames;
            this.e = bytesToNameCanonicalizer._collList;
            this.f = bytesToNameCanonicalizer._collCount;
            this.g = bytesToNameCanonicalizer._collEnd;
            this.h = bytesToNameCanonicalizer._longestCollisionList;
        }
    }

    private BytesToNameCanonicalizer(int i, boolean z, int i2) {
        this._parent = null;
        this._hashSeed = i2;
        this._intern = z;
        int i3 = 16;
        if (i >= 16) {
            if (((i - 1) & i) != 0) {
                while (i3 < i) {
                    i3 += i3;
                }
            } else {
                i3 = i;
            }
        }
        this._tableInfo = new AtomicReference<>(initTableInfo(i3));
    }

    private BytesToNameCanonicalizer(BytesToNameCanonicalizer bytesToNameCanonicalizer, boolean z, int i, b bVar) {
        this._parent = bytesToNameCanonicalizer;
        this._hashSeed = i;
        this._intern = z;
        this._tableInfo = null;
        this._count = bVar.a;
        this._mainHashMask = bVar.b;
        this._mainHash = bVar.c;
        this._mainNames = bVar.d;
        this._collList = bVar.e;
        this._collCount = bVar.f;
        this._collEnd = bVar.g;
        this._longestCollisionList = bVar.h;
        this._needRehash = false;
        this._mainHashShared = true;
        this._mainNamesShared = true;
        this._collListShared = true;
    }

    private b initTableInfo(int i) {
        b bVar = new b(0, i - 1, new int[i], new Name[i], null, 0, 0, 0);
        return bVar;
    }

    public static BytesToNameCanonicalizer createRoot() {
        long currentTimeMillis = System.currentTimeMillis();
        return createRoot((((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1);
    }

    protected static BytesToNameCanonicalizer createRoot(int i) {
        return new BytesToNameCanonicalizer(64, true, i);
    }

    public BytesToNameCanonicalizer makeChild(boolean z, boolean z2) {
        return new BytesToNameCanonicalizer(this, z2, this._hashSeed, (b) this._tableInfo.get());
    }

    public void release() {
        if (this._parent != null && maybeDirty()) {
            this._parent.mergeChild(new b(this));
            this._mainHashShared = true;
            this._mainNamesShared = true;
            this._collListShared = true;
        }
    }

    private void mergeChild(b bVar) {
        int i = bVar.a;
        b bVar2 = (b) this._tableInfo.get();
        if (i > bVar2.a) {
            if (i > MAX_ENTRIES_FOR_REUSE || bVar.h > 63) {
                bVar = initTableInfo(64);
            }
            this._tableInfo.compareAndSet(bVar2, bVar);
        }
    }

    public int size() {
        if (this._tableInfo != null) {
            return ((b) this._tableInfo.get()).a;
        }
        return this._count;
    }

    public int bucketCount() {
        return this._mainHash.length;
    }

    public boolean maybeDirty() {
        return !this._mainHashShared;
    }

    public int hashSeed() {
        return this._hashSeed;
    }

    public int collisionCount() {
        return this._collCount;
    }

    public int maxCollisionLength() {
        return this._longestCollisionList;
    }

    public static Name getEmptyName() {
        return Name1.getEmptyName();
    }

    public Name findName(int i) {
        int calcHash = calcHash(i);
        int i2 = this._mainHashMask & calcHash;
        int i3 = this._mainHash[i2];
        if ((((i3 >> 8) ^ calcHash) << 8) == 0) {
            Name name = this._mainNames[i2];
            if (name == null) {
                return null;
            }
            if (name.equals(i)) {
                return name;
            }
        } else if (i3 == 0) {
            return null;
        }
        int i4 = i3 & 255;
        if (i4 > 0) {
            a aVar = this._collList[i4 - 1];
            if (aVar != null) {
                return aVar.a(calcHash, i, 0);
            }
        }
        return null;
    }

    public Name findName(int i, int i2) {
        int calcHash = i2 == 0 ? calcHash(i) : calcHash(i, i2);
        int i3 = this._mainHashMask & calcHash;
        int i4 = this._mainHash[i3];
        if ((((i4 >> 8) ^ calcHash) << 8) == 0) {
            Name name = this._mainNames[i3];
            if (name == null) {
                return null;
            }
            if (name.equals(i, i2)) {
                return name;
            }
        } else if (i4 == 0) {
            return null;
        }
        int i5 = i4 & 255;
        if (i5 > 0) {
            a aVar = this._collList[i5 - 1];
            if (aVar != null) {
                return aVar.a(calcHash, i, i2);
            }
        }
        return null;
    }

    public Name findName(int[] iArr, int i) {
        if (i < 3) {
            int i2 = 0;
            int i3 = iArr[0];
            if (i >= 2) {
                i2 = iArr[1];
            }
            return findName(i3, i2);
        }
        int calcHash = calcHash(iArr, i);
        int i4 = this._mainHashMask & calcHash;
        int i5 = this._mainHash[i4];
        if ((((i5 >> 8) ^ calcHash) << 8) == 0) {
            Name name = this._mainNames[i4];
            if (name == null || name.equals(iArr, i)) {
                return name;
            }
        } else if (i5 == 0) {
            return null;
        }
        int i6 = i5 & 255;
        if (i6 > 0) {
            a aVar = this._collList[i6 - 1];
            if (aVar != null) {
                return aVar.a(calcHash, iArr, i);
            }
        }
        return null;
    }

    public Name addName(String str, int i, int i2) {
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        int calcHash = i2 == 0 ? calcHash(i) : calcHash(i, i2);
        Name constructName = constructName(calcHash, str, i, i2);
        _addSymbol(calcHash, constructName);
        return constructName;
    }

    public Name addName(String str, int[] iArr, int i) {
        if (this._intern) {
            str = InternCache.instance.intern(str);
        }
        int i2 = i < 3 ? i == 1 ? calcHash(iArr[0]) : calcHash(iArr[0], iArr[1]) : calcHash(iArr, i);
        Name constructName = constructName(i2, str, iArr, i);
        _addSymbol(i2, constructName);
        return constructName;
    }

    public int calcHash(int i) {
        int i2 = i ^ this._hashSeed;
        int i3 = i2 + (i2 >>> 15);
        return i3 ^ (i3 >>> 9);
    }

    public int calcHash(int i, int i2) {
        int i3 = ((i ^ (i >>> 15)) + (i2 * 33)) ^ this._hashSeed;
        return i3 + (i3 >>> 7);
    }

    public int calcHash(int[] iArr, int i) {
        if (i < 3) {
            throw new IllegalArgumentException();
        }
        int i2 = iArr[0] ^ this._hashSeed;
        int i3 = (((i2 + (i2 >>> 9)) * 33) + iArr[1]) * MULT2;
        int i4 = (i3 + (i3 >>> 15)) ^ iArr[2];
        int i5 = i4 + (i4 >>> 17);
        for (int i6 = 3; i6 < i; i6++) {
            int i7 = (i5 * 31) ^ iArr[i6];
            int i8 = i7 + (i7 >>> 3);
            i5 = i8 ^ (i8 << 7);
        }
        int i9 = i5 + (i5 >>> 15);
        return (i9 << 9) ^ i9;
    }

    protected static int[] calcQuads(byte[] bArr) {
        int i = 0;
        int length = bArr.length;
        int[] iArr = new int[((length + 3) / 4)];
        while (i < length) {
            int i2 = bArr[i] & 255;
            int i3 = i + 1;
            if (i3 < length) {
                i2 = (i2 << 8) | (bArr[i3] & 255);
                i3++;
                if (i3 < length) {
                    i2 = (i2 << 8) | (bArr[i3] & 255);
                    i3++;
                    if (i3 < length) {
                        i2 = (i2 << 8) | (bArr[i3] & 255);
                    }
                }
            }
            iArr[i3 >> 2] = i2;
            i = i3 + 1;
        }
        return iArr;
    }

    private void _addSymbol(int i, Name name) {
        int i2;
        if (this._mainHashShared) {
            unshareMain();
        }
        if (this._needRehash) {
            rehash();
        }
        this._count++;
        int i3 = this._mainHashMask & i;
        if (this._mainNames[i3] == null) {
            this._mainHash[i3] = i << 8;
            if (this._mainNamesShared) {
                unshareNames();
            }
            this._mainNames[i3] = name;
        } else {
            if (this._collListShared) {
                unshareCollision();
            }
            this._collCount++;
            int i4 = this._mainHash[i3];
            int i5 = i4 & 255;
            if (i5 == 0) {
                if (this._collEnd <= LAST_VALID_BUCKET) {
                    i2 = this._collEnd;
                    this._collEnd++;
                    if (i2 >= this._collList.length) {
                        expandCollision();
                    }
                } else {
                    i2 = findBestBucket();
                }
                this._mainHash[i3] = (i4 & InputDeviceCompat.SOURCE_ANY) | (i2 + 1);
            } else {
                i2 = i5 - 1;
            }
            a aVar = new a(name, this._collList[i2]);
            this._collList[i2] = aVar;
            this._longestCollisionList = Math.max(aVar.a(), this._longestCollisionList);
            if (this._longestCollisionList > 255) {
                reportTooManyCollisions(255);
            }
        }
        int length = this._mainHash.length;
        if (this._count > (length >> 1)) {
            int i6 = length >> 2;
            if (this._count > length - i6) {
                this._needRehash = true;
            } else if (this._collCount >= i6) {
                this._needRehash = true;
            }
        }
    }

    private void rehash() {
        int i;
        this._needRehash = false;
        this._mainNamesShared = false;
        int length = this._mainHash.length;
        int i2 = length + length;
        if (i2 > 65536) {
            nukeSymbols();
            return;
        }
        this._mainHash = new int[i2];
        this._mainHashMask = i2 - 1;
        Name[] nameArr = this._mainNames;
        this._mainNames = new Name[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            Name name = nameArr[i4];
            if (name != null) {
                i3++;
                int hashCode = name.hashCode();
                int i5 = this._mainHashMask & hashCode;
                this._mainNames[i5] = name;
                this._mainHash[i5] = hashCode << 8;
            }
        }
        int i6 = this._collEnd;
        if (i6 == 0) {
            this._longestCollisionList = 0;
            return;
        }
        this._collCount = 0;
        this._collEnd = 0;
        this._collListShared = false;
        a[] aVarArr = this._collList;
        this._collList = new a[aVarArr.length];
        int i7 = 0;
        for (int i8 = 0; i8 < i6; i8++) {
            for (a aVar = aVarArr[i8]; aVar != null; aVar = aVar.b) {
                i3++;
                Name name2 = aVar.a;
                int hashCode2 = name2.hashCode();
                int i9 = this._mainHashMask & hashCode2;
                int i10 = this._mainHash[i9];
                if (this._mainNames[i9] == null) {
                    this._mainHash[i9] = hashCode2 << 8;
                    this._mainNames[i9] = name2;
                } else {
                    this._collCount++;
                    int i11 = i10 & 255;
                    if (i11 == 0) {
                        if (this._collEnd <= LAST_VALID_BUCKET) {
                            i = this._collEnd;
                            this._collEnd++;
                            if (i >= this._collList.length) {
                                expandCollision();
                            }
                        } else {
                            i = findBestBucket();
                        }
                        this._mainHash[i9] = (i10 & InputDeviceCompat.SOURCE_ANY) | (i + 1);
                    } else {
                        i = i11 - 1;
                    }
                    a aVar2 = new a(name2, this._collList[i]);
                    this._collList[i] = aVar2;
                    i7 = Math.max(i7, aVar2.a());
                }
            }
        }
        this._longestCollisionList = i7;
        if (i3 != this._count) {
            StringBuilder sb = new StringBuilder();
            sb.append("Internal error: count after rehash ");
            sb.append(i3);
            sb.append("; should be ");
            sb.append(this._count);
            throw new RuntimeException(sb.toString());
        }
    }

    private void nukeSymbols() {
        this._count = 0;
        this._longestCollisionList = 0;
        Arrays.fill(this._mainHash, 0);
        Arrays.fill(this._mainNames, null);
        Arrays.fill(this._collList, null);
        this._collCount = 0;
        this._collEnd = 0;
    }

    private int findBestBucket() {
        a[] aVarArr = this._collList;
        int i = this._collEnd;
        int i2 = Integer.MAX_VALUE;
        int i3 = -1;
        for (int i4 = 0; i4 < i; i4++) {
            int a2 = aVarArr[i4].a();
            if (a2 < i2) {
                if (a2 == 1) {
                    return i4;
                }
                i3 = i4;
                i2 = a2;
            }
        }
        return i3;
    }

    private void unshareMain() {
        int[] iArr = this._mainHash;
        int length = this._mainHash.length;
        this._mainHash = new int[length];
        System.arraycopy(iArr, 0, this._mainHash, 0, length);
        this._mainHashShared = false;
    }

    private void unshareCollision() {
        a[] aVarArr = this._collList;
        if (aVarArr == null) {
            this._collList = new a[32];
        } else {
            int length = aVarArr.length;
            this._collList = new a[length];
            System.arraycopy(aVarArr, 0, this._collList, 0, length);
        }
        this._collListShared = false;
    }

    private void unshareNames() {
        Name[] nameArr = this._mainNames;
        int length = nameArr.length;
        this._mainNames = new Name[length];
        System.arraycopy(nameArr, 0, this._mainNames, 0, length);
        this._mainNamesShared = false;
    }

    private void expandCollision() {
        a[] aVarArr = this._collList;
        int length = aVarArr.length;
        this._collList = new a[(length + length)];
        System.arraycopy(aVarArr, 0, this._collList, 0, length);
    }

    private static Name constructName(int i, String str, int i2, int i3) {
        if (i3 == 0) {
            return new Name1(str, i, i2);
        }
        return new Name2(str, i, i2, i3);
    }

    private static Name constructName(int i, String str, int[] iArr, int i2) {
        if (i2 < 4) {
            switch (i2) {
                case 1:
                    return new Name1(str, i, iArr[0]);
                case 2:
                    return new Name2(str, i, iArr[0], iArr[1]);
                case 3:
                    Name3 name3 = new Name3(str, i, iArr[0], iArr[1], iArr[2]);
                    return name3;
            }
        }
        int[] iArr2 = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr2[i3] = iArr[i3];
        }
        return new NameN(str, i, iArr2, i2);
    }

    /* access modifiers changed from: protected */
    public void reportTooManyCollisions(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("Longest collision chain in symbol table (of size ");
        sb.append(this._count);
        sb.append(") now exceeds maximum, ");
        sb.append(i);
        sb.append(" -- suspect a DoS attack based on hash collisions");
        throw new IllegalStateException(sb.toString());
    }
}
