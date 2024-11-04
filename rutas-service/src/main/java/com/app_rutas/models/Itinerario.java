package com.app_rutas.models;

public class Itinerario {
    private Integer id;
    private String horaInicio;
    private String duracionEstimada;
    private String fecha;
    private Integer idConductorAsignado;
    private EstadoItinerario estado;
    


    public Itinerario() {
    }

    public Itinerario(String horaInicio, String duracionEstimada, String fecha, Integer idConductorAsignado, EstadoItinerario estado) {
        this.horaInicio = horaInicio;
        this.duracionEstimada = duracionEstimada;
        this.fecha = fecha;
        this.idConductorAsignado = idConductorAsignado;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(String duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getIdConductorAsignado() {
        return idConductorAsignado;
    }

    public void setIdConductorAsignado(Integer idConductorAsignado) {
        this.idConductorAsignado = idConductorAsignado;
    }

    public EstadoItinerario getEstado() {
        return estado;
    }

    public void setEstado(EstadoItinerario estado) {
        this.estado = estado;
    }
}
