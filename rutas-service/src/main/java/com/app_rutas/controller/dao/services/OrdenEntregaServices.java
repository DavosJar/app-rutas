package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.OrdenEntregaDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.OrdenEntrega;

public class OrdenEntregaServices {
    private OrdenEntregaDao obj;
    public OrdenEntregaServices() {
        this.obj = new OrdenEntregaDao();
    }
    public Boolean save() throws Exception {
        return obj.save();
    }

    @SuppressWarnings("rawtypes")
    public LinkedList listAll() throws Exception {
        return obj.getListAll();
    }

    public OrdenEntrega getOrdenEntrega() {
        return obj.getOrdenEntrega();
    }

    public void setOrdenEntrega(OrdenEntrega ordenEntrega) {
        obj.setOrdenEntrega(ordenEntrega);
    }

    public OrdenEntrega getByIndex(Integer index) throws Exception  {
        return obj.getByIndex(index);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }
    public Boolean update(OrdenEntrega ordenEntrega) throws Exception {
        return obj.update();
    }


    public Boolean delete() throws Exception {
        return obj.delete();
    }


    public String getByJsonByIndex(Integer index) throws Exception {
        return obj.getByJsonByIndex(index);
    }


    public OrdenEntrega get(Integer index)throws Exception{
        return obj.get(index);
    }
}


