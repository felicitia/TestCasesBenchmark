package org.apache.commons.codec.language.bm;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.codec.language.bm.Languages.LanguageSet;
import org.apache.commons.codec.language.bm.Rule.Phoneme;
import org.apache.commons.codec.language.bm.Rule.PhonemeExpr;

public class PhoneticEngine {
    private static final Map<NameType, Set<String>> NAME_PREFIXES = new EnumMap(NameType.class);
    private final boolean concat;
    private final Lang lang;
    private final NameType nameType;
    private final RuleType ruleType;

    static final class PhonemeBuilder {
        private final Set<Phoneme> phonemes;

        public static PhonemeBuilder empty(LanguageSet languageSet) {
            return new PhonemeBuilder(Collections.singleton(new Phoneme("", languageSet)));
        }

        private PhonemeBuilder(Set<Phoneme> set) {
            this.phonemes = set;
        }

        public PhonemeBuilder append(CharSequence charSequence) {
            HashSet hashSet = new HashSet();
            for (Phoneme append : this.phonemes) {
                hashSet.add(append.append(charSequence));
            }
            return new PhonemeBuilder(hashSet);
        }

        public PhonemeBuilder apply(PhonemeExpr phonemeExpr) {
            HashSet hashSet = new HashSet();
            for (Phoneme phoneme : this.phonemes) {
                for (Phoneme join : phonemeExpr.getPhonemes()) {
                    Phoneme join2 = phoneme.join(join);
                    if (!join2.getLanguages().isEmpty()) {
                        hashSet.add(join2);
                    }
                }
            }
            return new PhonemeBuilder(hashSet);
        }

        public Set<Phoneme> getPhonemes() {
            return this.phonemes;
        }

        public String makeString() {
            StringBuilder sb = new StringBuilder();
            for (Phoneme phoneme : this.phonemes) {
                if (sb.length() > 0) {
                    sb.append("|");
                }
                sb.append(phoneme.getPhonemeText());
            }
            return sb.toString();
        }
    }

    private static final class RulesApplication {
        private final List<Rule> finalRules;
        private boolean found;
        private int i;
        private final CharSequence input;
        private PhonemeBuilder phonemeBuilder;

        public RulesApplication(List<Rule> list, CharSequence charSequence, PhonemeBuilder phonemeBuilder2, int i2) {
            if (list == null) {
                throw new NullPointerException("The finalRules argument must not be null");
            }
            this.finalRules = list;
            this.phonemeBuilder = phonemeBuilder2;
            this.input = charSequence;
            this.i = i2;
        }

        public int getI() {
            return this.i;
        }

        public PhonemeBuilder getPhonemeBuilder() {
            return this.phonemeBuilder;
        }

        public RulesApplication invoke() {
            int i2 = 0;
            this.found = false;
            Iterator it = this.finalRules.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Rule rule = (Rule) it.next();
                int length = rule.getPattern().length();
                if (rule.patternAndContextMatches(this.input, this.i)) {
                    this.phonemeBuilder = this.phonemeBuilder.apply(rule.getPhoneme());
                    this.found = true;
                    i2 = length;
                    break;
                }
                i2 = length;
            }
            if (!this.found) {
                i2 = 1;
            }
            this.i += i2;
            return this;
        }

