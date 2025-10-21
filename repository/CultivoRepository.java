package com.myproyect.miproyect.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import java.sql.Date;

import com.myproyect.miproyect.model.CultivoModel;

@Repository
public class CultivoRepository extends STDRespository implements CultivoRepositoryInterface {

    public CultivoRepository() {
        super();
    }

    @Override
    public List<CultivoModel> listarCultivos() {
        List<CultivoModel> cultivos = new ArrayList<>();
        String query = "SELECT * FROM cultivos";
        CultivoModel cultivo = null;
        try {
            pstmt = conn.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Date fechaDeSiembraSQL = resultSet.getDate("fecha_de_siembra");
                Date fechaEstimadaCosechaSQL = resultSet.getDate("fecha_estimada_cosecha");
                // PARSEO DE DATE(sql) A LOCALDATE(java)
                LocalDate fechaDeSiembra = fechaDeSiembraSQL.toLocalDate();
                LocalDate fechaEstimadaCosecha = fechaEstimadaCosechaSQL.toLocalDate();

                cultivo = new CultivoModel(resultSet.getInt("id_cultivo"), resultSet.getInt("id_lote"),
                        resultSet.getString("tipo_de_siembra"), resultSet.getString("variedad_de_semilla"),
                        fechaDeSiembra, fechaEstimadaCosecha,
                        resultSet.getString("estado_del_cultivo"));

                cultivos.add(cultivo);
                cultivo = null;
            }

            return cultivos;
        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }

    }

    @Override
    public CultivoModel obtenerCultivo(int id) {
        String query = "SELECT * FROM cultivos WHERE id_cultivo = ?";
        CultivoModel cultivo = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Date fechaDeSiembraSQL = resultSet.getDate("fecha_de_siembra");
                Date fechaEstimadaCosechaSQL = resultSet.getDate("fecha_estimada_cosecha");
                // PARSEO DE DATE(sql) A LOCALDATE(java)
                LocalDate fechaDeSiembra = fechaDeSiembraSQL.toLocalDate();
                LocalDate fechaEstimadaCosecha = fechaEstimadaCosechaSQL.toLocalDate();

                cultivo = new CultivoModel(resultSet.getInt("id_cultivo"),
                        resultSet.getInt("id_lote"),
                        resultSet.getString("tipo_de_siembra"),
                        resultSet.getString("variedad_de_semilla"),
                        fechaDeSiembra, fechaEstimadaCosecha,
                        resultSet.getString("estado_del_cultivo"));
            }

            return cultivo;
        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }

    @Override
    public CultivoModel agregarCultivo(CultivoModel cultivo) {
        String query = "INSERT INTO cultivos(tipo_de_siembra,variedad_de_semilla,fecha_de_siembra,fecha_estimada_cosecha,estado_del_cultivo,id_lote)VALUES(?,?,?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);// RETURN_GENERATED_KEYS indica que
                                                                                  // queremos recuperar el id
                                                                                  // autogenerado
            pstmt.setString(1, cultivo.getTipoDeSiembra());
            pstmt.setString(2, cultivo.getVariedadDeSemilla());
            pstmt.setDate(3, Date.valueOf(cultivo.getFechaDeSiembra()));
            pstmt.setDate(4, Date.valueOf(cultivo.getFechaEstimadaCosecha()));
            pstmt.setString(5, cultivo.getEstadoDelCultivo());
            pstmt.setInt(6, cultivo.getIdLote());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();// Recupera el id autogenerado
                if (generatedKeys.next()) {
                    int nuevoId = generatedKeys.getInt(1);// obtiene el id
                    cultivo.setIdCultivo(nuevoId);
                }

                return cultivo;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean eliminarCultivo(int id) {
        String query = "DELETE FROM cultivos WHERE id_cultivo = ?";

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
    public CultivoModel actualizarCultivo(CultivoModel cultivo) {
        String query = "UPDATE cultivos SET estado_del_cultivo = ?, fecha_estimada_cosecha = ? WHERE id_cultivo = ?";
        try {
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, cultivo.getEstadoDelCultivo());
            pstmt.setDate(2, Date.valueOf(cultivo.getFechaEstimadaCosecha()));
            pstmt.setInt(3, cultivo.getIdCultivo());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return cultivo;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }
}

interface CultivoRepositoryInterface {

    abstract List<CultivoModel> listarCultivos();

    abstract CultivoModel obtenerCultivo(int id);

    abstract CultivoModel agregarCultivo(CultivoModel cultivo);

    abstract boolean eliminarCultivo(int id);

    abstract CultivoModel actualizarCultivo(CultivoModel cultivo);
}
