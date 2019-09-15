package com.example.batmanalarma;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    Button boton;
    TextView tiempoTextView;
    Boolean contadorCorinedo=false;
    CountDownTimer CountDownTimer;

    public void EmpezarDeNuevo(){
        tiempoTextView.setText("0:30");
        seekBar.setProgress(30);
        CountDownTimer.cancel();
        boton.setText("Iniciar!");
        seekBar.setEnabled(true);
        contadorCorinedo=false;
    }

    public void actualizarTiempo(int segundosRestantes){
        int minutes = (int)segundosRestantes/60;
        int seconds = segundosRestantes - minutes *60;

        String secondString = Integer.toString(seconds);

        if(seconds <=9){
            secondString="0"+secondString;
        }

        tiempoTextView.setText(Integer.toString(minutes)+ ":" +secondString);
    }

    public void iniciarTiempo(View view){
        if(contadorCorinedo==false) {

            contadorCorinedo = true;
            seekBar.setEnabled(false);
            boton.setText("Stop!");
            CountDownTimer=new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long tiempoRestante) {
                    actualizarTiempo((int) tiempoRestante / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.telefono);//getAplicationContext en vez de this porqye el this no se refiere a la aplicacion sino al contador
                    mPlayer.start();
                    EmpezarDeNuevo();
                }
            }.start();
        }else{
            EmpezarDeNuevo();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        tiempoTextView = (TextView)findViewById(R.id.tiempo);
        boton=(Button)findViewById(R.id.boton);

        seekBar.setMax(600);

        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                actualizarTiempo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
