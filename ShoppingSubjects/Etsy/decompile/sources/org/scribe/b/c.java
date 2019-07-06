package org.scribe.b;

import org.scribe.e.b;
import org.scribe.exceptions.OAuthParametersMissingException;
import org.scribe.model.e;

/* compiled from: BaseStringExtractorImpl */
public class c implements b {
    public String a(org.scribe.model.c cVar) {
        c(cVar);
        return String.format("%s&%s&%s", new Object[]{b.a(cVar.i().name()), b.a(cVar.g()), b(cVar)});
    }

    private String b(org.scribe.model.c cVar) {
        e eVar = new e();
        eVar.a(cVar.d());
        eVar.a(cVar.e());
        eVar.a(new e(cVar.a()));
        return eVar.c().a();
    }

    private void c(org.scribe.model.c cVar) {
        org.scribe.e.c.a((Object) cVar, "Cannot extract base string from null object");
        if (cVar.a() == null || cVar.a().size() <= 0) {
            throw new OAuthParametersMissingException(cVar);
        }
    }
}
