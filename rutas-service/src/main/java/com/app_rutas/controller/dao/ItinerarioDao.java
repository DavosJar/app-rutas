package com.app_rutas.controller.dao;

import javax.ws.rs.core.Link;

import com.app_rutas.controller.dao.implement.AdapterDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.EstadoItinerario;
import com.app_rutas.models.Itinerario;
import com.google.gson.Gson;

public class ItinerarioDao extends AdapterDao <Itinerario> {
    private Itinerario itinerario;
    private LinkedList<Itinerario> listAll;
    
    private Gson g = new Gson();
    
    public ItinerarioDao() {
        super(Itinerario.class);   
    }

    public Itinerario getItinerario() {
        if(itinerario == null) {
            itinerario = new Itinerario();
        }
        return itinerario;
    }

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    public LinkedList<Itinerario> getListAll() throws Exception {
        if(listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = listAll().getSize()+1;
        itinerario.setId(id);
        this.persist(this.itinerario);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(itinerario, itinerario.getId());
        this.listAll = listAll();
        return true;
    }
    
    public Boolean delete() throws Exception {
        if (listAll == null) {
            listAll = listAll(); 
        }
        this.delete(itinerario.getId());
        reindexIds();
        return true;
    }

    private void reindexIds() throws Exception {
        LinkedList<Itinerario> listAll = listAll();
        for (int i = 0; i < listAll.getSize(); i++) {
            Itinerario itinerario = listAll.get(i);
            itinerario.setId(i+1);
            this.merge(itinerario, itinerario.getId());
        }
    }

    public String getItinerarioJson() {
        return g.toJson(itinerario);
    }

    public String getItinerarioJson(Itinerario itinerario) {
        return g.toJson(itinerario);
    }

    public String getItinerarioJsonByIndex(Itinerario itinerario, Integer index) {
        return g.toJson(itinerario);
    }

    public Itinerario getItinerarioByIndex(Integer index) throws Exception {
        return get(index);
    }

    public String getItinerarioJsonByIndex(Integer index) throws Exception {
        return g.toJson(get(index));
    }
    public EstadoItinerario getEstado(String estado) {
        return EstadoItinerario.valueOf(estado);
    }
    public EstadoItinerario[] getEstados() {
        return EstadoItinerario.values();
    }

    public void setListAll(LinkedList<Itinerario> listAll) {
        this.listAll = listAll;
    }
    
}
