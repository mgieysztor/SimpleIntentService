package com.example.http.simpleintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button mPlusButton;
    Button mMinusButton;
    Button mMultiplyButton;
    Button mDivideButton;

    EditText mFirstValueEditText;
    EditText mSecondValueEditText;

    TextView mResultTextView;

    double wartoscA;
    double wartoscB;

    LocalBroadcastManager localBroadcastManager;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO odbierz odpowiedź i wyświetl wynik na ekranie
             double result = intent.getDoubleExtra("extra.VALUE", 0);
            mResultTextView.setText(String.valueOf(result));

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlusButton = (Button) findViewById(R.id.button_plus);
        mMinusButton = (Button) findViewById(R.id.button_minus);
        mMultiplyButton = (Button) findViewById(R.id.button_multiply);
        mDivideButton = (Button) findViewById(R.id.button_divide);

        mFirstValueEditText = (EditText) findViewById(R.id.first_value);
        mSecondValueEditText = (EditText) findViewById(R.id.second_value);

        mResultTextView = (TextView) findViewById(R.id.result);
//      to spróbuj!
//        wartoscA = Double.parseDouble(mFirstValueEditText.toString());
//        wartoscB = Double.parseDouble(mSecondValueEditText.toString());


        IntentFilter filter = new IntentFilter();
        filter.addAction("action.CALCULATION_RESULT");

        // filter.addAction("NAZWA_AKCJI"); TODO: wstaw nazwę akcji którą chcesz odebrać

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(broadcastReceiver, filter);


        // TODO: Wysyłanie parametrów po kliknięciu na guzik

        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wartoscA = Double.parseDouble(mFirstValueEditText.getText().toString());
//                wartoscA = Double.valueOf(mFirstValueEditText.getText().toString());
                wartoscB = Double.valueOf(mSecondValueEditText.getText().toString());
                Intent intent = new Intent(MainActivity.this, SimpleIntentService.class);
                intent.setAction("ADD");
                intent.putExtra("FACTOR_A", wartoscA);
                intent.putExtra("FACTOR_B", wartoscB);

                startService(intent);
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleIntentService.class);
                intent.setAction("SUBTRACT");
                intent.putExtra("FACTOR_A", wartoscA);
                intent.putExtra("FACTOR_B", wartoscB);
                startService(intent);
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        mMultiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleIntentService.class);
                intent.setAction("MULTIPLY");
                intent.putExtra("FACTOR_A", wartoscA);
                intent.putExtra("FACTOR_B", wartoscB);
                startService(intent);
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        mDivideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleIntentService.class);
                intent.setAction("DIVIDE");
                intent.putExtra("FACTOR_A", wartoscA);
                intent.putExtra("FACTOR_B", wartoscB);
                startService(intent);
                localBroadcastManager.sendBroadcast(intent);
            }
        });
//        Intent intent = new Intent();
//        intent.setAction("NAZWA_AKCJI");
//        intent.putExtra("NAZWA_PARAMETRU", wartoscA);
//        intent.putExtra("NAZWA_PARAMETRU", wartoscB);
//        localBroadcastManager.sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
