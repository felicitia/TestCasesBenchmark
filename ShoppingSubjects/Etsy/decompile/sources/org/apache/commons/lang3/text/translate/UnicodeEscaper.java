package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

public class UnicodeEscaper extends CodePointTranslator {
    private final int above;
    private final int below;
    private final boolean between;

    public UnicodeEscaper() {
        this(0, Integer.MAX_VALUE, true);
    }

    private UnicodeEscaper(int i, int i2, boolean z) {
        this.below = i;
        this.above = i2;
        this.between = z;
    }

    public static UnicodeEscaper below(int i) {
        return outsideOf(i, Integer.MAX_VALUE);
    }

    public static UnicodeEscaper above(int i) {
        return outsideOf(0, i);
    }

    public static UnicodeEscaper outsideOf(int i, int i2) {
        return new UnicodeEscaper(i, i2, false);
    }

    public static UnicodeEscaper between(int i, int i2) {
        return new UnicodeEscaper(i, i2, true);
    }

    public boolean translate(int i, Writer writer) throws IOException {
        if (this.between) {
            if (i < this.below || i > this.above) {
                return false;
            }
        } else if (i >= this.below && i <= this.above) {
            return false;
        }
        if (i > 65535) {
            StringBuilder sb = new StringBuilder();
            sb.append("\\u");
            sb.append(hex(i));
            writer.write(sb.toString());
        } else if (i > 4095) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("\\u");
            sb2.append(hex(i));
            writer.write(sb2.toString());
        } else if (i > 255) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("\\u0");
            sb3.append(hex(i));
            writer.write(sb3.toString());
        } else if (i > 15) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("\\u00");
            sb4.append(hex(i));
            writer.write(sb4.toString());
        } else {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("\\u000");
            sb5.append(hex(i));
            writer.write(sb5.toString());
        }
        return true;
    }
}
