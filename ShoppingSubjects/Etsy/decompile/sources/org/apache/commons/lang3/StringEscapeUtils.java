package org.apache.commons.lang3;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang3.text.translate.AggregateTranslator;
import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.EntityArrays;
import org.apache.commons.lang3.text.translate.LookupTranslator;
import org.apache.commons.lang3.text.translate.NumericEntityUnescaper;
import org.apache.commons.lang3.text.translate.NumericEntityUnescaper.OPTION;
import org.apache.commons.lang3.text.translate.OctalUnescaper;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;
import org.apache.commons.lang3.text.translate.UnicodeUnescaper;

public class StringEscapeUtils {
    public static final CharSequenceTranslator ESCAPE_CSV = new CsvEscaper();
    public static final CharSequenceTranslator ESCAPE_ECMASCRIPT = new AggregateTranslator(new LookupTranslator(new String[]{"'", "\\'"}, new String[]{"\"", "\\\""}, new String[]{"\\", "\\\\"}, new String[]{"/", "\\/"}), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE()), UnicodeEscaper.outsideOf(32, 127));
    public static final CharSequenceTranslator ESCAPE_HTML3 = new AggregateTranslator(new LookupTranslator(EntityArrays.BASIC_ESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE()));
    public static final CharSequenceTranslator ESCAPE_HTML4 = new AggregateTranslator(new LookupTranslator(EntityArrays.BASIC_ESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE()), new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE()));
    public static final CharSequenceTranslator ESCAPE_JAVA = new LookupTranslator(new String[]{"\"", "\\\""}, new String[]{"\\", "\\\\"}).with(new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE())).with(UnicodeEscaper.outsideOf(32, 127));
    public static final CharSequenceTranslator ESCAPE_XML = new AggregateTranslator(new LookupTranslator(EntityArrays.BASIC_ESCAPE()), new LookupTranslator(EntityArrays.APOS_ESCAPE()));
    public static final CharSequenceTranslator UNESCAPE_CSV = new CsvUnescaper();
    public static final CharSequenceTranslator UNESCAPE_ECMASCRIPT = UNESCAPE_JAVA;
    public static final CharSequenceTranslator UNESCAPE_HTML3 = new AggregateTranslator(new LookupTranslator(EntityArrays.BASIC_UNESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_UNESCAPE()), new NumericEntityUnescaper(new OPTION[0]));
    public static final CharSequenceTranslator UNESCAPE_HTML4 = new AggregateTranslator(new LookupTranslator(EntityArrays.BASIC_UNESCAPE()), new LookupTranslator(EntityArrays.ISO8859_1_UNESCAPE()), new LookupTranslator(EntityArrays.HTML40_EXTENDED_UNESCAPE()), new NumericEntityUnescaper(new OPTION[0]));
    public static final CharSequenceTranslator UNESCAPE_JAVA = new AggregateTranslator(new OctalUnescaper(), new UnicodeUnescaper(), new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_UNESCAPE()), new LookupTranslator(new String[]{"\\\\", "\\"}, new String[]{"\\\"", "\""}, new String[]{"\\'", "'"}, new String[]{"\\", ""}));
    public static final CharSequenceTranslator UNESCAPE_XML = new AggregateTranslator(new LookupTranslator(EntityArrays.BASIC_UNESCAPE()), new LookupTranslator(EntityArrays.APOS_UNESCAPE()), new NumericEntityUnescaper(new OPTION[0]));

    static class CsvEscaper extends CharSequenceTranslator {
        private static final char CSV_DELIMITER = ',';
        private static final char CSV_QUOTE = '\"';
        private static final String CSV_QUOTE_STR = String.valueOf(CSV_QUOTE);
        private static final char[] CSV_SEARCH_CHARS = {CSV_DELIMITER, CSV_QUOTE, CharUtils.CR, 10};

        CsvEscaper() {
        }

        public int translate(CharSequence charSequence, int i, Writer writer) throws IOException {
            if (i != 0) {
                throw new IllegalStateException("CsvEscaper should never reach the [1] index");
            }
            if (StringUtils.containsNone((CharSequence) charSequence.toString(), CSV_SEARCH_CHARS)) {
                writer.write(charSequence.toString());
            } else {
                writer.write(34);
                String obj = charSequence.toString();
                String str = CSV_QUOTE_STR;
                StringBuilder sb = new StringBuilder();
                sb.append(CSV_QUOTE_STR);
                sb.append(CSV_QUOTE_STR);
                writer.write(StringUtils.replace(obj, str, sb.toString()));
                writer.write(34);
            }
            return charSequence.length();
        }
    }

    static class CsvUnescaper extends CharSequenceTranslator {
        private static final char CSV_DELIMITER = ',';
        private static final char CSV_QUOTE = '\"';
        private static final String CSV_QUOTE_STR = String.valueOf(CSV_QUOTE);
        private static final char[] CSV_SEARCH_CHARS = {CSV_DELIMITER, CSV_QUOTE, CharUtils.CR, 10};

        CsvUnescaper() {
        }

        public int translate(CharSequence charSequence, int i, Writer writer) throws IOException {
            if (i != 0) {
                throw new IllegalStateException("CsvUnescaper should never reach the [1] index");
            } else if (charSequence.charAt(0) == '\"' && charSequence.charAt(charSequence.length() - 1) == '\"') {
                String obj = charSequence.subSequence(1, charSequence.length() - 1).toString();
                if (StringUtils.containsAny((CharSequence) obj, CSV_SEARCH_CHARS)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(CSV_QUOTE_STR);
                    sb.append(CSV_QUOTE_STR);
                    writer.write(StringUtils.replace(obj, sb.toString(), CSV_QUOTE_STR));
                } else {
                    writer.write(charSequence.toString());
                }
                return charSequence.length();
            } else {
                writer.write(charSequence.toString());
                return charSequence.length();
            }
        }
    }

    public static final String escapeJava(String str) {
        return ESCAPE_JAVA.translate(str);
    }

    public static final String escapeEcmaScript(String str) {
        return ESCAPE_ECMASCRIPT.translate(str);
    }

    public static final String unescapeJava(String str) {
        return UNESCAPE_JAVA.translate(str);
    }

    public static final String unescapeEcmaScript(String str) {
        return UNESCAPE_ECMASCRIPT.translate(str);
    }

    public static final String escapeHtml4(String str) {
        return ESCAPE_HTML4.translate(str);
    }

    public static final String escapeHtml3(String str) {
        return ESCAPE_HTML3.translate(str);
    }

    public static final String unescapeHtml4(String str) {
        return UNESCAPE_HTML4.translate(str);
    }

    public static final String unescapeHtml3(String str) {
        return UNESCAPE_HTML3.translate(str);
    }

    public static final String escapeXml(String str) {
        return ESCAPE_XML.translate(str);
    }

    public static final String unescapeXml(String str) {
        return UNESCAPE_XML.translate(str);
    }

    public static final String escapeCsv(String str) {
        return ESCAPE_CSV.translate(str);
    }

    public static final String unescapeCsv(String str) {
        return UNESCAPE_CSV.translate(str);
    }
}
