package kotlin.jvm.internal;

import kotlin.reflect.d;

public class FunctionReferenceImpl extends FunctionReference {
    private final String name;
    private final d owner;
    private final String signature;

    public FunctionReferenceImpl(int i, d dVar, String str, String str2) {
        super(i);
        this.owner = dVar;
        this.name = str;
        this.signature = str2;
    }

    public d getOwner() {
        return this.owner;
    }

    public String getName() {
        return this.name;
    }

    public String getSignature() {
        return this.signature;
    }
}
