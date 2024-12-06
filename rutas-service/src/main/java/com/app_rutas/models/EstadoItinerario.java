package com.app_rutas.models;

public enum EstadoItinerario {
    PLANIFICADO("Itinerario planificado, listo para comenzar."),
    EN_RUTA("En proceso de entrega."),
    DEMORADO("Reparto retrasado."),
    REPROGRAMADO("Itinerario modificado."),
    DETENIDO("Reparto temporalmente detenido."),
    CANCELADO("Reparto cancelado."),
    COMPLETADO("Entregas finalizadas."),
    ARCHIVADO("Itinerario guardado para registro.");

    @SuppressWarnings("FieldMayBeFinal")
    private String descripcion;
    
    private EstadoItinerario(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

}
