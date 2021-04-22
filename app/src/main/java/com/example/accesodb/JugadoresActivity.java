package com.example.accesodb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.accesodb.data.Jugador;
import com.example.accesodb.data.JugadoresHelper;

import java.util.List;

public class JugadoresActivity extends AppCompatActivity {

    private JugadoresHelper helper;
    private Button btnLimpiar, btnAgregar, btnActualizar, btnEliminar;
    private Button btnFirst, btnPrior, btnNext, btnLast;
    private EditText txtIdJugador, txtJugador, txtIdClub;
    private List<Jugador> jugadores;
    private Jugador jugadorActual;
    private int pos = 0;


    /**
     * Initialize the Clubs screen and load all the clubs from the db
     *
     * @author Unknow
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugadores);

        txtIdJugador = (EditText) findViewById(R.id.txtIdJugador);
        txtJugador = (EditText) findViewById(R.id.txtJugador);
        txtIdClub = (EditText) findViewById(R.id.txtIdClub);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnFirst = (Button) findViewById(R.id.btnFirst);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrior = (Button) findViewById(R.id.btnPrior);
        btnLast = (Button) findViewById(R.id.btnLast);

        //Recupero todos los clubes y los cargo en un arrayList
        helper = new JugadoresHelper(this);
        jugadores = helper.getJugadores();
        if (!jugadores.isEmpty()) {
            Jugador j = jugadores.get(0);
            jugadorActual = j;
            txtIdJugador.setText(String.valueOf(j.getIdJugador()));
            txtJugador.setText(j.getNombre());
            txtIdClub.setText(String.valueOf(j.getIdClub()));
            pos = 0;
        }


        /**
         * Clean all inputs
         * @author Unknow
         */
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtIdClub.setText("");
                txtJugador.setText("");
                txtIdClub.setText("");
                pos = 0;
            }
        });

        /**
         * Allows to create a club and insert them in DB
         * @param txtIdJugador input idJugador
         * @param txtJugador input Nombre
         * @param txtIdClub input txtIdClub
         * @author Unknow
         */
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jugador jugador = new Jugador(Integer.parseInt(txtIdJugador.getText().toString()),
                        txtJugador.getText().toString(),
                        Integer.parseInt(txtIdClub.getText().toString()));
                helper.saveJugador(jugador);
                jugadores.add(jugador);
                jugadorActual = jugador;
            }
        });

        /**
         * Allows to update a row from DB using an idClub
         * @param Club object
         * @param idClub current idClub
         * @author Pablo Lagger
         */
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jugador jugador = new Jugador(Integer.parseInt(txtIdJugador.getText().toString()),
                        txtJugador.getText().toString(),
                        Integer.parseInt(txtIdClub.getText().toString()));
                //FIXME is a good option first search the Club in db and then (if exist) update.
                helper.updateJugador(jugadorActual.getIdClub(), jugador);
            }

        });

        /**
         * Allows to delete a row from DB using an idJugador
         * @param idJugador
         * @author Pablo Lagger
         */
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idJugador = Integer.parseInt(txtJugador.getText().toString());
                //FIXME is a good option first search the Club in db and then (if exist) delete.
                helper.deleteJugador(idJugador);
            }

        });

        /**
         * Gets first value from Jugadores list
         * @author Pablo Lagger
         */
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jugador j = jugadores.get(0);
                jugadorActual = j;
                txtIdJugador.setText(String.valueOf(j.getIdJugador()));
                txtJugador.setText(j.getNombre());
                txtIdClub.setText(String.valueOf(j.getIdJugador()));
                pos = 0;
            }
        });

        /**
         * Gets prior value from currently displayed value
         * @author Pablo Lagger
         */
        btnPrior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos > 0) {
                    pos--;
                    Jugador j = jugadores.get(pos);
                    jugadorActual = j;
                    txtIdJugador.setText(String.valueOf(j.getIdJugador()));
                    txtJugador.setText(j.getNombre());
                    txtIdClub.setText(String.valueOf(j.getIdJugador()));
                }
            }
        });

        /**
         * Gets next value from currently displayed value
         * @author Pablo Lagger
         */
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jugadores.size() > pos) {
                    pos++;
                    Jugador j = jugadores.get(pos);
                    jugadorActual = j;
                    txtIdJugador.setText(String.valueOf(j.getIdJugador()));
                    txtJugador.setText(j.getNombre());
                    txtIdClub.setText(String.valueOf(j.getIdJugador()));
                }
            }
        });

        /**
         * Gets last value from Jugadores list
         * @author Pablo Lagger
         */
        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!jugadores.isEmpty()) {
                    pos = jugadores.size() - 1;
                    Jugador j = jugadores.get(pos);
                    jugadorActual = j;
                    txtIdJugador.setText(String.valueOf(j.getIdJugador()));
                    txtJugador.setText(j.getNombre());
                    txtIdClub.setText(String.valueOf(j.getIdJugador()));
                }
            }
        });
    }
}