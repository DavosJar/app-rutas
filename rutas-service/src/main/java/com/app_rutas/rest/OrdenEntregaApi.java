package com.app_rutas.rest;

import java.util.HashMap;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.app_rutas.controller.dao.services.OrdenEntregaServices;
import com.app_rutas.controller.excepcion.ListEmptyException;
import com.app_rutas.models.OrdenEntrega;

@Path("/orden-entrega")
public class OrdenEntregaApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllOrdenEntregas() {
        HashMap<String, Object> res = new HashMap<>();
        OrdenEntregaServices pes = new OrdenEntregaServices();
        try {
            res.put("status", "success");
            res.put("message", "Consulta realizada con éxito.");
            res.put("data", pes.listAll().toArray());
            return Response.ok(res).build();
        } catch (ListEmptyException e) {
            res.put("status", "error");
            res.put("message", "Lista de órdenes de entrega está vacía.");
            return Response.status(Status.NO_CONTENT).entity(res).build();
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getOrdenEntregaById(@PathParam("id") Integer id) {
        HashMap<String, Object> res = new HashMap<>();
        OrdenEntregaServices ordenEntregaServices = new OrdenEntregaServices();
        try {
            OrdenEntrega ordenEntrega = ordenEntregaServices.getOrdenEntregaById(id);
            res.put("status", "success");
            res.put("data", ordenEntrega);
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
    public Response save(OrdenEntrega ordenEntrega) {
        HashMap<String, Object> res = new HashMap<>();
        OrdenEntregaServices ordenEntregaServices = new OrdenEntregaServices();
        ordenEntregaServices.setOrdenEntrega(ordenEntrega);

        try {
            if (ordenEntregaServices.save()) {
                res.put("status", "success");
                res.put("message", "Orden de entrega registrada correctamente.");
                return Response.ok(res).build();
            } else {
                res.put("status", "error");
                res.put("message", "No se pudo registrar la orden de entrega.");
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
        OrdenEntregaServices pes = new OrdenEntregaServices();
        try {
            pes.getOrdenEntrega().getIdPedido();
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
    public Response update(OrdenEntrega ordenEntrega, @PathParam("id") Integer id) {
        HashMap<String, Object> res = new HashMap<>();
        OrdenEntregaServices pes = new OrdenEntregaServices();
        ordenEntrega.setId(id);
        pes.setOrdenEntrega(ordenEntrega);

        try {
            if (pes.update()) {
                res.put("status", "success");
                res.put("message", "Orden de entrega actualizada correctamente.");
                return Response.ok(res).build();
            } else {
                res.put("status", "error");
                res.put("message", "No se pudo actualizar la orden de entrega.");
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
            }
        } catch (Exception e) {
            res.put("status", "error");
            res.put("message", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
