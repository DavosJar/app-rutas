package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.ConductorAsignadoDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.ConductorAsignado;
import com.app_rutas.models.Estado;

public class ConductorAsignadoServices {
    private ConductorAsignadoDao obj;
    
    public ConductorAsignadoServices() {
        this.obj = new ConductorAsignadoDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList listAll() throws Exception {
        return obj.getListAll();
    }

    public ConductorAsignado getConductorAsignado() {
        return obj.getConductorAsignado();
    }

    public void setConductorAsignado(ConductorAsignado conductorAsignado) {
        obj.setConductorAsignado(conductorAsignado);
    }

    public ConductorAsignado getConductorAsignadoByIndex(Integer index) throws Exception  {
        return obj.getConductorAsignadoByIndex(index);
    }
    
    public String getConductorAsignadoJsonBy(Integer index) throws Exception {
        return obj.getConductorAsignadoJsonByIndex(index);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Boolean delete() throws Exception {
        return obj.delete();
    }

    public Estado getEstado(String estado) {
        return obj.getEstado(estado);
    }

    public Estado[] getEstados() {
        return obj.getEstados();
    }
    
    
}