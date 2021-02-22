package com.example.currencyconv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText=(EditText) findViewById(R.id.amount);
        TextView textView=(TextView) findViewById(R.id.res);
        Button button=(Button) findViewById(R.id.convertbtn);
        final Spinner spinner1=(Spinner) findViewById(R.id.firstlist);
        Spinner spinner2=(Spinner) findViewById(R.id.secondlist);
        String[] list={"USD","AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN","BAM","BBD","BDT","BGN","BHD","BIF","BMD","BND","BOB","BRL","BSD","BTN","BWP","BYN","BZD","CAD","CDF","CHF","CLP","CNY","COP","CRC","CUC","CUP","CVE","CZK","DJF","DKK","DOP","DZD","EGP","ERN","ETB","EUR","FJD","FKP","FOK","GBP","GEL","GGP","GHS","GIP","GMD","GNF","GTQ","GYD","HKD","HNL","HRK","HTG","HUF","IDR","ILS","IMP","INR","IQD","IRR","ISK","JMD","JOD","JPY","KES","KGS","KHR","KID","KMF","KRW","KWD","KYD","KZT","LAK","LBP","LKR","LRD","LSL","LYD","MAD","MDL","MGA","MKD","MMK","MNT","MOP","MRU","MUR","MVR","MWK","MXN","MYR","MZN","NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR","RON","RSD","RUB","RWF","SAR","SBD","SCR","SDG","SEK","SGD","SHP","SLL","SOS","SRD","SSP","STN","SYP","SZL","THB","TJS","TMT","TND","TOP","TRY","TTD","TVD","TWD","TZS","UAH","UGX","UYU","UZS","VES","VND","VUV","WST","XAF","XCD","XDR","XOF","XPF","YER","ZAR","ZMW"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, list);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaceRetrofit interfaceRetrofit= RetrofitBuilder.getRetrofitInstance().create(interfaceRetrofit.class);
                Call<JsonObject> call = interfaceRetrofit.getExchangeCurrency(spinner1.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                       Log.d("response", String.valueOf(response.body()));
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("conversion_rates");
                        double currency = Double.parseDouble(editText.getText().toString());
                        double multiplier = Double.parseDouble(rates.get(spinner2.getSelectedItem().toString()).toString());
                        double result = currency * multiplier;
                        textView.setText(String.valueOf(result));
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });

    }
}