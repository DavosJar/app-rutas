package com.app_rutas.rest;

import java.util.HashMap;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.app_rutas.controller.dao.services.ConductorAsignadoServices;
import com.app_rutas.controller.excepcion.ListEmptyException;

@Path("/conductorAsignado")
public class ConductorAsignadoApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAllProyects() throws ListEmptyException, Exception {
        HashMap<String, Object> res = new HashMap<>();
        ConductorAsignadoServices ps = new ConductorAsignadoServices();
        // EventoCrudServices ev = new EventoCrudServices();
        try {
            res.put("status", "OK");
            res.put("msg", "Consulta exitosa.");
            res.put("data", ps.listAll().toArray());
            if (ps.listAll().isEmpty()) {
                res.put("data", new Object[] {});
            }
            // ev.registrarEvento(TipoCrud.LIST, "Se ha consultado la lista de
            // conductorAsignados.");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("status", "ERROR");
            res.put("msg", "Error al obtener la lista de conductorAsignados: " + e.getMessage());
            // ev.registrarEvento(TipoCrud.LIST, "Error inesperado: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response getConductorAsignadoById(@PathParam("id") Integer id) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        ConductorAsignadoServices ps = new ConductorAsignadoServices();
        try {
            map.put("msg", "OK");
            map.put("data", ps.getConductorAsignadoById(id));
            if (ps.getConductorAsignadoById(id) == null) {
                map.put("msg", "ERROR");
                map.put("error", "No se encontro el conductorAsignado con id: " + id);
                return Response.status(Status.NOT_FOUND).entity(map).build();
            }
            return Response.ok(map).build();
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "ERROR");
            map.put("error", "Error inesperado: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        ConductorAsignadoServices ps = new ConductorAsignadoServices();
        /*
         * public Integer idConductor;
         * public Integer idVehiculo;
         * public String fechaAsignacion;
         * public String fechaFinalizacion;
         * public Estado estado;
         */
        try {
            if ((map.get("idConductor").toString() == null)) {
                throw new IllegalArgumentException("El idConductor es obligatorio.");
            } else {
                ps.getConductorAsignado().setIdConductor(Integer.valueOf(map.get("idConductor").toString()));
            }
            if ((map.get("idVehiculo").toString() == null)) {
                throw new IllegalArgumentException("El idVehiculo es obligatorio.");
            } else {
                ps.getConductorAsignado().setIdVehiculo(Integer.valueOf(map.get("idVehiculo").toString()));
            }

            if ((map.get("fechaAsignacion").toString() == null)) {
                throw new IllegalArgumentException("La fecha de asignacion es oblicatoria");
            } else {
                ps.getConductorAsignado().setFechaAsignacion(map.get("fechaAsignacion").toString());
            }

            if ((map.get("fechaFinalizacion").toString() == null)) {
                ps.getConductorAsignado().setFechaFinalizacion("none");
            } else {
                ps.getConductorAsignado().setFechaFinalizacion(map.get("fechaFinalizacion").toString());
            }
            if ((map.get("estado").toString() == null)) {
                throw new IllegalArgumentException("El estado es obligatorio");
            } else {
                ps.getConductorAsignado().setEstado(ps.getEstado(map.get("estado").toString()));
            }

            ps.save();
            res.put("estado", "Ok");
            res.put("data", "Registro guardado con exito.");
            return Response.ok(res).build();
        } catch (IllegalArgumentException e) {
            res.put("estado", "error");
            res.put("data", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/delete")
    public Response delete(@PathParam("id") Integer id) throws Exception {

        HashMap<String, Object> res = new HashMap<>();
        ConductorAsignadoServices ps = new ConductorAsignadoServices();
        try {
            ps.getConductorAsignado().setId(id);
            ps.delete();
            res.put("estado", "Ok");
            res.put("data", "Registro eliminado con exito.");

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response update(HashMap<String, Object> map) throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        ConductorAsignadoServices ps = new ConductorAsignadoServices();
        if (ps.getConductorAsignadoById(Integer.valueOf(map.get("id").toString())) != null) {
            try {
                ps.setConductorAsignado(ps.getConductorAsignadoById(Integer.valueOf(map.get("id").toString())));

                if (map.get("idConductor") != null) {
                    ps.getConductorAsignado().setIdConductor(Integer.valueOf(map.get("idConductor").toString()));
                }
                if (map.get("idVehiculo") != null) {
                    ps.getConductorAsignado().setIdVehiculo(Integer.valueOf(map.get("idVehiculo").toString()));
                }
                if (map.get("fechaAsignacion") != null) {
                    ps.getConductorAsignado().setFechaAsignacion(map.get("fechaAsignacion").toString());
                }
                if (map.get("fechaFinalizacion") != null) {
                    ps.getConductorAsignado().setFechaFinalizacion(map.get("fechaFinalizacion").toString());
                }
                if (map.get("estado") != null) {
                    ps.getConductorAsignado().setEstado(ps.getEstado(map.get("estado").toString()));
                }
                ps.update();
                res.put("estado", "Ok");
                res.put("data", "Registro actualizado con exito.");
                return Response.ok(res).build();
            } catch (Exception e) {
                res.put("estado", "error");
                res.put("data", "Error interno del servidor: " + e.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
            }
        } else {
            res.put("estado", "error");
            res.put("data", "No se encontro el conductorAsignado con id: " + map.get("id").toString());
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/search/ident/{identificacion}")
    public Response searchConductorAsignado(@PathParam("identificacion") String identificacion) throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        ConductorAsignadoServices ps = new ConductorAsignadoServices();
        try {
            res.put("estado", "Ok");
            res.put("data", ps.obtenerConductorAsignadoPor("identificacion", identificacion));
            if (ps.obtenerConductorAsignadoPor(identificacion, ps) == null) {
                res.put("estado", "error");
                res.put("data", "No se encontro el conductorAsignado con identificacion: " + identificacion);
                return Response.status(Response.Status.NOT_FOUND).entity(res).build();
            }
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/search/{atributo}/{valor}")
    public Response buscarConductorAsignados(@PathParam("atributo") String atributo, @PathParam("valor") String valor)
            throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        ConductorAsignadoServices ps = new ConductorAsignadoServices();
        try {
            res.put("estado", "Ok");
            res.put("data", ps.getConductorAsignadosBy(atributo, valor).toArray());
            if (ps.getConductorAsignadosBy(atributo, valor).isEmpty()) {
                res.put("data", new Object[] {});
            }
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/order/{atributo}/{orden}")
    public Response ordenarConductorAsignados(@PathParam("atributo") String atributo, @PathParam("orden") Integer orden)
            throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        ConductorAsignadoServices ps = new ConductorAsignadoServices();
        try {
            res.put("estado", "Ok");
            res.put("data", ps.order(atributo, orden).toArray());
            if (ps.order(atributo, orden).isEmpty()) {
                res.put("data", new Object[] {});
            }
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/estados")
    public Response getEstados() throws ListEmptyException, Exception {
        HashMap<String, Object> map = new HashMap<>();
        ConductorAsignadoServices ps = new ConductorAsignadoServices();
        map.put("msg", "OK");
        map.put("data", ps.getEstados());
        return Response.ok(map).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/criterios")
    public Response getCriterios() throws ListEmptyException, Exception {
        HashMap<String, Object> map = new HashMap<>();
        ConductorAsignadoServices ps = new ConductorAsignadoServices();
        map.put("msg", "OK");
        map.put("data", ps.getConductorAsignadoAttributeLists());
        return Response.ok(map).build();
    }
}