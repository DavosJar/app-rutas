package com.app_rutas.models;

public enum TipoVehiculo {
    AUTO("Automóvil"),
    CAMIONETA("Camioneta"),
    MOTOCICLETA("Motocicleta"),
    BICICLETA("Bicicleta"),
    CAMION("Camion");

    private final String descripcion;

    TipoVehiculo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
