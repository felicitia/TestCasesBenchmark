package defpackage;

import com.apiguard.APIGuard.AG_HTTP_COMMAND;
import java.util.Locale;

/* renamed from: i reason: default package */
/* compiled from: GA */
public abstract class i<REQ> {
    REQ a;
    boolean b;
    o c;
    AG_HTTP_COMMAND d = null;

    public abstract REQ a();

    i(REQ req, String str, String str2, o oVar) {
        this.c = oVar;
        this.a = req;
        this.b = false;
        try {
            this.d = AG_HTTP_COMMAND.valueOf(str2.toUpperCase(Locale.US));
            this.b = al.d(al.c(str));
        } catch (Exception e) {
            e.toString();
            al.a();
        }
    }
}
