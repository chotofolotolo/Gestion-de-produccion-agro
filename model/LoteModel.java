package com.myproyect.miproyect.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoteModel {
    private int idLote;//pk
    private String nombreLote;
    private double longitudLote;
    private double latitudLote;
    private String tipoDeCultivo;
}
