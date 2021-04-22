package com.example.accesodb.data;

import android.provider.BaseColumns;

public final class DataContract{

    private DataContract() {}
    /**
     * Clase interna para la definición de la tabla.
     * */
    public static class ClubesEntry implements BaseColumns{
        //BaseColumns se implementa para agregar una columna _ID extra (recomendado)
        public static final String TABLE_NAME = "clubes";
        //Definición de los campos de la tabla
        public static final String ID = "idClub";
        public static final String NOMBRE = "nombre";
        public static final String NROZONA = "nroZona";
    }

    public static class JugadoresEntry implements BaseColumns{
        //BaseColumns se implementa para agregar una columna _ID extra (recomendado)
        public static final String TABLE_NAME = "jugadores";
        //Definición de los campos de la tabla
        public static final String ID = "idJugador";
        public static final String NOMBRE = "nombre";
        public static final String ID_CLUB = "idClub";
    }
}
