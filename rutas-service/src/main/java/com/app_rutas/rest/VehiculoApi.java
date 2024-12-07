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

import com.app_rutas.controller.dao.services.VehiculoServices;
import com.app_rutas.controller.excepcion.ListEmptyException;

@Path("/vehiculo")
public class VehiculoApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAllProyects() throws ListEmptyException, Exception {
        HashMap<String, Object> res = new HashMap<>();
        VehiculoServices ps = new VehiculoServices();
        // EventoCrudServices ev = new EventoCrudServices();
        try {
            res.put("status", "OK");
            res.put("msg", "Consulta exitosa.");
            res.put("data", ps.listAll().toArray());
            if (ps.listAll().isEmpty()) {
                res.put("data", new Object[] {});
            }
            // ev.registrarEvento(TipoCrud.LIST, "Se ha consultado la lista de vehiculos.");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("status", "ERROR");
            res.put("msg", "Error al obtener la lista de vehiculos: " + e.getMessage());
            // ev.registrarEvento(TipoCrud.LIST, "Error inesperado: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response getVehiculoById(@PathParam("id") Integer id) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        VehiculoServices ps = new VehiculoServices();
        try {
            map.put("msg", "OK");
            map.put("data", ps.getVehiculoById(id));
            if (ps.getVehiculoById(id) == null) {
                map.put("msg", "ERROR");
                map.put("error", "No se encontro el vehiculo con id: " + id);
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
        VehiculoServices ps = new VehiculoServices();

        try {
            if (map.get("matricula") == null) {
                throw new IllegalArgumentException("La matricula es obligatoria");
            } else {
                ps.getVehiculo().setMatricula(map.get("matricula").toString());
            }
            if (map.get("marca") == null) {
                throw new IllegalArgumentException("La marca es obligatoria");
            } else {
                ps.getVehiculo().setMarca(map.get("marca").toString());
            }
            if (map.get("modelo") == null) {
                throw new IllegalArgumentException("El Modelo es obligatoria");
            } else {
                ps.getVehiculo().setModelo(map.get("modelo").toString());
            }
            if (map.get("capcidad") == null) {
                throw new IllegalArgumentException("La capacidad es obligatoria");
            } else {
                ps.getVehiculo().setCapcidad(Double.valueOf(map.get("capcidad").toString()));
            }
            if (map.get("potencia") == null) {
                throw new IllegalArgumentException("La potencia es obligatoria");
            } else {
                ps.getVehiculo().setPotencia(Integer.valueOf(map.get("potencia").toString()));
            }
            if (map.get("pesoTara") == null) {
                throw new IllegalArgumentException("El peso tara es obligatoria");
            } else {
                ps.getVehiculo().setPesoTara(Double.valueOf(map.get("pesoTara").toString()));
            }

            if (map.get("pesoMax") == null) {
                throw new IllegalArgumentException("El peso maximo es obligatoria");
            } else {
                ps.getVehiculo().setPesoMax(Double.valueOf(map.get("pesoMax").toString()));
            }
            if (map.get("esRefrigerado") == null) {
                throw new IllegalArgumentException("El campo es refrigerado es obligatoria");
            } else {
                ps.getVehiculo().setEsRefrigerado(Boolean.valueOf(map.get("esRefrigerado").toString()));
            }
            if (map.get("estadoActual") == null) {
                throw new IllegalArgumentException("El campo es obligatorio");
            } else {
                ps.getVehiculo().setEstadoActual(ps.getEstado(map.get("estadoActual").toString()));
            }
            if (map.get("licenciaRequerida") == null) {
                throw new IllegalArgumentException("El campo es obligatorio");
            } else {
                ps.getVehiculo().setLicenciaRequrida(ps.getTipoLic(map.get("licenciaRequerida").toString()));
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
        VehiculoServices ps = new VehiculoServices();
        try {
            ps.getVehiculo().setId(id);
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
    /*
     * String matricula;
     * String marca;
     * String Modelo;
     * Double capcidad;
     * Integer potencia;
     * Double pesoTara;
     * Double peroMaz;
     * Boolean esRefrigerado;
     * EstadoVehiculo estado;
     * TipoLicencia licenciaRequrida;
     */

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response update(HashMap<String, Object> map) throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        VehiculoServices ps = new VehiculoServices();
        if (ps.getVehiculoById(Integer.valueOf(map.get("id").toString())) != null) {
            try {
                ps.setVehiculo(ps.getVehiculoById(Integer.valueOf(map.get("id").toString())));
                if (map.get("matricula") != null) {
                    ps.getVehiculo().setMatricula(map.get("matricula").toString());
                }
                if (map.get("marca") != null) {
                    ps.getVehiculo().setMarca(map.get("marca").toString());
                }
                if (map.get("modelo") != null) {
                    ps.getVehiculo().setModelo(map.get("modelo").toString());
                }
                if (map.get("capacidad") != null) {
                    ps.getVehiculo().setCapcidad(Double.valueOf(map.get("capacidad").toString()));
                }
                if (map.get("potencia") != null) {
                    ps.getVehiculo().setPotencia(Integer.valueOf(map.get("potencia").toString()));
                }
                if (map.get("pesoTara") != null) {
                    ps.getVehiculo().setPesoTara(Double.valueOf(map.get("pesoTara").toString()));
                }
                if (map.get("pesoMax") != null) {
                    ps.getVehiculo().setPesoMax(Double.valueOf(map.get("pesoMax").toString()));
                }
                if (map.get("esRefrigerado") != null) {
                    ps.getVehiculo().setEsRefrigerado(Boolean.valueOf(map.get("esRefrigerado").toString()));
                }
                if (map.get("estadoActual") != null) {
                    ps.getVehiculo().setEstadoActual(ps.getEstado(map.get("estadoActual").toString()));
                }
                if (map.get("licenciaRequerida") != null) {
                    ps.getVehiculo().setLicenciaRequrida(ps.getTipoLic(map.get("licenciaRequerida").toString()));
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
            res.put("data", "No se encontro el vehiculo con id: " + map.get("id").toString());
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/search/ident/{identificacion}")
    public Response searchVehiculo(@PathParam("identificacion") String identificacion) throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        VehiculoServices ps = new VehiculoServices();
        try {
            res.put("estado", "Ok");
            res.put("data", ps.obtenerVehiculoPor("identificacion", identificacion));
            if (ps.obtenerVehiculoPor(identificacion, ps) == null) {
                res.put("estado", "error");
                res.put("data", "No se encontro el vehiculo con identificacion: " + identificacion);
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
    public Response buscarVehiculos(@PathParam("atributo") String atributo, @PathParam("valor") String valor)
            throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        VehiculoServices ps = new VehiculoServices();
        try {
            res.put("estado", "Ok");
            res.put("data", ps.getVehiculosBy(atributo, valor).toArray());
            if (ps.getVehiculosBy(atributo, valor).isEmpty()) {
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
    public Response ordenarVehiculos(@PathParam("atributo") String atributo, @PathParam("orden") Integer orden)
            throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        VehiculoServices ps = new VehiculoServices();
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
    @Path("/tipoidentificacion")
    public Response geTipos() throws ListEmptyException, Exception {
        HashMap<String, Object> map = new HashMap<>();
        VehiculoServices ps = new VehiculoServices();
        map.put("msg", "OK");
        map.put("data", ps.getTipos());
        return Response.ok(map).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/criterios")
    public Response getCriterios() throws ListEmptyException, Exception {
        HashMap<String, Object> map = new HashMap<>();
        VehiculoServices ps = new VehiculoServices();
        map.put("msg", "OK");
        map.put("data", ps.getVehiculoAttributeLists());
        return Response.ok(map).build();
    }
}