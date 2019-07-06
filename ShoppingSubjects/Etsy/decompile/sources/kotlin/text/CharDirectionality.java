package kotlin.text;

import kotlin.b;
import kotlin.c;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.s;
import kotlin.reflect.j;

/* compiled from: CharDirectionality.kt */
public enum CharDirectionality {
    UNDEFINED(-1),
    LEFT_TO_RIGHT(0),
    RIGHT_TO_LEFT(1),
    RIGHT_TO_LEFT_ARABIC(2),
    EUROPEAN_NUMBER(3),
    EUROPEAN_NUMBER_SEPARATOR(4),
    EUROPEAN_NUMBER_TERMINATOR(5),
    ARABIC_NUMBER(6),
    COMMON_NUMBER_SEPARATOR(7),
    NONSPACING_MARK(8),
    BOUNDARY_NEUTRAL(9),
    PARAGRAPH_SEPARATOR(10),
    SEGMENT_SEPARATOR(11),
    WHITESPACE(12),
    OTHER_NEUTRALS(13),
    LEFT_TO_RIGHT_EMBEDDING(14),
    LEFT_TO_RIGHT_OVERRIDE(15),
    RIGHT_TO_LEFT_EMBEDDING(16),
    RIGHT_TO_LEFT_OVERRIDE(17),
    POP_DIRECTIONAL_FORMAT(18);
    
    public static final a Companion = null;
    /* access modifiers changed from: private */
    public static final b b = null;
    private final int value;

    /* compiled from: CharDirectionality.kt */
    public static final class a {
        static final /* synthetic */ j[] a = null;

        static {
            a = new j[]{s.a((PropertyReference1) new PropertyReference1Impl(s.a(a.class), "directionalityMap", "getDirectionalityMap()Ljava/util/Map;"))};
        }

        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

    protected CharDirectionality(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    static {
        Companion = new a(null);
        b = c.a(CharDirectionality$Companion$directionalityMap$2.INSTANCE);
    }
}
