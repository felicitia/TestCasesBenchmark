package kotlin.jvm.internal;

import kotlin.reflect.d;

public class PropertyReference2Impl extends PropertyReference2 {
    private final String name;
    private final d owner;
    private final String signature;

    public PropertyReference2Impl(d dVar, String str, String str2) {
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

    public Object get(Object obj, Object obj2) {
        return getGetter().call(obj, obj2);
    }
}
