package com.app_rutas.models;

public class Conductor {
    private TipoLicencia licenciaConducir;
    private float salario;
    
    public TipoLicencia getLicenciaConducir() {
        return licenciaConducir;
    }
    public void setLicenciaConducir(TipoLicencia licenciaConducir) {
        this.licenciaConducir = licenciaConducir;
    }
    public float getSalario() {
        return salario;
    }
    public void setSalario(float salario) {
        this.salario = salario;
    }
}