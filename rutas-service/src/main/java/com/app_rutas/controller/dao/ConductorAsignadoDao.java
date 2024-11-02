package com.app_rutas.controller.dao;

import com.app_rutas.controller.dao.implement.AdapterDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.ConductorAsignado;
import com.app_rutas.models.Estado;
import com.google.gson.Gson;

public class ConductorAsignadoDao extends AdapterDao<ConductorAsignado> {
    private ConductorAsignado conductorAsignado;
    private LinkedList<ConductorAsignado> listAll;
    
    private Gson g = new Gson();
    
    public ConductorAsignadoDao() {
        super(ConductorAsignado.class);   
    }

    public ConductorAsignado getConductorAsignado() {
        if(conductorAsignado == null) {
            conductorAsignado = new ConductorAsignado();
        }
        return conductorAsignado;
    }

    public void setConductorAsignado(ConductorAsignado conductorAsignado) {
        this.conductorAsignado = conductorAsignado;
    }

    public LinkedList<ConductorAsignado> getListAll() throws Exception {
        if(listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = listAll().getSize()+1;
        conductorAsignado.setId(id);
        this.persist(this.conductorAsignado);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(conductorAsignado, conductorAsignado.getId());
        this.listAll = listAll();
        return true;
    }
    
    public Boolean delete() throws Exception {
        if (listAll == null) {
            listAll = listAll(); 
        }
        this.delete(conductorAsignado.getId());
        reindexIds();
        return true;
    }

    private void reindexIds() throws Exception {
        LinkedList<ConductorAsignado> listAll = listAll();
        for (int i = 0; i < listAll.getSize(); i++) {
            ConductorAsignado ca = listAll.get(i);
            ca.setId(i + 1);
            this.merge(ca, i + 1);
        }
    }
    
    public ConductorAsignado getConductorAsignadoByIndex(Integer index) throws Exception {
        return get(index);
    }

    public String getConductorAsignadoJsonByIndex(Integer index) throws Exception {
        return g.toJson(getConductorAsignadoByIndex(index));
    }

    public String toJsonAll() throws Exception {
        return g.toJson(getListAll());
    }

    public Estado getEstado(String estado) {
        return Estado.valueOf(estado);
    }

    public Estado[] getEstados() {
        return Estado.values();
    }
    public void setListAll(LinkedList<ConductorAsignado> listAll) {
        this.listAll = listAll;
    }
    
}
