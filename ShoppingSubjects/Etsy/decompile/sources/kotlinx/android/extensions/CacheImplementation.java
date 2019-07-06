package kotlinx.android.extensions;

/* compiled from: CacheImplementation.kt */
public enum CacheImplementation {
    SPARSE_ARRAY,
    HASH_MAP,
    NO_CACHE;
    
    public static final a Companion = null;
    /* access modifiers changed from: private */
    public static final CacheImplementation b = null;

    /* compiled from: CacheImplementation.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

    static {
        Companion = new a(null);
        b = HASH_MAP;
    }
}
