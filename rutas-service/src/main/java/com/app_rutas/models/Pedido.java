package com.app_rutas.models;

public class Pedido {

    private Integer id;
    private Integer idCliente;
    private TipoContenido contenido;
    private boolean requiereFrio;

    public Pedido(){}

    public Pedido(Integer id, Integer idCliente, TipoContenido contenido, boolean RequiereFrio){


        this.id = id;
        this.idCliente = idCliente;
        this.contenido = contenido;
        this.requiereFrio = false;

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
    public boolean isRequiereFrio() {
        return requiereFrio;
    }
    public void setRequiereFrio(boolean requiereFrio) {
        this.requiereFrio = requiereFrio;
    } 
    
}
