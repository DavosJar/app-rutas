package com.app_rutas.models;

public class Pedido {

    private Integer id;
    private Integer idCliente;
    private TipoContenido contenido;
    private Boolean requiereFrio;

    public Pedido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public TipoContenido getContenido() {
        return contenido;
    }

    public void setContenido(TipoContenido contenido) {
        this.contenido = contenido;
    }

    public Boolean isRequiereFrio() {
        return requiereFrio;
    }

    public void setRequiereFrio(Boolean requiereFrio) {
        this.requiereFrio = requiereFrio;
    }

}
