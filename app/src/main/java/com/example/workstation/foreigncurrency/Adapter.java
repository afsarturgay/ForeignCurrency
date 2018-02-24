package com.example.workstation.foreigncurrency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.workstation.foreigncurrency.model.Rate;
import com.example.workstation.foreigncurrency.model.Rates;

import java.util.List;

/**
 * Created by workstation on 07/02/2018.
 */

public class Adapter extends BaseAdapter {

    private List<Rate> mList;
    private Context mContext;

    public Adapter(Context mContext, List<Rate> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.currency_custom_row,viewGroup,false);
        ImageView countryFlagImageView = view.findViewById(R.id.countryFlagImageView);
        TextView currencyTextView = view.findViewById(R.id.currencyTextView);
        TextView rateTextView = view.findViewById(R.id.rateTextView);
        TextView currencySymbolTV = view.findViewById(R.id.currencySymbolTextView);
        countryFlagImageView.setImageDrawable(mContext.getResources().getDrawable(mList.get(i).getImageID()));
        currencyTextView.setText(mList.get(i).getCurrency());
        rateTextView.setText(String.valueOf(mList.get(i).getRate()));
        currencySymbolTV.setText(mList.get(i).getCurrencySymbol());



        return view;
    }
}
