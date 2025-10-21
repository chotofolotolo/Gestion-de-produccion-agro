package com.myproyect.miproyect.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.myproyect.miproyect.model.ProduccionModel;

@Repository
public class ProduccionRepository extends STDRespository implements ProduccionRepositoryInterface {

    public ProduccionRepository() {
        super();
    }

    public List<ProduccionModel> listarProducciones() {
        List<ProduccionModel> lista = new ArrayList<>();

        try {
            String query = "SELECT * FROM produccion";
            pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ProduccionModel produccion = new ProduccionModel();
                produccion.setIdProduccion(rs.getInt("id_produccion"));
                produccion.setIdCultivo(rs.getInt("id_cultivo"));
                produccion.setFechaDeCosecha(rs.getDate("fecha_de_cosecha").toLocalDate());
                produccion.setCalidadDelProducto(rs.getString("calidad_del_producto"));
                produccion.setCantidadTotalTon(rs.getDouble("cantidad_total_ton"));
                produccion.setRendimientoPorHectarea(rs.getDouble("rendimiento_por_hectarea"));

                lista.add(produccion);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("EXCEPTION-> " + ex.getMessage());
            return null;
        }
    }

    public ProduccionModel obtenerProduccion(int id) {
        try {
            String query = "SELECT * FROM produccion WHERE id_produccion = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                ProduccionModel produccion = new ProduccionModel();
                produccion.setIdProduccion(rs.getInt("id_produccion"));
                produccion.setIdCultivo(rs.getInt("id_cultivo"));
                produccion.setFechaDeCosecha(rs.getDate("fecha_de_cosecha").toLocalDate());
                produccion.setCalidadDelProducto(rs.getString("calidad_del_producto"));
                produccion.setCantidadTotalTon(rs.getDouble("cantidad_total_ton"));
                produccion.setRendimientoPorHectarea(rs.getDouble("rendimiento_por_hectarea"));

                return produccion;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("EXCEPTION-> " + ex.getMessage());
            return null;
        }
    }

    public ProduccionModel agregarProduccion(ProduccionModel produccion) {
        String query = "INSERT INTO produccion(id_cultivo, fecha_de_cosecha, calidad_del_producto, cantidad_total_ton, rendimiento_por_hectarea) VALUES(?,?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, produccion.getIdCultivo());

            if (produccion.getFechaDeCosecha() != null) {
                pstmt.setDate(2, Date.valueOf(produccion.getFechaDeCosecha()));
            } else {
                pstmt.setNull(2, java.sql.Types.DATE);
            }

            if (produccion.getCalidadDelProducto() != null) {
                pstmt.setString(3, produccion.getCalidadDelProducto());
            } else {
                pstmt.setNull(3, java.sql.Types.VARCHAR);
            }

            pstmt.setDouble(4, produccion.getCantidadTotalTon());
            pstmt.setDouble(5, produccion.getRendimientoPorHectarea());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet keys = pstmt.getGeneratedKeys();
                if (keys.next()) {
                    int nuevoId = keys.getInt(1);
                    produccion.setIdProduccion(nuevoId);
                }
                return produccion;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("EXCEPTION-> " + ex.getMessage());
            return null;
        }
    }

    public boolean eliminarProduccion(int id) {
        try {
            String query = "DELETE FROM produccion WHERE id_produccion = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            int filas = pstmt.executeUpdate();
            return filas > 0;
        } catch (SQLException ex) {
            System.out.println("EXCEPTION-> " + ex.getMessage());
            return false;
        }
    }

    public ProduccionModel actualizarProduccion(ProduccionModel produccion) {
        String query = "UPDATE produccion SET id_cultivo=?, fecha_de_cosecha=?, calidad_del_producto=?, cantidad_total_ton=?, rendimiento_por_hectarea=? WHERE id_produccion=?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, produccion.getIdCultivo());

            if (produccion.getFechaDeCosecha() != null) {
                pstmt.setDate(2, Date.valueOf(produccion.getFechaDeCosecha()));
            } else {
                pstmt.setNull(2, java.sql.Types.DATE);
            }

            if (produccion.getCalidadDelProducto() != null) {
                pstmt.setString(3, produccion.getCalidadDelProducto());
            } else {
                pstmt.setNull(3, java.sql.Types.VARCHAR);
            }

            pstmt.setDouble(4, produccion.getCantidadTotalTon());
            pstmt.setDouble(5, produccion.getRendimientoPorHectarea());
            pstmt.setInt(6, produccion.getIdProduccion());

            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                return produccion;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("EXCEPTION-> " + ex.getMessage());
            return null;
        }
    }
}

interface ProduccionRepositoryInterface {

    public List<ProduccionModel> listarProducciones();

    public ProduccionModel obtenerProduccion(int id);

    public ProduccionModel agregarProduccion(ProduccionModel produccion);

    public boolean eliminarProduccion(int id);

    public ProduccionModel actualizarProduccion(ProduccionModel produccion);
}