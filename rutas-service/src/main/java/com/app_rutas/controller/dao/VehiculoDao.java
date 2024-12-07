package com.app_rutas.controller.dao;

import com.app_rutas.controller.dao.implement.AdapterDao;
import com.app_rutas.controller.dao.implement.Contador;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.Vehiculo;
import com.app_rutas.models.Estado;
import com.app_rutas.models.EstadoVehiculo;
import com.app_rutas.models.Sexo;
import com.app_rutas.models.TipoIdentificacion;
import com.app_rutas.models.TipoLicencia;
import com.google.gson.Gson;
import java.lang.reflect.Method;

@SuppressWarnings({ "unchecked", "ConvertToTryWithResources" })
public class VehiculoDao extends AdapterDao<Vehiculo> {
    private Vehiculo vehiculo;
    private LinkedList<Vehiculo> listAll;

    public VehiculoDao() {
        super(Vehiculo.class);
    }

    public Vehiculo getVehiculo() {
        if (this.vehiculo == null) {
            this.vehiculo = new Vehiculo();
        }
        return this.vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LinkedList<Vehiculo> getListAll() throws Exception {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public boolean save() throws Exception {
        Integer id = Contador.obtenerValorActual(Vehiculo.class);
        try {
            this.vehiculo.setId(id);
            this.persist(this.vehiculo);
            Contador.actualizarContador(Vehiculo.class);
            this.listAll = listAll();
            return true;
        } catch (Exception e) {
            throw new Exception("Error al guardar el vehiculo: " + e.getMessage());
        }
    }

    public Boolean update() throws Exception {
        if (this.vehiculo == null || this.vehiculo.getId() == null) {
            throw new Exception("No se ha seleccionado un vehiculo para actualizar.");
        }
        if (listAll == null) {
            listAll = listAll();
        }
        Integer index = getVehiculoIndex("id", this.vehiculo.getId());
        if (index == -1) {
            throw new Exception("Vehiculo no encontrado.");
        }
        try {
            this.merge(this.vehiculo, index);
            listAll = listAll();
            return true;
        } catch (Exception e) {
            throw new Exception("Error al actualizar el vehiculo: " + e.getMessage());
        }
    }

    public Boolean delete() throws Exception {
        if (this.vehiculo == null || this.vehiculo.getId() == null) {
            throw new Exception("No se ha seleccionado un vehiculo para eliminar.");
        }
        if (listAll == null) {
            listAll = listAll();
        }
        Integer index = getVehiculoIndex("id", this.vehiculo.getId());
        if (index == -1) {
            throw new Exception("Vehiculo no encontrado.");
        }
        try {
            this.delete(index);
            listAll = listAll();
            return true;
        } catch (Exception e) {
            throw new Exception("Error al eliminar el vehiculo: " + e.getMessage());
        }
    }

    private LinkedList<Vehiculo> linearBinarySearch(String attribute, Object value) throws Exception {
        LinkedList<Vehiculo> lista = this.listAll().quickSort(attribute, 1);
        LinkedList<Vehiculo> vehiculos = new LinkedList<>();
        if (!lista.isEmpty()) {
            Vehiculo[] aux = lista.toArray();
            Integer low = 0;
            Integer high = aux.length - 1;
            Integer mid;
            Integer index = -1;
            String searchValue = value.toString().toLowerCase();
            while (low <= high) {
                mid = (low + high) / 2;

                String midValue = obtenerAttributeValue(aux[mid], attribute).toString().toLowerCase();
                System.out.println("Comparando: " + midValue + " con " + searchValue);

                if (midValue.startsWith(searchValue)) {
                    if (mid == 0 || !obtenerAttributeValue(aux[mid - 1], attribute).toString().toLowerCase()
                            .startsWith(searchValue)) {
                        index = mid;
                        break;
                    } else {
                        high = mid - 1;
                    }
                } else if (midValue.compareToIgnoreCase(searchValue) < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            if (index.equals(-1)) {
                return vehiculos;
            }

            Integer i = index;
            while (i < aux.length
                    && obtenerAttributeValue(aux[i], attribute).toString().toLowerCase().startsWith(searchValue)) {
                vehiculos.add(aux[i]);
                i++;
            }
        }
        return vehiculos;
    }

    public LinkedList<Vehiculo> buscar(String attribute, Object value) throws Exception {
        return linearBinarySearch(attribute, value);
    }

    public Vehiculo buscarPor(String attribute, Object value) throws Exception {
        LinkedList<Vehiculo> lista = listAll();
        Vehiculo p = null;

        if (!lista.isEmpty()) {
            Vehiculo[] vehiculos = lista.toArray();
            for (int i = 0; i < vehiculos.length; i++) {
                if (obtenerAttributeValue(vehiculos[i], attribute).toString().toLowerCase()
                        .equals(value.toString().toLowerCase())) {
                    p = vehiculos[i];
                    break;
                }
            }
        }
        return p;
    }

    private Integer getVehiculoIndex(String attribute, Object value) throws Exception {
        if (this.listAll == null) {
            this.listAll = listAll();
        }
        Integer index = -1;
        if (!this.listAll.isEmpty()) {
            Vehiculo[] vehiculos = this.listAll.toArray();
            for (int i = 0; i < vehiculos.length; i++) {
                if (obtenerAttributeValue(vehiculos[i], attribute).toString().toLowerCase()
                        .equals(value.toString().toLowerCase())) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    private Object obtenerAttributeValue(Object object, String attribute) throws Exception {
        String normalizedAttribute = "get" + attribute.substring(0, 1).toUpperCase()
                + attribute.substring(1).toLowerCase();
        Method[] methods = object.getClass().getMethods();

        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(normalizedAttribute) && method.getParameterCount() == 0) {
                return method.invoke(object);
            }
        }

        throw new NoSuchMethodException("No se encontor el atributo: " + attribute);
    }

    public String[] getVehiculoAttributeLists() {
        LinkedList<String> attributes = new LinkedList<>();
        for (Method m : Vehiculo.class.getDeclaredMethods()) {
            if (m.getName().startsWith("get")) {
                String attribute = m.getName().substring(3);
                if (!attribute.equalsIgnoreCase("id")) {
                    attributes.add(attribute.substring(0, 1).toLowerCase() + attribute.substring(1));
                }
            }
        }
        return attributes.toArray();
    }

    public LinkedList<Vehiculo> order(String attribute, Integer type) throws Exception {
        LinkedList<Vehiculo> lista = listAll();
        return lista.isEmpty() ? lista : lista.mergeSort(attribute, type);
    }

    public String toJson() throws Exception {
        Gson g = new Gson();
        return g.toJson(this.vehiculo);
    }

    public Vehiculo getVehiculoById(Integer id) throws Exception {
        return get(id);
    }

    public String getVehiculoJasonByIndex(Integer index) throws Exception {
        Gson g = new Gson();
        return g.toJson(get(index));
    }

    public TipoIdentificacion getTipo(String tipo) {
        return TipoIdentificacion.valueOf(tipo);
    }

    public TipoLicencia getTipoLic(String tipo) {
        return TipoLicencia.valueOf(tipo);
    }

    public TipoLicencia[] getTipos() {
        return TipoLicencia.values();
    }

    public EstadoVehiculo getEstado(String estado) {
        return EstadoVehiculo.valueOf(estado);
    }

    public EstadoVehiculo[] getEstados() {
        return EstadoVehiculo.values();
    }

    public String getVehiculoJson(Integer Index) throws Exception {
        Gson g = new Gson();
        return g.toJson(get(Index));
    }

}