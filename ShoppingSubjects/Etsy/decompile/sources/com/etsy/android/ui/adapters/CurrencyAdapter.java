package com.etsy.android.ui.adapters;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.EtsyCurrency;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.stylekit.e;
import com.etsy.android.uikit.adapter.BaseModelArrayAdapter;

public class CurrencyAdapter extends BaseModelArrayAdapter<EtsyCurrency> {

    private static class a {
        TextView a;
        TextView b;
        TextView c;

        public static a a(View view) {
            a aVar = new a();
            aVar.b = (TextView) view.findViewById(R.id.currency_name);
            aVar.a = (TextView) view.findViewById(R.id.currency_symbol);
            aVar.c = (TextView) view.findViewById(R.id.currency_code);
            return aVar;
        }
    }

    public CurrencyAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity, R.layout.list_item_currency, null);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = getLayoutInflater().inflate(getLayoutId(), null);
            aVar = a.a(view);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        EtsyCurrency etsyCurrency = (EtsyCurrency) getItem(i);
        if (etsyCurrency.getCode().equals(CurrencyUtil.c())) {
            e.a(aVar.b, (int) R.string.sk_typeface_bold);
        } else {
            e.a(aVar.b, (int) R.string.sk_typeface_normal);
        }
        aVar.b.setText(etsyCurrency.getName());
        aVar.a.setText(etsyCurrency.getUnit().getCurrencySymbol());
        aVar.c.setText(CurrencyUtil.a(etsyCurrency.getCode(), etsyCurrency.getUnit().getCurrencyCode().trim()));
        return view;
    }
}
