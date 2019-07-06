package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;

public final class CharsToNameCanonicalizer {
    protected static final int DEFAULT_TABLE_SIZE = 64;
    public static final int HASH_MULT = 33;
    static final int MAX_COLL_CHAIN_FOR_REUSE = 63;
    static final int MAX_COLL_CHAIN_LENGTH = 255;
    static final int MAX_ENTRIES_FOR_REUSE = 12000;
    protected static final int MAX_TABLE_SIZE = 65536;
    static final CharsToNameCanonicalizer sBootstrapSymbolTable = new CharsToNameCanonicalizer();
    protected a[] _buckets;
    protected final boolean _canonicalize;
    protected boolean _dirty;
    private final int _hashSeed;
    protected int _indexMask;
    protected final boolean _intern;
    protected int _longestCollisionList;
    protected CharsToNameCanonicalizer _parent;
    protected int _size;
    protected int _sizeThreshold;
    protected String[] _symbols;

    static final class a {
        private final String a;
        private final a b;
        private final int c;

        public a(String str, a aVar) {
            this.a = str;
            this.b = aVar;
            int i = 1;
            if (aVar != null) {
                i = 1 + aVar.c;
            }
            this.c = i;
        }

        public String a() {
            return this.a;
        }

        public a b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public String a(char[] cArr, int i, int i2) {
            String str = this.a;
            a aVar = this.b;
            while (true) {
                if (str.length() == i2) {
                    int i3 = 0;
                    while (str.charAt(i3) == cArr[i + i3]) {
                        i3++;
                        if (i3 >= i2) {
                            break;
                        }
                    }
                    if (i3 == i2) {
                        return str;
                    }
                }
                if (aVar == null) {
                    return null;
                }
                str = aVar.a();
                aVar = aVar.b();
            }
        }
    }

    private static int _thresholdSize(int i) {
        return i - (i >> 2);
    }

