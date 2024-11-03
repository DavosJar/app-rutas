package com.app_rutas.controller.dao;

import com.app_rutas.controller.dao.implement.AdapterDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.Pedido;
import com.app_rutas.models.TipoContenido;
import com.google.gson.Gson;

public class PedidoDao extends AdapterDao<Pedido>{
    private Pedido pedido;
    private LinkedList<Pedido> listAll;
    private Gson g = new Gson();

    public PedidoDao(){
        super(Pedido.class);
    }
    

    public Pedido getPedido(){
        if(pedido == null){
            pedido = new Pedido();
        }
        return pedido;
    }

    public void setPedido(Pedido pedido){
        this.pedido = pedido;
    }

    public LinkedList<Pedido> getListAll() throws Exception{
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = listAll().getSize() +1;
        getPedido().setId(id);
        this.persist(this.pedido);
        this.listAll = listAll();
        return true;
    }

    //Revisar estos codigos 

    public Boolean update() throws Exception {
        this.merge(pedido, pedido.getId());
        this.listAll = listAll();
        return true;
    }

    public Boolean delete() throws Exception{
        if(listAll == null){
            listAll = listAll();
        }
        this.delete(pedido.getId());
        reindexIds();
        return true;
    }


    public void reindexIds()throws Exception{
        LinkedList<Pedido> listAll = listAll();
        for(int i = 0; i < listAll.getSize(); i++){
            Pedido pe = listAll.get(i);
            pe.setId(i+1);
            this.merge(pe, i +1);
        }
    }

    public Pedido getPedidoByIndex(Integer index) throws Exception{
        return get(index);
    }

    public String getPedidoJsonByIdIndex(Integer index ) throws Exception{
        return g.toJson(get(index));
    }

    public String toJsonAll()throws Exception{
        return g.toJson(getListAll());
    }


    public TipoContenido getTipoContenido(String tipoContenido){
        return TipoContenido.valueOf(tipoContenido);
    }

    public TipoContenido[] getTiposContenido(){
        return TipoContenido.values();
    }

    public void setListAll(LinkedList<Pedido> listAll){
        this.listAll = listAll;
    } 
}
