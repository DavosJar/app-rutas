package com.app_rutas.rest;

import java.util.HashMap;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.app_rutas.controller.dao.services.PuntoEntregaServices;
import com.app_rutas.controller.excepcion.ListEmptyException;
import com.app_rutas.models.PuntoEntrega;
import com.google.gson.Gson;

@Path("/punto-entrega")
public class PuntoEntregaApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllPuntos() throws ListEmptyException {
        HashMap<String, Object> res = new HashMap<>();
        PuntoEntregaServices pes = new PuntoEntregaServices();
        try {
            res.put("status", "success");
            res.put("message", "Consulta realizada con éxito.");
            res.put("data", pes.listAll().toArray());
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getPuntoEntregaById(@PathParam("id") Integer id) {
        HashMap<String, Object> res = new HashMap<>();
        PuntoEntregaServices pes = new PuntoEntregaServices();
        try {
            res.put("status", "success");
            res.put("data", pes.getPuntoEntregaJsonById(id));
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/save")
    public Response save(PuntoEntrega puntoEntrega) {
        HashMap<String, Object> res = new HashMap<>();
        PuntoEntregaServices pes = new PuntoEntregaServices();
        pes.setPuntoEntrega(puntoEntrega);

        try {
            if (pes.save()) {
                res.put("status", "success");
                res.put("message", "Punto de entrega registrado correctamente.");
                return Response.ok(res).build();
            } else {
                res.put("status", "error");
                res.put("message", "No se pudo registrar el punto de entrega.");
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
            }
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/delete")
    public Response delete(@PathParam("id") Integer id) {
        HashMap<String, Object> res = new HashMap<>();
        PuntoEntregaServices pes = new PuntoEntregaServices();
        try {
            pes.getPuntoEntrega().setIdPuntoEntrega(id);
            pes.delete();
            res.put("status", "success");
            res.put("message", "Punto de entrega eliminado con éxito.");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/update")
    public Response update(PuntoEntrega puntoEntrega, @PathParam("id") Integer id) {
        HashMap<String, Object> res = new HashMap<>();
        PuntoEntregaServices pes = new PuntoEntregaServices();

        puntoEntrega.setIdPuntoEntrega(id);
        pes.setPuntoEntrega(puntoEntrega);

        try {
            pes.update();
            res.put("status", "success");
            res.put("message", "Punto de entrega actualizado con éxito.");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
