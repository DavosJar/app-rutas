package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.ConductorDao;
import com.app_rutas.models.Conductor;
import com.app_rutas.models.TipoLicencia;

import java.util.Optional;

public class ConductorServices {
    private final ConductorDao conductorDao;

    // Constructor
    public ConductorServices() {
        this.conductorDao = new ConductorDao();
    }

    // Método para agregar un nuevo conductor
    public boolean agregarConductor(String licencia, float salario) {
        Conductor conductor = new Conductor();
        conductor.setLicenciaConducir(TipoLicencia.valueOf(licencia));  // Convierte el String a TipoLicencia
        conductor.setSalario(salario);
        return conductorDao.guardar(conductor);
    }

    // Método para actualizar la información de un conductor
    public boolean actualizarConductor(String licencia, float nuevoSalario) {
        Conductor conductorExistente = conductorDao.obtenerPorLicencia(licencia);
        if (conductorExistente != null) {
            conductorExistente.setSalario(nuevoSalario);
            return conductorDao.actualizar(conductorExistente);
        }
        return false;
    }

    // Método para eliminar un conductor por su licencia
    public boolean eliminarConductor(String licencia) {
        return conductorDao.eliminar(licencia);
    }

    // Método para obtener un conductor por su licencia
    public Optional<Conductor> obtenerConductor(String licencia) {
        return Optional.ofNullable(conductorDao.obtenerPorLicencia(licencia));
    }

    // Método para obtener todos los conductores
    public com.app_rutas.controller.tda.list.LinkedList<Conductor> obtenerTodosLosConductores() {
        return conductorDao.obtenerTodos();
    }

    // Método para obtener un conductor en formato JSON
    public String obtenerConductorEnJson(String licencia) {
        Conductor conductor = conductorDao.obtenerPorLicencia(licencia);
        return conductor != null ? conductorDao.convertirConductorAJson(conductor) : null;
    }

    // Método para agregar un conductor desde un JSON
    public boolean agregarConductorDesdeJson(String json) {
        Conductor conductor = conductorDao.convertirJsonAConductor(json);
        if (conductor != null) {
            return conductorDao.guardar(conductor);
        }
        return false;
    }
}
