package com.example.accesodb.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ClubesHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Jugadores.db";

    public ClubesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crea la tabla de clubes si no existe
        final String CREATE_CLUBES = "CREATE TABLE " + DataContract.ClubesEntry.TABLE_NAME + "("
            + DataContract.ClubesEntry._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + DataContract.ClubesEntry.ID + " TEXT NOT NULL,  "
            + DataContract.ClubesEntry.NOMBRE + " TEXT NOT NULL, "
            + DataContract.ClubesEntry.NROZONA + " TEXT NOT NULL )";
        db.execSQL(CREATE_CLUBES);
        //Crea las otras tablas de jugadores
        final String CREATE_JUGADORES = "";
        final String CREATE_PARTIDOS = "";
    }
    //lo implementamos aunque no hagamos nada en caso de actualizacion de la BD.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public long saveClub(Club club){
        //obtenemos una referencia a la base de datos para operaciones de grabación.
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(DataContract.ClubesEntry.TABLE_NAME, null, club.toContentValues());
    }

    public long deleteClub(int clubId){
        //obtenemos una referencia a la base de datos para operaciones de eliminación.
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(DataContract.ClubesEntry.TABLE_NAME, DataContract.ClubesEntry.ID + "=?", new String[]{String.valueOf(clubId)});
    }

    public long updateClub (int clubId, Club club){
        SQLiteDatabase db = getWritableDatabase();
        return db.update(DataContract.ClubesEntry.TABLE_NAME, club.toContentValues(), DataContract.ClubesEntry.ID + "=?", new String[]{String.valueOf(clubId)});
    }

    public List<Club> getClubes(){
        List<Club> clubes = new ArrayList<Club>();
        //obtenemos una referencia a la base de datos para operaciones de lectura.
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(DataContract.ClubesEntry.TABLE_NAME,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Club aux = new Club();
                aux.setIdClub(cursor.getInt(cursor.getColumnIndex("idClub")));
                aux.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                aux.setNroZona(cursor.getInt(cursor.getColumnIndex("nroZona")));
                clubes.add(aux);
            }while (cursor.moveToNext());
        }
        return clubes;
    }

    public Club getClubById(int idClub){
        Club aux = new Club();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor =  db.query(DataContract.ClubesEntry.TABLE_NAME,
                        null,
                        DataContract.ClubesEntry.ID + " = ? ",
                        new String[] {String.valueOf(idClub)},
                        null,
                        null,
                        null,
                        null);
        if(cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            Log.i("nombre", cursor.getString((cursor.getColumnIndex("nombre"))));
            aux.setIdClub(cursor.getInt(cursor.getColumnIndex("id_Club")));
            aux.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
            aux.setNroZona(cursor.getInt(cursor.getColumnIndex("nroZona")));
            Log.i("nombre", aux.getNombre());
        }

        return aux;
    }
}

