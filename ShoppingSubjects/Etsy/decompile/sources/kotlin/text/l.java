package kotlin.text;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.b.c;
import kotlin.b.d;

/* compiled from: Regex.kt */
public final class l {
    /* access modifiers changed from: private */
    public static final j b(Matcher matcher, int i, CharSequence charSequence) {
        if (!matcher.find(i)) {
            return null;
        }
        return new k(matcher, charSequence);
    }

    /* access modifiers changed from: private */
    public static final j b(Matcher matcher, CharSequence charSequence) {
        if (!matcher.matches()) {
            return null;
        }
        return new k(matcher, charSequence);
    }

    /* access modifiers changed from: private */
    public static final c b(MatchResult matchResult) {
        return d.b(matchResult.start(), matchResult.end());
    }

    /* access modifiers changed from: private */
    public static final c b(MatchResult matchResult, int i) {
        return d.b(matchResult.start(i), matchResult.end(i));
    }

    /* access modifiers changed from: private */
    public static final int b(Iterable<? extends f> iterable) {
        int i = 0;
        for (f value : iterable) {
            i |= value.getValue();
        }
        return i;
    }
}