        public boolean isFound() {
            return this.found;
        }
    }

    static {
        NAME_PREFIXES.put(NameType.ASHKENAZI, Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"bar", "ben", "da", "de", "van", "von"}))));
        NAME_PREFIXES.put(NameType.SEPHARDIC, Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"al", "el", "da", "dal", "de", "del", "dela", "de la", "della", "des", "di", "do", "dos", "du", "van", "von"}))));
        NAME_PREFIXES.put(NameType.GENERIC, Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"da", "dal", "de", "del", "dela", "de la", "della", "des", "di", "do", "dos", "du", "van", "von"}))));
    }

    private static CharSequence cacheSubSequence(final CharSequence charSequence) {
        final CharSequence[][] charSequenceArr = (CharSequence[][]) Array.newInstance(CharSequence.class, new int[]{charSequence.length(), charSequence.length()});
        return new CharSequence() {
            public char charAt(int i) {
                return charSequence.charAt(i);
            }

            public int length() {
                return charSequence.length();
            }

            public CharSequence subSequence(int i, int i2) {
                if (i == i2) {
                    return "";
                }
                int i3 = i2 - 1;
                CharSequence charSequence = charSequenceArr[i][i3];
                if (charSequence == null) {
                    charSequence = charSequence.subSequence(i, i2);
                    charSequenceArr[i][i3] = charSequence;
                }
                return charSequence;
            }
        };
    }

    private static String join(Iterable<String> iterable, String str) {
        StringBuilder sb = new StringBuilder();
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            sb.append((String) it.next());
        }
        while (it.hasNext()) {
            sb.append(str);
            sb.append((String) it.next());
        }
        return sb.toString();
    }

    public PhoneticEngine(NameType nameType2, RuleType ruleType2, boolean z) {
        if (ruleType2 == RuleType.RULES) {
            StringBuilder sb = new StringBuilder();
            sb.append("ruleType must not be ");
            sb.append(RuleType.RULES);
            throw new IllegalArgumentException(sb.toString());
        }
        this.nameType = nameType2;
        this.ruleType = ruleType2;
        this.concat = z;
        this.lang = Lang.instance(nameType2);
    }

    private PhonemeBuilder applyFinalRules(PhonemeBuilder phonemeBuilder, List<Rule> list) {
        if (list == null) {
            throw new NullPointerException("finalRules can not be null");
        } else if (list.isEmpty()) {
            return phonemeBuilder;
        } else {
            TreeSet treeSet = new TreeSet(Phoneme.COMPARATOR);
            for (Phoneme phoneme : phonemeBuilder.getPhonemes()) {
                PhonemeBuilder empty = PhonemeBuilder.empty(phoneme.getLanguages());
                CharSequence cacheSubSequence = cacheSubSequence(phoneme.getPhonemeText());
                int i = 0;
                while (i < cacheSubSequence.length()) {
                    RulesApplication invoke = new RulesApplication(list, cacheSubSequence, empty, i).invoke();
                    boolean isFound = invoke.isFound();
                    PhonemeBuilder phonemeBuilder2 = invoke.getPhonemeBuilder();
                    PhonemeBuilder append = !isFound ? phonemeBuilder2.append(cacheSubSequence.subSequence(i, i + 1)) : phonemeBuilder2;
                    i = invoke.getI();
                    empty = append;
                }
                treeSet.addAll(empty.getPhonemes());
            }
            return new PhonemeBuilder(treeSet);
        }
    }

    public String encode(String str) {
        return encode(str, this.lang.guessLanguages(str));
    }

    public String encode(String str, LanguageSet languageSet) {
        String str2;
        List instance = Rule.getInstance(this.nameType, RuleType.RULES, languageSet);
        List instance2 = Rule.getInstance(this.nameType, this.ruleType, "common");
        List instance3 = Rule.getInstance(this.nameType, this.ruleType, languageSet);
        String trim = str.toLowerCase(Locale.ENGLISH).replace('-', ' ').trim();
        int i = 0;
        if (this.nameType == NameType.GENERIC) {
            if (trim.length() < 2 || !trim.substring(0, 2).equals("d'")) {
                for (String str3 : (Set) NAME_PREFIXES.get(this.nameType)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str3);
                    sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    if (trim.startsWith(sb.toString())) {
                        String substring = trim.substring(str3.length() + 1);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str3);
                        sb2.append(substring);
                        String sb3 = sb2.toString();
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("(");
                        sb4.append(encode(substring));
                        sb4.append(")-(");
                        sb4.append(encode(sb3));
                        sb4.append(")");
                        return sb4.toString();
                    }
                }
            } else {
                String substring2 = trim.substring(2);
                StringBuilder sb5 = new StringBuilder();
                sb5.append("d");
                sb5.append(substring2);
                String sb6 = sb5.toString();
                StringBuilder sb7 = new StringBuilder();
                sb7.append("(");
                sb7.append(encode(substring2));
                sb7.append(")-(");
                sb7.append(encode(sb6));
                sb7.append(")");
                return sb7.toString();
            }
        }
        List<String> asList = Arrays.asList(trim.split("\\s+"));
        ArrayList<String> arrayList = new ArrayList<>();
        switch (this.nameType) {
            case SEPHARDIC:
                for (String split : asList) {
                    String[] split2 = split.split("'");
                    arrayList.add(split2[split2.length - 1]);
                }
                arrayList.removeAll((Collection) NAME_PREFIXES.get(this.nameType));
                break;
            case ASHKENAZI:
                arrayList.addAll(asList);
                arrayList.removeAll((Collection) NAME_PREFIXES.get(this.nameType));
                break;
            case GENERIC:
                arrayList.addAll(asList);
                break;
            default:
                StringBuilder sb8 = new StringBuilder();
                sb8.append("Unreachable case: ");
                sb8.append(this.nameType);
                throw new IllegalStateException(sb8.toString());
        }
        if (this.concat) {
            str2 = join(arrayList, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        } else if (arrayList.size() == 1) {
            str2 = (String) asList.iterator().next();
        } else {
            StringBuilder sb9 = new StringBuilder();
            for (String str4 : arrayList) {
                sb9.append("-");
                sb9.append(encode(str4));
            }
            return sb9.substring(1);
        }
        PhonemeBuilder empty = PhonemeBuilder.empty(languageSet);
        CharSequence cacheSubSequence = cacheSubSequence(str2);
        while (i < cacheSubSequence.length()) {
            RulesApplication invoke = new RulesApplication(instance, cacheSubSequence, empty, i).invoke();
            i = invoke.getI();
            empty = invoke.getPhonemeBuilder();
        }
        return applyFinalRules(applyFinalRules(empty, instance2), instance3).makeString();
    }

    public Lang getLang() {
        return this.lang;
    }

    public NameType getNameType() {
        return this.nameType;
    }

    public RuleType getRuleType() {
        return this.ruleType;
    }

    public boolean isConcat() {
        return this.concat;
    }
}
