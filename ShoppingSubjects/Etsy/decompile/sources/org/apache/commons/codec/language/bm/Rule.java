package org.apache.commons.codec.language.bm;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.bm.Languages.LanguageSet;

public class Rule {
    public static final String ALL = "ALL";
    public static final RPattern ALL_STRINGS_RMATCHER = new RPattern() {
        public boolean isMatch(CharSequence charSequence) {
            return true;
        }
    };
    private static final String DOUBLE_QUOTE = "\"";
    private static final String HASH_INCLUDE = "#include";
    private static final Map<NameType, Map<RuleType, Map<String, List<Rule>>>> RULES = new EnumMap(NameType.class);
    private final RPattern lContext;
    private final String pattern;
    private final PhonemeExpr phoneme;
    private final RPattern rContext;

    public static final class Phoneme implements PhonemeExpr {
        public static final Comparator<Phoneme> COMPARATOR = new Comparator<Phoneme>() {
            public int compare(Phoneme phoneme, Phoneme phoneme2) {
                for (int i = 0; i < phoneme.phonemeText.length(); i++) {
                    if (i >= phoneme2.phonemeText.length()) {
                        return 1;
                    }
                    int charAt = phoneme.phonemeText.charAt(i) - phoneme2.phonemeText.charAt(i);
                    if (charAt != 0) {
                        return charAt;
                    }
                }
                if (phoneme.phonemeText.length() < phoneme2.phonemeText.length()) {
                    return -1;
                }
                return 0;
            }
        };
        private final LanguageSet languages;
        /* access modifiers changed from: private */
        public final CharSequence phonemeText;

        public Phoneme(CharSequence charSequence, LanguageSet languageSet) {
            this.phonemeText = charSequence;
            this.languages = languageSet;
        }

