package dagger.internal;

public final class ReferenceReleasingProviderManager {

    private enum Operation {
        RELEASE {
            /* access modifiers changed from: 0000 */
            public void execute(g<?> gVar) {
                gVar.a();
            }
        },
        RESTORE {
            /* access modifiers changed from: 0000 */
            public void execute(g<?> gVar) {
                gVar.c();
            }
        };

        /* access modifiers changed from: 0000 */
        public abstract void execute(g<?> gVar);
    }
}
