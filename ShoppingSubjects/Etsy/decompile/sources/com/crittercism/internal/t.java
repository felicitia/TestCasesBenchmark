package com.crittercism.internal;

import java.net.Socket;
import java.net.SocketImplFactory;

public final class t implements SocketImplFactory {
    private static boolean a = false;
    private d b;
    private c c;

    public static boolean a(d dVar, c cVar) {
        if (a) {
            return a;
        }
        t tVar = new t(dVar, cVar);
        try {
            tVar.createSocketImpl();
            Socket.setSocketImplFactory(tVar);
            a = true;
            return true;
        } catch (Throwable unused) {
            return a;
        }
    }

    private t(d dVar, c cVar) {
        this.b = dVar;
        this.c = cVar;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.net.SocketImpl, com.crittercism.internal.s] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [java.net.SocketImpl, com.crittercism.internal.s]
      assigns: [com.crittercism.internal.s]
      uses: [java.net.SocketImpl]
      mth insns count: 4
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.net.SocketImpl createSocketImpl() {
        /*
            r3 = this;
            com.crittercism.internal.s r0 = new com.crittercism.internal.s
            com.crittercism.internal.d r1 = r3.b
            com.crittercism.internal.c r2 = r3.c
            r0.<init>(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.t.createSocketImpl():java.net.SocketImpl");
    }
}
