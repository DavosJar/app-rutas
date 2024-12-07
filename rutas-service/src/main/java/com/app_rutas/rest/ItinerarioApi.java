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

import com.app_rutas.controller.dao.services.ItinerarioServices;
import com.app_rutas.controller.excepcion.ListEmptyException;
import com.app_rutas.models.EstadoItinerario;

@Path("/itinerario")
public class ItinerarioApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAllProyects() throws ListEmptyException, Exception {
        HashMap<String, Object> res = new HashMap<>();
        ItinerarioServices ps = new ItinerarioServices();
        // EventoCrudServices ev = new EventoCrudServices();
        try {
            res.put("status", "OK");
            res.put("msg", "Consulta exitosa.");
            res.put("data", ps.listAll().toArray());
            if (ps.listAll().isEmpty()) {
                res.put("data", new Object[] {});
            }
            // ev.registrarEvento(TipoCrud.LIST, "Se ha consultado la lista de
            // itinerarios.");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("status", "ERROR");
            res.put("msg", "Error al obtener la lista de itinerarios: " + e.getMessage());
            // ev.registrarEvento(TipoCrud.LIST, "Error inesperado: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response getItinerarioById(@PathParam("id") Integer id) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        ItinerarioServices ps = new ItinerarioServices();
        try {
            map.put("msg", "OK");
            map.put("data", ps.getItinerarioById(id));
            if (ps.getItinerarioById(id) == null) {
                map.put("msg", "ERROR");
                map.put("error", "No se encontro el itinerario con id: " + id);
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
    /*
     * private String horaInicio;
     * private String duracionEstimada;
     * private String fecha;
     * private Integer idConductorAsignado;
     * private EstadoItinerario estado;
     */

    @Path("/save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        ItinerarioServices ps = new ItinerarioServices();

        try {
            if (map.get("horaInicio") == null || map.get("duracionEstimada") == null || map.get("fecha") == null
                    || map.get("idConductorAsignado") == null || map.get("estado") == null) {
                throw new IllegalArgumentException("Faltan datos.");
            }
            ps.getItinerario().setHoraInicio(map.get("horaInicio").toString());
            ps.getItinerario().setDuracionEstimada(map.get("duracionEstimada").toString());
            ps.getItinerario().setFecha(map.get("fecha").toString());
            ps.getItinerario().setIdConductorAsignado(Integer.valueOf(map.get("idConductorAsignado").toString()));
            ps.getItinerario().setEstado(EstadoItinerario.valueOf(map.get("estado").toString()));

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
        ItinerarioServices ps = new ItinerarioServices();
        try {
            ps.getItinerario().setId(id);
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
        ItinerarioServices ps = new ItinerarioServices();
        if (ps.getItinerarioById(Integer.valueOf(map.get("id").toString())) != null) {
            try {
                if (ps.getItinerarioById(Integer.valueOf(map.get("id").toString())) == null) {
                    ps.getItinerario().setId(Integer.valueOf(map.get("id").toString()));
                }
                if (map.get("horaInicio") != null) {
                    ps.getItinerario().setHoraInicio(map.get("horaInicio").toString());
                }

                if (map.get("duracionEstimada") != null) {
                    ps.getItinerario().setDuracionEstimada(map.get("duracionEstimada").toString());
                }

                if (map.get("fecha") != null) {
                    ps.getItinerario().setFecha(map.get("fecha").toString());
                }

                if (map.get("idConductorAsignado") != null) {
                    ps.getItinerario()
                            .setIdConductorAsignado(Integer.valueOf(map.get("idConductorAsignado").toString()));
                }

                if (map.get("estado") != null) {
                    ps.getItinerario().setEstado(EstadoItinerario.valueOf(map.get("estado").toString()));
                }

                System.out.println("falta alguin dato");
                ps.setItinerario(ps.getItinerarioById(Integer.valueOf(map.get("id").toString())));
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
            res.put("data", "No se encontro el itinerario con id: " + map.get("id").toString());
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/search/ident/{identificacion}")
    public Response searchItinerario(@PathParam("identificacion") String identificacion) throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        ItinerarioServices ps = new ItinerarioServices();
        try {
            res.put("estado", "Ok");
            res.put("data", ps.obtenerItinerarioPor("identificacion", identificacion));
            if (ps.obtenerItinerarioPor(identificacion, ps) == null) {
                res.put("estado", "error");
                res.put("data", "No se encontro el itinerario con identificacion: " + identificacion);
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
    public Response buscarItinerarios(@PathParam("atributo") String atributo, @PathParam("valor") String valor)
            throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        ItinerarioServices ps = new ItinerarioServices();
        try {
            res.put("estado", "Ok");
            res.put("data", ps.getItinerariosBy(atributo, valor).toArray());
            if (ps.getItinerariosBy(atributo, valor).isEmpty()) {
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
    public Response ordenarItinerarios(@PathParam("atributo") String atributo, @PathParam("orden") Integer orden)
            throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        ItinerarioServices ps = new ItinerarioServices();
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
    @Path("/criterios")
    public Response getCriterios() throws ListEmptyException, Exception {
        HashMap<String, Object> map = new HashMap<>();
        ItinerarioServices ps = new ItinerarioServices();
        map.put("msg", "OK");
        map.put("data", ps.getItinerarioAttributeLists());
        return Response.ok(map).build();
    }
}