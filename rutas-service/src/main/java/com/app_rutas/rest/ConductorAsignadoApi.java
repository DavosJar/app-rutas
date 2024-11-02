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

import com.app_rutas.controller.dao.services.ConductorAsignadoServices;
import com.app_rutas.controller.excepcion.ListEmptyException;

@Path("/conductor-asignado")
public class ConductorAsignadoApi {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getConductorAsignado()throws ListEmptyException{
        HashMap res = new HashMap<>();
        ConductorAsignadoServices cas = new ConductorAsignadoServices();
        try {
            res.put("estado", "success");
            res.put("mensaje", "Consulta realizada con exito.");
            res.put("data", cas.listAll().toArray());
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
    public Response getConductorAsignado(@PathParam("id") int index){
        String jasonResponse = "";
        ConductorAsignadoServices cas = new ConductorAsignadoServices();
        try {
            jasonResponse = cas.getConductorAsignadoJsonBy(index);
            return Response.ok(jasonResponse).build();
        } catch (Exception e) {
            HashMap res = new HashMap<>();
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/save")
    public Response save(HashMap map){
        HashMap res = new HashMap<>();
        ConductorAsignadoServices cas = new ConductorAsignadoServices();

        cas.getConductorAsignado().setIdConductor(Integer.valueOf(map.get("idConductor").toString()));
        cas.getConductorAsignado().setIdRuta(Integer.valueOf(map.get("idRuta").toString()));
        cas.getConductorAsignado().setIdVehiculo(Integer.valueOf(map.get("idVehiculo").toString()));
        cas.getConductorAsignado().setFechaAsignacion(map.get("fechaAsignacion").toString());
        cas.getConductorAsignado().setEstado(cas.getConductorAsignado().getEstado());

        try {
            cas.save();
            res.put("estado", "success");
            res.put("data", "Registro guardado con exito.");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }

    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/delete")
    public Response delete(@PathParam("id") Integer id){
        HashMap res = new HashMap<>();
        ConductorAsignadoServices cas = new ConductorAsignadoServices();
        try {
            cas.getConductorAsignado().setId(id);
            cas.delete();
            res.put("estado", "success");
            res.put("data", "Registro eliminado con exito.");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/update")
    public Response update(@PathParam("id") Integer id, HashMap map){
        HashMap res = new HashMap<>();
        ConductorAsignadoServices cas = new ConductorAsignadoServices();
        
        cas.getConductorAsignado().setId(id);
        cas.getConductorAsignado().setIdConductor(Integer.valueOf(map.get("idConductor").toString()));
        cas.getConductorAsignado().setIdRuta(Integer.valueOf(map.get("idRuta").toString()));
        cas.getConductorAsignado().setIdVehiculo(Integer.valueOf(map.get("idVehiculo").toString()));
        cas.getConductorAsignado().setFechaAsignacion(map.get("fechaAsignacion").toString());
        cas.getConductorAsignado().setFechaFinalizacion(map.get("fechaFinalizacion").toString());
        cas.getConductorAsignado().setEstado(cas.getConductorAsignado().getEstado());
        try {
            cas.update();
            res.put("estado", "success");
            res.put("data", "Registro actualizado con exito.");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
    
}
