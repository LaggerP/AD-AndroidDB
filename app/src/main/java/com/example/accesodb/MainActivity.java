package com.example.accesodb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnClubes, btnJugadores, btnPartidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClubes = (Button) findViewById(R.id.admClubes);
        btnJugadores = (Button) findViewById(R.id.admJugadores);
        btnPartidos = (Button) findViewById(R.id.admPartidos);

        btnClubes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClubesActivity.class);
                startActivity(intent);
            }
        });

        btnJugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JugadoresActivity.class);
                startActivity(intent);
            }
        });

        btnPartidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Agregue el código para llegar una actividad para ingresar partidos
                 *
                 * */
            }
        });
    }
}
