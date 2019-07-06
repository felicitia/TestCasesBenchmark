package com.onfido.android.sdk.capture.ui.camera.liveness;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.CustomTypefaceSpan;
import java.util.Random;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public enum LivenessChallenge {
    ;
    
    private final Type b;

    static final class DIGITS extends LivenessChallenge {
        private final Random a;
        private final boolean b;
        public int[] query;

        DIGITS(String str, int i) {
            super(str, i, Type.RECITE);
            this.a = new Random();
            this.b = true;
            reload();
        }

        private final int[] a() {
            return CollectionsKt.toIntArray(CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(this.a.nextInt(10)), Integer.valueOf(this.a.nextInt(10)), Integer.valueOf(this.a.nextInt(10))}));
        }

        public int[] getQuery() {
            int[] iArr = this.query;
            if (iArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("query");
            }
            return iArr;
        }

        public boolean isSpoken() {
            return this.b;
        }

        public void onDraw(Context context, int i, ViewGroup viewGroup) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(viewGroup, "container");
            viewGroup.removeView(viewGroup.findViewById(R.id.challenge));
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(getQuery()[0]);
            sb.append(" - ");
            sb.append(getQuery()[1]);
            sb.append(" - ");
            sb.append(getQuery()[2]);
            String sb2 = sb.toString();
            View inflate = View.inflate(context, R.layout.onfido_challenge_digits, viewGroup);
            CharSequence charSequence = sb2;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
            int i2 = 0;
            int i3 = 0;
            while (i2 < charSequence.length()) {
                char charAt = charSequence.charAt(i2);
                int i4 = i3 + 1;
                if ('0' <= charAt && '9' >= charAt) {
                    Typeface typeface = Typeface.DEFAULT_BOLD;
                    Intrinsics.checkExpressionValueIsNotNull(typeface, "Typeface.DEFAULT_BOLD");
                    spannableStringBuilder.setSpan(new CustomTypefaceSpan("normal", typeface), i3, i4, 18);
                }
                i2++;
                i3 = i4;
            }
            ((TextView) inflate.findViewById(R.id.subtitle)).setText(spannableStringBuilder);
            Rect rect = new Rect();
            ((TextView) inflate.findViewById(R.id.title)).getPaint().getTextBounds(sb2, 0, sb2.length(), rect);
            LayoutParams layoutParams = ((TextView) inflate.findViewById(R.id.title)).getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            }
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(layoutParams2.leftMargin, i - rect.height(), layoutParams2.rightMargin, layoutParams2.bottomMargin);
        }

        public void reload() {
            setQuery(a());
        }

        public void setQuery(int[] iArr) {
            Intrinsics.checkParameterIsNotNull(iArr, "<set-?>");
            this.query = iArr;
        }
    }

    public enum MovementType {
        public static final MovementType TURN_LEFT = null;
        public static final MovementType TURN_RIGHT = null;
        private static final /* synthetic */ MovementType[] a = null;
        private final String b;

        static {
            MovementType movementType = new MovementType("TURN_LEFT", 0, "turnLeft");
            TURN_LEFT = movementType;
            MovementType movementType2 = new MovementType("TURN_RIGHT", 1, "turnRight");
            TURN_RIGHT = movementType2;
            a = new MovementType[]{movementType, movementType2};
        }

        protected MovementType(String str, int i, String str2) {
            Intrinsics.checkParameterIsNotNull(str2, "id");
            this.b = str2;
        }

        public static MovementType valueOf(String str) {
            return (MovementType) Enum.valueOf(MovementType.class, str);
        }

        public static MovementType[] values() {
            return (MovementType[]) a.clone();
        }

        public final String getId() {
            return this.b;
        }
    }

    static final class TURN_FACE extends LivenessChallenge {
        private final Random a;
        private final boolean b;
        public MovementType query;

        TURN_FACE(String str, int i) {
            super(str, i, Type.MOVEMENT);
            this.a = new Random();
            reload();
        }

        private final MovementType a() {
            return MovementType.values()[this.a.nextInt(((Object[]) MovementType.values()).length)];
        }

        public MovementType getQuery() {
            MovementType movementType = this.query;
            if (movementType == null) {
                Intrinsics.throwUninitializedPropertyAccessException("query");
            }
            return movementType;
        }

        public boolean isSpoken() {
            return this.b;
        }

        public void onDraw(Context context, int i, ViewGroup viewGroup) {
            int i2;
            int i3;
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(viewGroup, "container");
            viewGroup.removeView(viewGroup.findViewById(R.id.challenge));
            switch (getQuery()) {
                case TURN_LEFT:
                    i2 = R.string.onfido_liveness_challenge_turn_left_title;
                    break;
                case TURN_RIGHT:
                    i2 = R.string.onfido_liveness_challenge_turn_right_title;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            String string = context.getString(i2);
            switch (getQuery()) {
                case TURN_RIGHT:
                    i3 = R.drawable.onfido_liveness_arrow_right;
                    break;
                case TURN_LEFT:
                    i3 = R.drawable.onfido_liveness_arrow_left;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            View inflate = View.inflate(context, R.layout.onfido_challenge_movement, viewGroup);
            ((TextView) inflate.findViewById(R.id.movementTitle)).setText(string);
            Rect rect = new Rect();
            ((TextView) inflate.findViewById(R.id.movementTitle)).getPaint().getTextBounds(string, 0, string.length(), rect);
            LayoutParams layoutParams = ((TextView) inflate.findViewById(R.id.movementTitle)).getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            }
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(layoutParams2.leftMargin, i - rect.height(), layoutParams2.rightMargin, layoutParams2.bottomMargin);
            ((ImageView) inflate.findViewById(R.id.arrow)).setImageDrawable(ContextCompat.getDrawable(context, i3));
        }

        public void reload() {
            setQuery(a());
        }

        public void setQuery(MovementType movementType) {
            Intrinsics.checkParameterIsNotNull(movementType, "<set-?>");
            this.query = movementType;
        }
    }

    public enum Type {
        public static final Type MOVEMENT = null;
        public static final Type RECITE = null;
        private static final /* synthetic */ Type[] a = null;

        static {
            Type type = new Type("RECITE", 0);
            RECITE = type;
            Type type2 = new Type("MOVEMENT", 1);
            MOVEMENT = type2;
            a = new Type[]{type, type2};
        }

        protected Type(String str, int i) {
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) a.clone();
        }
    }

    protected LivenessChallenge(Type type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.b = type;
    }

    public abstract Object getQuery();

    public final Type getType() {
        return this.b;
    }

    public abstract boolean isSpoken();

    public abstract void onDraw(Context context, int i, ViewGroup viewGroup);

    public abstract void reload();
}
