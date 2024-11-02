package com.app_rutas.models;

public class OrdenEntrega {
    private Integer id;
    private String horaMinima;
    private String horaMaxima;
    private String fechaEntrega;
    private String ubicacionActual;
    private Integer idPedido;

    public OrdenEntrega() {
    }

    public OrdenEntrega(Integer id, String horaMinima, String horaMaxima, String fechaEntrega, String ubicacionActual) {
        this.id = id;
        this.horaMinima = horaMinima;
        this.horaMaxima = horaMaxima;
        this.fechaEntrega = fechaEntrega;
        this.ubicacionActual = ubicacionActual;

    }

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
    
    @Override
    public String toString() {
        return "OrdenEntrega{" +
                "idOrdenEntrega=" + id +
                ", horaMinima='" + horaMinima + '\'' +
                ", horaMaxima='" + horaMaxima + '\'' +
                ", fechaEntrega='" + fechaEntrega + '\'' +
                ", ubicacionActual='" + ubicacionActual + '\'' +
                '}';
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
}
