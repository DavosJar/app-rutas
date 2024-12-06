// package com.app_rutas.rest;

// import com.app_rutas.controller.dao.services.ConductorServices;
// import com.app_rutas.models.Conductor;
// import javax.ws.rs.*;
// import javax.ws.rs.core.MediaType;
// import javax.ws.rs.core.Response;
// import javax.ws.rs.core.Response.Status;
// import java.util.HashMap;

// @Path("/conductor")
// public class ConductorApi {

//     private final ConductorServices conductorServices = new ConductorServices();

//     @GET
//     @Produces(MediaType.APPLICATION_JSON)
//     @Path("/list")
//     public Response getAllConductores() {
//         HashMap<String, Object> res = new HashMap<>();
//         try {
//             res.put("estado", "success");
//             res.put("mensaje", "Consulta realizada con éxito.");
//             res.put("data", conductorServices.obtenerTodosLosConductores().toArray());
//             return Response.ok(res).build();
//         } catch (Exception e) {
//             res.put("estado", "error");
//             res.put("mensaje", "Error interno del servidor: " + e.getMessage());
//             return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
//         }
//     }

//     @GET
//     @Produces(MediaType.APPLICATION_JSON)
//     @Path("/{licencia}")
//     public Response getConductorByLicencia(@PathParam("licencia") String licencia) {
//         HashMap<String, Object> res = new HashMap<>();
//         try {
//             Conductor conductor = conductorServices.obtenerConductor(licencia).orElse(null);
//             if (conductor != null) {
//                 res.put("estado", "success");
//                 res.put("data", conductor);
//                 return Response.ok(res).build();
//             } else {
//                 res.put("estado", "error");
//                 res.put("mensaje", "Conductor no encontrado.");
//                 return Response.status(Status.NOT_FOUND).entity(res).build();
//             }
//         } catch (Exception e) {
//             res.put("estado", "error");
//             res.put("mensaje", "Error interno del servidor: " + e.getMessage());
//             return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
//         }
//     }

//     @POST
//     @Consumes(MediaType.APPLICATION_JSON)
//     @Produces(MediaType.APPLICATION_JSON)
//     public Response addConductor(Conductor conductor) {
//         HashMap<String, Object> res = new HashMap<>();
//         try {
//             boolean isAdded = conductorServices.agregarConductor(
//                 conductor.getLicenciaConducir().name(), conductor.getSalario());
//             if (isAdded) {
//                 res.put("estado", "success");
//                 res.put("mensaje", "Conductor agregado con éxito.");
//                 return Response.status(Status.CREATED).entity(res).build();
//             } else {
//                 res.put("estado", "error");
//                 res.put("mensaje", "No se pudo agregar el conductor.");
//                 return Response.status(Status.BAD_REQUEST).entity(res).build();
//             }
//         } catch (Exception e) {
//             res.put("estado", "error");
//             res.put("mensaje", "Error interno del servidor: " + e.getMessage());
//             return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
//         }
//     }

//     @PUT
//     @Path("/{licencia}")
//     @Consumes(MediaType.APPLICATION_JSON)
//     @Produces(MediaType.APPLICATION_JSON)
//     public Response updateConductor(@PathParam("licencia") String licencia, Conductor conductor) {
//         HashMap<String, Object> res = new HashMap<>();
//         try {
//             boolean isUpdated = conductorServices.actualizarConductor(licencia, conductor.getSalario());
//             if (isUpdated) {
//                 res.put("estado", "success");
//                 res.put("mensaje", "Conductor actualizado con éxito.");
//                 return Response.ok(res).build();
//             } else {
//                 res.put("estado", "error");
//                 res.put("mensaje", "Conductor no encontrado o no se pudo actualizar.");
//                 return Response.status(Status.NOT_FOUND).entity(res).build();
//             }
//         } catch (Exception e) {
//             res.put("estado", "error");
//             res.put("mensaje", "Error interno del servidor: " + e.getMessage());
//             return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
//         }
//     }

//     @DELETE
//     @Path("/{licencia}")
//     @Produces(MediaType.APPLICATION_JSON)
//     public Response deleteConductor(@PathParam("licencia") String licencia) {
//         HashMap<String, Object> res = new HashMap<>();
//         try {
//             boolean isDeleted = conductorServices.eliminarConductor(licencia);
//             if (isDeleted) {
//                 res.put("estado", "success");
//                 res.put("mensaje", "Conductor eliminado con éxito.");
//                 return Response.ok(res).build();
//             } else {
//                 res.put("estado", "error");
//                 res.put("mensaje", "Conductor no encontrado.");
//                 return Response.status(Status.NOT_FOUND).entity(res).build();
//             }
//         } catch (Exception e) {
//             res.put("estado", "error");
//             res.put("mensaje", "Error interno del servidor: " + e.getMessage());
//             return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
//         }
//     }
// }
