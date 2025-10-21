package com.myproyect.miproyect.model;

import lombok.Data;

import java.time.LocalDate;

import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProduccionModel {
    private int idProduccion;// PK
    private int idCultivo;// FK
    private LocalDate fechaDeCosecha;
    private String calidadDelProducto;
    private double cantidadTotalTon;
    private double rendimientoPorHectarea;

    public ProduccionModel(int idProduccion, int idCultivo, LocalDate fechaDeCosecha,
            String calidadDelProducto, double cantidadTotalTon, double rendimientoPorHectarea) {
        this.idProduccion = idProduccion;
        this.idCultivo = idCultivo;
        this.fechaDeCosecha = fechaDeCosecha;
        this.calidadDelProducto = calidadDelProducto;
        this.cantidadTotalTon = cantidadTotalTon;
        this.rendimientoPorHectarea = rendimientoPorHectarea;
    }
}
