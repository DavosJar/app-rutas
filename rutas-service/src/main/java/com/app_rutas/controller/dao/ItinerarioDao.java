package com.app_rutas.controller.dao;

import com.app_rutas.controller.dao.implement.AdapterDao;
import com.app_rutas.controller.dao.implement.Contador;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.EstadoItinerario;
import com.app_rutas.models.Itinerario;
import com.app_rutas.models.Sexo;
import com.app_rutas.models.TipoIdentificacion;
import com.google.gson.Gson;
import java.lang.reflect.Method;

@SuppressWarnings({ "unchecked", "ConvertToTryWithResources" })
public class ItinerarioDao extends AdapterDao<Itinerario> {
    private Itinerario itinerario;
    private LinkedList<Itinerario> listAll;

    public ItinerarioDao() {
        super(Itinerario.class);
    }

    public Itinerario getItinerario() {
        if (this.itinerario == null) {
            this.itinerario = new Itinerario();
        }
        return this.itinerario;
    }

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    public LinkedList<Itinerario> getListAll() throws Exception {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public boolean save() throws Exception {
        Integer id = Contador.obtenerValorActual(Itinerario.class);
        try {
            this.itinerario.setId(id);
            this.persist(this.itinerario);
            Contador.actualizarContador(Itinerario.class);
            this.listAll = listAll();
            return true;
        } catch (Exception e) {
            throw new Exception("Error al guardar el itinerario: " + e.getMessage());
        }
    }

    public Boolean update() throws Exception {
        if (this.itinerario == null || this.itinerario.getId() == null) {
            throw new Exception("No se ha seleccionado un itinerario para actualizar.");
        }
        if (listAll == null) {
            listAll = listAll();
        }
        Integer index = getItinerarioIndex("id", this.itinerario.getId());
        if (index == -1) {
            throw new Exception("Itinerario no encontrado.");
        }
        try {
            this.merge(this.itinerario, index);
            listAll = listAll();
            return true;
        } catch (Exception e) {
            throw new Exception("Error al actualizar el itinerario: " + e.getMessage());
        }
    }

    public Boolean delete() throws Exception {
        if (this.itinerario == null || this.itinerario.getId() == null) {
            throw new Exception("No se ha seleccionado un itinerario para eliminar.");
        }
        if (listAll == null) {
            listAll = listAll();
        }
        Integer index = getItinerarioIndex("id", this.itinerario.getId());
        if (index == -1) {
            throw new Exception("Itinerario no encontrado.");
        }
        try {
            this.delete(index);
            listAll = listAll();
            return true;
        } catch (Exception e) {
            throw new Exception("Error al eliminar el itinerario: " + e.getMessage());
        }
    }

    private LinkedList<Itinerario> linearBinarySearch(String attribute, Object value) throws Exception {
        LinkedList<Itinerario> lista = this.listAll().quickSort(attribute, 1);
        LinkedList<Itinerario> itinerarios = new LinkedList<>();
        if (!lista.isEmpty()) {
            Itinerario[] aux = lista.toArray();
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
                return itinerarios;
            }

            Integer i = index;
            while (i < aux.length
                    && obtenerAttributeValue(aux[i], attribute).toString().toLowerCase().startsWith(searchValue)) {
                itinerarios.add(aux[i]);
                i++;
            }
        }
        return itinerarios;
    }

    public LinkedList<Itinerario> buscar(String attribute, Object value) throws Exception {
        return linearBinarySearch(attribute, value);
    }

    public Itinerario buscarPor(String attribute, Object value) throws Exception {
        LinkedList<Itinerario> lista = listAll();
        Itinerario p = null;

        if (!lista.isEmpty()) {
            Itinerario[] itinerarios = lista.toArray();
            for (int i = 0; i < itinerarios.length; i++) {
                if (obtenerAttributeValue(itinerarios[i], attribute).toString().toLowerCase()
                        .equals(value.toString().toLowerCase())) {
                    p = itinerarios[i];
                    break;
                }
            }
        }
        return p;
    }

    private Integer getItinerarioIndex(String attribute, Object value) throws Exception {
        if (this.listAll == null) {
            this.listAll = listAll();
        }
        Integer index = -1;
        if (!this.listAll.isEmpty()) {
            Itinerario[] itinerarios = this.listAll.toArray();
            for (int i = 0; i < itinerarios.length; i++) {
                if (obtenerAttributeValue(itinerarios[i], attribute).toString().toLowerCase()
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

    public String[] getItinerarioAttributeLists() {
        LinkedList<String> attributes = new LinkedList<>();
        for (Method m : Itinerario.class.getDeclaredMethods()) {
            if (m.getName().startsWith("get")) {
                String attribute = m.getName().substring(3);
                if (!attribute.equalsIgnoreCase("id")) {
                    attributes.add(attribute.substring(0, 1).toLowerCase() + attribute.substring(1));
                }
            }
        }
        return attributes.toArray();
    }

    public LinkedList<Itinerario> order(String attribute, Integer type) throws Exception {
        LinkedList<Itinerario> lista = listAll();
        return lista.isEmpty() ? lista : lista.mergeSort(attribute, type);
    }

    public String toJson() throws Exception {
        Gson g = new Gson();
        return g.toJson(this.itinerario);
    }

    public Itinerario getItinerarioById(Integer id) throws Exception {
        return get(id);
    }

    public EstadoItinerario getEstados(String estado) {
        return EstadoItinerario.valueOf(estado);
    }

    public EstadoItinerario[] getEstados() {
        return EstadoItinerario.values();
    }

}