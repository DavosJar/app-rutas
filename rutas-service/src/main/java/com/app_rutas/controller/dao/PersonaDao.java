package com.app_rutas.controller.dao;

import com.app_rutas.controller.dao.implement.AdapterDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.Persona;
import com.app_rutas.models.Sexo;
import com.app_rutas.models.TipoIdentificacion;
import com.google.gson.Gson;

public class PersonaDao extends AdapterDao<Persona> {
    private Persona persona;
    private LinkedList<Persona> listAll;
    private Gson g = new Gson();
    
    public PersonaDao() {
        super(Persona.class);   
    }
    public Persona getPersona() {
        if(persona == null) {
            persona = new Persona();
        }
        return persona;
    }S
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public LinkedList<Persona> getListAll() throws Exception {
        if(listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }
    public Boolean save() throws Exception {
        Integer id = listAll().getSize()+1;
        persona.setId(id);
        this.persist(this.persona);
        this.listAll = listAll();
        return true;
    }
    public Boolean update() throws Exception {
        this.merge(persona, persona.getId());
        this.listAll = listAll();
        return true;
    }
    public Boolean delete() throws Exception {
        if (listAll == null) {
            listAll = listAll(); 
        }
        this.delete(persona.getId());
        reindexIds();
        return true;
    }
    private void reindexIds() throws Exception {
        LinkedList<Persona> listAll = listAll();
        for (int i = 0; i < listAll.getSize(); i++) {
            Persona p = listAll.get(i);
            p.setId(i + 1);
            this.merge(p, i + 1);
        }
    }

    public Persona getPersonaByIndex(Integer Index) throws Exception {
        return get(Index);
    }

    public String getPersonaJsonByIndex(Integer Index) throws Exception {
        return g.toJson(get(Index));
    }

    public TipoIdentificacion getTipos(String tipo) {
        return TipoIdentificacion.valueOf(tipo);
    }
    public TipoIdentificacion[] getTiposIdentificacion(){
        return TipoIdentificacion.values();
    }
    public Sexo getSexo(String sexo) {
        return Sexo.valueOf(sexo);
    }
    public Sexo[] getSexos(){
        return Sexo.values();
    }
    public void setListAll(LinkedList<Persona> listAll) {
        this.listAll = listAll;
    }
}