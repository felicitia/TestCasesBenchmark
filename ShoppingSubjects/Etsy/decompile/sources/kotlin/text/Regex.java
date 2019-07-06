package kotlin.text;

import com.etsy.android.lib.models.ResponseConstants;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.collections.f;
import kotlin.collections.o;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.p;
import kotlin.sequences.c;
import kotlin.sequences.d;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: Regex.kt */
public final class Regex implements Serializable {
    public static final a Companion = new a(null);
    private Set<? extends RegexOption> _options;
    private final Pattern nativePattern;

    /* compiled from: Regex.kt */
    private static final class Serialized implements Serializable {
        public static final a Companion = new a(null);
        private static final long serialVersionUID = 0;
        private final int flags;
        private final String pattern;

        /* compiled from: Regex.kt */
        public static final class a {
            private a() {
            }

            public /* synthetic */ a(o oVar) {
                this();
            }
        }

        public Serialized(String str, int i) {
            p.b(str, "pattern");
            this.pattern = str;
            this.flags = i;
        }

        public final int getFlags() {
            return this.flags;
        }

        public final String getPattern() {
            return this.pattern;
        }

        private final Object readResolve() {
            Pattern compile = Pattern.compile(this.pattern, this.flags);
            p.a((Object) compile, "Pattern.compile(pattern, flags)");
            return new Regex(compile);
        }
    }

    /* compiled from: Regex.kt */
    public static final class a {
        /* access modifiers changed from: private */
        public final int a(int i) {
            return (i & 2) != 0 ? i | 64 : i;
        }

        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

    public static /* synthetic */ j find$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.find(charSequence, i);
    }

    public static /* synthetic */ c findAll$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.findAll(charSequence, i);
    }

    public static /* synthetic */ List split$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.split(charSequence, i);
    }

    public Regex(Pattern pattern) {
        p.b(pattern, "nativePattern");
        this.nativePattern = pattern;
    }

    public Regex(String str) {
        p.b(str, "pattern");
        Pattern compile = Pattern.compile(str);
        p.a((Object) compile, "Pattern.compile(pattern)");
        this(compile);
    }

    public Regex(String str, RegexOption regexOption) {
        p.b(str, "pattern");
        p.b(regexOption, "option");
        Pattern compile = Pattern.compile(str, Companion.a(regexOption.getValue()));
        p.a((Object) compile, "Pattern.compile(pattern,…nicodeCase(option.value))");
        this(compile);
    }

    public Regex(String str, Set<? extends RegexOption> set) {
        p.b(str, "pattern");
        p.b(set, ResponseConstants.OPTIONS);
        Pattern compile = Pattern.compile(str, Companion.a(l.b((Iterable<? extends f>) set)));
        p.a((Object) compile, "Pattern.compile(pattern,…odeCase(options.toInt()))");
        this(compile);
    }

    public final String getPattern() {
        String pattern = this.nativePattern.pattern();
        p.a((Object) pattern, "nativePattern.pattern()");
        return pattern;
    }

    public final Set<RegexOption> getOptions() {
        Set<? extends RegexOption> set = this._options;
        if (set != null) {
            return set;
        }
        int flags = this.nativePattern.flags();
        EnumSet allOf = EnumSet.allOf(RegexOption.class);
        o.a((Iterable<? extends T>) allOf, (b<? super T, Boolean>) new Regex$fromInt$$inlined$apply$lambda$1<Object,Boolean>(flags));
        Set<? extends RegexOption> unmodifiableSet = Collections.unmodifiableSet(allOf);
        p.a((Object) unmodifiableSet, "Collections.unmodifiable…mask == it.value }\n    })");
        this._options = unmodifiableSet;
        return unmodifiableSet;
    }

    public final boolean matches(CharSequence charSequence) {
        p.b(charSequence, "input");
        return this.nativePattern.matcher(charSequence).matches();
    }

    public final boolean containsMatchIn(CharSequence charSequence) {
        p.b(charSequence, "input");
        return this.nativePattern.matcher(charSequence).find();
    }

    public final j find(CharSequence charSequence, int i) {
        p.b(charSequence, "input");
        Matcher matcher = this.nativePattern.matcher(charSequence);
        p.a((Object) matcher, "nativePattern.matcher(input)");
        return l.b(matcher, i, charSequence);
    }

    public final c<j> findAll(CharSequence charSequence, int i) {
        p.b(charSequence, "input");
        return d.a(new Regex$findAll$1(this, charSequence, i), Regex$findAll$2.INSTANCE);
    }

    public final j matchEntire(CharSequence charSequence) {
        p.b(charSequence, "input");
        Matcher matcher = this.nativePattern.matcher(charSequence);
        p.a((Object) matcher, "nativePattern.matcher(input)");
        return l.b(matcher, charSequence);
    }

    public final String replace(CharSequence charSequence, String str) {
        p.b(charSequence, "input");
        p.b(str, "replacement");
        String replaceAll = this.nativePattern.matcher(charSequence).replaceAll(str);
        p.a((Object) replaceAll, "nativePattern.matcher(in…).replaceAll(replacement)");
        return replaceAll;
    }

    public final String replace(CharSequence charSequence, b<? super j, ? extends CharSequence> bVar) {
        p.b(charSequence, "input");
        p.b(bVar, "transform");
        int i = 0;
        j find$default = find$default(this, charSequence, 0, 2, null);
        if (find$default == null) {
            return charSequence.toString();
        }
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        do {
            if (find$default == null) {
                p.a();
            }
            sb.append(charSequence, i, find$default.a().f().intValue());
            sb.append((CharSequence) bVar.invoke(find$default));
            i = find$default.a().g().intValue() + 1;
            find$default = find$default.d();
            if (i >= length) {
                break;
            }
        } while (find$default != null);
        if (i < length) {
            sb.append(charSequence, i, length);
        }
        String sb2 = sb.toString();
        p.a((Object) sb2, "sb.toString()");
        return sb2;
    }

    public final String replaceFirst(CharSequence charSequence, String str) {
        p.b(charSequence, "input");
        p.b(str, "replacement");
        String replaceFirst = this.nativePattern.matcher(charSequence).replaceFirst(str);
        p.a((Object) replaceFirst, "nativePattern.matcher(in…replaceFirst(replacement)");
        return replaceFirst;
    }

    public final List<String> split(CharSequence charSequence, int i) {
        p.b(charSequence, "input");
        if (!(i >= 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Limit must be non-negative, but was ");
            sb.append(i);
            sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
            throw new IllegalArgumentException(sb.toString().toString());
        }
        Pattern pattern = this.nativePattern;
        if (i == 0) {
            i = -1;
        }
        String[] split = pattern.split(charSequence, i);
        p.a((Object) split, "nativePattern.split(inpu…imit == 0) -1 else limit)");
        return f.a(split);
    }

    public String toString() {
        String pattern = this.nativePattern.toString();
        p.a((Object) pattern, "nativePattern.toString()");
        return pattern;
    }

    public final Pattern toPattern() {
        return this.nativePattern;
    }

    private final Object writeReplace() {
        String pattern = this.nativePattern.pattern();
        p.a((Object) pattern, "nativePattern.pattern()");
        return new Serialized(pattern, this.nativePattern.flags());
    }
}
