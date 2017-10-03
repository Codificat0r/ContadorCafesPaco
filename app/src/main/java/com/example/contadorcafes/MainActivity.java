package com.example.contadorcafes;

import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeUnit;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnMas;
    private Button btnMenos;
    private Button btnEmpezar;
    private TextView txtCafes;
    private TextView txtNumero;
    private TextView txtTiempo;
    private CountDownTimer cuenta;
    private int tiempo;
    private double minutos;
    private double segundos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMas = (Button)findViewById(R.id.btnMas);
        btnMenos = (Button)findViewById(R.id.btnMenos);
        btnEmpezar = (Button)findViewById(R.id.btnEmpezar);
        txtCafes = (TextView)findViewById(R.id.txtCafes);
        txtNumero = (TextView)findViewById(R.id.txtNumero);
        txtTiempo = (TextView)findViewById(R.id.txtTiempo);

    }

    @Override
    public void onClick(View v) {
        if (v == btnMas) {

        }
        if (v == btnMenos) {

        }
        if (v == btnEmpezar) {
            cuenta = new CountDownTimer(tiempo * 60 * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    minutos = (millisUntilFinished / 1000) / 60;
                    segundos = (millisUntilFinished / 1000) % 60;
                    txtTiempo.setText(Integer.toString(Math.round((float)minutos)) + ":" + Integer.toString(Math.round((float)segundos)));
                }

                @Override
                public void onFinish() {

                }
            };

            cuenta.start();
            btnMenos.setEnabled(false);
            btnMas.setEnabled(false);
        }
    }
}
