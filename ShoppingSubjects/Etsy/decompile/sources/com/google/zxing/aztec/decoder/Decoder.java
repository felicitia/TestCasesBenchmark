package com.google.zxing.aztec.decoder;

import com.etsy.android.lib.convos.Draft;
import com.etsy.android.lib.models.apiv3.SearchCategoryRedirectPage;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import org.apache.commons.math3.geometry.VectorFormat;

public final class Decoder {
    private static final String[] a = {"CTRL_PS", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] b = {"CTRL_PS", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", SearchCategoryRedirectPage.PARAM_QUERY, "r", "s", "t", "u", "v", "w", EtsyDialogFragment.OPT_X_BUTTON, "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] c = {"CTRL_PS", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] d = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", Draft.IMAGE_DELIMITER, ";", "<", "=", ">", "?", "[", "]", VectorFormat.DEFAULT_PREFIX, VectorFormat.DEFAULT_SUFFIX, "CTRL_UL"};
    private static final String[] e = {"CTRL_PS", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};

    private enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }
}
