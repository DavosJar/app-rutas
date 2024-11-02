package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.PuntoEntregaDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.PuntoEntrega;

public class PuntoEntregaServices {
    private PuntoEntregaDao obj;

    public PuntoEntregaServices() {
        this.obj = new PuntoEntregaDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList<PuntoEntrega> listAll() throws Exception {
        return obj.getListAll();
    }

    public PuntoEntrega getPuntoEntrega() {
        return obj.getPuntoEntrega();
    }

    public void setPuntoEntrega(PuntoEntrega puntoEntrega) {
        obj.setPuntoEntrega(puntoEntrega);
    }

    public PuntoEntrega getPuntoEntregaById(Integer id) throws Exception {
        return obj.getPuntoEntregaById(id);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Boolean update(PuntoEntrega puntoEntrega) throws Exception {
        obj.setPuntoEntrega(puntoEntrega);
        return obj.update();
    }

    public Boolean delete() throws Exception {
        return obj.delete();
    }

    public String toJson() {
        return obj.toJson();
    }

    public String getPuntoEntregaJsonById(Integer id) throws Exception {
        return obj.getPuntoEntregaJsonById(id);
    }
}
