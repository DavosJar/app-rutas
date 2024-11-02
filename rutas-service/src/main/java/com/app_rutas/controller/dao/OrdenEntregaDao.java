package com.app_rutas.controller.dao;

import com.app_rutas.controller.dao.implement.AdapterDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.OrdenEntrega;
import com.app_rutas.models.Persona;
import com.app_rutas.models.Sexo;
import com.app_rutas.models.TipoIdentificacion;
import com.google.gson.Gson;


public class OrdenEntregaDao extends AdapterDao<OrdenEntrega> {
    private OrdenEntrega ordenEntrega;
    private LinkedList<OrdenEntrega> listAll;
    private Gson g = new Gson();
    

    public OrdenEntregaDao(){
        super(OrdenEntrega.class);
    }

    public OrdenEntrega getOrdenEntrega(){
        if(ordenEntrega == null){
            ordenEntrega = new OrdenEntrega();
        }
        return ordenEntrega;
    }
    
    public void setOrdenEntrega(OrdenEntrega ordenEntrega){
        this.ordenEntrega = ordenEntrega;
    }

    public LinkedList<OrdenEntrega> getListAll()throws Exception{
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }
    public Boolean save() throws Exception{
        Integer id = listAll().getSize()+1;
        ordenEntrega.setId(id);
        this.persist(this.ordenEntrega);
        this.listAll = listAll();
        return true;
    }

    public Boolean update()throws Exception{
        this.merge(ordenEntrega, ordenEntrega.getId());
        this.listAll = listAll();
        return true;
    }

    public Boolean delete()throws Exception{
        if (listAll == null) {
            listAll = listAll();
        }
        this.delete(ordenEntrega.getId());
        reindexIds();
        return true;
    }

    private void reindexIds() throws Exception {
        LinkedList<OrdenEntrega> listAll = listAll();
        for (int i = 0; i < listAll.getSize(); i++) {
            OrdenEntrega p = listAll.get(i);
            p.setId(i + 1);
            this.merge(p, i + 1);
        }
    }

    public OrdenEntrega getOrdenEntregaByIndex(Integer Index) throws Exception {
        return get(Index);
    }

    public String getOrdenEntregaJsonByIndex(Integer Index) throws Exception {
        return g.toJson(get(Index));
    }

    public String toJson(){
        return g.toJson(getOrdenEntrega());
    }

    public void setListAll(LinkedList<OrdenEntrega> listAll){
        this.listAll = listAll();
    }
}  
   