package com.contextlogic.wish.util;

import android.text.SpannableString;
import android.text.TextUtils;
import com.contextlogic.wish.ui.text.WishFontSpan;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static String toTitleCase(String str) {
        char[] charArray;
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (char c : str.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                z = true;
            } else if (z) {
                c = Character.toTitleCase(c);
                z = false;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String joinList(ArrayList<String> arrayList, String str, boolean z, boolean z2) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayList.size(); i++) {
            stringBuffer.append((String) arrayList.get(i));
            if (i < arrayList.size() - 2) {
                if (z2) {
                    stringBuffer.append(", ");
                } else {
                    stringBuffer.append(",");
                }
            } else if (i == arrayList.size() - 2) {
                if (z) {
                    stringBuffer.append(String.format(" %s ", new Object[]{str}));
                } else {
                    stringBuffer.append(String.format("%s", new Object[]{str}));
                }
            }
        }
        return stringBuffer.toString();
    }

    public static String join(ArrayList<String> arrayList, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            if (i != 0) {
                sb.append(str);
            }
            sb.append((String) arrayList.get(i));
        }
        return sb.toString();
    }

    public static String capitalize(String str) {
        if (str == null || TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toUpperCase(str.charAt(0)));
        sb.append(str.substring(1));
        return sb.toString();
    }

    public static String join(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length - 1; i++) {
            sb.append(charArray[i]);
            sb.append(str2);
        }
        sb.append(charArray[charArray.length - 1]);
        return sb.toString();
    }

    public static SpannableString boldSubstring(String str, String str2) {
        if (str == null || str2 == null || str.isEmpty() || str2.isEmpty()) {
            return null;
        }
        SpannableString spannableString = new SpannableString(str);
        int indexOf = str.indexOf(str2);
        int length = str2.length() + indexOf;
        if (indexOf == -1 || indexOf > length || length > str.length()) {
            return spannableString;
        }
        spannableString.setSpan(new WishFontSpan(1), indexOf, length, 33);
        return spannableString;
    }

    public static List<String> pullLinks(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        Matcher matcher = Pattern.compile("\\(?\\b(https://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]").matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            if (group.startsWith("(") && group.endsWith(")")) {
                group = group.substring(1, group.length() - 1);
            }
            arrayList.add(group);
        }
        return arrayList;
    }
}
