package com.app_rutas.rest;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE; 
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.app_rutas.controller.dao.services.ItinerarioServices;
import com.app_rutas.controller.excepcion.ListEmptyException;
import com.app_rutas.models.Itinerario;

@Path("/itinerario")
public class ItinerarioApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")

    public Response getAllItinerios() throws ListEmptyException, Exception {
        HashMap res = new HashMap<>();
        ItinerarioServices is = new ItinerarioServices();
        try {
            res.put("estado", "success");
            res.put("mensaje", "Consulta realizada con exito.");
            res.put("data", is.listAll().toArray());
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

    public Response getItinerarioByIndex(@PathParam("id") Integer Index) {
        String jsonResponse = "";
        ItinerarioServices is = new ItinerarioServices();
        try {
            jsonResponse = is.getItinerarioJsonByIndex(Index);
            return Response.ok(jsonResponse).build();
        } catch (Exception e) {
            HashMap res = new HashMap<>();
            res.put("estado", "error");
            res.put("estado", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Itinerario itinerario) {
        HashMap<String, Object> res = new HashMap<>();
        ItinerarioServices is = new ItinerarioServices();

        // Verificar que el objeto itinerario no sea nulo
        if (itinerario == null) {
            res.put("estado", "error");
            res.put("data", "El itinerario no puede ser nulo.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Verificar que los campos obligatorios no sean nulos o vacíos
        if (itinerario.getHoraInicio() == null || itinerario.getHoraInicio().isEmpty()) {
            res.put("estado", "error");
            res.put("data", "La hora de inicio no puede ser nula o vacía.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }
        
        if (itinerario.getDuracionEstimada() == null || itinerario.getDuracionEstimada().isEmpty()) {
            res.put("estado", "error");
            res.put("data", "La duración estimada no puede ser nula o vacía.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        if (itinerario.getFecha() == null || itinerario.getFecha().isEmpty()) {
            res.put("estado", "error");
            res.put("data", "La fecha no puede ser nula o vacía.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Validar el idConductorAsignado
        if (itinerario.getIdConductorAsignado() == null) {
            res.put("estado", "error");
            res.put("data", "El ID del conductor asignado no puede ser nulo.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Validar el estado
        if (itinerario.getEstado() == null) {
            res.put("estado", "error");
            res.put("data", "El estado no puede ser nulo.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        try {
            is.setItinerario(itinerario);
            is.save();

            res.put("estado", "success");
            res.put("data", "Itinerario guardado con éxito.");
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
    public Response delete(@PathParam("id") Integer Index) {
        HashMap res = new HashMap<>();
        ItinerarioServices is = new ItinerarioServices();
        try {
            is.getItinerario().setId(Index);
            is.delete();
            res.put("estado", "success");
            res.put("data", "Itinerario eliminado con exito.");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}/update")
    public Response update(@PathParam("id") Integer id, Itinerario itinerario) {
        HashMap<String, Object> res = new HashMap<>();
        ItinerarioServices is = new ItinerarioServices();
        
        // Verifica que el objeto itinerario no sea nulo
        if (itinerario == null) {
            res.put("estado", "error");
            res.put("data", "El itinerario no puede ser nulo.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Configura el ID
        itinerario.setId(id);

        // Aquí puedes realizar las validaciones necesarias
        if (itinerario.getHoraInicio() == null || itinerario.getHoraInicio().isEmpty()) {
            res.put("estado", "error");
            res.put("data", "La hora de inicio no puede ser nula o vacía.");
            return Response.status(Status.BAD_REQUEST).entity(res).build();
        }

        // Repite las validaciones para otros campos...

        try {
            is.setItinerario(itinerario);
            is.update();
            res.put("estado", "success");
            res.put("message", "Itinerario actualizado con éxito.");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("message", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

}