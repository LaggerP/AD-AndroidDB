package com.example.accesodb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.accesodb.data.Club;
import com.example.accesodb.data.ClubesHelper;

import java.util.List;

public class ClubesActivity extends AppCompatActivity {

    private ClubesHelper helper;
    private Button btnLimpiar, btnAgregar, btnActualizar, btnEliminar;
    private Button btnFirst, btnPrior, btnNext, btnLast;
    private EditText txtIdClub, txtNombre, txtNroZona;
    private List<Club> clubes;
    private Club actualClub;
    private int pos = 0;


    /**
     * Initialize the Clubs screen and load all the clubs from the db
     *
     * @author Unknow
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubes);

        txtIdClub = (EditText) findViewById(R.id.txtIdClub);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtNroZona = (EditText) findViewById(R.id.txtNroZona);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnFirst = (Button) findViewById(R.id.btnFirst);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrior = (Button) findViewById(R.id.btnPrior);
        btnLast = (Button) findViewById(R.id.btnLast);

        //Recupero todos los clubes y los cargo en un arrayList
        helper = new ClubesHelper(this);
        clubes = helper.getClubes();
        if (!clubes.isEmpty()) {
            Club c = clubes.get(0);
            actualClub = c;
            txtIdClub.setText(String.valueOf(c.getIdClub()));
            txtNombre.setText(c.getNombre());
            txtNroZona.setText(String.valueOf(c.getNroZona()));
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
                txtNombre.setText("");
                txtNroZona.setText("");
                pos = 0;
            }
        });

        /**
         * Allows to create a club and insert them in DB
         * @param txtIdClub input idClub
         * @param txtNombre input Nombre
         * @param txtNroZona input NroZona
         * @author Unknow
         */
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Club club = new Club(Integer.parseInt(txtIdClub.getText().toString()),
                        txtNombre.getText().toString(),
                        Integer.parseInt(txtNroZona.getText().toString()));
                helper.saveClub(club);
                clubes.add(club);
                actualClub = club;
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
                Club club = new Club(Integer.parseInt(txtIdClub.getText().toString()),
                        txtNombre.getText().toString(),
                        Integer.parseInt(txtNroZona.getText().toString()));
                //FIXME is a good option first search the Club in db and then (if exist) update.
                helper.updateClub(actualClub.getIdClub(), club);
            }

        });

        /**
         * Allows to delete a row from DB using an idClub
         * @param idClub
         * @author Pablo Lagger
         */
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idClub = Integer.parseInt(txtIdClub.getText().toString());
                //FIXME is a good option first search the Club in db and then (if exist) delete.
                helper.deleteClub(idClub);
            }

        });

        /**
         * Gets first value from Club list
         * @author Unknow
         */
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Club c = clubes.get(0);
                actualClub = c;
                txtIdClub.setText(String.valueOf(c.getIdClub()));
                txtNombre.setText(c.getNombre());
                txtNroZona.setText(String.valueOf(c.getNroZona()));
                pos = 0;
            }
        });

        /**
         * Gets prior value from currently displayed value
         * @author Unknow
         */
        btnPrior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos > 0) {
                    pos--;
                    Club c = clubes.get(pos);
                    actualClub = c;
                    txtIdClub.setText(String.valueOf(c.getIdClub()));
                    txtNombre.setText(c.getNombre());
                    txtNroZona.setText(String.valueOf(c.getNroZona()));
                }
            }
        });

        /**
         * Gets next value from currently displayed value
         * @author Unknow
         */
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clubes.size() > pos) {
                    pos++;
                    Club c = clubes.get(pos);
                    actualClub = c;
                    txtIdClub.setText(String.valueOf(c.getIdClub()));
                    txtNombre.setText(c.getNombre());
                    txtNroZona.setText(String.valueOf(c.getNroZona()));
                }
            }
        });

        /**
         * Gets last value from Club list
         * @author Unknow
         */
        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clubes.isEmpty()) {
                    pos = clubes.size() - 1;
                    Club c = clubes.get(pos);
                    actualClub = c;
                    txtIdClub.setText(String.valueOf(c.getIdClub()));
                    txtNombre.setText(c.getNombre());
                    txtNroZona.setText(String.valueOf(c.getNroZona()));
                }
            }
        });
    }

}
