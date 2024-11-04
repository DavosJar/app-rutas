package com.app_rutas.controller.dao;

import com.app_rutas.controller.dao.implement.AdapterDao;
import com.app_rutas.models.Conductor;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class ConductorDao extends AdapterDao<Conductor> {
    private Conductor conductor;
    private List<Conductor> listAll;
    private Gson gson;

    public ConductorDao() {
        super(Conductor.class); // Asegúrate de que AdapterDao tiene un constructor compatible
        this.listAll = new ArrayList<>();
        this.gson = new Gson();
    }

    // Métodos de ejemplo para agregar y convertir a JSON
    public String toJson(Conductor conductor) {
        return gson.toJson(conductor);
    }

    public Conductor fromJson(String json) {
        return gson.fromJson(json, Conductor.class);
    }
}
