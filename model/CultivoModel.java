package com.myproyect.miproyect.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CultivoModel {
    private int idCultivo;// PK
    private int idLote;// FK
    private String tipoDeSiembra;
    private String variedadDeSemilla;
    private LocalDate fechaDeSiembra;
    private LocalDate fechaEstimadaCosecha;
    private String estadoDelCultivo;

    public CultivoModel(int idCultivo, int idLote, String tipoDeSiembra, String variedadDeSemilla,
            LocalDate fechaDeSiembra, LocalDate fechaEstimadaCosecha, String estadoDelCultivo) {
        this.idCultivo = idCultivo;
        this.idLote = idLote;
        this.tipoDeSiembra = tipoDeSiembra;
        this.variedadDeSemilla = variedadDeSemilla;
        this.fechaDeSiembra = fechaDeSiembra;
        this.fechaEstimadaCosecha = fechaEstimadaCosecha;
        this.estadoDelCultivo = estadoDelCultivo;
    }
}
