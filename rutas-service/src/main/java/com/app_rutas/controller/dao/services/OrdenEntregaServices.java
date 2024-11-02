package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.OrdenEntregaDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.OrdenEntrega;
import com.app_rutas.models.PuntoEntrega;
import com.google.gson.Gson;

public class OrdenEntregaServices {
    private OrdenEntregaDao obj;

    public OrdenEntregaServices() {
        this.obj = new OrdenEntregaDao();
    }

    public Boolean save()throws Exception{
        return obj.save();
    }

    public LinkedList<OrdenEntrega> listAll()throws Exception{
        return obj.getListAll();
    }

    public OrdenEntrega getOrdenEntrega(){
        return obj.getOrdenEntrega();
    }

    public void setOrdenEntrega(OrdenEntrega ordenEntrega) {
        obj.setOrdenEntrega(ordenEntrega);
    }

    public OrdenEntrega getOrdenEntregaById(Integer id) throws Exception {
        return obj.getOrdenEntregaByIndex(id);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Boolean update(OrdenEntrega ordenEntrega) throws Exception {
        obj.setOrdenEntrega(ordenEntrega);
        return obj.update();
    }

    public Boolean delete() throws Exception {
        return obj.delete();
    }

    public String toJson() {
        return obj.toJson();
    }

    public String getOrdenEntregaJsonById(Integer id) throws Exception {
        return obj.getOrdenEntregaJsonByIndex(id);
    }
}