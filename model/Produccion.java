package com.myproyect.miproyect.model;

import lombok.Data;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produccion {
    private int idProduccion;//PK
    private int idCultivo;//FK
    private LocalDate fechaDeCosecha;
    private String calidadDelProducto;
    private double cantidadTotalTon;
    private double rendimientoPorHectarea;
}
