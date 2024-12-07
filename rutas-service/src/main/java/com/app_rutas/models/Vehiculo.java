package com.app_rutas.models;

public class Vehiculo {
    Integer id;
    String matricula;
    String marca;
    String Modelo;
    Double capacidad;
    Integer potencia;
    Double pesoTara;
    Double pesoMax;
    Boolean esRefrigerado;
    EstadoVehiculo estadoActual;
    TipoLicencia licenciaRequrida;

    public Vehiculo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public Double getCapcidad() {
        return capacidad;
    }

    public void setCapcidad(Double capcidad) {
        this.capacidad = capcidad;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public Double getPesoTara() {
        return pesoTara;
    }

    public void setPesoTara(Double pesoTara) {
        this.pesoTara = pesoTara;
    }

    public Double getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(Double pesoMax) {
        this.pesoMax = pesoMax;
    }

    public Boolean getEsRefrigerado() {
        return esRefrigerado;
    }

    public void setEsRefrigerado(Boolean esRefrigerado) {
        this.esRefrigerado = esRefrigerado;
    }

    public EstadoVehiculo getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoVehiculo estado) {
        this.estadoActual = estado;
    }

    public TipoLicencia getLicenciaRequrida() {
        return licenciaRequrida;
    }

    public void setLicenciaRequrida(TipoLicencia licenciaRequrida) {
        this.licenciaRequrida = licenciaRequrida;
    }

}
