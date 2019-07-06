package com.contextlogic.wish.dialog.multibutton;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.util.FontUtil;
import java.util.ArrayList;

public class MultiButtonDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    private boolean mCancelable;

    public enum ImageSize {
        EXTRA_SMALL,
        SMALL,
        MEDIUM,
        LARGE;

        public static ImageSize fromInteger(int i) {
            switch (i) {
                case 0:
                    return EXTRA_SMALL;
                case 1:
                    return SMALL;
                case 2:
                    return MEDIUM;
                case 3:
                    return LARGE;
                default:
                    return null;
            }
        }
    }

    public static class MultiButtonDialogFragmentBuilder<A extends BaseActivity> {
        private int mBackgroundColor;
        private boolean mCancelable;
        private ArrayList<MultiButtonDialogChoice> mChoices;
        private boolean mHideXButton;
        private int mImageResource;
        private ImageSize mImageSize;
        private CharSequence mSubTitle;
        private String mTitle;
        private WishImage mWishImage;

        public MultiButtonDialogFragmentBuilder setTitle(String str) {
            this.mTitle = str;
            return this;
        }

        public MultiButtonDialogFragmentBuilder setSubTitle(CharSequence charSequence) {
            this.mSubTitle = charSequence;
            return this;
        }

        public MultiButtonDialogFragmentBuilder setImageResource(int i) {
            this.mImageResource = i;
            return this;
        }

        public MultiButtonDialogFragmentBuilder setButtons(ArrayList<MultiButtonDialogChoice> arrayList) {
            this.mChoices = arrayList;
            return this;
        }

        public MultiButtonDialogFragmentBuilder hideXButton() {
            this.mHideXButton = true;
            return this;
        }

        public MultiButtonDialogFragmentBuilder setCancelable(boolean z) {
            this.mCancelable = z;
            return this;
        }

        public MultiButtonDialogFragmentBuilder setImageSize(ImageSize imageSize) {
            this.mImageSize = imageSize;
            return this;
        }

        public MultiButtonDialogFragmentBuilder setWishImage(WishImage wishImage) {
            this.mWishImage = wishImage;
            return this;
        }

        public MultiButtonDialogFragment build() {
            return MultiButtonDialogFragment.createMultiButtonDialog(this.mTitle, this.mSubTitle, this.mImageResource, this.mBackgroundColor, this.mHideXButton, this.mCancelable, this.mChoices, this.mImageSize, this.mWishImage);
        }
    }

    public static MultiButtonDialogFragment<BaseActivity> createMultiButtonDialog(String str, CharSequence charSequence, int i, int i2, boolean z, boolean z2, ArrayList<MultiButtonDialogChoice> arrayList, ImageSize imageSize, WishImage wishImage) {
        MultiButtonDialogFragment<BaseActivity> multiButtonDialogFragment = new MultiButtonDialogFragment<>();
        Bundle bundle = new Bundle();
        if (str != null) {
            bundle.putString("ArgumentTitle", str);
        }
        if (charSequence != null) {
            bundle.putCharSequence("ArgumentSubtitle", charSequence);
        }
        if (i != 0) {
            bundle.putInt("ArgumentImage", i);
        }
        if (i2 != 0) {
            bundle.putInt("ArgumentColor", i2);
        }
        if (arrayList != null && arrayList.size() > 0) {
            bundle.putParcelableArrayList("ArgumentChoices", arrayList);
        }
        if (z) {
            bundle.putBoolean("ArgumentXButton", z);
        }
        if (imageSize != null) {
            bundle.putInt("ArgumentImageSize", imageSize.ordinal());
        }
        bundle.putBoolean("ArgumentCancelable", z2);
        if (wishImage != null) {
            bundle.putParcelable("ArgumentWishImage", wishImage);
        }
        multiButtonDialogFragment.setArguments(bundle);
        return multiButtonDialogFragment;
    }

    public static MultiButtonDialogFragment<BaseActivity> createMultiButtonYesDialog(String str, CharSequence charSequence, String str2, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(MultiButtonDialogChoice.createCustomYesChoice(str2, i));
        return createMultiButtonDialog(str, charSequence, 0, 0, false, true, arrayList, null, null);
    }

    public static MultiButtonDialogFragment<BaseActivity> createCustomMultiButtonYesNoDialog(String str, CharSequence charSequence, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(MultiButtonDialogChoice.createCustomYesChoice(str2, R.drawable.main_button_selector));
        arrayList.add(MultiButtonDialogChoice.createCustomNoChoice(str3));
        return createMultiButtonDialog(str, charSequence, 0, 0, false, true, arrayList, null, null);
    }

    public static MultiButtonDialogFragment<BaseActivity> createMultiButtonYesNoDialog(String str, CharSequence charSequence) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(MultiButtonDialogChoice.createYesChoice());
        arrayList.add(MultiButtonDialogChoice.createNoChoice());
        return createMultiButtonDialog(str, charSequence, 0, 0, true, true, arrayList, null, null);
    }

    public static MultiButtonDialogFragment<BaseActivity> createMultiButtonOkDialog(String str, CharSequence charSequence) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(MultiButtonDialogChoice.createOkChoice());
        return createMultiButtonDialog(str, charSequence, 0, 0, true, true, arrayList, null, null);
    }

    public static MultiButtonDialogFragment<BaseActivity> createMultiButtonErrorDialog(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = WishApplication.getInstance().getString(R.string.general_error);
        }
        CharSequence charSequence2 = charSequence;
        ArrayList arrayList = new ArrayList();
        arrayList.add(MultiButtonDialogChoice.createOkChoice());
        return createMultiButtonDialog(WishApplication.getInstance().getString(R.string.oops), charSequence2, 0, 0, true, true, arrayList, null, null);
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.multi_button_dialog_fragment, viewGroup, false);
        Bundle arguments = getArguments();
        this.mCancelable = arguments.getBoolean("ArgumentCancelable", true);
        String string = arguments.getString("ArgumentTitle");
        CharSequence charSequence = arguments.getCharSequence("ArgumentSubtitle");
        int i = arguments.getInt("ArgumentImage");
        int i2 = arguments.getInt("ArgumentColor");
        boolean z = getArguments().getBoolean("ArgumentXButton");
        WishImage wishImage = (WishImage) getArguments().getParcelable("ArgumentWishImage");
        ArrayList parcelableArrayList = arguments.getParcelableArrayList("ArgumentChoices");
        ImageSize fromInteger = ImageSize.fromInteger(getArguments().getInt("ArgumentImageSize", ImageSize.SMALL.ordinal()));
        AutoReleasableImageView autoReleasableImageView = (AutoReleasableImageView) inflate.findViewById(R.id.multi_button_dialog_fragment_x_button);
        AutoReleasableImageView autoReleasableImageView2 = (AutoReleasableImageView) inflate.findViewById(R.id.multi_button_dialog_fragment_image);
        NetworkImageView networkImageView = (NetworkImageView) inflate.findViewById(R.id.multi_button_dialog_fragment_network_image);
        ThemedTextView themedTextView = (ThemedTextView) inflate.findViewById(R.id.multi_button_dialog_fragment_title);
        ThemedTextView themedTextView2 = (ThemedTextView) inflate.findViewById(R.id.multi_button_dialog_fragment_description);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.multi_button_dialog_fragment_content);
        if (i != 0) {
            autoReleasableImageView2.setImageResource(i);
            autoReleasableImageView2.setVisibility(0);
        } else if (wishImage != null) {
            networkImageView.setImage(wishImage);
            networkImageView.setVisibility(0);
        }
        if (i2 != 0) {
            inflate.setBackgroundColor(WishApplication.getInstance().getResources().getColor(i2));
            linearLayout.setBackgroundColor(WishApplication.getInstance().getResources().getColor(i2));
        }
        if (string != null) {
            themedTextView.setText(string);
        } else {
            themedTextView.setVisibility(8);
        }
        if (charSequence != null) {
            themedTextView2.setText(charSequence);
            themedTextView2.setLinkTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
            themedTextView2.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            themedTextView2.setVisibility(8);
        }
        if (z) {
            autoReleasableImageView.setVisibility(8);
        } else {
            linearLayout.setPadding(linearLayout.getPaddingLeft(), WishApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.eight_padding), linearLayout.getPaddingRight(), linearLayout.getPaddingBottom());
            autoReleasableImageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MultiButtonDialogFragment.this.cancel();
                }
            });
        }
        if (fromInteger != null) {
            if (autoReleasableImageView2.getVisibility() == 0) {
                adjustImageSize(fromInteger, autoReleasableImageView2);
            } else if (networkImageView.getVisibility() == 0) {
                adjustImageSize(fromInteger, networkImageView);
            }
        }
        addChoices((LinearLayout) inflate.findViewById(R.id.multi_button_dialog_fragment_choices_container), parcelableArrayList);
        setPaddings(linearLayout, autoReleasableImageView, networkImageView, themedTextView);
        return inflate;
    }

    private void setTopPadding(View view, int i) {
        view.setPadding(view.getPaddingLeft(), WishApplication.getInstance().getResources().getDimensionPixelOffset(i), view.getPaddingRight(), view.getPaddingBottom());
    }

    private void setPaddings(View view, View view2, View view3, View view4) {
        if (view3.getVisibility() == 0) {
            setTopPadding(view4, R.dimen.sixteen_padding);
        } else if (view2.getVisibility() == 0) {
            setTopPadding(view, R.dimen.eight_padding);
        }
    }

    public boolean isCancelable() {
        return this.mCancelable;
    }

    private void adjustImageSize(ImageSize imageSize, AutoReleasableImageView autoReleasableImageView) {
        LayoutParams layoutParams = (LayoutParams) autoReleasableImageView.getLayoutParams();
        switch (imageSize) {
            case EXTRA_SMALL:
                layoutParams.height = getResources().getDimensionPixelSize(R.dimen.dialog_extra_small_view_height);
                break;
            case MEDIUM:
                layoutParams.height = getResources().getDimensionPixelSize(R.dimen.dialog_medium_view_height);
                break;
            case LARGE:
                layoutParams.height = getResources().getDimensionPixelSize(R.dimen.dialog_large_view_height);
                break;
        }
        autoReleasableImageView.setLayoutParams(layoutParams);
    }

    private void adjustImageSize(ImageSize imageSize, NetworkImageView networkImageView) {
        LayoutParams layoutParams = (LayoutParams) networkImageView.getLayoutParams();
        switch (imageSize) {
            case EXTRA_SMALL:
                layoutParams.height = getResources().getDimensionPixelSize(R.dimen.dialog_extra_small_view_height);
                layoutParams.width = layoutParams.height;
                break;
            case MEDIUM:
                layoutParams.height = getResources().getDimensionPixelSize(R.dimen.dialog_medium_view_height);
                layoutParams.width = layoutParams.height;
                break;
            case LARGE:
                layoutParams.height = getResources().getDimensionPixelSize(R.dimen.dialog_large_view_height);
                layoutParams.width = layoutParams.height;
                break;
        }
        networkImageView.setLayoutParams(layoutParams);
    }

    public void addChoices(LinearLayout linearLayout, ArrayList<MultiButtonDialogChoice> arrayList) {
        linearLayout.removeAllViews();
        if (arrayList == null || arrayList.size() == 0) {
            linearLayout.setVisibility(8);
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            linearLayout.addView(getViewForChoice((MultiButtonDialogChoice) arrayList.get(i), i));
        }
    }

    private View getViewForChoice(final MultiButtonDialogChoice multiButtonDialogChoice, int i) {
        View view;
        if (multiButtonDialogChoice.getChoiceType() == ChoiceType.TEXT_ONLY) {
            view = getViewForTextChoice(multiButtonDialogChoice);
        } else {
            view = getViewForDefaultChoice(multiButtonDialogChoice);
        }
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        if (i > 0) {
            layoutParams.setMargins(0, getResources().getDimensionPixelOffset(R.dimen.sixteen_padding), 0, 0);
        }
        layoutParams.gravity = 17;
        view.setLayoutParams(layoutParams);
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MultiButtonDialogFragment.this.makeSelection(multiButtonDialogChoice.getChoiceId());
            }
        });
        return view;
    }

    private ThemedTextView getViewForTextChoice(MultiButtonDialogChoice multiButtonDialogChoice) {
        ThemedTextView themedTextView = new ThemedTextView(getContext());
        int textColorId = multiButtonDialogChoice.getTextColorId();
        if (textColorId == 0) {
            textColorId = R.color.text_hint;
        }
        themedTextView.setTextColor(WishApplication.getInstance().getResources().getColor(textColorId));
        themedTextView.setText(multiButtonDialogChoice.getText());
        themedTextView.setGravity(17);
        return themedTextView;
    }

    private ThemedButton getViewForDefaultChoice(MultiButtonDialogChoice multiButtonDialogChoice) {
        ThemedButton themedButton = new ThemedButton(getContext());
        themedButton.setAllCaps(false);
        themedButton.setTypeface(FontUtil.getTypefaceForStyle(1), 1);
        themedButton.setText(multiButtonDialogChoice.getText());
        int textColorId = multiButtonDialogChoice.getTextColorId();
        if (textColorId == 0) {
            textColorId = R.color.text_hint;
        }
        themedButton.setTextColor(WishApplication.getInstance().getResources().getColor(textColorId));
        if (multiButtonDialogChoice.getBackgroundType() == BackgroundType.COLOR) {
            themedButton.setBackgroundColor(WishApplication.getInstance().getResources().getColor(multiButtonDialogChoice.getBackgroundColorId()));
        } else if (multiButtonDialogChoice.getBackgroundType() == BackgroundType.DRAWABLE) {
            themedButton.setBackgroundResource(multiButtonDialogChoice.getBackgroundDrawableId());
        } else {
            themedButton.setBackgroundResource(0);
        }
        themedButton.setTextSize(0, (float) WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.text_size_button_larger));
        return themedButton;
    }
}
