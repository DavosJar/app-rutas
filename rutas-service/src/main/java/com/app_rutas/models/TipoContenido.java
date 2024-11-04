package com.app_rutas.models;

public enum TipoContenido {
    ALIMENTACION("Alimentacion"),
    LIMPIEZA("Limpieza"),
    HIGIENE("Higiene"),
    ELECTRONICOS("Electronicos"),
    ROPA("Ropa"),
    MUEBLES("Muebles"),
    FARMACEUTICO("Farmaceutico"),
    VARIOS("Varios");

    private final String tipo;

    TipoContenido(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
