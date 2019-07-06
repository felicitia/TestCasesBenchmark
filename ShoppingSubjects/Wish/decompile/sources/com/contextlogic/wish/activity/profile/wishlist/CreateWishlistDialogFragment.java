package com.contextlogic.wish.activity.profile.wishlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.activity.profile.ProfileServiceFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.util.KeyboardUtil;
import java.util.ArrayList;

public class CreateWishlistDialogFragment extends BaseDialogFragment {
    private ImageView mCancelButton;
    /* access modifiers changed from: private */
    public TextView mDoneButton;
    private EditText mNameText;
    private TableLayout mSuggestionTableLayout;
    private TextView mSuggestionText;
    private ArrayList<String> mSuggestions;
    private TextView mTitleText;

    /* access modifiers changed from: protected */
    public boolean requiresKeyboard() {
        return true;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.create_wishlist_dialog_fragment, viewGroup, false);
        this.mSuggestions = new ArrayList<>();
        this.mTitleText = (TextView) inflate.findViewById(R.id.create_wishlist_title);
        this.mCancelButton = (ImageView) inflate.findViewById(R.id.create_wishlist_cancel_button);
        this.mCancelButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CreateWishlistDialogFragment.this.cancel();
                KeyboardUtil.hideKeyboard((Fragment) CreateWishlistDialogFragment.this);
            }
        });
        this.mDoneButton = (TextView) inflate.findViewById(R.id.create_wishlist_modal_done_button);
        this.mDoneButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CreateWishlistDialogFragment.this.handleDone();
                KeyboardUtil.hideKeyboard((Fragment) CreateWishlistDialogFragment.this);
            }
        });
        this.mDoneButton.setEnabled(false);
        this.mNameText = (EditText) inflate.findViewById(R.id.create_wishlist_name_text);
        this.mNameText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.toString().trim().length() == 0) {
                    CreateWishlistDialogFragment.this.mDoneButton.setEnabled(false);
                } else {
                    CreateWishlistDialogFragment.this.mDoneButton.setEnabled(true);
                }
            }
        });
        this.mSuggestionText = (TextView) inflate.findViewById(R.id.create_wishlist_suggestion_title);
        this.mSuggestionTableLayout = (TableLayout) inflate.findViewById(R.id.create_wishlist_suggestion_table);
        if (!(bundle == null || bundle.getStringArrayList("SavedStateSuggestions") == null)) {
            updateSuggestions(bundle.getStringArrayList("SavedStateSuggestions"));
        }
        return inflate;
    }

    public void setInitialName(String str) {
        this.mTitleText.setText(getString(R.string.rename_wishlist));
        this.mDoneButton.setText(getString(R.string.done));
        this.mNameText.setText(str);
    }

    public void loadNameSuggestions(final String str) {
        withServiceFragment(new ServiceTask() {
            public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                if (serviceFragment instanceof ProfileServiceFragment) {
                    ((ProfileServiceFragment) serviceFragment).getWishlistNameSuggestion(str);
                } else if (serviceFragment instanceof BaseProductFeedServiceFragment) {
                    ((BaseProductFeedServiceFragment) serviceFragment).getWishlistNameSuggestion(str);
                } else if (serviceFragment instanceof CartServiceFragment) {
                    ((CartServiceFragment) serviceFragment).getWishlistNameSuggestion(str);
                }
            }
        });
    }

    public void updateSuggestions(ArrayList<String> arrayList) {
        this.mSuggestions = arrayList;
        if (this.mSuggestions == null || this.mSuggestions.size() < 1) {
            this.mSuggestionText.setVisibility(8);
            if (this.mSuggestionTableLayout != null) {
                this.mSuggestionTableLayout.setVisibility(8);
                return;
            }
            return;
        }
        initSuggestions();
    }

    /* access modifiers changed from: private */
    public void handleDone() {
        Bundle bundle = new Bundle();
        bundle.putString("ResultName", this.mNameText.getText().toString());
        makeSelection(bundle);
    }

    /* access modifiers changed from: private */
    public void handleSelectSuggestion(CharSequence charSequence) {
        this.mNameText.setText(charSequence);
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("SavedStateSuggestions", this.mSuggestions);
    }

    public void initSuggestions() {
        int i = 0;
        while (i < 6 && i < this.mSuggestionTableLayout.getChildCount() && i < this.mSuggestions.size()) {
            TableRow tableRow = (TableRow) this.mSuggestionTableLayout.getChildAt(i / 2);
            final TextView textView = (TextView) tableRow.getChildAt(i % 2);
            textView.setText((CharSequence) this.mSuggestions.get(i));
            textView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    CreateWishlistDialogFragment.this.handleSelectSuggestion(textView.getText());
                }
            });
            tableRow.setVisibility(0);
            textView.setVisibility(0);
            i++;
        }
        this.mSuggestionText.setVisibility(0);
        this.mSuggestionTableLayout.setVisibility(0);
    }
}