        public Phoneme append(CharSequence charSequence) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.phonemeText.toString());
            sb.append(charSequence.toString());
            return new Phoneme(sb.toString(), this.languages);
        }

        public LanguageSet getLanguages() {
            return this.languages;
        }

        public Iterable<Phoneme> getPhonemes() {
            return Collections.singleton(this);
        }

        public CharSequence getPhonemeText() {
            return this.phonemeText;
        }

        public Phoneme join(Phoneme phoneme) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.phonemeText.toString());
            sb.append(phoneme.phonemeText.toString());
            return new Phoneme(sb.toString(), this.languages.restrictTo(phoneme.languages));
        }
    }

    public interface PhonemeExpr {
        Iterable<Phoneme> getPhonemes();
    }

    public static final class PhonemeList implements PhonemeExpr {
        private final List<Phoneme> phonemes;

        public PhonemeList(List<Phoneme> list) {
            this.phonemes = list;
        }

        public List<Phoneme> getPhonemes() {
            return this.phonemes;
        }
    }

    public interface RPattern {
        boolean isMatch(CharSequence charSequence);
    }

    static {
        NameType[] values;
        RuleType[] values2;
        for (NameType nameType : NameType.values()) {
            EnumMap enumMap = new EnumMap(RuleType.class);
            for (RuleType ruleType : RuleType.values()) {
                HashMap hashMap = new HashMap();
                for (String str : Languages.getInstance(nameType).getLanguages()) {
                    try {
                        hashMap.put(str, parseRules(createScanner(nameType, ruleType, str), createResourceName(nameType, ruleType, str)));
                    } catch (IllegalStateException e) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Problem processing ");
                        sb.append(createResourceName(nameType, ruleType, str));
                        throw new IllegalStateException(sb.toString(), e);
                    }
                }
                if (!ruleType.equals(RuleType.RULES)) {
                    hashMap.put("common", parseRules(createScanner(nameType, ruleType, "common"), createResourceName(nameType, ruleType, "common")));
                }
                enumMap.put(ruleType, Collections.unmodifiableMap(hashMap));
            }
            RULES.put(nameType, Collections.unmodifiableMap(enumMap));
        }
    }

    /* access modifiers changed from: private */
    public static boolean contains(CharSequence charSequence, char c) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (charSequence.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    private static String createResourceName(NameType nameType, RuleType ruleType, String str) {
        return String.format("org/apache/commons/codec/language/bm/%s_%s_%s.txt", new Object[]{nameType.getName(), ruleType.getName(), str});
    }

    private static Scanner createScanner(NameType nameType, RuleType ruleType, String str) {
        String createResourceName = createResourceName(nameType, ruleType, str);
        InputStream resourceAsStream = Languages.class.getClassLoader().getResourceAsStream(createResourceName);
        if (resourceAsStream != null) {
            return new Scanner(resourceAsStream, "UTF-8");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to load resource: ");
        sb.append(createResourceName);
        throw new IllegalArgumentException(sb.toString());
    }

    private static Scanner createScanner(String str) {
        String format = String.format("org/apache/commons/codec/language/bm/%s.txt", new Object[]{str});
        InputStream resourceAsStream = Languages.class.getClassLoader().getResourceAsStream(format);
        if (resourceAsStream != null) {
            return new Scanner(resourceAsStream, "UTF-8");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to load resource: ");
        sb.append(format);
        throw new IllegalArgumentException(sb.toString());
    }

    /* access modifiers changed from: private */
    public static boolean endsWith(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        int length = charSequence.length() - 1;
        for (int length2 = charSequence2.length() - 1; length2 >= 0; length2--) {
            if (charSequence.charAt(length) != charSequence2.charAt(length2)) {
                return false;
            }
            length--;
        }
        return true;
    }

    public static List<Rule> getInstance(NameType nameType, RuleType ruleType, LanguageSet languageSet) {
        return getInstance(nameType, ruleType, languageSet.isSingleton() ? languageSet.getAny() : Languages.ANY);
    }

    public static List<Rule> getInstance(NameType nameType, RuleType ruleType, String str) {
        List<Rule> list = (List) ((Map) ((Map) RULES.get(nameType)).get(ruleType)).get(str);
        if (list != null) {
            return list;
        }
        throw new IllegalArgumentException(String.format("No rules found for %s, %s, %s.", new Object[]{nameType.getName(), ruleType.getName(), str}));
    }

    private static Phoneme parsePhoneme(String str) {
        int indexOf = str.indexOf("[");
        if (indexOf < 0) {
            return new Phoneme(str, Languages.ANY_LANGUAGE);
        }
        if (str.endsWith("]")) {
            return new Phoneme(str.substring(0, indexOf), LanguageSet.from(new HashSet(Arrays.asList(str.substring(indexOf + 1, str.length() - 1).split("[+]")))));
        }
        throw new IllegalArgumentException("Phoneme expression contains a '[' but does not end in ']'");
    }

    private static PhonemeExpr parsePhonemeExpr(String str) {
        if (!str.startsWith("(")) {
            return parsePhoneme(str);
        }
        if (!str.endsWith(")")) {
            throw new IllegalArgumentException("Phoneme starts with '(' so must end with ')'");
        }
        ArrayList arrayList = new ArrayList();
        String substring = str.substring(1, str.length() - 1);
        for (String parsePhoneme : substring.split("[|]")) {
            arrayList.add(parsePhoneme(parsePhoneme));
        }
        if (substring.startsWith("|") || substring.endsWith("|")) {
            arrayList.add(new Phoneme("", Languages.ANY_LANGUAGE));
        }
        return new PhonemeList(arrayList);
    }

    private static List<Rule> parseRules(Scanner scanner, String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean z = false;
        while (scanner.hasNextLine()) {
            i++;
            String nextLine = scanner.nextLine();
            if (z) {
                if (nextLine.endsWith("*/")) {
                    z = false;
                }
            } else if (nextLine.startsWith("/*")) {
                z = true;
            } else {
                int indexOf = nextLine.indexOf("//");
                String trim = (indexOf >= 0 ? nextLine.substring(0, indexOf) : nextLine).trim();
                if (trim.length() != 0) {
                    if (trim.startsWith(HASH_INCLUDE)) {
                        String trim2 = trim.substring(HASH_INCLUDE.length()).trim();
                        if (trim2.contains(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
                            PrintStream printStream = System.err;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Warining: malformed import statement: ");
                            sb.append(nextLine);
                            printStream.println(sb.toString());
                        } else {
                            Scanner createScanner = createScanner(trim2);
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str);
                            sb2.append("->");
                            sb2.append(trim2);
                            arrayList.addAll(parseRules(createScanner, sb2.toString()));
                        }
                    } else {
                        String[] split = trim.split("\\s+");
                        if (split.length != 4) {
                            PrintStream printStream2 = System.err;
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("Warning: malformed rule statement split into ");
                            sb3.append(split.length);
                            sb3.append(" parts: ");
                            sb3.append(nextLine);
                            printStream2.println(sb3.toString());
                        } else {
                            try {
                                final int i2 = i;
                                final String str2 = str;
                                AnonymousClass2 r5 = new Rule(stripQuotes(split[0]), stripQuotes(split[1]), stripQuotes(split[2]), parsePhonemeExpr(stripQuotes(split[3]))) {
                                    private final String loc = str2;
                                    private final int myLine = i2;

                                    public String toString() {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("Rule");
                                        sb.append("{line=");
                                        sb.append(this.myLine);
                                        sb.append(", loc='");
                                        sb.append(this.loc);
                                        sb.append('\'');
                                        sb.append('}');
                                        return sb.toString();
                                    }
                                };
                                arrayList.add(r5);
                            } catch (IllegalArgumentException e) {
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("Problem parsing line ");
                                sb4.append(i);
                                throw new IllegalStateException(sb4.toString(), e);
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private static RPattern pattern(final String str) {
        boolean startsWith = str.startsWith("^");
        boolean endsWith = str.endsWith("$");
        final String substring = str.substring(startsWith ? 1 : 0, endsWith ? str.length() - 1 : str.length());
        if (substring.contains("[")) {
            boolean startsWith2 = substring.startsWith("[");
            boolean endsWith2 = substring.endsWith("]");
            if (startsWith2 && endsWith2) {
                final String substring2 = substring.substring(1, substring.length() - 1);
                if (!substring2.contains("[")) {
                    boolean startsWith3 = substring2.startsWith("^");
                    if (startsWith3) {
                        substring2 = substring2.substring(1);
                    }
                    final boolean z = true ^ startsWith3;
                    if (startsWith && endsWith) {
                        return new RPattern() {
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() == 1 && Rule.contains(substring2, charSequence.charAt(0)) == z;
                            }
                        };
                    }
                    if (startsWith) {
                        return new RPattern() {
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() > 0 && Rule.contains(substring2, charSequence.charAt(0)) == z;
                            }
                        };
                    }
                    if (endsWith) {
                        return new RPattern() {
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() > 0 && Rule.contains(substring2, charSequence.charAt(charSequence.length() - 1)) == z;
                            }
                        };
                    }
                }
            }
        } else if (!startsWith || !endsWith) {
            if ((startsWith || endsWith) && substring.length() == 0) {
                return ALL_STRINGS_RMATCHER;
            }
            if (startsWith) {
                return new RPattern() {
                    public boolean isMatch(CharSequence charSequence) {
                        return Rule.startsWith(charSequence, substring);
                    }
                };
            }
            if (endsWith) {
                return new RPattern() {
                    public boolean isMatch(CharSequence charSequence) {
                        return Rule.endsWith(charSequence, substring);
                    }
                };
            }
        } else if (substring.length() == 0) {
            return new RPattern() {
                public boolean isMatch(CharSequence charSequence) {
                    return charSequence.length() == 0;
                }
            };
        } else {
            return new RPattern() {
                public boolean isMatch(CharSequence charSequence) {
                    return charSequence.equals(substring);
                }
            };
        }
        return new RPattern() {
            Pattern pattern = Pattern.compile(str);

            public boolean isMatch(CharSequence charSequence) {
                return this.pattern.matcher(charSequence).find();
            }
        };
    }

    /* access modifiers changed from: private */
    public static boolean startsWith(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        for (int i = 0; i < charSequence2.length(); i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static String stripQuotes(String str) {
        if (str.startsWith(DOUBLE_QUOTE)) {
            str = str.substring(1);
        }
        return str.endsWith(DOUBLE_QUOTE) ? str.substring(0, str.length() - 1) : str;
    }

    public Rule(String str, String str2, String str3, PhonemeExpr phonemeExpr) {
        this.pattern = str;
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("$");
        this.lContext = pattern(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("^");
        sb2.append(str3);
        this.rContext = pattern(sb2.toString());
        this.phoneme = phonemeExpr;
    }

    public RPattern getLContext() {
        return this.lContext;
    }

    public String getPattern() {
        return this.pattern;
    }

    public PhonemeExpr getPhoneme() {
        return this.phoneme;
    }

    public RPattern getRContext() {
        return this.rContext;
    }

    public boolean patternAndContextMatches(CharSequence charSequence, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Can not match pattern at negative indexes");
        }
        int length = this.pattern.length() + i;
        boolean z = false;
        if (length > charSequence.length()) {
            return false;
        }
        boolean equals = charSequence.subSequence(i, length).equals(this.pattern);
        boolean isMatch = this.rContext.isMatch(charSequence.subSequence(length, charSequence.length()));
        boolean isMatch2 = this.lContext.isMatch(charSequence.subSequence(0, i));
        if (equals && isMatch && isMatch2) {
            z = true;
        }
        return z;
    }
}
