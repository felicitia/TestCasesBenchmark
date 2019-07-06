package org.scribe.a;

import java.io.OutputStream;
import org.scribe.c.b;
import org.scribe.e.c;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.SignatureType;

/* compiled from: ServiceBuilder */
public class a {
    private String a;
    private String b;
    private String c = "oob";
    private org.scribe.a.a.a d;
    private String e;
    private SignatureType f = SignatureType.Header;
    private OutputStream g = null;

    public a a(Class<? extends org.scribe.a.a.a> cls) {
        this.d = b(cls);
        return this;
    }

    private org.scribe.a.a.a b(Class<? extends org.scribe.a.a.a> cls) {
        c.a((Object) cls, "Api class cannot be null");
        try {
            return (org.scribe.a.a.a) cls.newInstance();
        } catch (Exception e2) {
            throw new OAuthException("Error while creating the Api object", e2);
        }
    }

    public a a(String str) {
        c.a((Object) str, "Callback can't be null");
        this.c = str;
        return this;
    }

    public a b(String str) {
        c.a(str, "Invalid Api key");
        this.a = str;
        return this;
    }

    public a c(String str) {
        c.a(str, "Invalid Api secret");
        this.b = str;
        return this;
    }

    public a d(String str) {
        c.a(str, "Invalid OAuth scope");
        this.e = str;
        return this;
    }

    public b a() {
        c.a((Object) this.d, "You must specify a valid api through the provider() method");
        c.a(this.a, "You must provide an api key");
        c.a(this.b, "You must provide an api secret");
        org.scribe.a.a.a aVar = this.d;
        org.scribe.model.a aVar2 = new org.scribe.model.a(this.a, this.b, this.c, this.f, this.e, this.g);
        return aVar.b(aVar2);
    }
}
