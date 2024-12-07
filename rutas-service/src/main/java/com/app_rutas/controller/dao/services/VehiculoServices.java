package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.VehiculoDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.Vehiculo;
import com.app_rutas.models.Estado;
import com.app_rutas.models.EstadoVehiculo;
import com.app_rutas.models.TipoLicencia;

public class VehiculoServices {

    @SuppressWarnings("FieldMayBeFinal")
    private VehiculoDao obj;

    public VehiculoServices() {
        obj = new VehiculoDao();
    }

    public Vehiculo getVehiculo() {
        return obj.getVehiculo();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean delete() throws Exception {
        return obj.delete();
    }

    public LinkedList<Vehiculo> listAll() throws Exception {
        return obj.getListAll();
    }

    public void setVehiculo(Vehiculo Pprsona) {
        obj.setVehiculo(Pprsona);
    }

    public Vehiculo getVehiculoById(Integer id) throws Exception {
        return obj.getVehiculoById(id);

    }

    public String toJson() throws Exception {
        return obj.toJson();

    }

    public LinkedList<Vehiculo> getVehiculosBy(String atributo, Object valor) throws Exception {
        return obj.buscar(atributo, valor);
    }

    public LinkedList<Vehiculo> order(String atributo, Integer type) throws Exception {
        return obj.order(atributo, type);
    }

    public Vehiculo obtenerVehiculoPor(String atributo, Object valor) throws Exception {
        return obj.buscarPor(atributo, valor);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public TipoLicencia getTipoLic(String licencia) {
        return obj.getTipoLic(licencia);
    }

    public TipoLicencia[] getTipos() {
        return obj.getTipos();
    }

    public EstadoVehiculo getEstado(String estadoActual) {
        return obj.getEstado(estadoActual);
    }

    public EstadoVehiculo[] getEstados() {
        return obj.getEstados();
    }

    public String[] getVehiculoAttributeLists() {
        return obj.getVehiculoAttributeLists();
    }

}