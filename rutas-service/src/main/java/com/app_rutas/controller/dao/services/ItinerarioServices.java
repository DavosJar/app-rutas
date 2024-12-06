package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.ItinerarioDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.EstadoItinerario;
import com.app_rutas.models.Itinerario;

public class ItinerarioServices {
    private ItinerarioDao obj;
    public ItinerarioServices() {
        this.obj = new ItinerarioDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    @SuppressWarnings("rawtypes")
    public LinkedList listAll() throws Exception {
        return obj.getListAll();
    }

    public Itinerario getItinerario() {
        return obj.getItinerario();
    }

    public void setItinerario(Itinerario itinerario) {
        obj.setItinerario(itinerario);
    }

    public Itinerario getItinerarioByIndex(Integer index) throws Exception  {
        return obj.getItinerarioByIndex(index);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Boolean update(Itinerario itinerario) throws Exception {
        return obj.update();
    }

    public Boolean delete() throws Exception {
        return obj.delete();
    }

    public Boolean delete(Itinerario itinerario) throws Exception {
        return obj.delete();
    }

    public EstadoItinerario getEstadoItineario(String estado) {
        return obj.getEstado(estado);
    }

    public EstadoItinerario[] getEstados() {
        return obj.getEstados();
    }

    public String getItinerarioJsonByIndex(Integer index) throws Exception {
        return obj.getItinerarioJsonByIndex(index);
    }

    public void setListAll (LinkedList<Itinerario> listAll){
        obj.setListAll(listAll);
    }
}

