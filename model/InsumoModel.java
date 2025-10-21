package com.myproyect.miproyect.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsumoModel {
    private int idInsumo;//PK
    private int idLote;//FK
    private String nombreInsumo;
    private String tipoInsumo;
    private int cantidadInsumos;
    private double costoUnitario;
    private LocalDate fechaCompra;
}
