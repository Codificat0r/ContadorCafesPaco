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
    private TimeUnit tiempo;
    private int milisegundos = 300000;

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

        cuenta = new CountDownTimer(milisegundos, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                milisegundos -= 1000;
                txtTiempo.setText(String.format(SimpleDateFormat.MINUTE_SECOND, milisegundos));
            }

            @Override
            public void onFinish() {
                txtTiempo.setText("Fin de la cuenta");
                txtCafes.setText(Integer.toString(Integer.parseInt(txtCafes.getText().toString()) + 1));
                btnMenos.setEnabled(true);
                btnMas.setEnabled(true);
                milisegundos = 300000;
            }
        };

        txtTiempo.setText(String.format(SimpleDateFormat.MINUTE_SECOND, milisegundos));
    }

    @Override
    public void onClick(View v) {
        if (v == btnMas) {
            milisegundos += 60000;
            cuenta = new CountDownTimer(milisegundos, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    milisegundos -= 1000;
                    txtTiempo.setText(String.format(SimpleDateFormat.MINUTE_SECOND, milisegundos));
                }

                @Override
                public void onFinish() {
                    txtTiempo.setText("Fin de la cuenta");
                    txtCafes.setText(Integer.toString(Integer.parseInt(txtCafes.getText().toString()) + 1));
                    btnMenos.setEnabled(true);
                    btnMas.setEnabled(true);
                    milisegundos = 300000;
                }
            };
            txtTiempo.setText(String.format(SimpleDateFormat.MINUTE_SECOND, cuenta.toString()));
        }
        if (v == btnMenos) {
            if (milisegundos - 60000 >= 0) {
                milisegundos -= 60000;
                cuenta = new CountDownTimer(milisegundos, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        milisegundos -= 1000;
                        txtTiempo.setText(String.format(SimpleDateFormat.MINUTE_SECOND, milisegundos));
                    }

                    @Override
                    public void onFinish() {
                        txtTiempo.setText("Fin de la cuenta");
                        txtCafes.setText(Integer.toString(Integer.parseInt(txtCafes.getText().toString()) + 1));
                        btnMenos.setEnabled(true);
                        btnMas.setEnabled(true);
                        milisegundos = 300000;
                    }
                };
                txtTiempo.setText(String.format(SimpleDateFormat.MINUTE_SECOND, cuenta.toString()));
            }
        }
        if (v == btnEmpezar) {
            cuenta.start();
            btnMenos.setEnabled(false);
            btnMas.setEnabled(false);
        }
    }
}
