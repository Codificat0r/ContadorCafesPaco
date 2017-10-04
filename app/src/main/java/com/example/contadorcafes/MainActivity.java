package com.example.contadorcafes;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMas;
    private Button btnMenos;
    private Button btnEmpezar;
    private Button btnReestablecer;
    private TextView txtCafes;
    private TextView txtNumero;
    private TextView txtTiempo;
    private CountDownTimer cuenta;
    private int tiempo;
    private double minutos;
    private double segundos;
    private int cafes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMas = (Button) findViewById(R.id.btnMas);
        btnMenos = (Button) findViewById(R.id.btnMenos);
        btnEmpezar = (Button) findViewById(R.id.btnEmpezar);
        btnReestablecer = (Button) findViewById(R.id.btnReestablecer);
        txtCafes = (TextView) findViewById(R.id.txtCafes);
        txtNumero = (TextView) findViewById(R.id.txtNumero);
        txtTiempo = (TextView) findViewById(R.id.txtTiempo);
        cafes = 0;
        tiempo = 5;

        btnEmpezar.setOnClickListener(this);
        btnMas.setOnClickListener(this);
        btnMenos.setOnClickListener(this);
        btnReestablecer.setOnClickListener(this);

        btnReestablecer.setEnabled(false);

        txtNumero.setText(Integer.toString(cafes));
        txtTiempo.setText(Integer.toString(tiempo) + ":" + "00");
    }

    @Override
    public void onClick(View v) {
        if (v == btnMas) {
            tiempo += 1;
            txtTiempo.setText(Integer.toString(tiempo) + ":" + "00");
        }
        if (v == btnMenos) {
            if (tiempo > 1)
                tiempo -= 1;
            txtTiempo.setText(Integer.toString(tiempo) + ":" + "00");
        }
        if (v == btnReestablecer) {
            btnMenos.setEnabled(true);
            btnMas.setEnabled(true);
            btnEmpezar.setEnabled(true);
            btnReestablecer.setEnabled(false);
            cafes = 0;
            txtNumero.setText(Integer.toString(cafes));
        }
        if (v == btnEmpezar) {
            cuenta = new CountDownTimer(tiempo * 60 * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    minutos = (millisUntilFinished / 1000) / 60;
                    segundos = (millisUntilFinished / 1000) % 60;
                    txtTiempo.setText(Integer.toString(Math.round((float) minutos)) + ":" + String.format("%02d", Integer.toString(Math.round((float) segundos))));
                }

                @Override
                public void onFinish() {
                    MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.pop);
                    mp.start();
                    btnMenos.setEnabled(true);
                    btnMas.setEnabled(true);
                    btnEmpezar.setEnabled(true);
                    txtTiempo.setText(Integer.toString(tiempo) + ":" + "00");
                    if (cafes < 9) {
                        cafes += 1;
                        txtNumero.setText(Integer.toString(cafes));


                    } else {
                        cafes += 1;
                        txtNumero.setText(Integer.toString(cafes));
                        if (cafes == 10) {
                            AlertDialog.Builder popup = new AlertDialog.Builder(MainActivity.this);
                            popup.setTitle("Fin");
                            popup.setMessage("Fin!");
                            popup.setPositiveButton("Ok", null);
                            popup.show();
                        }
                        mp.start();
                        btnMenos.setEnabled(false);
                        btnMas.setEnabled(false);
                        btnEmpezar.setEnabled(false);
                        btnReestablecer.setEnabled(true);
                    }
                }
            };

            btnMenos.setEnabled(false);
            btnMas.setEnabled(false);
            btnEmpezar.setEnabled(false);
            cuenta.start();

        }
    }
}
