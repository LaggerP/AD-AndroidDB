package com.example.accesodb.data;

import android.content.ContentValues;

public class Club {

    private int idClub;
    private String nombre;
    private int nroZona;

    public Club(int idClub, String nombre, int nroZona) {
        this.idClub = idClub;
        this.nombre = nombre;
        this.nroZona = nroZona;
    }

    public Club() {
    }

    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNroZona() {
        return nroZona;
    }

    public void setNroZona(int nroZona) {
        this.nroZona = nroZona;
    }

    /**
     * Convierte el estado en pares clave valor para hacer el insert
     */
    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DataContract.ClubesEntry.ID, idClub);
        cv.put(DataContract.ClubesEntry.NOMBRE, nombre);
        cv.put(DataContract.ClubesEntry.NROZONA, nroZona);
        return cv;
    }
}
