package org.apache.commons.codec.language.bm;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.bm.Languages.LanguageSet;

public class Lang {
    private static final String LANGUAGE_RULES_RN = "org/apache/commons/codec/language/bm/lang.txt";
    private static final Map<NameType, Lang> Langs = new EnumMap(NameType.class);
    private final Languages languages;
    private final List<LangRule> rules;

    private static final class LangRule {
        /* access modifiers changed from: private */
        public final boolean acceptOnMatch;
        /* access modifiers changed from: private */
        public final Set<String> languages;
        private final Pattern pattern;

        private LangRule(Pattern pattern2, Set<String> set, boolean z) {
            this.pattern = pattern2;
            this.languages = set;
            this.acceptOnMatch = z;
        }

        public boolean matches(String str) {
            return this.pattern.matcher(str).find();
        }
    }

    static {
        NameType[] values;
        for (NameType nameType : NameType.values()) {
            Langs.put(nameType, loadFromResource(LANGUAGE_RULES_RN, Languages.getInstance(nameType)));
        }
    }

    public static Lang instance(NameType nameType) {
        return (Lang) Langs.get(nameType);
    }

    public static Lang loadFromResource(String str, Languages languages2) {
        ArrayList arrayList = new ArrayList();
        InputStream resourceAsStream = Lang.class.getClassLoader().getResourceAsStream(str);
        if (resourceAsStream == null) {
            throw new IllegalStateException("Unable to resolve required resource:org/apache/commons/codec/language/bm/lang.txt");
        }
        Scanner scanner = new Scanner(resourceAsStream, "UTF-8");
        while (true) {
            boolean z = false;
            while (true) {
                if (!scanner.hasNextLine()) {
                    return new Lang(arrayList, languages2);
                }
                String nextLine = scanner.nextLine();
                if (z) {
                    if (nextLine.endsWith("*/")) {
                    }
                } else if (nextLine.startsWith("/*")) {
                    z = true;
                } else {
                    int indexOf = nextLine.indexOf("//");
                    String trim = (indexOf >= 0 ? nextLine.substring(0, indexOf) : nextLine).trim();
                    if (trim.length() != 0) {
                        String[] split = trim.split("\\s+");
                        if (split.length != 3) {
                            PrintStream printStream = System.err;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Warning: malformed line '");
                            sb.append(nextLine);
                            sb.append("'");
                            printStream.println(sb.toString());
                        } else {
                            arrayList.add(new LangRule(Pattern.compile(split[0]), new HashSet(Arrays.asList(split[1].split("\\+"))), split[2].equals("true")));
                        }
                    }
                }
            }
        }
    }

    private Lang(List<LangRule> list, Languages languages2) {
        this.rules = Collections.unmodifiableList(list);
        this.languages = languages2;
    }

    public String guessLanguage(String str) {
        LanguageSet guessLanguages = guessLanguages(str);
        return guessLanguages.isSingleton() ? guessLanguages.getAny() : Languages.ANY;
    }

    public LanguageSet guessLanguages(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        HashSet hashSet = new HashSet(this.languages.getLanguages());
        for (LangRule langRule : this.rules) {
            if (langRule.matches(lowerCase)) {
                if (langRule.acceptOnMatch) {
                    hashSet.retainAll(langRule.languages);
                } else {
                    hashSet.removeAll(langRule.languages);
                }
            }
        }
        LanguageSet from = LanguageSet.from(hashSet);
        return from.equals(Languages.NO_LANGUAGES) ? Languages.ANY_LANGUAGE : from;
    }
}
