package com.app_rutas.models;

public class OrdenEntrega {
    private Integer id;
    private String horaMinima;
    private String horaMaxima;
    private String fechaEntrega;
    private String ubicacionActual;
    private Boolean estado;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoraMinima() {
        return this.horaMinima;
    }

    public void setHoraMinima(String horaMinima) {
        this.horaMinima = horaMinima;
    }

    public String getHoraMaxima() {
        return this.horaMaxima;
    }

    public void setHoraMaxima(String horaMaxima) {
        this.horaMaxima = horaMaxima;
    }

    public String getFechaEntrega() {
        return this.fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getUbicacionActual() {
        return this.ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
