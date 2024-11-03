package com.app_rutas.controller.dao;

import com.app_rutas.controller.dao.implement.AdapterDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.TipoVehiculo;
import com.app_rutas.models.Vehiculo;
import com.google.gson.Gson;

public class VehiculoDao extends AdapterDao<Vehiculo>{

    private Vehiculo vehiculo;
    private LinkedList<Vehiculo> listAll;
    private Gson g = new Gson();

    public VehiculoDao(){
        super(Vehiculo.class);
    }

    public Vehiculo getVehiculo(){
        if(vehiculo == null){
            vehiculo = new Vehiculo();
        }
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }

    public LinkedList<Vehiculo> getListAll()throws Exception{
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }


    public Boolean save() throws Exception{
        Integer id = listAll().getSize() +1;
        getVehiculo().setId(id);
        this.persist(this.vehiculo);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(vehiculo, vehiculo.getId());
        this.listAll = listAll();
        return true;
    }

    public Boolean delete() throws Exception{
        if(listAll == null){
            listAll = listAll();
        }
        this.delete(vehiculo.getId());
        reindexIds();
        return true;
    }

    public void reindexIds()throws Exception{
        LinkedList<Vehiculo> listAll = listAll();
        for(int i = 0; i < listAll.getSize(); i++){
            Vehiculo ve = listAll.get(i);
            ve.setId(i+1);
            this.merge(ve, i +1);
        }
    }

    public Vehiculo getPedidoByIndex(Integer index) throws Exception{
        return get(index);
    }

    public String getVehiculoJsonByIdIndex(Integer index ) throws Exception{
        return g.toJson(get(index));
    }

    public String toJsonAll()throws Exception{
        return g.toJson(getListAll());
    }


    public TipoVehiculo getTipoVehiculo(String tipoVehiculo){
        return TipoVehiculo.valueOf(tipoVehiculo);
    }

    public TipoVehiculo[] getTipoVehiculo(){
        return TipoVehiculo.values();
    }

    public void setListAll(LinkedList<Vehiculo> listAll){
        this.listAll = listAll;
    } 
    
}