    public static CharsToNameCanonicalizer createRoot() {
        long currentTimeMillis = System.currentTimeMillis();
        return createRoot((((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1);
    }

    protected static CharsToNameCanonicalizer createRoot(int i) {
        return sBootstrapSymbolTable.makeOrphan(i);
    }

    private CharsToNameCanonicalizer() {
        this._canonicalize = true;
        this._intern = true;
        this._dirty = true;
        this._hashSeed = 0;
        this._longestCollisionList = 0;
        initTables(64);
    }

    private void initTables(int i) {
        this._symbols = new String[i];
        this._buckets = new a[(i >> 1)];
        this._indexMask = i - 1;
        this._size = 0;
        this._longestCollisionList = 0;
        this._sizeThreshold = _thresholdSize(i);
    }

    private CharsToNameCanonicalizer(CharsToNameCanonicalizer charsToNameCanonicalizer, boolean z, boolean z2, String[] strArr, a[] aVarArr, int i, int i2, int i3) {
        this._parent = charsToNameCanonicalizer;
        this._canonicalize = z;
        this._intern = z2;
        this._symbols = strArr;
        this._buckets = aVarArr;
        this._size = i;
        this._hashSeed = i2;
        int length = strArr.length;
        this._sizeThreshold = _thresholdSize(length);
        this._indexMask = length - 1;
        this._longestCollisionList = i3;
        this._dirty = false;
    }

    public CharsToNameCanonicalizer makeChild(boolean z, boolean z2) {
        String[] strArr;
        a[] aVarArr;
        int i;
        int i2;
        int i3;
        synchronized (this) {
            strArr = this._symbols;
            aVarArr = this._buckets;
            i = this._size;
            i2 = this._hashSeed;
            i3 = this._longestCollisionList;
        }
        CharsToNameCanonicalizer charsToNameCanonicalizer = new CharsToNameCanonicalizer(this, z, z2, strArr, aVarArr, i, i2, i3);
        return charsToNameCanonicalizer;
    }

    private CharsToNameCanonicalizer makeOrphan(int i) {
        CharsToNameCanonicalizer charsToNameCanonicalizer = new CharsToNameCanonicalizer(null, true, true, this._symbols, this._buckets, this._size, i, this._longestCollisionList);
        return charsToNameCanonicalizer;
    }

    private void mergeChild(CharsToNameCanonicalizer charsToNameCanonicalizer) {
        if (charsToNameCanonicalizer.size() > MAX_ENTRIES_FOR_REUSE || charsToNameCanonicalizer._longestCollisionList > 63) {
            synchronized (this) {
                initTables(64);
                this._dirty = false;
            }
        } else if (charsToNameCanonicalizer.size() > size()) {
            synchronized (this) {
                this._symbols = charsToNameCanonicalizer._symbols;
                this._buckets = charsToNameCanonicalizer._buckets;
                this._size = charsToNameCanonicalizer._size;
                this._sizeThreshold = charsToNameCanonicalizer._sizeThreshold;
                this._indexMask = charsToNameCanonicalizer._indexMask;
                this._longestCollisionList = charsToNameCanonicalizer._longestCollisionList;
                this._dirty = false;
            }
        }
    }

    public void release() {
        if (maybeDirty() && this._parent != null) {
            this._parent.mergeChild(this);
            this._dirty = false;
        }
    }

    public int size() {
        return this._size;
    }

    public int bucketCount() {
        return this._symbols.length;
    }

    public boolean maybeDirty() {
        return this._dirty;
    }

    public int hashSeed() {
        return this._hashSeed;
    }

    public int collisionCount() {
        a[] aVarArr;
        int i = 0;
        for (a aVar : this._buckets) {
            if (aVar != null) {
                i += aVar.c();
            }
        }
        return i;
    }

    public int maxCollisionLength() {
        return this._longestCollisionList;
    }

    public String findSymbol(char[] cArr, int i, int i2, int i3) {
        if (i2 < 1) {
            return "";
        }
        if (!this._canonicalize) {
            return new String(cArr, i, i2);
        }
        int _hashToIndex = _hashToIndex(i3);
        String str = this._symbols[_hashToIndex];
        if (str != null) {
            if (str.length() == i2) {
                int i4 = 0;
                while (str.charAt(i4) == cArr[i + i4]) {
                    i4++;
                    if (i4 >= i2) {
                        break;
                    }
                }
                if (i4 == i2) {
                    return str;
                }
            }
            a aVar = this._buckets[_hashToIndex >> 1];
            if (aVar != null) {
                String a2 = aVar.a(cArr, i, i2);
                if (a2 != null) {
                    return a2;
                }
            }
        }
        if (!this._dirty) {
            copyArrays();
            this._dirty = true;
        } else if (this._size >= this._sizeThreshold) {
            rehash();
            _hashToIndex = _hashToIndex(calcHash(cArr, i, i2));
        }
        String str2 = new String(cArr, i, i2);
        if (this._intern) {
            str2 = InternCache.instance.intern(str2);
        }
        this._size++;
        if (this._symbols[_hashToIndex] == null) {
            this._symbols[_hashToIndex] = str2;
        } else {
            int i5 = _hashToIndex >> 1;
            a aVar2 = new a(str2, this._buckets[i5]);
            this._buckets[i5] = aVar2;
            this._longestCollisionList = Math.max(aVar2.c(), this._longestCollisionList);
            if (this._longestCollisionList > 255) {
                reportTooManyCollisions(255);
            }
        }
        return str2;
    }

    public int _hashToIndex(int i) {
        return (i + (i >>> 15)) & this._indexMask;
    }

    public int calcHash(char[] cArr, int i, int i2) {
        int i3 = this._hashSeed;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = (i3 * 33) + cArr[i4];
        }
        if (i3 == 0) {
            return 1;
        }
        return i3;
    }

    public int calcHash(String str) {
        int length = str.length();
        int i = this._hashSeed;
        for (int i2 = 0; i2 < length; i2++) {
            i = (i * 33) + str.charAt(i2);
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    private void copyArrays() {
        String[] strArr = this._symbols;
        int length = strArr.length;
        this._symbols = new String[length];
        System.arraycopy(strArr, 0, this._symbols, 0, length);
        a[] aVarArr = this._buckets;
        int length2 = aVarArr.length;
        this._buckets = new a[length2];
        System.arraycopy(aVarArr, 0, this._buckets, 0, length2);
    }

    private void rehash() {
        int length = this._symbols.length;
        int i = length + length;
        if (i > 65536) {
            this._size = 0;
            Arrays.fill(this._symbols, null);
            Arrays.fill(this._buckets, null);
            this._dirty = true;
            return;
        }
        String[] strArr = this._symbols;
        a[] aVarArr = this._buckets;
        this._symbols = new String[i];
        this._buckets = new a[(i >> 1)];
        this._indexMask = i - 1;
        this._sizeThreshold = _thresholdSize(i);
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            String str = strArr[i4];
            if (str != null) {
                i2++;
                int _hashToIndex = _hashToIndex(calcHash(str));
                if (this._symbols[_hashToIndex] == null) {
                    this._symbols[_hashToIndex] = str;
                } else {
                    int i5 = _hashToIndex >> 1;
                    a aVar = new a(str, this._buckets[i5]);
                    this._buckets[i5] = aVar;
                    i3 = Math.max(i3, aVar.c());
                }
            }
        }
        int i6 = length >> 1;
        for (int i7 = 0; i7 < i6; i7++) {
            for (a aVar2 = aVarArr[i7]; aVar2 != null; aVar2 = aVar2.b()) {
                i2++;
                String a2 = aVar2.a();
                int _hashToIndex2 = _hashToIndex(calcHash(a2));
                if (this._symbols[_hashToIndex2] == null) {
                    this._symbols[_hashToIndex2] = a2;
                } else {
                    int i8 = _hashToIndex2 >> 1;
                    a aVar3 = new a(a2, this._buckets[i8]);
                    this._buckets[i8] = aVar3;
                    i3 = Math.max(i3, aVar3.c());
                }
            }
        }
        this._longestCollisionList = i3;
        if (i2 != this._size) {
            StringBuilder sb = new StringBuilder();
            sb.append("Internal error on SymbolTable.rehash(): had ");
            sb.append(this._size);
            sb.append(" entries; now have ");
            sb.append(i2);
            sb.append(".");
            throw new Error(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void reportTooManyCollisions(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("Longest collision chain in symbol table (of size ");
        sb.append(this._size);
        sb.append(") now exceeds maximum, ");
        sb.append(i);
        sb.append(" -- suspect a DoS attack based on hash collisions");
        throw new IllegalStateException(sb.toString());
    }
}
