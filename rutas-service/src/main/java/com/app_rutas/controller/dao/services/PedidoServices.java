package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.PedidoDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.Pedido;
import com.app_rutas.models.TipoContenido;

public class PedidoServices {
    private PedidoDao obj;

    public PedidoServices(){
        this.obj = new PedidoDao();
    }

    public Boolean save() throws Exception{
        return obj.save();
    }

    public LinkedList listAll() throws Exception{
        return obj.getListAll();
    }

    public Pedido getPedido(){
        return obj.getPedido();
    }

    public void setPedido(Pedido pedido){
        obj.setPedido(pedido);
    }

    public Pedido getPedidoByIndex(Integer index) throws Exception{
        return obj.getPedidoByIndex(index);
    }

    public String getPedidoJsonByIndex(Integer index) throws Exception{
        return obj.getPedidoJsonByIdIndex(index);
    }

    public Boolean update() throws Exception{
        return obj.update();
    }
    
    public Boolean delete() throws Exception{
        return obj.delete();
    }

    public TipoContenido getTipoContenido(String tipoContenido){
        return obj.getTipoContenido(tipoContenido);
    }

    public TipoContenido[] getTipoContenido(){
        return obj.getTiposContenido();
    }
    
}
