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

import com.app_rutas.controller.dao.services.PedidoServices;
import com.app_rutas.controller.excepcion.ListEmptyException;

@Path("/pedido")
public class PedidoApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAllProyects() throws ListEmptyException, Exception {
        HashMap<String, Object> res = new HashMap<>();
        PedidoServices ps = new PedidoServices();
        try {
            res.put("status", "OK");
            res.put("msg", "Consulta exitosa.");
            res.put("data", ps.listAll().toArray());
            if (ps.listAll().isEmpty()) {
                res.put("data", new Object[] {});
            }
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("status", "ERROR");
            res.put("msg", "Error al obtener la lista de pedidos: " + e.getMessage());
            // ev.registrarEvento(TipoCrud.LIST, "Error inesperado: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response getPedidoById(@PathParam("id") Integer id) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        PedidoServices ps = new PedidoServices();
        try {
            map.put("msg", "OK");
            map.put("data", ps.getPedidoById(id));
            if (ps.getPedidoById(id) == null) {
                map.put("msg", "ERROR");
                map.put("error", "No se encontro el pedido con id: " + id);
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
        PedidoServices ps = new PedidoServices();

        try {
            if (map.get("idCliente") != null && !map.get("idCliente").toString().isEmpty()) {
                ps.getPedido().setIdCliente(Integer.valueOf(map.get("idCliente").toString()));

            }
            if (map.get("contenido") != null && !map.get("contenido").toString().isEmpty()) {
                ps.getPedido().setContenido(ps.getTipo(map.get("contenido").toString()));
            }
            if (map.get("requiereFrio") != null && !map.get("requiereFrio").toString().isEmpty()) {
                ps.getPedido().setRequiereFrio(Boolean.parseBoolean(map.get("requiereFrio").toString()));
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
        PedidoServices ps = new PedidoServices();
        try {
            ps.getPedido().setId(id);
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
        PedidoServices ps = new PedidoServices();
        if (ps.getPedidoById(Integer.valueOf(map.get("id").toString())) != null) {
            try {
                if (map.get("id") == null || map.get("id").toString().isEmpty()) {
                    throw new IllegalArgumentException("El campo 'id' es obligatorio.");
                }
                ps.setPedido(ps.getPedidoById(Integer.valueOf(map.get("id").toString())));
                if (map.get("idCliente") != null && !map.get("idCliente").toString().isEmpty()) {
                    ps.getPedido().setId(Integer.parseInt(map.get("idCliente").toString()));
                }

                if (map.get("contenido") != null && !map.get("contenido").toString().isEmpty()) {
                    ps.getPedido().setContenido(ps.getTipo(map.get("contenido").toString()));
                }

                if (map.get("requiereFrio") != null && !map.get("requiereFrio").toString().isEmpty()) {
                    ps.getPedido().setRequiereFrio(Boolean.parseBoolean(map.get("requiereFrio").toString()));
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
            res.put("data", "No se encontro el pedido con id: " + map.get("id").toString());
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/search/ident/{identificacion}")
    public Response searchPedido(@PathParam("identificacion") String identificacion) throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        PedidoServices ps = new PedidoServices();
        try {
            res.put("estado", "Ok");
            res.put("data", ps.obtenerPedidoPor("identificacion", identificacion));
            if (ps.obtenerPedidoPor(identificacion, ps) == null) {
                res.put("estado", "error");
                res.put("data", "No se encontro el pedido con identificacion: " + identificacion);
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
    public Response buscarPedidos(@PathParam("atributo") String atributo, @PathParam("valor") String valor)
            throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        PedidoServices ps = new PedidoServices();
        try {
            res.put("estado", "Ok");
            res.put("data", ps.getPedidosBy(atributo, valor).toArray());
            if (ps.getPedidosBy(atributo, valor).isEmpty()) {
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
    public Response ordenarPedidos(@PathParam("atributo") String atributo, @PathParam("orden") Integer orden)
            throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        PedidoServices ps = new PedidoServices();
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
    @Path("/tipo_contenido")
    public Response geTipos() throws ListEmptyException, Exception {
        HashMap<String, Object> map = new HashMap<>();
        PedidoServices ps = new PedidoServices();
        map.put("msg", "OK");
        map.put("data", ps.getTipos());
        return Response.ok(map).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/criterios")
    public Response getCriterios() throws ListEmptyException, Exception {
        HashMap<String, Object> map = new HashMap<>();
        PedidoServices ps = new PedidoServices();
        map.put("msg", "OK");
        map.put("data", ps.getPedidoAttributeLists());
        return Response.ok(map).build();
    }
}