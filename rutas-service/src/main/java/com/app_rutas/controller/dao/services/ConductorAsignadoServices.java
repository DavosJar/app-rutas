package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.ConductorAsignadoDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.ConductorAsignado;
import com.app_rutas.models.Estado;
import com.app_rutas.models.Sexo;
import com.app_rutas.models.TipoIdentificacion;

public class ConductorAsignadoServices {

    @SuppressWarnings("FieldMayBeFinal")
    private ConductorAsignadoDao obj;

    public ConductorAsignadoServices() {
        obj = new ConductorAsignadoDao();
    }

    public ConductorAsignado getConductorAsignado() {
        return obj.getConductorAsignado();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean delete() throws Exception {
        return obj.delete();
    }

    public LinkedList<ConductorAsignado> listAll() throws Exception {
        return obj.getListAll();
    }

    public void setConductorAsignado(ConductorAsignado Pprsona) {
        obj.setConductorAsignado(Pprsona);
    }

    public ConductorAsignado getConductorAsignadoById(Integer id) throws Exception {
        return obj.getConductorAsignadoById(id);

    }

    public String toJson() throws Exception {
        return obj.toJson();

    }

    public LinkedList<ConductorAsignado> getConductorAsignadosBy(String atributo, Object valor) throws Exception {
        return obj.buscar(atributo, valor);
    }

    public LinkedList<ConductorAsignado> order(String atributo, Integer type) throws Exception {
        return obj.order(atributo, type);
    }

    public ConductorAsignado obtenerConductorAsignadoPor(String atributo, Object valor) throws Exception {
        return obj.buscarPor(atributo, valor);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Estado getEstado(String estado) {
        return obj.getEstado(estado);
    }

    public Estado[] getEstados() {
        return obj.getEstados();
    }

    public String[] getConductorAsignadoAttributeLists() {
        return obj.getConductorAsignadoAttributeLists();
    }

}