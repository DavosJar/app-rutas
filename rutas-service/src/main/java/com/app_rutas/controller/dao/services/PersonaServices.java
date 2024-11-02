package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.PersonaDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.Persona;
import com.app_rutas.models.Sexo;
import com.app_rutas.models.TipoIdentificacion;

public class PersonaServices {
    private PersonaDao obj;
    public PersonaServices() {
        this.obj = new PersonaDao();
    }
    public Boolean save() throws Exception {
        return obj.save();
    }

    @SuppressWarnings("rawtypes")
    public LinkedList listAll() throws Exception {
        return obj.getListAll();
    }

    public Persona getPersona() {
        return obj.getPersona();
    }

    public void setPersona(Persona persona) {
        obj.setPersona(persona);
    }

    public Persona getPersonaByIndex(Integer index) throws Exception  {
        return obj.getPersonaByIndex(index);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }
    public Boolean update(Persona persona) throws Exception {
        return obj.update();
    }


    public Boolean delete() throws Exception {
        return obj.delete();
    }

    public TipoIdentificacion getTipos(String tipo) {
        return obj.getTipos(tipo);
    }

    public String getPersonaJsonByIndex(Integer index) throws Exception {
        return obj.getPersonaJsonByIndex(index);
    }
    public TipoIdentificacion[] getTipos() {
        return TipoIdentificacion.values();
    }


    public Sexo getSexo(String sexo) {
        return Sexo.valueOf(sexo);
    }
    public Sexo[] getSexos() {
        return Sexo.values();
    }

}


