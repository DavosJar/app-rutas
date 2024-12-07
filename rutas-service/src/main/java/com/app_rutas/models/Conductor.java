package com.app_rutas.models;

public class Conductor extends Persona {
    private TipoLicencia licenciaConducir;
    private Double salario;

    public Conductor() {
        super();
    }

    public TipoLicencia getLicenciaConducir() {
        return licenciaConducir;
    }

    public void setLicenciaConducir(TipoLicencia licenciaConducir) {
        this.licenciaConducir = licenciaConducir;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}