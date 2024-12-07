package com.app_rutas.models;

public enum EstadoVehiculo {
    DISPONIBLE("Disponible"),
    OCUPADO("Ocupado"),
    MANTENIMIENTO("Mantenimiento");

    private final String estado;

    EstadoVehiculo(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

}
