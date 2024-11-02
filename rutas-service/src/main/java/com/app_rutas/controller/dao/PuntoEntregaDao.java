package com.app_rutas.controller.dao;

import com.app_rutas.controller.dao.implement.AdapterDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.PuntoEntrega;
import com.google.gson.Gson;

public class PuntoEntregaDao extends AdapterDao<PuntoEntrega> {
    private PuntoEntrega puntoEntrega;
    private LinkedList<PuntoEntrega> listAll;
    private Gson g = new Gson();

    public PuntoEntregaDao() {
        super(PuntoEntrega.class);
    }

    public PuntoEntrega getPuntoEntrega() {
        if (puntoEntrega == null) {
            puntoEntrega = new PuntoEntrega();
        }
        return puntoEntrega;
    }

    public void setPuntoEntrega(PuntoEntrega puntoEntrega) {
        this.puntoEntrega = puntoEntrega;
    }

    public LinkedList<PuntoEntrega> getListAll() throws Exception {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = listAll().getSize() + 1;
        puntoEntrega.setIdPuntoEntrega(id);
        this.persist(this.puntoEntrega);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(puntoEntrega, puntoEntrega.getIdPuntoEntrega());
        this.listAll = listAll();
        return true;
    }

    public Boolean delete() throws Exception {
        if (listAll == null) {
            listAll = listAll();
        }
        this.delete(puntoEntrega.getIdPuntoEntrega());
        reindexIds();
        return true;
    }

    private void reindexIds() throws Exception {
        LinkedList<PuntoEntrega> listAll = listAll();
        for (int i = 0; i < listAll.getSize(); i++) {
            PuntoEntrega p = listAll.get(i);
            p.setIdPuntoEntrega(i + 1);
            this.merge(p, i + 1);
        }
    }

    public PuntoEntrega getPuntoEntregaById(Integer id) throws Exception {
        return get(id);
    }

    public String getPuntoEntregaJsonById(Integer id) throws Exception {
        return g.toJson(get(id));
    }

    public String toJson() {
        return g.toJson(getPuntoEntrega());
    }

    public void setListAll(LinkedList<PuntoEntrega> listAll) {
        this.listAll = listAll();
    }
}
