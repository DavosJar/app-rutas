package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.PedidoDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.Pedido;
import com.app_rutas.models.TipoContenido;

public class PedidoServices {

    @SuppressWarnings("FieldMayBeFinal")
    private PedidoDao obj;

    public PedidoServices() {
        obj = new PedidoDao();
    }

    public Pedido getPedido() {
        return obj.getPedido();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean delete() throws Exception {
        return obj.delete();
    }

    public LinkedList<Pedido> listAll() throws Exception {
        return obj.getListAll();
    }

    public void setPedido(Pedido Pprsona) {
        obj.setPedido(Pprsona);
    }

    public Pedido getPedidoById(Integer id) throws Exception {
        return obj.getPedidoById(id);

    }

    public String toJson() throws Exception {
        return obj.toJson();

    }

    public LinkedList<Pedido> getPedidosBy(String atributo, Object valor) throws Exception {
        return obj.buscar(atributo, valor);
    }

    public LinkedList<Pedido> order(String atributo, Integer type) throws Exception {
        return obj.order(atributo, type);
    }

    public Pedido obtenerPedidoPor(String atributo, Object valor) throws Exception {
        return obj.buscarPor(atributo, valor);
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public String[] getPedidoAttributeLists() {
        return obj.getPedidoAttributeLists();
    }

    public TipoContenido getTipo(String tipo) {
        return obj.getTipo(tipo);
    }

    public TipoContenido[] getTipos() {
        return obj.getTipos();
    }
}