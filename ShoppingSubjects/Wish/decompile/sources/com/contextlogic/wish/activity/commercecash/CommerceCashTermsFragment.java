package com.contextlogic.wish.activity.commercecash;

import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.api.model.WishCommerceCashTerms;
import com.contextlogic.wish.api.model.WishCommerceCashTerms.Term;
import java.util.ArrayList;

public class CommerceCashTermsFragment extends UiFragment<CommerceCashTermsActivity> {
    private LinearLayout mTermsContainer;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.commerce_cash_terms_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mTermsContainer = (LinearLayout) findViewById(R.id.commerce_cash_terms_fragment_container);
        loadTerms();
    }

    private void loadTerms() {
        withServiceFragment(new ServiceTask<BaseActivity, CommerceCashTermsServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CommerceCashTermsServiceFragment commerceCashTermsServiceFragment) {
                commerceCashTermsServiceFragment.loadCommerceCashTerms();
            }
        });
    }

    public void handleLoadingInfoSuccess(WishCommerceCashTerms wishCommerceCashTerms) {
        if (wishCommerceCashTerms != null) {
            ArrayList terms = wishCommerceCashTerms.getTerms();
            for (int i = 0; i < terms.size(); i++) {
                CommerceCashTermView commerceCashTermView = new CommerceCashTermView(getContext());
                commerceCashTermView.setup((Term) terms.get(i), i);
                this.mTermsContainer.addView(commerceCashTermView);
            }
        }
    }
}
