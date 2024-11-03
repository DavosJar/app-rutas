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
import com.app_rutas.controller.dao.services.VehiculoServices;
import com.app_rutas.controller.excepcion.ListEmptyException;

@Path("/vehiculo")
public class VehiculoApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAllVehiculo() throws ListEmptyException, Exception {
        HashMap<String, Object> res = new HashMap<>();
        VehiculoServices vs = new VehiculoServices();
        try {
            res.put("estado", "success");
            res.put("mensaje", "Consulta realizada con éxito.");
            res.put("data", vs.listAll().toArray());
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
    public Response getVehiculoByIndex(@PathParam("id") Integer index) {
        String jsonResponse = "";
        VehiculoServices vs = new VehiculoServices();
        try {
            jsonResponse = vs.getVehiculoJsonByIndex(index);
            return Response.ok(jsonResponse).build();
        } catch (Exception e) {
            HashMap<String, String> res = new HashMap<>();
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/save")
    public Response save(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        VehiculoServices vs = new VehiculoServices();
        try {
            if (map.get("marca") == null || map.get("marca").toString().isEmpty()) {
                throw new IllegalArgumentException("El campo marca es obligatorio.");
            }

            // Set attributes from the map
            vs.getVehiculo().setMarca(map.get("marca").toString());
            vs.getVehiculo().setModelo(map.get("modelo").toString());
            vs.getVehiculo().setPlaca(map.get("placa").toString());
            vs.getVehiculo().setPotencia(Integer.parseInt(map.get("potencia").toString()));
            vs.getVehiculo().setPesoMin(Float.parseFloat(map.get("pesoMin").toString()));
            vs.getVehiculo().setPesoMax(Float.parseFloat(map.get("pesoMax").toString()));
            vs.getVehiculo().setTiene_refrigeracion(Boolean.parseBoolean(map.get("tiene_refrigeracion").toString()));
            if (map.get("tipo_Vehiculo") != null) {
                vs.getVehiculo().setTipo_Vehiculo(vs.getTipoVehiculo(map.get("tipo_Vehiculo").toString()));
            }

            vs.save();
            res.put("estado", "success");
            res.put("data", "Registro guardado con éxito.");
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
    public Response delete(@PathParam("id") Integer id) {
        HashMap<String, String> res = new HashMap<>();
        VehiculoServices vs = new VehiculoServices();

        try {
            vs.getVehiculo().setId(id);
            vs.delete();
            res.put("estado", "success");
            res.put("data", "Registro eliminado con éxito.");
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
    public Response update(HashMap<String, Object> map, @PathParam("id") Integer id) {
        HashMap<String, String> res = new HashMap<>();
        VehiculoServices vs = new VehiculoServices();

        try {
            if (map == null || !map.containsKey("marca") || !map.containsKey("modelo") || !map.containsKey("placa")) {
                res.put("estado", "error");
                res.put("data", "Parámetros insuficientes.");
                return Response.status(Status.BAD_REQUEST).entity(res).build();
            }

            // Set attributes
            vs.getVehiculo().setId(id);
            vs.getVehiculo().setMarca(map.get("marca").toString());
            vs.getVehiculo().setModelo(map.get("modelo").toString());
            vs.getVehiculo().setPlaca(map.get("placa").toString());
            vs.getVehiculo().setPotencia(Integer.parseInt(map.get("potencia").toString()));
            vs.getVehiculo().setPesoMin(Float.parseFloat(map.get("pesoMin").toString()));
            vs.getVehiculo().setPesoMax(Float.parseFloat(map.get("pesoMax").toString()));
            vs.getVehiculo().setTiene_refrigeracion(Boolean.parseBoolean(map.get("tiene_refrigeracion").toString()));
            if (map.get("tipo_Vehiculo") != null) {
                vs.getVehiculo().setTipo_Vehiculo(vs.getTipoVehiculo(map.get("tipo_Vehiculo").toString()));
            }

            vs.update();
            res.put("estado", "success");
            res.put("data", "Registro actualizado con éxito.");
            return Response.ok(res).build();
        } catch (NumberFormatException e) {
            res.put("estado", "error");
            res.put("data", "Error en el formato de los datos: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
        } catch (Exception e) {
            res.put("estado", "error");
            res.put("data", "Error interno del servidor: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
