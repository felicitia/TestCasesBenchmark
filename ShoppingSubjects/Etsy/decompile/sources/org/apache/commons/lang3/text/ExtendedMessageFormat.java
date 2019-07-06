package org.apache.commons.lang3.text;

import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;

public class ExtendedMessageFormat extends MessageFormat {
    private static final String DUMMY_PATTERN = "";
    private static final char END_FE = '}';
    private static final String ESCAPED_QUOTE = "''";
    private static final int HASH_SEED = 31;
    private static final char QUOTE = '\'';
    private static final char START_FE = '{';
    private static final char START_FMT = ',';
    private static final long serialVersionUID = -2362048321261811743L;
    private final Map<String, ? extends FormatFactory> registry;
    private String toPattern;

    public ExtendedMessageFormat(String str) {
        this(str, Locale.getDefault());
    }

    public ExtendedMessageFormat(String str, Locale locale) {
        this(str, locale, null);
    }

    public ExtendedMessageFormat(String str, Map<String, ? extends FormatFactory> map) {
        this(str, Locale.getDefault(), map);
    }

    public ExtendedMessageFormat(String str, Locale locale, Map<String, ? extends FormatFactory> map) {
        super("");
        setLocale(locale);
        this.registry = map;
        applyPattern(str);
    }

    public String toPattern() {
        return this.toPattern;
    }

    public final void applyPattern(String str) {
        Object obj;
        String str2;
        if (this.registry == null) {
            super.applyPattern(str);
            this.toPattern = super.toPattern();
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        StringBuilder sb = new StringBuilder(str.length());
        int i = 0;
        ParsePosition parsePosition = new ParsePosition(0);
        char[] charArray = str.toCharArray();
        int i2 = 0;
        while (parsePosition.getIndex() < str.length()) {
            char c = charArray[parsePosition.getIndex()];
            boolean z = true;
            if (c != '\'') {
                if (c == '{') {
                    i2++;
                    seekNonWs(str, parsePosition);
                    int index = parsePosition.getIndex();
                    int readArgumentIndex = readArgumentIndex(str, next(parsePosition));
                    sb.append(START_FE);
                    sb.append(readArgumentIndex);
                    seekNonWs(str, parsePosition);
                    if (charArray[parsePosition.getIndex()] == ',') {
                        str2 = parseFormatDescription(str, next(parsePosition));
                        obj = getFormat(str2);
                        if (obj == null) {
                            sb.append(START_FMT);
                            sb.append(str2);
                        }
                    } else {
                        str2 = null;
                        obj = null;
                    }
                    arrayList.add(obj);
                    if (obj == null) {
                        str2 = null;
                    }
                    arrayList2.add(str2);
                    Validate.isTrue(arrayList.size() == i2);
                    if (arrayList2.size() != i2) {
                        z = false;
                    }
                    Validate.isTrue(z);
                    if (charArray[parsePosition.getIndex()] != '}') {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Unreadable format element at position ");
                        sb2.append(index);
                        throw new IllegalArgumentException(sb2.toString());
                    }
                }
                sb.append(charArray[parsePosition.getIndex()]);
                next(parsePosition);
            } else {
                appendQuotedString(str, parsePosition, sb, true);
            }
        }
        super.applyPattern(sb.toString());
        this.toPattern = insertFormats(super.toPattern(), arrayList2);
        if (containsElements(arrayList)) {
            Format[] formats = getFormats();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Format format = (Format) it.next();
                if (format != null) {
                    formats[i] = format;
                }
                i++;
            }
            super.setFormats(formats);
        }
    }

    public void setFormat(int i, Format format) {
        throw new UnsupportedOperationException();
    }

    public void setFormatByArgumentIndex(int i, Format format) {
        throw new UnsupportedOperationException();
    }

    public void setFormats(Format[] formatArr) {
        throw new UnsupportedOperationException();
    }

