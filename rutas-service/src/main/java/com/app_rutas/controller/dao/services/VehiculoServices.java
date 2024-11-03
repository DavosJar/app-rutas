package com.app_rutas.controller.dao.services;

import com.app_rutas.controller.dao.VehiculoDao;
import com.app_rutas.controller.tda.list.LinkedList;
import com.app_rutas.models.TipoVehiculo;
import com.app_rutas.models.Vehiculo;

public class VehiculoServices {

    private VehiculoDao obj;

    public VehiculoServices(){
        this.obj = new VehiculoDao();
    }

    public Boolean save() throws Exception{
        return obj.save();
    }

    public LinkedList listAll() throws Exception{
        return obj.getListAll();
    }

    public Vehiculo getVehiculo(){
        return obj.getVehiculo();
    }

    public void setVehiculo(Vehiculo vehiculo){
        obj.setVehiculo(vehiculo);
    }

    public Vehiculo getVehiculoByIndex(Integer index) throws Exception{
        return obj.getPedidoByIndex(index);
    }

    public String getVehiculoJsonByIndex(Integer index) throws Exception{
        return obj.getVehiculoJsonByIdIndex(index);
    }
    
    public Boolean update() throws Exception{
        return obj.update();
    }
    
    public Boolean delete() throws Exception{
        return obj.delete();
    }

    public TipoVehiculo getTipoVehiculo(String tipoVehiculo){
        return obj.getTipoVehiculo(tipoVehiculo);
    }

    public TipoVehiculo[] getTipoVehiculo(){
        return obj.getTipoVehiculo();
    }
}
