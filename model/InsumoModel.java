package com.myproyect.miproyect.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsumoModel {
    private int idInsumo;// PK
    private int idLote;// FK
    private String nombreInsumo;
    private String tipoInsumo;
    private double cantidadInsumos;
    private BigDecimal costoUnitario;
    private LocalDate fechaCompra;

    public InsumoModel(int idInsumo, int idLote, String nombreInsumo, String tipoInsumo, double cantidadInsumos,
            BigDecimal costoUnitario, LocalDate fechaCompra) {
        this.idInsumo = idInsumo;
        this.idLote = idLote;
        this.nombreInsumo = nombreInsumo;
        this.tipoInsumo = tipoInsumo;
        this.cantidadInsumos = cantidadInsumos;
        this.costoUnitario = costoUnitario;
        this.fechaCompra = fechaCompra;
    }
}
