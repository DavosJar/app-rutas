package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.ItinerarioDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.EstadoItinerario;
import com.app_rutas.models.Itinerario;

public class ItinerarioServices {

    @SuppressWarnings("FieldMayBeFinal")
    private ItinerarioDao obj;

    public ItinerarioServices() {
        obj = new ItinerarioDao();
    }

    public Itinerario getItinerario() {
        return obj.getItinerario();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean delete() throws Exception {
        return obj.delete();
    }

    public LinkedList<Itinerario> listAll() throws Exception {
        return obj.getListAll();
    }

    public void setItinerario(Itinerario Pprsona) {
        obj.setItinerario(Pprsona);
    }

    public Itinerario getItinerarioById(Integer id) throws Exception {
        return obj.getItinerarioById(id);

    }

    public String toJson() throws Exception {
        return obj.toJson();

    }

    public LinkedList<Itinerario> getItinerariosBy(String atributo, Object valor) throws Exception {
        return obj.buscar(atributo, valor);
    }

    public LinkedList<Itinerario> order(String atributo, Integer type) throws Exception {
        return obj.order(atributo, type);
    }

    public Itinerario obtenerItinerarioPor(String atributo, Object valor) throws Exception {
        return obj.buscarPor(atributo, valor);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public String[] getItinerarioAttributeLists() {
        return obj.getItinerarioAttributeLists();
    }

    public EstadoItinerario getEstado(String estado) {
        return obj.getEstados(estado);
    }

    public EstadoItinerario[] getEstados() {
        return obj.getEstados();
    }

}