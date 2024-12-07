package com.app_rutas.models;

public enum TipoLicencia {
    A("A"),
    A1("A1"),
    B("B"),
    C("C"),
    C1("C1"),
    D("D"),
    D1("D1"),
    E("E"),
    E1("E1"),
    F("F"),
    G("G");

    private final String tipo;

    TipoLicencia(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}