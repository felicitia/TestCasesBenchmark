package org.scribe.b;

import java.util.Map;
import java.util.Map.Entry;
import org.scribe.e.b;
import org.scribe.exceptions.OAuthParametersMissingException;
import org.scribe.model.c;

/* compiled from: HeaderExtractorImpl */
public class e implements d {
    public String a(c cVar) {
        b(cVar);
        Map a = cVar.a();
        StringBuilder sb = new StringBuilder(a.size() * 20);
        sb.append("OAuth ");
        for (Entry entry : a.entrySet()) {
            if (sb.length() > "OAuth ".length()) {
                sb.append(", ");
            }
            sb.append(String.format("%s=\"%s\"", new Object[]{entry.getKey(), b.a((String) entry.getValue())}));
        }
        return sb.toString();
    }

    private void b(c cVar) {
        org.scribe.e.c.a((Object) cVar, "Cannot extract a header from a null object");
        if (cVar.a() == null || cVar.a().size() <= 0) {
            throw new OAuthParametersMissingException(cVar);
        }
    }
}
