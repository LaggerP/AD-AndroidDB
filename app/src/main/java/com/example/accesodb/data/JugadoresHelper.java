package com.example.accesodb.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class JugadoresHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Jugadores.db";

    public JugadoresHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crea las otras tablas de jugadores
        final String CREATE_JUGADORES = "CREATE TABLE " + DataContract.JugadoresEntry.TABLE_NAME + "("
                + DataContract.JugadoresEntry._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + DataContract.JugadoresEntry.ID + " TEXT NOT NULL,  "
                + DataContract.JugadoresEntry.NOMBRE + " TEXT NOT NULL, "
                + DataContract.JugadoresEntry.ID_CLUB + " TEXT NOT NULL )";
        db.execSQL(CREATE_JUGADORES);
    }

    //lo implementamos aunque no hagamos nada en caso de actualizacion de la BD.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long saveJugador(Jugador jugador) {
        //obtenemos una referencia a la base de datos para operaciones de grabación.
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(DataContract.JugadoresEntry.TABLE_NAME, null, jugador.toContentValues());
    }

    public long deleteJugador(int jugadorId) {
        //obtenemos una referencia a la base de datos para operaciones de eliminación.
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(DataContract.JugadoresEntry.TABLE_NAME, DataContract.JugadoresEntry.ID + "=?", new String[]{String.valueOf(jugadorId)});
    }

    public long updateJugador(int jugadorId, Jugador jugador) {
        //obtenemos una referencia a la base de datos para operaciones de actualización.
        SQLiteDatabase db = getWritableDatabase();
        return db.update(DataContract.JugadoresEntry.TABLE_NAME, jugador.toContentValues(), DataContract.JugadoresEntry.ID + "=?", new String[]{String.valueOf(jugadorId)});
    }

    public List<Jugador> getJugadores() {
        List<Jugador> jugadores = new ArrayList<Jugador>();
        //obtenemos una referencia a la base de datos para operaciones de lectura.
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DataContract.JugadoresEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Jugador aux = new Jugador();
                aux.setIdJugador(cursor.getInt(cursor.getColumnIndex("idJugador")));
                aux.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                aux.setIdClub(cursor.getInt(cursor.getColumnIndex("idClub")));
                jugadores.add(aux);
            } while (cursor.moveToNext());
        }
        return jugadores;
    }

    public Jugador getJugadorById(int idJugador) {
        Jugador aux = new Jugador();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DataContract.JugadoresEntry.TABLE_NAME,
                null,
                DataContract.JugadoresEntry.ID + " = ? ",
                new String[]{String.valueOf(idJugador)},
                null,
                null,
                null,
                null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            aux.setIdJugador(cursor.getInt(cursor.getColumnIndex("idJugador")));
            aux.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
            aux.setIdClub(cursor.getInt(cursor.getColumnIndex("idClub")));
        }

        return aux;
    }
}

