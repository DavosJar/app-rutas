package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.ConductorDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.Conductor;
import com.app_rutas.models.Sexo;
import com.app_rutas.models.TipoIdentificacion;
import com.app_rutas.models.TipoLicencia;

public class ConductorServices {

    @SuppressWarnings("FieldMayBeFinal")
    private ConductorDao obj;

    public ConductorServices() {
        obj = new ConductorDao();
    }

    public Conductor getConductor() {
        return obj.getConductor();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean delete() throws Exception {
        return obj.delete();
    }

    public LinkedList<Conductor> listAll() throws Exception {
        return obj.getListAll();
    }

    public void setConductor(Conductor Pprsona) {
        obj.setConductor(Pprsona);
    }

    public Conductor getConductorById(Integer id) throws Exception {
        return obj.getConductorById(id);

    }

    public String toJson() throws Exception {
        return obj.toJson();

    }

    public LinkedList<Conductor> getConductorsBy(String atributo, Object valor) throws Exception {
        return obj.buscar(atributo, valor);
    }

    public LinkedList<Conductor> order(String atributo, Integer type) throws Exception {
        return obj.order(atributo, type);
    }

    public Conductor obtenerConductorPor(String atributo, Object valor) throws Exception {
        return obj.buscarPor(atributo, valor);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public TipoIdentificacion getTipo(String tipo) {
        return obj.getTipo(tipo);
    }

    public TipoIdentificacion[] getTipos() {
        return obj.getTipos();
    }

    public Sexo getSexo(String sexo) {
        return obj.getSexo(sexo);
    }

    public Sexo[] getSexos() {
        return obj.getSexos();
    }

    public String[] getConductorAttributeLists() {
        return obj.getConductorAttributeLists();
    }

    public TipoLicencia getTiposLicencia(String tipo) {
        return obj.getTiposLicencia(tipo);
    }

    public TipoLicencia[] getTiposLicencias() {
        return obj.getTiposLicencias();
    }

}