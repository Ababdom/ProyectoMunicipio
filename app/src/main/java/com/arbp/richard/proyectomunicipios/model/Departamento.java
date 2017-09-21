package com.arbp.richard.proyectomunicipios.model;

import com.google.gson.annotations.SerializedName;

public class Departamento {
    private String nombre;
    @SerializedName("nro_provincias")
    private int nroProvincias;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNroProvincias() {
        return nroProvincias;
    }

    public void setNroProvincias(int nroProvincias) {
        this.nroProvincias = nroProvincias;
    }
}