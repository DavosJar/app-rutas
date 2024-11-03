package com.app_rutas.rest;

import java.util.HashMap;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

    public Response getAllPedido() throws ListEmptyException, Exception{
        HashMap res = new HashMap<>();
        PedidoServices pe = new PedidoServices();
        try{
            res.put("estado", "success");
            res.put("mensaje", "Consulta realizada con exito.");
            res.put("data", pe.listAll().toArray());
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")

    public Response getPedidoByIndex(@PathParam("id") Integer Index){
        String jsonResponse = "";
        PedidoServices pe = new PedidoServices();
        try{
            jsonResponse = pe.getPedidoJsonByIndex(Index);
            return Response.ok(jsonResponse).build();
        }catch(Exception e){
            HashMap res = new HashMap<>();
            res.put("estado", "error");
            res.put("estado", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/save") 
    public Response save(HashMap map) {
        HashMap res = new HashMap<>();
        PedidoServices pe = new PedidoServices();
            try{

            if(map.get("idPersona") == null || map.get("idPersona").toString().isEmpty()){
                throw new IllegalArgumentException("Este campo es obligatorio.");
            }
            pe.getPedido().setIdCliente(Integer.valueOf(map.get("idPersona").toString()));
            if(map.get("contenido")!= null){
                pe.getPedido().setContenido(pe.getTipoContenido(map.get("contenido").toString()));
            }
            if(map.get("requiereFrio")== null){
                pe.getPedido().setRequiereFrio(false);   
        }
            pe.save();
            res.put("estado", "success");
            res.put("data", "Registro guardado con exito.");
            return Response.ok(res).build();
        }catch(IllegalArgumentException e){
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
    public Response delete(@PathParam("id") Integer id){
        HashMap res = new HashMap<>();
        PedidoServices pe = new PedidoServices();

        try{
            pe.getPedido().setId(id);
            pe.delete();
            res.put("estado","success");
            res.put("data", "Registro eliminado con exito.");
            return Response.ok(res).build();
        }catch(Exception e){
            res.put("estado", "error");
            res.put("data", "Error interno del servidor"+ e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/update")
    public Response update(HashMap<String, Object> map, @PathParam("id") Integer id) {
        HashMap<String, String> res = new HashMap<>();
        PedidoServices pe = new PedidoServices();
    
        try {
            // Validación básica del mapa de entrada
            if (map == null || !map.containsKey("IdCliente") || !map.containsKey("contenido") || !map.containsKey("requiereFrio")) {
                res.put("estado", "error");
                res.put("data", "Parámetros insuficientes.");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            }
    
            // Asignación de valores con parseo adecuado y manejo de posibles excepciones
            pe.getPedido().setId(id);
            pe.getPedido().setIdCliente(Integer.parseInt(map.get("IdCliente").toString()));
            pe.getPedido().setContenido(pe.getTipoContenido(map.get("contenido").toString()));
            pe.getPedido().setRequiereFrio(Boolean.parseBoolean(map.get("requiereFrio").toString()));
    
            // Actualización del pedido
            pe.update();
            res.put("estado", "success");
            res.put("data", "Registro actualizado con éxito.");
            return Response.ok(res).build();
        } catch (NumberFormatException e) {
            res.put("estado", "error");
            res.put("data", "Error en el formato de los datos: " + e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
    

}
