package com.example.accesodb.data;

import android.content.ContentValues;

public class Jugador {

    private int idJugador;
    private String nombre;
    private int idClub;

    public Jugador(int idJugador, String nombre, int idClub) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.idClub = idClub;
    }

    public Jugador() {

    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int nroZona) {
        this.idClub = idClub;
    }

    /**
     * Convierte el estado en pares clave valor para hacer el insert
     */
    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DataContract.JugadoresEntry.ID, idClub);
        cv.put(DataContract.JugadoresEntry.NOMBRE, nombre);
        cv.put(DataContract.JugadoresEntry.ID_CLUB, idClub);
        return cv;
    }
}
