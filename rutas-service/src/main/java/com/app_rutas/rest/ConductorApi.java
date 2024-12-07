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

import com.app_rutas.controller.dao.services.ConductorServices;
import com.app_rutas.controller.excepcion.ListEmptyException;

@Path("/conductor")
public class ConductorApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAllProyects() throws ListEmptyException, Exception {
        HashMap<String, Object> res = new HashMap<>();
        ConductorServices cs = new ConductorServices();
        try {
            res.put("status", "OK");
            res.put("msg", "Consulta exitosa.");
            res.put("data", cs.listAll().toArray());
            if (cs.listAll().isEmpty()) {
                res.put("data", new Object[] {});
            }
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("status", "ERROR");
            res.put("msg", "Error al obtener la lista de conductors: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response getConductorById(@PathParam("id") Integer id) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        ConductorServices cs = new ConductorServices();
        try {
            map.put("msg", "OK");
            map.put("data", cs.getConductorById(id));
            if (cs.getConductorById(id) == null) {
                map.put("msg", "ERROR");
                map.put("error", "No se encontro el conductor con id: " + id);
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
        ConductorServices cs = new ConductorServices();

        try {
            if (map.get("nombre") == null || map.get("nombre").toString().isEmpty()) {
                throw new IllegalArgumentException("El campo 'nombre' es obligatorio.");
            }
            cs.getConductor().setNombre(map.get("nombre").toString());

            if (map.get("apellido") == null || map.get("apellido").toString().isEmpty()) {
                throw new IllegalArgumentException("El campo 'apellido' es obligatorio.");
            }
            cs.getConductor().setApellido(map.get("apellido").toString());

            if (map.get("tipoIdentificacion") != null) {
                cs.getConductor().setTipoIdentificacion(cs.getTipo(map.get("tipoIdentificacion").toString()));
            }
            if (map.get("identificacion") != null) {
                cs.getConductor().setIdentificacion(map.get("identificacion").toString());
            }
            if (map.get("fechaNacimiento") != null) {
                cs.getConductor().setFechaNacimiento(map.get("fechaNacimiento").toString());
            }
            if (map.get("direccion") != null) {
                cs.getConductor().setDireccion(map.get("direccion").toString());
            }
            if (map.get("telefono") != null) {
                cs.getConductor().setTelefono(map.get("telefono").toString());
            }
            if (map.get("email") != null) {
                cs.getConductor().setEmail(map.get("email").toString());
            }
            if (map.get("sexo") != null) {
                cs.getConductor().setSexo(cs.getSexo(map.get("sexo").toString()));
            }
            if (map.get("tipoLicencia") != null) {
                cs.getConductor().setLicenciaConducir(cs.getTiposLicencia(map.get("licenciConducir").toString()));
            }
            if (map.get("salario") != null) {
                cs.getConductor().setSalario(Double.valueOf(map.get("salario").toString()));
            }
            cs.save();
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
        ConductorServices cs = new ConductorServices();
        try {
            cs.getConductor().setId(id);
            cs.delete();
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
        ConductorServices cs = new ConductorServices();
        if (cs.getConductorById(Integer.valueOf(map.get("id").toString())) != null) {
            try {
                if (map.get("id") == null || map.get("id").toString().isEmpty()) {
                    throw new IllegalArgumentException("El campo 'id' es obligatorio.");
                }
                if (map.get("nombre") == null || map.get("nombre").toString().isEmpty()) {
                    throw new IllegalArgumentException("El campo 'nombre' es obligatorio.");
                }
                if (map.get("apellido") == null || map.get("apellido").toString().isEmpty()) {
                    throw new IllegalArgumentException("El campo 'apellido' es obligatorio.");
                }
                if (map.get("tipoIdentificacion") == null || map.get("tipoIdentificacion").toString().isEmpty()) {
                    throw new IllegalArgumentException("El campo 'tipoIdentificacion' es obligatorio.");
                }
                if (map.get("identificacion") == null || map.get("identificacion").toString().isEmpty()) {
                    throw new IllegalArgumentException("El campo 'identificacion' es obligatorio.");
                }
                if (map.get("fechaNacimiento") == null || map.get("fechaNacimiento").toString().isEmpty()) {
                    throw new IllegalArgumentException("El campo 'fechaNacimiento' es obligatorio.");
                }
                if (map.get("direccion") == null || map.get("direccion").toString().isEmpty()) {
                    throw new IllegalArgumentException("El campo 'direccion' es obligatorio.");
                }
                if (map.get("telefono") == null || map.get("telefono").toString().isEmpty()) {
                    throw new IllegalArgumentException("El campo 'telefono' es obligatorio.");
                }
                if (map.get("email") == null || map.get("email").toString().isEmpty()) {
                    throw new IllegalArgumentException("El campo 'email' es obligatorio.");
                }
                if (map.get("sexo") == null || map.get("sexo").toString().isEmpty()) {
                    throw new IllegalArgumentException("El campo 'sexo' es obligatorio.");
                }
                System.out.println("falta alguin dato");
                cs.setConductor(cs.getConductorById(Integer.valueOf(map.get("id").toString())));
                cs.getConductor().setNombre(map.get("nombre").toString());
                cs.getConductor().setApellido(map.get("apellido").toString());
                cs.getConductor().setTipoIdentificacion(cs.getTipo(map.get("tipoIdentificacion").toString()));
                cs.getConductor().setIdentificacion(map.get("identificacion").toString());
                cs.getConductor().setFechaNacimiento(map.get("fechaNacimiento").toString());
                cs.getConductor().setDireccion(map.get("direccion").toString());
                cs.getConductor().setTelefono(map.get("telefono").toString());
                cs.getConductor().setEmail(map.get("email").toString());
                cs.getConductor().setSexo(cs.getSexo(map.get("sexo").toString()));

                cs.update();
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
            res.put("data", "No se encontro el conductor con id: " + map.get("id").toString());
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/search/ident/{identificacion}")
    public Response searchConductor(@PathParam("identificacion") String identificacion) throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        ConductorServices cs = new ConductorServices();
        try {
            res.put("estado", "Ok");
            res.put("data", cs.obtenerConductorPor("identificacion", identificacion));
            if (cs.obtenerConductorPor(identificacion, cs) == null) {
                res.put("estado", "error");
                res.put("data", "No se encontro el conductor con identificacion: " + identificacion);
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
    public Response buscarConductors(@PathParam("atributo") String atributo, @PathParam("valor") String valor)
            throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        ConductorServices cs = new ConductorServices();
        try {
            res.put("estado", "Ok");
            res.put("data", cs.getConductorsBy(atributo, valor).toArray());
            if (cs.getConductorsBy(atributo, valor).isEmpty()) {
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
    public Response ordenarConductors(@PathParam("atributo") String atributo, @PathParam("orden") Integer orden)
            throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        ConductorServices cs = new ConductorServices();
        try {
            res.put("estado", "Ok");
            res.put("data", cs.order(atributo, orden).toArray());
            if (cs.order(atributo, orden).isEmpty()) {
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
    @Path("/sexo")
    public Response getSexo() throws ListEmptyException, Exception {
        HashMap<String, Object> map = new HashMap<>();
        ConductorServices cs = new ConductorServices();
        map.put("msg", "OK");
        map.put("data", cs.getSexos());
        return Response.ok(map).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tipoidentificacion")
    public Response geTipos() throws ListEmptyException, Exception {
        HashMap<String, Object> map = new HashMap<>();
        ConductorServices cs = new ConductorServices();
        map.put("msg", "OK");
        map.put("data", cs.getTipos());
        return Response.ok(map).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/criterios")
    public Response getCriterios() throws ListEmptyException, Exception {
        HashMap<String, Object> map = new HashMap<>();
        ConductorServices cs = new ConductorServices();
        map.put("msg", "OK");
        map.put("data", cs.getConductorAttributeLists());
        return Response.ok(map).build();
    }
}