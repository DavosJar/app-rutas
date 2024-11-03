package com.app_rutas.models;

public class Vehiculo {
    private Integer id;
    private Integer potencia;
    private String marca;
    private String modelo;
    private String placa;
    private float pesoMin;
    private float pesoMax;
    private boolean tiene_refrigeracion;
    private TipoVehiculo tipo_Vehiculo;

    
    public Vehiculo() {
        
    }

    public Vehiculo(Integer id, Integer potencia, String marca, String modelo, String placa, float pesoMin,
            float pesoMax, boolean tiene_refrigeracion, TipoVehiculo tipo_Vehiculo) {
        this.id = id;
        this.potencia = potencia;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.pesoMin = pesoMin;
        this.pesoMax = pesoMax;
        this.tiene_refrigeracion = tiene_refrigeracion;
        this.tipo_Vehiculo = tipo_Vehiculo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public float getPesoMin() {
        return pesoMin;
    }

    public void setPesoMin(float pesoMin) {
        this.pesoMin = pesoMin;
    }

    public float getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(float pesoMax) {
        this.pesoMax = pesoMax;
    }

    public boolean isTiene_refrigeracion() {
        return tiene_refrigeracion;
    }

    public void setTiene_refrigeracion(boolean tiene_refrigeracion) {
        this.tiene_refrigeracion = tiene_refrigeracion;
    }

    public TipoVehiculo getTipo_Vehiculo() {
        return tipo_Vehiculo;
    }

    public void setTipo_Vehiculo(TipoVehiculo tipo_Vehiculo) {
        this.tipo_Vehiculo = tipo_Vehiculo;
    }



    

}
