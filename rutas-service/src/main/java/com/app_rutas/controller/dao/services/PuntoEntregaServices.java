package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.PuntoEntregaDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.PuntoEntrega;

public class PuntoEntregaServices {

    @SuppressWarnings("FieldMayBeFinal")
    private PuntoEntregaDao obj;

    public PuntoEntregaServices() {
        obj = new PuntoEntregaDao();
    }

    public PuntoEntrega getPuntoEntrega() {
        return obj.getPuntoEntrega();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean delete() throws Exception {
        return obj.delete();
    }

    public LinkedList<PuntoEntrega> listAll() throws Exception {
        return obj.getListAll();
    }

    public void setPuntoEntrega(PuntoEntrega Pprsona) {
        obj.setPuntoEntrega(Pprsona);
    }

    public PuntoEntrega getPuntoEntregaById(Integer id) throws Exception {
        return obj.getPuntoEntregaById(id);

    }

    public String toJson() throws Exception {
        return obj.toJson();

    }

    public LinkedList<PuntoEntrega> getPuntoEntregasBy(String atributo, Object valor) throws Exception {
        return obj.buscar(atributo, valor);
    }

    public LinkedList<PuntoEntrega> order(String atributo, Integer type) throws Exception {
        return obj.order(atributo, type);
    }

    public PuntoEntrega obtenerPuntoEntregaPor(String atributo, Object valor) throws Exception {
        return obj.buscarPor(atributo, valor);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public String[] getPuntoEntregaAttributeLists() {
        return obj.getPuntoEntregaAttributeLists();
    }

}