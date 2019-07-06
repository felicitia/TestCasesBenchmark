package defpackage;

import com.apiguard.APIGuard.AG_HTTP_COMMAND;

/* renamed from: j reason: default package */
/* compiled from: GA */
public abstract class j<RES> {
    RES a;
    boolean b = false;
    o c;

    public abstract RES a();

    j(RES res, String str, String str2, o oVar) {
        this.c = oVar;
        this.a = res;
        try {
            AG_HTTP_COMMAND.valueOf(str2);
            this.b = al.d(al.c(str));
        } catch (Exception e) {
            e.toString();
            al.a();
        }
    }
}
