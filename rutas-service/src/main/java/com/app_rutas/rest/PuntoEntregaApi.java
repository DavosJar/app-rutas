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

import com.app_rutas.controller.dao.services.PuntoEntregaServices;
import com.app_rutas.controller.excepcion.ListEmptyException;

@Path("/punto_entrega")
public class PuntoEntregaApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAllProyects() throws ListEmptyException, Exception {
        HashMap<String, Object> res = new HashMap<>();
        PuntoEntregaServices ps = new PuntoEntregaServices();
        // EventoCrudServices ev = new EventoCrudServices();
        try {
            res.put("status", "OK");
            res.put("msg", "Consulta exitosa.");
            res.put("data", ps.listAll().toArray());
            if (ps.listAll().isEmpty()) {
                res.put("data", new Object[] {});
            }
            // ev.registrarEvento(TipoCrud.LIST, "Se ha consultado la lista de
            // puntoEntregas.");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("status", "ERROR");
            res.put("msg", "Error al obtener la lista de puntoEntregas: " + e.getMessage());
            // ev.registrarEvento(TipoCrud.LIST, "Error inesperado: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response getPuntoEntregaById(@PathParam("id") Integer id) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        PuntoEntregaServices ps = new PuntoEntregaServices();
        try {
            map.put("msg", "OK");
            map.put("data", ps.getPuntoEntregaById(id));
            if (ps.getPuntoEntregaById(id) == null) {
                map.put("msg", "ERROR");
                map.put("error", "No se encontro el puntoEntrega con id: " + id);
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
        PuntoEntregaServices ps = new PuntoEntregaServices();

        try {
            if (map.get("ciudad") != null) {
                ps.getPuntoEntrega().setCiudad(map.get("ciudad").toString());
            } else {
                throw new IllegalArgumentException("El campo ciudad es obligatorio.");
            }
            if (map.get("latitud") != null) {
                ps.getPuntoEntrega().setLatitud(Double.valueOf(map.get("latitud").toString()));
            } else {
                throw new IllegalArgumentException("El campo latitud es obligatorio.");
            }
            if (map.get("longitud") != null) {
                ps.getPuntoEntrega().setLongitud(Double.valueOf(map.get("longitud").toString()));
            } else {
                throw new IllegalArgumentException("El campo longitud es obligatorio.");
            }
            if (map.get("direccion") != null) {
                ps.getPuntoEntrega().setDireccion(map.get("direccion").toString());
            } else {
                throw new IllegalArgumentException("El campo direccion es obligatorio.");
            }
            if (map.get("ubicacionActual") != null) {
                ps.getPuntoEntrega().setUbicacionActual(map.get("ubicacionActual").toString());
            } else {
                throw new IllegalArgumentException("El campo ubicacionActual es obligatorio.");
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
        PuntoEntregaServices ps = new PuntoEntregaServices();
        try {
            ps.getPuntoEntrega().setId(id);
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
        PuntoEntregaServices ps = new PuntoEntregaServices();
        if (ps.getPuntoEntregaById(Integer.valueOf(map.get("id").toString())) != null) {
            try {
                ps.setPuntoEntrega(ps.getPuntoEntregaById(Integer.valueOf(map.get("id").toString())));

                if (map.get("ciudad") != null) {
                    ps.getPuntoEntrega().setCiudad(map.get("ciudad").toString());
                }

                if (map.get("latitud") != null) {
                    ps.getPuntoEntrega().setLatitud(Double.valueOf(map.get("latitud").toString()));
                }

                if (map.get("longitud") != null) {
                    ps.getPuntoEntrega().setLongitud(Double.valueOf(map.get("longitud").toString()));
                }

                if (map.get("direccion") != null) {
                    ps.getPuntoEntrega().setDireccion(map.get("direccion").toString());
                }

                if (map.get("ubicacionActual") != null) {
                    ps.getPuntoEntrega().setUbicacionActual(map.get("ubicacionActual").toString());
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
            res.put("data", "No se encontro el puntoEntrega con id: " + map.get("id").toString());
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/search/ident/{identificacion}")
    public Response searchPuntoEntrega(@PathParam("identificacion") String identificacion) throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        PuntoEntregaServices ps = new PuntoEntregaServices();
        try {
            res.put("estado", "Ok");
            res.put("data", ps.obtenerPuntoEntregaPor("identificacion", identificacion));
            if (ps.obtenerPuntoEntregaPor(identificacion, ps) == null) {
                res.put("estado", "error");
                res.put("data", "No se encontro el puntoEntrega con identificacion: " + identificacion);
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
    public Response buscarPuntoEntregas(@PathParam("atributo") String atributo, @PathParam("valor") String valor)
            throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        PuntoEntregaServices ps = new PuntoEntregaServices();
        try {
            res.put("estado", "Ok");
            res.put("data", ps.getPuntoEntregasBy(atributo, valor).toArray());
            if (ps.getPuntoEntregasBy(atributo, valor).isEmpty()) {
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
    public Response ordenarPuntoEntregas(@PathParam("atributo") String atributo, @PathParam("orden") Integer orden)
            throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        PuntoEntregaServices ps = new PuntoEntregaServices();
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
        PuntoEntregaServices ps = new PuntoEntregaServices();
        map.put("msg", "OK");
        map.put("data", ps.getPuntoEntregaAttributeLists());
        return Response.ok(map).build();
    }
}