    public void setFormatsByArgumentIndex(Format[] formatArr) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !super.equals(obj) || ObjectUtils.notEqual(getClass(), obj.getClass())) {
            return false;
        }
        ExtendedMessageFormat extendedMessageFormat = (ExtendedMessageFormat) obj;
        return !ObjectUtils.notEqual(this.toPattern, extendedMessageFormat.toPattern) && !ObjectUtils.notEqual(this.registry, extendedMessageFormat.registry);
    }

    public int hashCode() {
        return (31 * ((super.hashCode() * 31) + ObjectUtils.hashCode(this.registry))) + ObjectUtils.hashCode(this.toPattern);
    }

    private Format getFormat(String str) {
        String str2;
        if (this.registry != null) {
            int indexOf = str.indexOf(44);
            if (indexOf > 0) {
                String trim = str.substring(0, indexOf).trim();
                str2 = str.substring(indexOf + 1).trim();
                str = trim;
            } else {
                str2 = null;
            }
            FormatFactory formatFactory = (FormatFactory) this.registry.get(str);
            if (formatFactory != null) {
                return formatFactory.getFormat(str, str2, getLocale());
            }
        }
        return null;
    }

    private int readArgumentIndex(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        seekNonWs(str, parsePosition);
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = false;
        while (!z && parsePosition.getIndex() < str.length()) {
            char charAt = str.charAt(parsePosition.getIndex());
            if (Character.isWhitespace(charAt)) {
                seekNonWs(str, parsePosition);
                charAt = str.charAt(parsePosition.getIndex());
                if (!(charAt == ',' || charAt == '}')) {
                    z = true;
                    next(parsePosition);
                }
            }
            if ((charAt == ',' || charAt == '}') && stringBuffer.length() > 0) {
                try {
                    return Integer.parseInt(stringBuffer.toString());
                } catch (NumberFormatException unused) {
                }
            }
            boolean z2 = !Character.isDigit(charAt);
            stringBuffer.append(charAt);
            z = z2;
            next(parsePosition);
        }
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid format argument index at position ");
            sb.append(index);
            sb.append(": ");
            sb.append(str.substring(index, parsePosition.getIndex()));
            throw new IllegalArgumentException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Unterminated format element at position ");
        sb2.append(index);
        throw new IllegalArgumentException(sb2.toString());
    }

    private String parseFormatDescription(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        seekNonWs(str, parsePosition);
        int index2 = parsePosition.getIndex();
        int i = 1;
        while (parsePosition.getIndex() < str.length()) {
            char charAt = str.charAt(parsePosition.getIndex());
            if (charAt == '\'') {
                getQuotedString(str, parsePosition, false);
            } else if (charAt == '{') {
                i++;
            } else if (charAt != '}') {
                continue;
            } else {
                i--;
                if (i == 0) {
                    return str.substring(index2, parsePosition.getIndex());
                }
            }
            next(parsePosition);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unterminated format element at position ");
        sb.append(index);
        throw new IllegalArgumentException(sb.toString());
    }

    private String insertFormats(String str, ArrayList<String> arrayList) {
        if (!containsElements(arrayList)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length() * 2);
        ParsePosition parsePosition = new ParsePosition(0);
        int i = -1;
        int i2 = 0;
        while (parsePosition.getIndex() < str.length()) {
            char charAt = str.charAt(parsePosition.getIndex());
            if (charAt == '\'') {
                appendQuotedString(str, parsePosition, sb, false);
            } else if (charAt != '{') {
                if (charAt == '}') {
                    i2--;
                }
                sb.append(charAt);
                next(parsePosition);
            } else {
                i2++;
                if (i2 == 1) {
                    i++;
                    sb.append(START_FE);
                    sb.append(readArgumentIndex(str, next(parsePosition)));
                    String str2 = (String) arrayList.get(i);
                    if (str2 != null) {
                        sb.append(START_FMT);
                        sb.append(str2);
                    }
                }
            }
        }
        return sb.toString();
    }

    private void seekNonWs(String str, ParsePosition parsePosition) {
        char[] charArray = str.toCharArray();
        do {
            int isMatch = StrMatcher.splitMatcher().isMatch(charArray, parsePosition.getIndex());
            parsePosition.setIndex(parsePosition.getIndex() + isMatch);
            if (isMatch <= 0) {
                return;
            }
        } while (parsePosition.getIndex() < str.length());
    }

    private ParsePosition next(ParsePosition parsePosition) {
        parsePosition.setIndex(parsePosition.getIndex() + 1);
        return parsePosition;
    }

    private StringBuilder appendQuotedString(String str, ParsePosition parsePosition, StringBuilder sb, boolean z) {
        int index = parsePosition.getIndex();
        char[] charArray = str.toCharArray();
        if (!z || charArray[index] != '\'') {
            int i = index;
            for (int index2 = parsePosition.getIndex(); index2 < str.length(); index2++) {
                if (z && str.substring(index2).startsWith(ESCAPED_QUOTE)) {
                    sb.append(charArray, i, parsePosition.getIndex() - i);
                    sb.append(QUOTE);
                    parsePosition.setIndex(ESCAPED_QUOTE.length() + index2);
                    i = parsePosition.getIndex();
                } else if (charArray[parsePosition.getIndex()] != '\'') {
                    next(parsePosition);
                } else {
                    next(parsePosition);
                    if (sb == null) {
                        sb = null;
                    } else {
                        sb.append(charArray, i, parsePosition.getIndex() - i);
                    }
                    return sb;
                }
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unterminated quoted string at position ");
            sb2.append(index);
            throw new IllegalArgumentException(sb2.toString());
        }
        next(parsePosition);
        if (sb == null) {
            sb = null;
        } else {
            sb.append(QUOTE);
        }
        return sb;
    }

    private void getQuotedString(String str, ParsePosition parsePosition, boolean z) {
        appendQuotedString(str, parsePosition, null, z);
    }

    private boolean containsElements(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return false;
        }
        for (Object obj : collection) {
            if (obj != null) {
                return true;
            }
        }
        return false;
    }
}
