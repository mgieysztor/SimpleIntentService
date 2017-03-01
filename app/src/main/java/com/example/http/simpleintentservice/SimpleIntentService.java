package com.example.http.simpleintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


public class SimpleIntentService extends IntentService {

    public SimpleIntentService() {
        super("SimpleIntentService");
    }

    double result;

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {
            String action = intent.getAction();
            double valueA = intent.getDoubleExtra("FACTOR_A", 0);


            if ("ADD".equals(action)) {
                Log.i("AKCJA", " dodawanie");
                result = add(intent.getDoubleExtra("FACTOR_A", 0), intent.getDoubleExtra("FACTOR_B", 0));
                //TODO pobierz parametry, oblicz i odeślij wynik
            } else if ("SUBTRACT".equals(action)) {
                result = subtract(intent.getDoubleExtra("FACTOR_A", 0), intent.getDoubleExtra("FACTOR_B", 0));
                //TODO pobierz parametry, oblicz i odeślij wynik
            } else if ("MULTIPLY".equals(action)) {
                result = multiply(intent.getDoubleExtra("FACTOR_A", 0), intent.getDoubleExtra("FACTOR_B", 0));

            } else if ("DIVIDE".equals(action)){
                result = divide(intent.getDoubleExtra("FACTOR_A", 0), intent.getDoubleExtra("FACTOR_B", 0));
            }
        }
        broadcastResult(result);

        Log.i("RESULT: ", result + "");
    }

    private void broadcastResult(double result) {
        Intent intent = new Intent();
        intent.setAction("action.CALCULATION_RESULT");
        intent.putExtra("extra.VALUE", result);

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.sendBroadcast(intent);
    }

    private double add(double a, double b) {
        return a + b;
    }

    private double subtract(double a, double b) {
        return a - b;
    }

    private double multiply(double a, double b) {
        return a * b;
    }

    private double divide(double a, double b) {
        return a / b;
    }
}
