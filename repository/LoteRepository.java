package com.myproyect.miproyect.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.myproyect.miproyect.model.LoteModel;

@Repository
public class LoteRepository extends STDRespository implements LoteRepositoryInterface {

    public LoteRepository() {
        super();
    }

    @Override
    public List<LoteModel> listarLotes() {
        List<LoteModel> lotes = new ArrayList<>();
        String query = "SELECT * FROM lote";
        try {
            pstmt = conn.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                LoteModel lote = new LoteModel(
                        resultSet.getInt("id_lote"),
                        resultSet.getString("nombre_lote"),
                        resultSet.getBigDecimal("longitud_lote"),
                        resultSet.getBigDecimal("latidud_lote"),
                        resultSet.getString("tipo_de_cultivo"));
                lotes.add(lote);
            }

            return lotes;
        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return List.of();
        }
    }

    @Override
    public LoteModel obtenerLote(int id) {
        String query = "SELECT * FROM lote WHERE id_lote = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return new LoteModel(
                        resultSet.getInt("id_lote"),
                        resultSet.getString("nombre_lote"),
                        resultSet.getBigDecimal("longitud_lote"),
                        resultSet.getBigDecimal("latidud_lote"),
                        resultSet.getString("tipo_de_cultivo"));
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }

    @Override
    public LoteModel agregarLote(LoteModel lote) {
        String query = "INSERT INTO lote(nombre_lote, longitud_lote, latidud_lote, tipo_de_cultivo) VALUES (?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, lote.getNombreLote());
            pstmt.setBigDecimal(2, lote.getLongitudLote());
            pstmt.setBigDecimal(3, lote.getLatitudLote());
            pstmt.setString(4, lote.getTipoDeCultivo());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    lote.setIdLote(generatedKeys.getInt(1));
                }
                return lote;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean eliminarLote(int id) {
        String query = "DELETE FROM lote WHERE id_lote = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return false;
        }
    }

    @Override
    public LoteModel actualizarLote(LoteModel lote) {
        String query = "UPDATE lote SET nombre_lote = ?, longitud_lote = ?, latidud_lote = ?, tipo_de_cultivo = ? WHERE id_lote = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, lote.getNombreLote());
            pstmt.setBigDecimal(2, lote.getLongitudLote());
            pstmt.setBigDecimal(3, lote.getLatitudLote());
            pstmt.setString(4, lote.getTipoDeCultivo());
            pstmt.setInt(5, lote.getIdLote());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return lote;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }
}

interface LoteRepositoryInterface {
    List<LoteModel> listarLotes();

    LoteModel obtenerLote(int id);

    LoteModel agregarLote(LoteModel lote);

    boolean eliminarLote(int id);

    LoteModel actualizarLote(LoteModel lote);
}
