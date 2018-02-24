package com.example.workstation.foreigncurrency;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workstation.foreigncurrency.model.CurrencyResponse;
import com.example.workstation.foreigncurrency.model.Rate;
import com.example.workstation.foreigncurrency.model.Rates;
import com.example.workstation.foreigncurrency.network.Api;
import com.example.workstation.foreigncurrency.network.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView currencyListView;
    private List<Rate> mList;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currencyListView = findViewById(R.id.currencyListView);
        mList = new ArrayList<Rate>();
        getCurrency();

        currencyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View view1 = layoutInflater.inflate(R.layout.exchange_layout,null);

                final EditText fromCurrencyEditText = view1.findViewById(R.id.fromCurrencyEditTxt);
                final EditText toCurrencyEditText = view1.findViewById(R.id.toCurrencyEdtText);
                toCurrencyEditText.setClickable(false);
                final double rate = mList.get(i).getRate();
                fromCurrencyEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i0, int i1, int i2) {
                        double temp = 0;
                        if (!TextUtils.isEmpty(charSequence)){
                            temp = Double.valueOf(charSequence.toString()) * rate;
                            new DecimalFormat("#0.00").format(temp);
                        }
                        toCurrencyEditText.setText(String.valueOf(temp));
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                TextView baseTextView = view1.findViewById(R.id.currencyBaseTxtView);
                TextView destTextView = view1.findViewById(R.id.destCurrencyTxtView);


                baseTextView.setText("TRY");
                destTextView.setText(mList.get(i).getCurrencySymbol());
                fromCurrencyEditText.setText("1");
                toCurrencyEditText.setText(String.valueOf(mList.get(i).getRate()));

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(view1);
                builder.setCancelable(true);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.change_currency_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void getCurrency(){
        Service client = Api.getApi().create(Service.class);
        Call<CurrencyResponse> call = client.getLatestCurrency();

        call.enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                if (response.isSuccessful()){
                    CurrencyResponse currencyResponse = response.body();
                    Rates rates = currencyResponse.rates;
                    Rate usd = new Rate(rates.uSD,"Amerikan Dolari","USD",R.drawable.us);
                    mList.add(usd);
                    Rate eur = new Rate(rates.eUR,"Euro","EUR",R.drawable.european);
                    mList.add(eur);
                    Rate aud = new Rate(rates.aUD,"Avustralya Dolari","AUD",R.drawable.aud);
                    mList.add(aud);
                    Rate cad = new Rate(rates.cAD,"Kanada Dolari","CAD",R.drawable.cad);
                    mList.add(cad);
                    Rate chf = new Rate(rates.cHF,"Isvicre Frangi","CHF",R.drawable.sweden);
                    mList.add(chf);
                    Rate gbp = new Rate(rates.gBP,"Ingiliz Sterlini","GBP",R.drawable.uk);
                    mList.add(gbp);
                    adapter = new Adapter(getApplicationContext(),mList);
                    currencyListView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                Log.i("wrong",t.getMessage().toString());
            }
        });
    }
}
